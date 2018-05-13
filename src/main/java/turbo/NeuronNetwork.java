package turbo;

import lombok.Data;
import turbo.model.Matrix2d;

@Data
class NeuronNetwork {
    private final NeuronLayer layer;
    private Matrix2d outputLayer;

    NeuronNetwork(NeuronLayer layer, Matrix2d outputLayer) {
        this.outputLayer = outputLayer;
        this.layer = layer;
    }

    void think(Matrix2d inputs) {
        outputLayer = inputs.multiplyBy(layer.getWeights()).apply(layer.getActivationFunction());
    }

    void train(Matrix2d inputs, Matrix2d outputs, int numberOfTrainingIterations) {
        for (int i = 0; i < numberOfTrainingIterations; ++i) {

            think(inputs);

            Matrix2d errorLayer = outputs.subtract(outputLayer);
            Matrix2d deltaLayer = errorLayer.scalarMultiplyBy(outputLayer.apply(layer.getActivationFunctionDerivative()));
            Matrix2d adjustmentLayer = inputs.transpose().multiplyBy(deltaLayer);
            this.layer.adjustWeights(adjustmentLayer);
        }
    }
}
