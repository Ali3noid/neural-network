package turbo;

import lombok.Data;
import turbo.model.Matrix2d;

import java.util.Arrays;
import java.util.function.Function;

@Data
public class NeuronLayer {

    private Matrix2d weights;
    private final Function<Double, Double> activationFunctionDerivative;
    private final Function<Double, Double> activationFunction;

    NeuronLayer(int numberOfNeurons, int numberOfInputsPerNeuron) {
        weights = new Matrix2d(new double[numberOfInputsPerNeuron][numberOfNeurons]);
        weights.fillRandomValues();
        activationFunction = NeuralNetworkMath::sigmoid;
        activationFunctionDerivative = NeuralNetworkMath::sigmoidDerivative;
    }

    @Override
    public String toString() {
        return "NeuronLayer(weights=" + Arrays.deepToString(this.getWeights().getValues()) + ")";
    }

    void adjustWeights(Matrix2d adjustment) {
        weights = weights.add(adjustment);
    }
}
