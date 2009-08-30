(import model.*)

(deftemplate Interface (declare (from-class Interface)))
(deftemplate Method    (declare (from-class Method)))
(deftemplate Class       (declare (from-class model.Class)))
(deftemplate Inherits    (declare (from-class Inherits)))
(deftemplate Implements    (declare (from-class Implements)))
(deftemplate Call    (declare (from-class Call)))

(defglobal ?*x* = "void")

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

(deftemplate metodoFamiliar
	(slot metodo1)
    (slot metodo2)
)

(deftemplate metodoFamiliar_counted
	(slot metodo1)
    (slot metodo2)
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

(deftemplate llamado_no_directo
	"comment"
	(slot caller_id)
    (slot calle_id))


(defrule init_fan-in_metric
    (Method (id ?Method))
    =>
    (assert (fan-in_metric (method_id ?Method) (metric 0))) 
    (assert (fan-in_metric_acum (method_id ?Method) (metric 0)))
)

(defrule assert_familiar_6
    (declare (salience 10000))
	(Inherits (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(elem ?X)(elem2 ?Y)))
)

(defrule assert_familiar_7
    (declare (salience 10000))
	(Implements (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(elem ?X)(elem2 ?Y)))
)

(defrule assert_familiar_4
    (declare (salience 5000))
	(Inherits (child_id ?X) (father_id ?Y))
    (familiar (elem ?Y) (elem2 ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule assert_familiar_5
    (declare (salience 5000))
	(Implements (child_id ?X) (father_id ?Y))
    (familiar (elem ?Y) (elem2 ?Z))
	=>
    (assert (familiar(elem ?X)(elem2 ?Z)))
)

(defrule acum_fan-in_padres
    (declare (salience 1000))
	(Method (id ?Method)(name ?MethodName)(class_id ?Class))
    (familiar (elem ?Class) (elem2 ?Familiar))
    (not (father_counted (elem ?Class) (elem2 ?Familiar)(metodo ?Method)))
    (Method (id ?FamiliarMethod) (name ?MethodName)(class_id ?Familiar))
    =>
    (assert (father_counted (elem ?Class) (elem2 ?Familiar) (metodo ?Method))) ;se marca como contado. 
    (assert (metodoFamiliar (metodo1 ?Method) (metodo2 ?FamiliarMethod))) ;se marcan los metodos familiares de un metodo 
 
)

(defrule acum_fan-in_hijos
    (declare (salience 1000))
    (Method (id ?Method)(name ?MethodName)(class_id ?Class))
    (familiar (elem ?Familiar) (elem2 ?Class))
    (not (son_counted (elem ?Class) (elem2 ?Familiar)(metodo ?Method)))
    (Method (id ?FamiliarMethod) (name ?MethodName)(class_id ?Familiar))
    =>
    (assert (son_counted (elem ?Class) (elem2 ?Familiar)(metodo ?Method))) ;se marca como contado. 
    (assert (metodoFamiliar (metodo1 ?Method) (metodo2 ?FamiliarMethod))) ;se marcan los metodos familiares de un metodo

)

(defrule propagarLlamadas
   	(declare (salience 500))
    (Call (callee_id ?metodoLlamado)(caller_id ?metodoLlamador))
    (metodoFamiliar (metodo1 ?metodoLlamado)(metodo2 ?metodoFamiliar))
    (not (metodoFamiliar_counted(metodo1 ?metodoLlamado)(metodo2 ?metodoFamiliar)))
	=>
    (assert (metodoFamiliar_counted(metodo1 ?metodoLlamado)(metodo2 ?metodoFamiliar)))
    (assert (llamado_no_directo(calle_id ?metodoFamiliar)(caller_id ?metodoLlamador)))
    )

(defrule count_callers
    (declare (salience 100))
    (Call (caller_id ?Caller) (callee_id ?Method))    
    (not (call_counted (caller_id ?Caller) (callee_id ?Method)))
    ?OldFanInMetric <- (fan-in_metric (method_id ?Method) (metric ?Metric))
    =>
    (assert (call_counted (caller_id ?Caller)(callee_id ?Method)))
    (bind ?NewMetric (+ ?Metric 1))        
    (modify ?OldFanInMetric (metric ?NewMetric))  
)

(defrule count_Callers_noDirectos
	"comment"
	(declare (salience 100))
    (llamado_no_directo(calle_id ?metodoLlamado)(caller_id ?metodoLlamador))
    (not (call_counted(callee_id ?metodoLlamado)(caller_id ?metodoLlamador)))
    ?OldFanInMetricAcum <- (fan-in_metric_acum (method_id ?metodoLlamado) (metric ?MetricAcum))
	=>
    (assert (call_counted (callee_id ?metodoLlamado)(caller_id ?metodoLlamador)))
    (bind ?NewMetricAcum (+ ?MetricAcum 1))
    (modify ?OldFanInMetricAcum (metric ?NewMetricAcum))
    )


(defrule final_fan-in
	"calcula el fan in total, esto es el propio más el acumulado"
	(declare (salience -100))
	(fan-in_metric(method_id ?Method) (metric ?OwnValue))
    (fan-in_metric_acum (method_id ?Method) (metric ?AcumValue))
    =>
    (bind ?NewValue (+ ?OwnValue ?AcumValue))
    (assert (final_fan-in_metric (method_id ?Method) (metric ?NewValue)))
)

(defquery llamados
	"comment"
	(declare (variables ?ln))
    (call_counted (caller_id ?Caller) (callee_id ?Method))
	
    )

(defquery UniqueMethods
	"comment"
	(declare (variables ?ln))
    (Method (id ?mi) (class_id ?class)(returnType ?*x*))
    (final_fan-in_metric(method_id ?mi)(metric ?m))
    
   
    )
