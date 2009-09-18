
(deftemplate Method
    
    (slot id)
    (slot name)
    (slot returnType)
    (slot class_id)
    (slot parametros))

(deftemplate Call
    
    (slot caller_id)
    (slot callee_id)
    (slot precedence)
    (slot id)
    
    )


(deftemplate call_counted
    (slot caller_id)
    (slot callee_id)
    )

(deftemplate callee_counted
    (slot caller_id)
    (slot callee_id)
    )


(deftemplate cantMetodosLlamados
    "cantidad de metodos llamados"
    (slot method)
    (slot cantidad))

(deftemplate cantMetodosQueLlamados
    "cantidad de metodos llamados"
    (slot method)
    (slot cantidad))

(deftemplate redirectMethod
    "cantidad de metodos llamados"
    (slot metodoBase)
    (slot metodoRedireccionado))

(assert(Method (id Aa)(name a)))
(assert(Method (id Bb)(name b)))
(assert(Method (id Cc)(name c)))
(assert(Method (id Dd)(name d)))

(assert(Call (caller_id Bb)(callee_id Aa)))

(assert(Call (caller_id Aa)(callee_id Bb)))
(assert(Call (caller_id Cc)(callee_id Bb)))
(assert(Call (caller_id Dd)(callee_id Bb)))
(assert(Call (caller_id Bb)(callee_id Aa)))
(assert(Call (caller_id Aa)(callee_id Bb)))
(assert(Call (caller_id Cc)(callee_id Bb)))
(assert(Call (caller_id Dd)(callee_id Bb)))


(assert (cantMetodosLlamados (method Aa) (cantidad 0)))
(assert (cantMetodosLlamados (method Bb) (cantidad 0)))
(assert (cantMetodosQueLlamados (method Aa) (cantidad 0)))
(assert (cantMetodosQueLlamados (method Bb) (cantidad 0)))

(defrule callerMethods
    "Defino los métodos que llaman solo a un método"
    (declare (salience 10000))
    (Method (id ?MetodoLlamador))
    (Method (id ?MetodoLlamado))
    (Call (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado))
    (not (call_counted (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado)))
    ?OldCantMetodosLlamados <- (cantMetodosLlamados (method ?MetodoLlamador) (cantidad ?Cant))
    =>
    (assert (call_counted (caller_id ?MetodoLlamador)(callee_id ?MetodoLlamado)))
    (bind ?NewMetric (+ ?Cant 1))
    (modify ?OldCantMetodosLlamados (cantidad ?NewMetric))
    )

"Defino los métodos que llaman solo a un método"
(defrule calleeMethods
    (declare (salience 5000))
    (Method (id ?MetodoLlamador))
    (Method (id ?MetodoLlamado))
    (Call (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado))
    (not (callee_counted (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado)))
    ?OldCantMetodosLlamados <- (cantMetodosQueLlamados (method ?MetodoLlamado) (cantidad ?Cant))
    =>
    (assert (callee_counted (caller_id ?MetodoLlamador)(callee_id ?MetodoLlamado)))
    (bind ?NewMetric (+ ?Cant 1))
    (modify ?OldCantMetodosLlamados (cantidad ?NewMetric))
    )

(defrule redirectorMethod
    (declare (salience 1000))
    (cantMetodosLlamados (method ?MetodoLlamador) (cantidad ?cant1))
    (cantMetodosQueLlamados (method ?MetodoLlamado) (cantidad ?cant2))
    (Call (caller_id ?MetodoLlamador) (callee_id ?MetodoLlamado))
    =>
    (if (and (= ?cant1 1)(= ?cant2 1)) then
        (assert (redirectMethod	(metodoBase ?MetodoLlamador)(metodoRedireccionado ?MetodoLlamado)))
        
        )
    )


(run)
(facts)

;(defquery fanInTotal
;	"comment"
;	(declare (variables ?ln))
;	(final_fan-in_metric(method_id ?mi)(metric ?m))
;    (Method (id ?mi) (class_id ?class))
;
;    )
;
;(defquery llamados
;	"comment"
;	(declare (variables ?ln))
;    (call_counted (caller_id ?Caller) (callee_id ?Method))
;
;    )
;
;(defquery FinalfanIn
;	"comment"
;	(declare (variables ?ln))
;    (final_fan-in_metric(method_id ?metodo)(metric ?m))
;
;
;    )
;
;(defquery fanInTotalBorderDec
;	"comment"
;	(declare (variables ?ln))
;    (final_fan-in_metric(method_id ?*metodo*)(metric ?m))
;	(fan-in_metric_acum (method_id ?*metodo*)(metric ?m1))
;    (fan-in_metric (method_id ?*metodo*)(metric ?m2))
;
;    )
;
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
;    (llamado_no_directo (caller_id ?Caller) (calle_id ?*metodo*))
;
;    )
;
;
