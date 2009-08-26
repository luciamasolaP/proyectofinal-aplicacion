(import model.*)

(deftemplate Interface (declare (from-class Interface)))
(deftemplate Method    (declare (from-class Method)))
;(deftemplate Define_In    (declare (from-class Define_In)))
(deftemplate Class       (declare (from-class model.Class)))
(deftemplate Inherits    (declare (from-class Inherits)))
(deftemplate Implements    (declare (from-class Implements)))
(deftemplate Call    (declare (from-class Call)))

(deftemplate call_counted
	(slot caller_id)
    (slot callee_id)
)

(deftemplate final
	(slot id)
    (slot number)
)

(deftemplate implements_counted
	(slot class_id)
    (slot Interface_id)		
)

(deftemplate inherit_counted
	(slot class_id)
    (slot father_id)
)

(deftemplate fan-in_metric
    "Fan-in de un método."
    (slot method_id)    
    (slot metric)
)

(deftemplate fan-in_metric_acum
    "Fan-in de un método."
    (slot method_id)    
    (slot metric)
)

(deftemplate final_fan-in_metric
    "Fan-in de un método."
    (slot method_id)    
    (slot metric)
)

(deftemplate familiar
	(slot elem)
    (slot elem2)
)

(deftemplate father_counted
	(slot elem)
    (slot elem2)
	(slot metodo)
)

(deftemplate son_counted
	(slot elem)
    (slot elem2)
	(slot metodo)
)

(deftemplate calculado
	(slot id)
)

(defrule init_fan-in_metric
    (Method (id ?Method))
    =>
    (assert (fan-in_metric (method_id ?Method) (metric 0))) 
    (assert (fan-in_metric_acum (method_id ?Method) (metric 0)))
  ;  (assert (final_fan-in_metric (method_id ?Method) (metric 0)))  
)

(defrule count_callers
    (declare (salience 10000))
    (Call (caller_id ?Caller) (callee_id ?Method))    
    (not (call_counted (caller_id ?Caller) (callee_id ?Method)))
    ?OldFanInMetric <- (fan-in_metric (method_id ?Method) (metric ?Metric))
    =>
    (assert (call_counted (caller_id ?Caller) (callee_id ?Method)))
    (bind ?NewMetric (+ ?Metric 1))        
    (modify ?OldFanInMetric (metric ?NewMetric))  
)



(defrule assert_familiar_6
    (declare (salience 5000))
	(Inherits (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(elem ?X)(elem2 ?Y)))
)

(defrule assert_familiar_7
    (declare (salience 5000))
	(Implements (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(elem ?X)(elem2 ?Y)))
)


(defrule assert_familiar_4
    (declare (salience 500))
	(Inherits (child_id ?X) (father_id ?Y))
    (familiar (elem ?Y) (elem2 ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule assert_familiar_5
    (declare (salience 500))
	(Implements (child_id ?X) (father_id ?Y))
    (familiar (elem ?Y) (elem2 ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)



(defrule acum_fan-in_padres
    (declare (salience 100))
	(Method (id ?Method)(name ?MethodName)(class_id ?Class))
    (familiar (elem ?Class) (elem2 ?Familiar))
    (not (father_counted (elem ?Class) (elem2 ?Familiar)(metodo ?Method)))
    (Method (id ?FamiliarMethod) (name ?MethodName)(class_id ?Familiar))
    (fan-in_metric (method_id ?Method) (metric ?MethodMetric))
    ?OldFanInMetric <- (fan-in_metric_acum (method_id ?FamiliarMethod) (metric ?FamiliarMethodMetric))
    =>
    (assert (father_counted (elem ?Class) (elem2 ?Familiar) (metodo ?Method))) ;se marca como contado. 
    (bind ?NewMetric (+ ?MethodMetric ?FamiliarMethodMetric))
    (modify ?OldFanInMetric (metric ?NewMetric))

)

(defrule acum_fan-in_hijos
    (declare (salience 100))
    (Method (id ?Method)(name ?MethodName)(class_id ?Class))
    (familiar (elem ?Familiar) (elem2 ?Class))
    (not (son_counted (elem ?Class) (elem2 ?Familiar)(metodo ?Method)))
    (Method (id ?FamiliarMethod) (name ?MethodName)(class_id ?Familiar))
    (fan-in_metric (method_id ?Method) (metric ?MethodMetric))   
    ?OldFanInMetric <- (fan-in_metric_acum (method_id ?FamiliarMethod) (metric ?FamiliarMethodMetric))
    =>
    (assert (son_counted (elem ?Class) (elem2 ?Familiar)(metodo ?Method))) ;se marca como contado. 
    (bind ?NewMetric (+ ?MethodMetric ?FamiliarMethodMetric))
    (modify ?OldFanInMetric (metric ?NewMetric))

)

(defrule final_fan-in
	"calcula el fan in total, esto es el propio más el acumulado"
	(declare (salience -100))
	(fan-in_metric(method_id ?Method) (metric ?OwnValue))
    (fan-in_metric_acum (method_id ?Method) (metric ?AcumValue))
   ; ?OldFanInMetric <- (final_fan-in_metric (method_id ?Method))
    =>
    (bind ?NewValue (+ ?OwnValue ?AcumValue))
    (assert (final_fan-in_metric (method_id ?Method) (metric ?NewValue)))
   ; (modify ?OldFanInMetric (metric ?NewValue))
)


(defquery fanInTotal
	"comment"
	(declare (variables ?ln))
	(final_fan-in_metric(method_id ?mi)(metric ?m))
    )

(defquery llamados
	"comment"
	(declare (variables ?ln))
	(Call (caller_id ?Caller) (callee_id ?Method))
    )


