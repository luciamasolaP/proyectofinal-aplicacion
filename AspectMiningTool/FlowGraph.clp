(import JessIntegrationModel.*)
(import aspectminingtool.JessIntegrationModel.FlowGraph.*)

;Templates

(deftemplate Interface (declare (from-class Interface)))
(deftemplate Method    (declare (from-class Method)))
(deftemplate Class       (declare (from-class JessIntegrationModel.Class)))
(deftemplate Inherits    (declare (from-class Inherits)))
(deftemplate Implements    (declare (from-class Implements)))
(deftemplate Call    (declare (from-class Call)))

(deftemplate OutsideBeforeExecution
	(declare (from-class OutsideBeforeExecution))
)

(deftemplate OutsideAfterExecution
	(declare (from-class OutsideAfterExecution))	
)

(deftemplate InsideFirstExecution
	(declare (from-class InsideFirstExecution))
)

(deftemplate InsideLastExecution
	(declare (from-class InsideLastExecution))
)

(deftemplate OutsideBeforeExecutionMetric
	(slot method)
    (slot metric)
)

(deftemplate OutsideAfterExecutionMetric
	(slot method)
    (slot metric)
)

(deftemplate InsideFirstExecutionMetric
	(slot method)
    (slot metric)
)

(deftemplate InsideLastExecutionMetric
	(slot method)
    (slot metric)
)

(deftemplate OutsideBeforeRelationCounted
	"comment"
	(slot call_1)    
    (slot call_2)
)

(deftemplate OutsideAfterRelationCounted
	"comment"
	(slot call_1)    
    (slot call_2)
)

(deftemplate InsideFirstRelationCounted
	"comment"
	(slot call_id)    
    (slot method_id)
)

(deftemplate InsideLastRelationCounted
	"comment"
	(slot call_id)    
    (slot method_id)
)
;Rules

(defrule generate_OutsideExecution_relations
	"comment"
    (declare (salience 5000))
   	(Call (id ?call_1) (caller_id ?method_X) (callee_id ?method_Y) (precedence ?precedence_1))
    (Call (id ?call_2) (caller_id ?method_X) (callee_id ?method_Z) (precedence ?precedence_2))
    (not (OutsideBeforeExecution (call_id ?call_1) (call_id2 ?call_2))) ;para ver si ya no la conté.
    =>      
    (bind ?distance (- ?precedence_2 ?precedence_1))
    (if (= ?distance 1) then	    
	    (bind ?relation_1 (new OutsideBeforeExecution ?call_1 ?call_2))
        (bind ?relation_2 (new OutsideAfterExecution ?call_2 ?call_1))
        (add ?relation_1)
        (add ?relation_2)
    )    
 )

(defrule generate_InsideFirstExecution_relations
	"Genero este tipo de relación u º p, si u es el primero del método p."
    (declare (salience 5000))
	(Call (id ?call) (caller_id ?method_X) (callee_id ?method_Y) (precedence ?precedence))
    (not (InsideFirstExecution (call_id ?call_1) (method_id ?method_X))) ;para ver si ya no la conté.
    =>
    (if (= ?precedence 1) then	  
        (bind ?relation (new InsideFirstExecution ?call ?method_X))
        (add ?relation)        
    )
)

(defrule init_InsideLastExecution_relations
	"comment"    
    (declare (salience 5000))    
    (Call (id ?call) (caller_id ?method_X) (callee_id ?method_Y) (precedence ?precedence))
    (not (InsideLastExecution (method_id ?method_X)))
	=>
    (bind ?relation (new InsideLastExecution ?call ?method_X))
    (add ?relation)    
 )

(defrule generate_InsideLastExecution_relations
	"Genero este tipo de relación u º p, si u es el último del método p."
    (declare (salience 4000))
    (Call (id ?call_1) (caller_id ?method_X) (callee_id ?method_Y) (precedence ?precedence_1))
    ?actualRelation <- (InsideLastExecution (call_id ?call_2) (method_id ?method_X))
    (Call (id ?call_2) (caller_id ?method_X) (callee_id ?method_Z) (precedence ?precedence_2))
	=>
    (if (> ?precedence_1 ?precedence_2) then
    	(retract ?actualRelation)
        (bind ?newRelation (new InsideLastExecution ?call_1 ?method_X))
    	(add ?newRelation)            
    )
)

;Calculate Relations Metrics

(defrule init_OutsideBeforeExecution_Metric
	"comment"
    (declare (salience 3000))
    (OutsideBeforeExecution (call_id ?call_1))
    (Call (id ?call_1)(callee_id ?method))
	=>
    (assert (OutsideBeforeExecutionMetric (method ?method) (metric 0)) )
)

(defrule init_OutsideAfterExecution_Metric
	"comment"
    (declare (salience 3000))
    (OutsideAfterExecution (call_id ?call_1))
    (Call (id ?call_1)(callee_id ?method))
	=>
    (assert (OutsideAfterExecutionMetric (method ?method) (metric 0)) )
)

(defrule init_InsideFirstExecution_Metric
	"comment"
    (declare (salience 3000))
    (InsideFirstExecution (call_id ?call))
    (Call (id ?call)(callee_id ?method))   
	=>
    (assert (InsideFirstExecutionMetric (method ?method) (metric 0)) )
)

(defrule init_InsideLastExecution_Metric
	"comment"
    (declare (salience 3000))
    (InsideLastExecution (call_id ?call))
    (Call (id ?call)(callee_id ?method))    
	=>
    (assert (InsideLastExecutionMetric (method ?method) (metric 0)) )
)

(defrule generate_OutsideBeforeExecution_Metric
	"comment"
    (declare (salience 2000))
    (OutsideBeforeExecution (call_id ?call_1) (call_id2 ?call_2))
    (not (OutsideBeforeRelationCounted (call_1 ?call_1) (call_2 ?call_2)))
    (Call (id ?call_1)(callee_id ?method))
    ?oldMetric <- (OutsideBeforeExecutionMetric (method ?method) (metric ?metric))    
	=>    
    (assert (OutsideBeforeRelationCounted (call_1 ?call_1) (call_2 ?call_2)) )
    (bind ?newMetric (+ ?metric 1))
    (modify ?oldMetric (metric ?newMetric))       
)

(defrule generate_OutsideAfterExecution_Metric
	"comment"
    (declare (salience 2000))
    (OutsideAfterExecution (call_id ?call_1) (call_id2 ?call_2))
    (not (OutsideAfterRelationCounted (call_1 ?call_1) (call_2 ?call_2)))
    (Call (id ?call_1)(callee_id ?method))
    ?oldMetric <- (OutsideAfterExecutionMetric (method ?method) (metric ?metric))    
	=>    
    (assert (OutsideAfterRelationCounted (call_1 ?call_1) (call_2 ?call_2)) )
    (bind ?newMetric (+ ?metric 1))
    (modify ?oldMetric (metric ?newMetric))   
)

(defrule generate_InsideFirstExecution_Metric
	"comment"
	(declare (salience 2000))
    (InsideFirstExecution (call_id ?call) (method_id ?method))
    (not (InsideFirstRelationCounted (call_id ?call) (method_id ?method)))
    (Call (id ?call)(callee_id ?method2))
    ?oldMetric <- (InsideFirstExecutionMetric (method ?method2) (metric ?metric))    
	=>    
    (assert (InsideFirstRelationCounted (call_id ?call) (method_id ?method)) )
    (bind ?newMetric (+ ?metric 1))
    (modify ?oldMetric (metric ?newMetric))  	
)

(defrule generate_InsideLastExecution_Metric
	"comment"
	(declare (salience 2000))
    (InsideLastExecution (call_id ?call) (method_id ?method))
    (not (InsideLastRelationCounted (call_id ?call) (method_id ?method)))
    (Call (id ?call)(callee_id ?method2))
    ?oldMetric <- (InsideLastExecutionMetric (method ?method2) (metric ?metric))    
	=>    
    (assert (InsideLastRelationCounted (call_id ?call) (method_id ?method)) )
    (bind ?newMetric (+ ?metric 1))
    (modify ?oldMetric (metric ?newMetric)) 	
)


(defquery get_OutsideBeforeExecution_Metric
	"comment"
	(declare (variables ?ln))
    (Method (id ?method))
    (OutsideBeforeExecutionMetric (method ?method)(metric ?metric))	
)

(defquery get_OutsideAfterExecution_Metric
	"comment"
	(declare (variables ?ln))
    (Method (id ?method))
    (OutsideAfterExecutionMetric (method ?method)(metric ?metric))	
)

(defquery get_InsideFirstExecution_Metric
	"comment"
	(declare (variables ?ln))
    (Method (id ?method))
    (InsideFirstExecutionMetric (method ?method)(metric ?metric))	
)

(defquery get_InsideLastExecution_Metric
	"comment"
	(declare (variables ?ln))
    (Method (id ?method))
    (InsideLastExecutionMetric (method ?method)(metric ?metric))	
)
