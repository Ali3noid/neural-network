package turbo;

import lombok.Data;

import java.util.Arrays;
import java.util.function.Function;

import static java.lang.Math.random;
import static turbo.NeuralNetworkMath.matrixAdd;


@Data
public class NeuronLayer {

    private double[][] weights;
    public final Function<Double, Double> activationFunctionDerivative;
    public final Function<Double, Double> activationFunction;

    public NeuronLayer(int numberOfNeurons, int numberOfInputsPerNeuron) {
        weights = new double[numberOfInputsPerNeuron][numberOfNeurons];

        for (int i = 0; i < numberOfInputsPerNeuron; ++i) {
            for (int j = 0; j < numberOfNeurons; ++j) {
                weights[i][j] = random() - 0.5;
            }
        }

        activationFunction = NeuralNetworkMath::sigmoid;
        activationFunctionDerivative = NeuralNetworkMath::sigmoidDerivative;
    }

    public void adjustWeights(double[][] adjustment) {
        this.weights = matrixAdd(weights, adjustment);
    }

    @Override
    public String toString() {
        return "NeuronLayer(weights=" + Arrays.deepToString(this.getWeights()) + ")";
    }
}
