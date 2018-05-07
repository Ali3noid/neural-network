package turbo;

import lombok.Data;

import static turbo.MatrixUtil.apply;
import static turbo.NeuralNetworkMath.matrixMultiply;
import static turbo.NeuralNetworkMath.matrixSubtract;
import static turbo.NeuralNetworkMath.matrixTranspose;
import static turbo.NeuralNetworkMath.scalarMultiply;

@Data
public class NeuronNetwork {
    private final NeuronLayer layer;
    private double[][] outputLayer;

    public NeuronNetwork(NeuronLayer layer) {
        this.layer = layer;
    }

    public void think(double[][] inputs) {
        outputLayer = apply(matrixMultiply(inputs, layer.getWeights()), layer.getActivationFunction());
    }

    public void train(double[][] inputs, double[][] outputs, int numberOfTrainingIterations) {
        for (int i = 0; i < numberOfTrainingIterations; ++i) {

            think(inputs);

            double[][] errorLayer = matrixSubtract(outputs, outputLayer);
            double[][] deltaLayer = scalarMultiply(errorLayer, apply(outputLayer, layer.getActivationFunctionDerivative()));

            double[][] adjustmentLayer = matrixMultiply(matrixTranspose(inputs), deltaLayer);
            this.layer.adjustWeights(adjustmentLayer);
        }
    }
}
