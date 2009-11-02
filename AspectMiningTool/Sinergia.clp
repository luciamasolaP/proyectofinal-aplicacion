(import aspectminingtool.JessIntegrationModel.Sinergia.*)

(deftemplate OutsideBeforeExecutionMetric    (declare (from-class OutsideBeforeExecutionMetric)))
(deftemplate OutsideAfterExecutionMetric    (declare (from-class OutsideAfterExecutionMetric)))
(deftemplate InsideFirstExecutionMetric    (declare (from-class InsideFirstExecutionMetric)))
(deftemplate InsideLastExecutionMetric    (declare (from-class InsideLastExecutionMetric)))
(deftemplate FanInMetric    (declare (from-class FanInMetric)))
(deftemplate UniqueMethodsMetric    (declare (from-class UniqueMethodsMetric)))


(deftemplate fan-in_umbral
	(slot umbral)    		
)

(deftemplate unique_method_umbral
	(slot umbral)
)

(deftemplate fan-in_trust
	(slot trust)    		
)

(deftemplate unique_method_trust
	(slot trust)
)

(deftemplate execution_relation_trust
	(slot trust)
)

(deftemplate OutsideBeforeExecution_umbral
    (slot umbral) 
)

(deftemplate OutsideAfterExecution_umbral
    (slot umbral) 
)

(deftemplate InsideFirstExecution_umbral
    (slot umbral) 
)

(deftemplate InsideLastExecution_umbral
    (slot umbral) 
)

(deftemplate umbral_trust
	(slot trust)
)

(deftemplate fan-in_seed
	(slot method)
)

(deftemplate fan-in_seed_Counted
	"comment"
	(slot method))

(deftemplate unique_method_seed
	(slot method)
)

(deftemplate unique_method_seed_Counted
	"comment"
	(slot method))

(deftemplate execution_relation_seed
	(slot method)    
)

(deftemplate execution_relation_seed_Counted
	"comment"
	(slot method))

(deftemplate seed
	(slot method)
    (slot trust)
)

(assert (fan-in_umbral (umbral 1)))
(assert (unique_method_umbral (umbral 1)))
(assert (InsideFirstExecution_umbral (umbral 1)))
(assert (InsideLastExecution_umbral (umbral 1)))
(assert (OutsideAfterExecution_umbral (umbral 1)))
(assert (OutsideBeforeExecution_umbral (umbral 1)))
(assert (fan-in_trust (trust 33)))
(assert (unique_method_trust (trust 33)))
(assert (execution_relation_trust (trust 33)))
(assert (umbral_trust (trust 50)))
;    (fan-in_trust (trust 33))
;    (unique_method_trust (trust 33))
;    (execution_relation_trust (trust 33))
;    
;	;Umbral de confianza que debe alcanzar un seed para ser considerado como tal.
;    (umbral_trust (trust 50))

(deffacts global_facts
	"comment"
;    (FanInMetric (method m1) (metric 90))
;    (FanInMetric (method m2) (metric 60))
;    (FanInMetric (method m3) (metric 30))
;    (FanInMetric (method m4) (metric 10))
    
;    (fan-in_umbral (umbral 0))    
    
;    (UniqueMethodsMetric (method m1) (metric 80))
;    (UniqueMethodsMetric (method m2) (metric 50))
;    (UniqueMethodsMetric (method m3) (metric 20))
;    (UniqueMethodsMetric (method m4) (metric 5))
    
;    (unique_method_umbral (umbral 0))    
    
;    (InsideFirstExecutionMetric (method m1) (metric 20))
;    (InsideFirstExecutionMetric (method m2) (metric 20))
;    (InsideFirstExecutionMetric (method m3) (metric 5))
;    (InsideFirstExecutionMetric (method m4) (metric 0))
    
;    (InsideFirstExecution_umbral (umbral 0))
    
;    (InsideLastExecutionMetric (method m1) (metric 2))
;    (InsideLastExecutionMetric (method m2) (metric 2))
;    (InsideLastExecutionMetric (method m3) (metric 15))
;    (InsideLastExecutionMetric (method m4) (metric 50))
    
;    (InsideLastExecution_umbral (umbral 0))
    
;    (OutsideAfterExecutionMetric (method m1) (metric 80))
;    (OutsideAfterExecutionMetric (method m1) (metric 50))
;    (OutsideAfterExecutionMetric (method m1) (metric 40))
;    (OutsideAfterExecutionMetric (method m1) (metric 10))
    
;    (OutsideAfterExecution_umbral (umbral 0))
    
;    (OutsideBeforeExecutionMetric (method m1) (metric 80))
;    (OutsideBeforeExecutionMetric (method m1) (metric 50))
;    (OutsideBeforeExecutionMetric (method m1) (metric 40))
;    (OutsideBeforeExecutionMetric (method m1) (metric 10))
    
;    (OutsideBeforeExecution_umbral (umbral 0))
    
    ;Algoritmos equiconfiables.
;    (fan-in_trust (trust 33))
;    (unique_method_trust (trust 33))
;    (execution_relation_trust (trust 33))
    
	;Umbral de confianza que debe alcanzar un seed para ser considerado como tal.
;    (umbral_trust (trust 50))
	
)

;Rules

(defrule mark_as_fan-in_seed
	"comment"
    (declare (salience 1500))
	?FanInMetric <- (FanInMetric (method ?Method) (metric ?Metric))
    (fan-in_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (fan-in_seed (method ?Method)))
    )
    (retract ?FanInMetric)
)

(defrule mark_as_unique_method_seed
	"comment"
    (declare (salience 1500))
	?UniqueMethodsMetric <- (UniqueMethodsMetric (method ?Method) (metric ?Metric))
    (unique_method_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (unique_method_seed (method ?Method)))
    )
    (retract ?UniqueMethodsMetric)
)

(defrule mark_as_execution_relation_seed
	"comment"
    (declare (salience 1500))
	?OutsideBeforeExecutionMetric <- (OutsideBeforeExecutionMetric (method ?Method) (metric ?Metric))
    (OutsideBeforeExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method)))
       (retract ?OutsideBeforeExecutionMetric)
    )    
)

(defrule mark_as_execution_relation_seed2
	"comment"
    (declare (salience 1500))
	?OutsideAfterExecutionMetric <- (OutsideAfterExecutionMetric (method ?Method) (metric ?Metric))
    (OutsideAfterExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method)))
       (retract ?OutsideAfterExecutionMetric)
    )    
)

(defrule mark_as_execution_relation_seed3
	"comment"
    (declare (salience 1500))
	?InsideFirstExecutionMetric <- (InsideFirstExecutionMetric (method ?Method) (metric ?Metric))
    (InsideFirstExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method)))
       (retract ?InsideFirstExecutionMetric)
    )    
)

(defrule mark_as_execution_relation_seed4
	"comment"
    (declare (salience 1500))
	?InsideLastExecutionMetric <- (InsideLastExecutionMetric (method ?Method) (metric ?Metric))
    (InsideLastExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method)))
       (retract ?InsideLastExecutionMetric)
    )    
)

;///////////////////////init seeds////////////////////////////
(defrule init_seed_fan-in
	"comment"
    (declare (salience 1000))
    (fan-in_seed (method ?Method))
    (not (seed (method ?Method)))    
	=>
	(assert (seed (method ?Method) (trust 0)))
)

(defrule init_seed_unique_method
	"comment"
    (declare (salience 1000))
    (unique_method_seed (method ?Method))
    (not (seed (method ?Method)))    
	=>
	(assert (seed (method ?Method) (trust 0)))
)

(defrule init_seed_execution_relation
	"comment"
    (declare (salience 1000))
    (execution_relation_seed (method ?Method))
    (not (seed (method ?Method)))    
	=>
	(assert (seed (method ?Method) (trust 0)))
)
;////////////////////////////////////////////////////////////////

(defrule acum_seed_fan-in
	"comment"
    (declare (salience 500))
    ?FanInSeed <- (fan-in_seed (method ?Method))
    ?Seed <- (seed (method ?Method) (trust ?Trust))
    (fan-in_trust (trust ?FanInTrust))    
	=>
    (bind ?NewTrust (+ ?FanInTrust ?Trust))
    (modify ?Seed (trust ?NewTrust))
    (retract ?FanInSeed) ;Si luego se quiere usar entonces no debemos eliminarlo sino contarlo como computado.
    (assert (fan-in_seed_Counted(method ?Method)))
)

(defrule acum_seed_unique_method
	"comment"
    (declare (salience 500))
    ?UniqueMethodSeed <- (unique_method_seed (method ?Method))
    ?Seed <- (seed (method ?Method) (trust ?Trust))
    (unique_method_trust (trust ?UniqueMethodTrust))    
	=>
    (bind ?NewTrust (+ ?UniqueMethodTrust ?Trust))
    (modify ?Seed (trust ?NewTrust))
    (retract ?UniqueMethodSeed)
    (assert (unique_method_seed_Counted(method ?Method)))
)

(defrule acum_seed_execution_relation
	"comment"
    (declare (salience 500))
    ?ExecutionRelationSeed <- (execution_relation_seed (method ?Method))
    ?Seed <- (seed (method ?Method) (trust ?Trust))
    (execution_relation_trust (trust ?ExecutionRelationTrust))    
	=>
    (bind ?NewTrust (+ ?ExecutionRelationTrust ?Trust))
    (modify ?Seed (trust ?NewTrust))
    (retract ?ExecutionRelationSeed)
    (assert (execution_relation_seed_Counted(method ?Method)))
)

;Remove the seeds that do not reach the threshold.

(defrule remove_false_seeds
	"comment"
    (declare (salience 100))
    ?Seed <- (seed (method ?Method) (trust ?Trust))
    (umbral_trust (trust ?GeneralTrust))    
	=>
    (if (< ?Trust ?GeneralTrust) then
    	(retract ?Seed)    
    )    
)

(defquery getSeeds
	"comment"
	(declare (variables ?ln))
    (seed (method ?method)(trust ?trust))
    )

(defquery getFanInSeeds
	"comment"
	(declare (variables ?method))
    (fan-in_seed_Counted (method ?method))
    )

(defquery getUniqueMethodsSeeds
	"comment"
	(declare (variables ?method))
    (unique_method_seed_Counted (method ?method))
    )

(defquery getFlowGraphSeeds
	"comment"
	(declare (variables ?method))
    (execution_relation_seed_Counted (method ?method))
    )

;(reset)
;(run)
(facts)





