(import JessIntegrationModel.*)

;(deftemplate Interface (declare (from-class Interface)))
(deftemplate Method    (declare (from-class Method)))
;(deftemplate Class       (declare (from-class JessIntegrationModel.Class)))
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
	(slot clase1)
    (slot clase2)
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
	(slot clase1)
    (slot clase2)
	(slot metodo)
)

(deftemplate son_counted
	(slot clase1)
    (slot clase2)
	(slot metodo)
)

(deftemplate calculado
	(slot id)
)

(deftemplate llamado_no_directo
	"comment"
	(slot caller_id)
    (slot callee_id))

;(defglobal ?*metodo* = "CH.ifa.draw.figures/BorderDecorator//displayBox///")

(defrule init_fan-in_metric
    (Method (id ?Method))
    =>
    (assert (fan-in_metric (method_id ?Method) (metric 0))) 
    (assert (fan-in_metric_acum (method_id ?Method) (metric 0)))
)

(defrule assert_familiar_1
    (declare (salience 10000))
	(Inherits (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(clase1 ?X)(clase2 ?Y)))
)

(defrule assert_familiar_2
    (declare (salience 10000))
	(Implements (child_id ?X) (father_id ?Y))    
	=>
    (assert (familiar(clase1 ?X)(clase2 ?Y)))
)

(defrule assert_familiar_3
    (declare (salience 5000))
	(Inherits (child_id ?X) (father_id ?Y))
    (familiar (clase1 ?Y) (clase2 ?Z))
	=>
    (assert (familiar(clase1 ?X)(clase2 ?Z)))
)

(defrule assert_familiar_4
    (declare (salience 5000))
	(Implements (child_id ?X) (father_id ?Y))
    (familiar (clase1 ?Y) (clase2 ?Z))
	=>
    (assert (familiar(clase1 ?X)(clase2 ?Z)))
)

(defrule metodos_familiares_padres
    (declare (salience 1000))
	(Method (id ?Method)(name ?MethodName)(class_id ?Class)(parametros ?p))
    (familiar (clase1 ?Class) (clase2 ?Familiar))
    (not (father_counted (clase1 ?Class) (clase2 ?Familiar)(metodo ?Method)))
    (Method (id ?FamiliarMethod) (name ?MethodName)(class_id ?Familiar)(parametros ?p))
    =>
    (assert (father_counted (clase1 ?Class) (clase2 ?Familiar) (metodo ?Method))) ;se marca como contado. 
    (assert (metodoFamiliar (metodo1 ?Method) (metodo2 ?FamiliarMethod))) ;se marcan los metodos familiares de un metodo 
 
)

(defrule metodos_familiares_hijos
    (declare (salience 1000))
    (Method (id ?Method)(name ?MethodName)(class_id ?Class)(parametros ?p))
    (familiar (clase1 ?Familiar) (clase2 ?Class))
    (not (son_counted (clase1 ?Class) (clase2 ?Familiar)(metodo ?Method)))
    (Method (id ?FamiliarMethod) (name ?MethodName)(class_id ?Familiar)(parametros ?p))
    =>
    (assert (son_counted (clase1 ?Class) (clase2 ?Familiar)(metodo ?Method))) ;se marca como contado. 
    (assert (metodoFamiliar (metodo1 ?Method) (metodo2 ?FamiliarMethod))) ;se marcan los metodos familiares de un metodo

)

(defrule propagarLlamadas
   	(declare (salience 500))
    (Call (callee_id ?metodoLlamado)(caller_id ?metodoLlamador))
    (metodoFamiliar (metodo1 ?metodoLlamado)(metodo2 ?metodoFamiliar))
    (not (metodoFamiliar_counted(metodo1 ?metodoLlamado)(metodo2 ?metodoFamiliar)))
	=>
    (assert (metodoFamiliar_counted(metodo1 ?metodoLlamado)(metodo2 ?metodoFamiliar)))
    (assert (llamado_no_directo(callee_id ?metodoFamiliar)(caller_id ?metodoLlamador)))
    )

;(defrule propagarLlamadas1
;   	(declare (salience 500))
;    (Call (callee_id ?metodoLlamado)(caller_id ?metodoLlamador))
;    (metodoFamiliar (metodo1 ?metodoLlamado)(metodo2 ?metodoLlamador))
;    (not (metodoFamiliar_counted(metodo1 ?metodoLlamado)(metodo2 ?metodoLlamador)))
;	=>
;    (assert (metodoFamiliar_counted(metodo1 ?metodoLlamado)(metodo2 ?metodoLlamador)))
;    (assert (llamado_no_directo(callee_id ?metodoLlamador)(caller_id ?metodoLlamador)))
;    )

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
    (llamado_no_directo(callee_id ?metodoLlamado)(caller_id ?metodoLlamador))
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

(defquery fanInTotal
	"comment"
	(declare (variables ?ln))
	(final_fan-in_metric(method_id ?mi)(metric ?m))
    (Method (id ?mi) (class_id ?class))
   
    )

(defquery llamados
	"comment"
	(declare (variables ?ln))
    (call_counted (caller_id ?Caller) (callee_id ?Method))
	
    )

(defquery FinalfanIn
	"comment"
	(declare (variables ?ln))
    (final_fan-in_metric(method_id ?metodo)(metric ?m))
    )

;(defquery fanInTotalBorderDec
;	"comment"
;	(declare (variables ?ln))
;    (final_fan-in_metric(method_id ?*metodo*)(metric ?m))
;	(fan-in_metric_acum (method_id ?*metodo*)(metric ?m1))
;    (fan-in_metric (method_id ?*metodo*)(metric ?m2))
;   
;    )

;(defquery llamadosBorderDec
;	"comment"
;	(declare (variables ?ln))
;    (call_counted (caller_id ?Caller) (callee_id ?*metodo*))
;
;    )
;
;(defquery llamadosNoDirectos
;	"comment"
;	(declare (variables ?ln))
;    (llamado_no_directo (caller_id ?Caller) (callee_id ?*metodo*))
;    )

(defquery llamadosNoDirectos1
	"comment"
	(declare (variables ?ln))
    (llamado_no_directo (caller_id ?Caller) (callee_id ?callee))
    )

(defquery metodoFamiliar
	"comment"
	(declare (variables ?ln))
    (metodoFamiliar (metodo1 ?metodo1) (metodo2 ?metodo2))
	)

(defquery familiar
	"comment"
	(declare (variables ?ln))
    (familiar(clase1 ?X)(clase2 ?Z))
	)

(defquery fatherC
	"comment"
	(declare (variables ?ln))
	(father_counted (clase1 ?Class) (clase2 ?Familiar) (metodo ?Method))
    )
