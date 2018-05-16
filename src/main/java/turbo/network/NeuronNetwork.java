package turbo.network;

import lombok.Data;
import turbo.model.Matrix2d;
import turbo.util.ActivationFunction;
import turbo.util.MatrixFunction;

@Data
public class NeuronNetwork {
    private Matrix2d weights;
    private Matrix2d outputLayer;
    private final MatrixFunction activationFunctionDerivative;
    private final MatrixFunction activationFunction;

    public NeuronNetwork(int numberOfOutputNeurons, int numberOfInputsPerNeuron) {
        weights = new Matrix2d(new double[numberOfInputsPerNeuron][numberOfOutputNeurons]);
        weights.fillRandomValues();
        activationFunction = ActivationFunction::sigmoid;
        activationFunctionDerivative = ActivationFunction::sigmoidDerivative;
    }

    public void think(Matrix2d inputs) {
        outputLayer = inputs.multiplyBy(weights).apply(activationFunction);
    }

    public void train(Matrix2d inputs, Matrix2d learnOutputs, int numberOfTrainingIterations) {
        this.outputLayer = learnOutputs;
        for (int i = 0; i < numberOfTrainingIterations; ++i) {

            think(inputs);

            Matrix2d errorLayer = learnOutputs.subtract(outputLayer);
            Matrix2d deltaLayer = errorLayer.scalarMultiplyBy(outputLayer.apply(activationFunctionDerivative));
            Matrix2d adjustmentLayer = inputs.transpose().multiplyBy(deltaLayer);
            adjustWeights(adjustmentLayer);
        }
    }

    private void adjustWeights(Matrix2d adjustment) {
        weights = weights.add(adjustment);
    }
}
