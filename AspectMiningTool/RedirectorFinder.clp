(import JessIntegrationModel.*)


(deftemplate Method    (declare (from-class Method)))
(deftemplate Class       (declare (from-class JessIntegrationModel.Class)))
(deftemplate Call    (declare (from-class Call)))
(deftemplate Abstract (declare (from-class Abstract)))


(deftemplate cantMetodosPorClase
	"Define la cantidad de métodos de una clase"
	(slot idClase)
    (slot cantMet))

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



;(assert (Class (id A)))
;(assert (Class (id B)))
;(assert (Class (id C)))
;(assert (Class (id D)))
;
;
;(assert(Method (id Aa1)(name a1)(class_id A)))
;(assert(Method (id Aa2)(name a2)(class_id A)))
;(assert(Method (id Aa3)(name a3)(class_id A)))
;(assert(Method (id Aa4)(name a4)(class_id A)))
;
;(assert(Method (id Bb1)(name b1)(class_id B)))
;(assert(Method (id Bb2)(name b2)(class_id B)))
;(assert(Method (id Bb3)(name b3)(class_id B)))
;(assert(Method (id Bb4)(name b4)(class_id B)))
;(assert(Method (id Bb5)(name b5)(class_id B)))
;
;(assert(Method (id Cc1)(name c1)(class_id C)))
;(assert(Method (id Cc2)(name c2)(class_id C)))
;(assert(Method (id Cc3)(name c3)(class_id C)))
;
;(assert(Method (id Dd1)(name d1)(class_id D)))
;(assert(Method (id Dd2)(name d2)(class_id D)))
;
;
;(assert(Call (caller_id Aa1)(callee_id Dd1)))
;
;(assert(Call (caller_id Bb1)(callee_id Aa1)))
;(assert(Call (caller_id Bb2)(callee_id Aa2)))
;(assert(Call (caller_id Bb3)(callee_id Aa3)))
;(assert(Call (caller_id Bb3)(callee_id Dd2)))
;(assert(Call (caller_id Bb5)(callee_id Cc2)))
;
;(assert(Call (caller_id Cc1)(callee_id Bb4)))
;(assert(Call (caller_id Cc3)(callee_id Dd2)))
;
;(assert(Call (caller_id Dd1)(callee_id Bb4)))

;(defrule initData1
;    (declare (salience 1000000))
;    (Method (id ?method))
;	=>
;    (assert (cantMetodosLlamados (method ?method) (cantidad 0)))
;    (assert (cantMetodosMeLlaman (method ?method) (cantidad 0)))
;    )

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
    "Calcula la cantidad de métodos de las clases abstractas"
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



(defrule redirectorMethod
    "define los métodos redireccionadores"
    (declare (salience 1000))
    (Call (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado))
	(Method (id ?MetodoLlamador)(class_id ?classIdLlamador))
	(Method (id ?MetodoLlamado)(class_id ?classIdLlamada))
    (not 
        (and 
            	(Call (caller_id ?otroMetodoClaseLlamadora) (callee_id ?MetodoLlamado))
              	(Method (id ?otroMetodoClaseLlamadora&~?MetodoLlamador)(class_id ?classIdLlamador))
        )
    ); no existe una llamada al ?MetodoLlamado desde un método de la misma clase del método llamados ?classIdLlamador    
    (not (and  
            	(Call (caller_id ?MetodoLlamador)(callee_id ?otroMetodoClaseLlamada))
              	(Method (id ?otroMetodoClaseLlamada&~?MetodoLlamado)(class_id ?classIdLlamada))
         )
    );el metodo llamador ?MetodoLlamados no llama a otro ningún otro método de la clase llamadas ?classIdLlamada
    
    =>
    (assert (redirectMethod	(metodoBase ?MetodoLlamador)(claseBase ?classIdLlamador)(metodoRedireccionado ?MetodoLlamado) (claseRedireccionada ?classIdLlamada)))
        
)



(defrule initRedirectorDataClass
    "crea el hecho cantRedirecPorClase para las clases que posean algún método redireccionador"
    (declare (salience 500))
    (redirectMethod	(claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada))
    (not (redirectClassCounted (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)))
	=>
    (assert (redirectClassCounted (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)))
    (assert (cantRedirecPorClase (claseBase ?classIdeLlamador)(claseRedireccionada ?classIdLlamada)(cant 0)))
    )

(defrule finalRedirectMetodosPorClase
    "calcula la cantidad de métodos redireccionadores por clase"
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

(defquery metodosRedirectorsPorClase
	"comment"
	(declare (variables ?claseLlamadora ?claseLlamada))
    (redirectMethod	(claseBase ?claseLlamadora)(claseRedireccionada ?claseLlamada)(metodoBase ?MetodoLlamador)(metodoRedireccionado ?MetodoLlamado))
	)

;(run)
;(facts)
