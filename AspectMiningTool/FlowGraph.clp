(import JessIntegrationModel.*)
(import aspectminingtool.JessIntegrationModel.FlowGraph.*)

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

(defrule generate_OutsideExecution_relations
	"comment"
   	(Call (id ?call_1) (caller_id ?method_X) (callee_id ?method_Y) (precedence ?precedence_1))
    (Call (id ?call_2) (caller_id ?method_X) (callee_id ?method_Z) (precedence ?precedence_2))
    (Method (id ?method_X))
    (Method (id ?method_Y))
    (Method (id ?method_Z))
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
	(Call (id ?call) (caller_id ?method_X) (callee_id ?method_Y) (precedence ?precedence))
    =>
    (if (= ?precedence 1) then	  
        (bind ?relation (new InsideFirstExecution ?call ?method_X))
        (add ?relation)        
    )
)

(defrule init_InsideLastExecution_relations
	"comment"    
    (Call (id ?call) (caller_id ?method_X) (callee_id ?method_Y) (precedence ?precedence))
    (not (InsideLastExecution (method_id ?method_X)))
	=>
    (bind ?relation (new InsideLastExecution ?call ?method_X))
    (add ?relation)    
 )

(defrule generate_InsideLastExecution_relations
	"Genero este tipo de relación u º p, si u es el último del método p."
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
