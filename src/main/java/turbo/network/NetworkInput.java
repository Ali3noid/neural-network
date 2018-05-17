package turbo.network;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NetworkInput {
    private Integer numberOfInputsPerNeuron;
    private Integer numberOfOutputNeurons;
    private Integer numberOfInputsToTrain;
    private Integer numberOfOutputToPredict;
    private Integer numberOfIterations;
    private double[][] inputsToTrain;
    private double[][] outputsToTrain;
    private double[][] inputsToPredicate;
}
