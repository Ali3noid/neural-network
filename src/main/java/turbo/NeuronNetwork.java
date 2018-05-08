package turbo;

import lombok.Data;

import static turbo.MatrixUtil.apply;
import static turbo.NeuralNetworkMath.matrixTranspose;
import static turbo.matricesstrategy.Operation.getMultiplication;
import static turbo.matricesstrategy.Operation.getScalarMultiplication;
import static turbo.matricesstrategy.Operation.getSubtraction;

@Data
class NeuronNetwork {
    private final NeuronLayer layer;
    private double[][] outputLayer;

    NeuronNetwork(NeuronLayer layer) {
        this.layer = layer;
    }

    void think(double[][] inputs) {
        outputLayer = apply(getMultiplication().execute(inputs, layer.getWeights()), layer.getActivationFunction());
    }

    void train(double[][] inputs, double[][] outputs, int numberOfTrainingIterations) {
        for (int i = 0; i < numberOfTrainingIterations; ++i) {

            think(inputs);

            double[][] errorLayer = getSubtraction().execute(outputs, outputLayer);
            double[][] deltaLayer = getScalarMultiplication().execute(errorLayer, apply(outputLayer, layer.getActivationFunctionDerivative()));
            double[][] adjustmentLayer = getMultiplication().execute(matrixTranspose(inputs), deltaLayer);
            this.layer.adjustWeights(adjustmentLayer);
        }
    }
}
