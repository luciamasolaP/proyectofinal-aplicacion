(import model.*)

(deftemplate Interface (declare (from-class Interface)))
(deftemplate Method    (declare (from-class Method)))
(deftemplate Define_In    (declare (from-class Define_In)))
(deftemplate Class       (declare (from-class Class)))
(deftemplate Inherits    (declare (from-class Inherits)))
(deftemplate Implements    (declare (from-class Implements)))
(deftemplate Call    (declare (from-class Call)))

(deftemplate call_counted
	(slot caller_id)
    (slot callee_id)
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
)

(deftemplate son_counted
	(slot elem)
    (slot elem2)
)


(defrule init_fan-in_metric
    (Method (id ?Method))
    =>
    (assert (fan-in_metric (method_id ?Method) (metric 0))) 
    (assert (fan-in_metric_acum (method_id ?Method) (metric 0)))
    (assert (final_fan-in_metric (method_id ?Method) (metric 0)))  
)

(defrule count_callers
    (Call (caller_id ?Caller) (callee_id ?Method))    
    (not (call_counted (caller_id ?Caller) (callee_id ?Method)))
    ?OldFanInMetric <- (fan-in_metric (method_id ?Method) (metric ?Metric))    
    =>
    (assert (call_counted (caller_id ?Caller) (callee_id ?Method)))
    (bind ?NewMetric (+ ?Metric 1))        
    (retract ?OldFanInMetric)
    (assert (fan-in_metric (method_id ?Method) (metric ?NewMetric)))    
)

"Define los familiares de cada clase e Interface."
(defrule assert_familiar_1
	(Inherits (child_id ?X) (father_id ?Y))
    (Inherits (child_id ?Y) (father_id ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule assert_familiar_2
	(Inherits (child_id ?X) (father_id ?Y))
    (Implements (child_id ?Y) (father_id ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule assert_familiar_3
	(Implements (child_id ?X) (father_id ?Y))
    (Implements (child_id ?Y) (father_id ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule assert_familiar_4
	(Inherits (child_id ?X) (father_id ?Y))
    (familiar (elem ?Y) (elem2 ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule assert_familiar_5
	(Inherits (child_id ?X) (father_id ?Y))
    (familiar (elem ?Y) (elem2 ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule assert_familiar_6
	(Inherits (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(elem ?X)(elem2 ?Y)))
)

(defrule assert_familiar_7
	(Implements (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(elem ?X)(elem2 ?Y)))
)

(defrule acum_fan-in_padres
	(Method (id ?Method)(name ?MethodName))
    (fan-in_metric (method_id ?Method) (metric ?MethodMetric))
    (Define_In (method_id ?Method) (class_id ?Class))
    (familiar (elem ?Class) (elem2 ?Familiar))
    (not (father_counted (elem ?Class) (elem2 ?Familiar))) 
    (Define_In (method_id ?FamiliarMethod) (class_id ?Familiar))
    (Method (id ?FamiliarMethod) (name ?MethodName))
    ?OldFanInMetric <- (fan-in_metric_acum (method_id ?FamiliarMethod) (metric ?FamiliarMethodMetric))
    =>
    (assert (father_counted (elem ?Class) (elem2 ?Familiar))) ;se marca como contado. 
    (bind ?NewMetric (+ ?MethodMetric ?FamiliarMethodMetric))
    (retract ?OldFanInMetric) ;se elimina el fan-in viejo.
    (assert (fan-in_metric_acum (method_id ?FamiliarMethod) (metric ?NewMetric))) ;se agrega el fan-in nuevo.
)

(defrule acum_fan-in_hijos
	(Method (id ?Method)(name ?MethodName))
    (fan-in_metric (method_id ?Method) (metric ?MethodMetric))
    (Define_In (method_id ?Method) (class_id ?Class))
    (familiar (elem ?Familiar) (elem2 ?Class))
    (not (son_counted (elem ?Class) (elem2 ?Familiar))) 
    (Define_In (method_id ?FamiliarMethod) (class_id ?Familiar))
    (Method (id ?FamiliarMethod) (name ?MethodName))
    ?OldFanInMetric <- (fan-in_metric_acum (method_id ?FamiliarMethod) (metric ?FamiliarMethodMetric))
    =>
    (assert (son_counted (elem ?Class) (elem2 ?Familiar))) ;se marca como contado. 
    (bind ?NewMetric (+ ?MethodMetric ?FamiliarMethodMetric))
    (retract ?OldFanInMetric) ;se elimina el fan-in viejo.
    (assert (fan-in_metric_acum (method_id ?FamiliarMethod) (metric ?NewMetric))) ;se agrega el fan-in nuevo.
)

(defrule final_fan-in
	"calcula el fan in total, esto es el propio más el acumulado"
	(Method (id ?Method))
	(fan-in_metric(method_id ?Method) (metric ?OwnValue))
    (fan-in_metric_acum (method_id ?Method) (metric ?AcumValue))
    ?OldFanInMetric <- (final_fan-in_metric (method_id ?Method) (metric ?))
    =>
    (bind ?NewValue (+ ?OwnValue ?AcumValue))
    (modify ?OldFanInMetric (metric ?NewValue))
)

(defquery prueba
	"comment"
	(declare (variables ?ln))
	(final_fan-in_metric(method_id ?mi)(metric ?m))
    )

(defquery prueba1
	"comment"
	(declare (variables ?ln))
	(Call (caller_id ?Caller) (callee_id ?Method))
    )


