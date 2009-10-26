(import JessIntegrationModel.*)

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

(deftemplate fan-in_metric
	(slot method)
    (slot metric)
)

(deftemplate fan-in_seed
	(slot method)
    (slot metric)
)

(deftemplate unique_method_metric
	(slot method)
    (slot metric)
)

(deftemplate unique_method_seed
	(slot method)
    (slot metric)
)

(deftemplate fan-in_umbral
	(slot umbral)    		
)

(deftemplate unique_method_umbral
	(slot umbral)
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

(deftemplate execution_relation_seed
	(slot method)
    (slot metric)
)

(defrule mark_as_fan-in_seed
	"comment"
	?Fan-in_metric <- (fan-in_metric (method ?Method) (metric ?Metric))
    (fan-in_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (fan-in_seed (method ?Method) (metric ?Metric)))
    )
    (retract ?Fan-in_metric)
)

(defrule mark_as_unique_method_seed
	"comment"
	?Unique_method_metric <- (unique_method_metric (method ?Method) (metric ?Metric))
    (unique_method_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (unique_method_seed (method ?Method) (metric ?Metric)))
    )
    (retract ?Unique_method_metric)
)

(defrule mark_as_execution_relation_seed
	"comment"
	?OutsideBeforeExecutionMetric <- (OutsideBeforeExecutionMetric (method ?Method) (metric ?Metric))
    (OutsideBeforeExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method) (metric ?Metric)))
       (retract ?OutsideBeforeExecutionMetric)
    )    
)

(defrule mark_as_execution_relation_seed2
	"comment"
	?OutsideAfterExecutionMetric <- (OutsideAfterExecutionMetric (method ?Method) (metric ?Metric))
    (OutsideAfterExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method) (metric ?Metric)))
       (retract ?OutsideAfterExecutionMetric)
    )    
)

(defrule mark_as_execution_relation_seed3
	"comment"
	?InsideFirstExecutionMetric <- (InsideFirstExecutionMetric (method ?Method) (metric ?Metric))
    (InsideFirstExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method) (metric ?Metric)))
       (retract ?InsideFirstExecutionMetric)
    )    
)

(defrule mark_as_execution_relation_seed4
	"comment"
	?InsideLastExecutionMetric <- (InsideLastExecutionMetric (method ?Method) (metric ?Metric))
    (InsideLastExecution_umbral (umbral ?Umbral))
    =>
    (if (>= ?Metric ?Umbral) then
       (assert (execution_relation_seed (method ?Method) (metric ?Metric)))
       (retract ?InsideLastExecutionMetric)
    )    
)