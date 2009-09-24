(import JessIntegrationModel.*)

;(deftemplate Interface (declare (from-class Interface)))
(deftemplate Method    (declare (from-class Method)))
(deftemplate Class       (declare (from-class JessIntegrationModel.Class)))
;(deftemplate Inherits    (declare (from-class Inherits)))
;(deftemplate Implements    (declare (from-class Implements)))
(deftemplate Call    (declare (from-class Call)))
(deftemplate Abstract (declare (from-class Abstract)))


(deftemplate cantMetodosPorClase
	"Define la cantidad de métodos de una clase"
	(slot idClase)
    (slot cantMet))

(deftemplate cantMetodosLlamados
    "cantidad de metodos llamados"
    (slot method)
    (slot cantidad))

(deftemplate cantMetodosMeLlaman
    "cantidad de metodos llamados"
    (slot method)
    (slot cantidad))

(deftemplate methodCounted
    (slot idMethod)
    (slot idClass)
    )

(deftemplate cantRedirecPorClase
    (slot claseBase)
    (slot claseRedireccionada)
    (slot cant)
    )



(deftemplate caller_counted
    (slot caller_id)
    (slot callee_id)
    )

(deftemplate callee_counted
    (slot caller_id)
    (slot callee_id)
    )

(deftemplate redirectMethod
    "cantidad de metodos llamados"
    (slot metodoBase)
    (slot claseBase)
    (slot metodoRedireccionado)
    (slot claseRedireccionada))

(deftemplate redirectClassCounted
    "cantidad de metodos llamados"
    (slot claseBase)
    (slot claseRedireccionada))

(deftemplate redirectMethodCounted
    "cantidad de metodos llamados"
    (slot metodoBase)
    (slot claseBase)
    (slot metodoRedireccionado)
    (slot claseRedireccionada))


(defrule initData1
    (declare (salience 1000000))
    (Method (id ?method))
	=>
    (assert (cantMetodosLlamados (method ?method) (cantidad 0)))
    (assert (cantMetodosMeLlaman (method ?method) (cantidad 0)))
    )

(defrule initData2
    (declare (salience 1000000))
	(Class (id ?idClass))
	=>
   (assert (cantMetodosPorClase (idClase ?idClass) (cantMet 0)))
)
	
(defrule initData3
    (declare (salience 1000000))
	(Abstract (id ?idClass))
	=>
   (assert (cantMetodosPorClase (idClase ?idClass) (cantMet 0)))
)


(defrule calculaCantidadMetodosClase
    "Calcula la cantidad de métodos de cada clase"
    (declare (salience 100000))
    (Class (id ?idClass))
    (Method (id ?metodo) (class_id ?idClass))
    (not (methodCounted(idMethod ?metodo) (idClass ?idClass)))
    ?OldCantMetodos <- (cantMetodosPorClase (idClase ?idClass) (cantMet ?Cant))
    =>
    (assert (methodCounted (idMethod ?metodo)(idClass ?idClass)))
    (bind ?NewMetric (+ ?Cant 1))
    (modify ?OldCantMetodos (cantMet ?NewMetric))
    )

(defrule cantidadMetodosClaseAbstracta
    "Calcula la cantidad de métodos de cada clase"
    (declare (salience 100000))
    (Abstract (id ?idClass))
    (Method (id ?metodo) (class_id ?idClass))
    (not (methodCounted(idMethod ?metodo) (idClass ?idClass)))
    ?OldCantMetodos <- (cantMetodosPorClase (idClase ?idClass) (cantMet ?Cant))
    =>
    (assert (methodCounted(idMethod ?metodo)(idClass ?idClass)))
    (bind ?NewMetric (+ ?Cant 1))
    (modify ?OldCantMetodos (cantMet ?NewMetric))
    )

"los dos siguientes traen problemas para hacer los facts, si los sacas se pueden hacer assert, pero se va en memoria en el execute"
(defrule callerMethods
    "Defino la cantidad de métodos que llama un método"
    (declare (salience 10000))
    (Method (id ?MetodoLlamador))
    (Method (id ?MetodoLlamado))
    (Call (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado))
    (not (caller_counted (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado)))
    ?OldCantMetodosLlamados <- (cantMetodosLlamados (method ?MetodoLlamador) (cantidad ?Cant))
    =>
    (assert (caller_counted (caller_id ?MetodoLlamador)(callee_id ?MetodoLlamado)))
    (bind ?NewMetric (+ ?Cant 1))
    (modify ?OldCantMetodosLlamados (cantidad ?NewMetric))
    )

(defrule calleeMethods
    "Defino la cantidad de métodos que llaman a un método"
    (declare (salience 10000))
    (Method (id ?MetodoLlamador))
    (Method (id ?MetodoLlamado))
    (Call (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado))
    (not (callee_counted (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado)))
    ?OldCantMetodosLlamados <- (cantMetodosMeLlaman (method ?MetodoLlamado) (cantidad ?Cant))
    =>
    (assert (callee_counted (caller_id ?MetodoLlamador)(callee_id ?MetodoLlamado)))
    (bind ?NewMetric (+ ?Cant 1))
    (modify ?OldCantMetodosLlamados (cantidad ?NewMetric))
    )


"el siguiente método da error al hacer el execute, es el que hace que se vaya de memoria"
(defrule redirectorMethod
    (declare (salience 1000))
    (cantMetodosLlamados (method ?MetodoLlamador) (cantidad ?cant1))
    (cantMetodosMeLlaman (method ?MetodoLlamado) (cantidad ?cant2))
    (Call (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado))
	(Method (id ?MetodoLlamador)(class_id ?classIdLlamador))
	(Method (id ?MetodoLlamado)(class_id ?classIdLlamada))
    =>
    (if (and (= ?cant1 1)(= ?cant2 1)) then
        (assert (redirectMethod	(metodoBase ?MetodoLlamador)(claseBase ?classIdLlamador)(metodoRedireccionado ?MetodoLlamado) (claseRedireccionada ?classIdLlamada)))
        
        )
    )


(defrule initRedirectorDataClass
    (declare (salience 500))
	(redirectMethod	(claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada))
    (not (redirectClassCounted (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)))
	=>
    (assert (redirectClassCounted (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)))
    (assert (cantRedirecPorClase (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)(cant 0)))
    )

(defrule finalRedirectMetodosPorClase
   	(declare (salience 250))
	?OldCantRedicMetodos <- (cantRedirecPorClase (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)(cant ?Cant))
	(redirectMethod	(claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)(metodoBase ?MetodoLlamador)(metodoRedireccionado ?MetodoLlamado))
    (not (redirectMethodCounted(metodoBase ?MetodoLlamador)(claseBase ?classIdeLlamador)(metodoRedireccionado ?MetodoLlamado) (claseRedireccionada ?classIdLlamada)))
    
    =>
    (assert (redirectMethodCounted(metodoBase ?MetodoLlamador)(claseBase ?classIdeLlamador)(metodoRedireccionado ?MetodoLlamado) (claseRedireccionada ?classIdLlamada)))
    (bind ?NewMetric (+ ?Cant 1))
    (modify ?OldCantRedicMetodos (cant ?NewMetric))
    )


(defquery cantMetodos
	"comment"
	(declare (variables ?idClase))
    (cantMetodosPorClase (idClase ?idClase)(cantMet ?cant))
	)


(defquery cantRedirectorMethods
	"comment"
	(declare (variables ?ln))
    (cantRedirecPorClase (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)(cant ?Cant))
	)


