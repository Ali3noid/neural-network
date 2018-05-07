package turbo;

import lombok.Data;

import java.util.Arrays;
import java.util.function.Function;

import static java.lang.Math.random;
import static turbo.MatrixUtil.loopThroughMatrix;
import static turbo.NeuralNetworkMath.matrixAdd;


@Data
public class NeuronLayer {

    private double[][] weights;
    public final Function<Double, Double> activationFunctionDerivative;
    public final Function<Double, Double> activationFunction;

    NeuronLayer(int numberOfNeurons, int numberOfInputsPerNeuron) {
        weights = new double[numberOfInputsPerNeuron][numberOfNeurons];
        loopThroughMatrix(weights, (i, j) ->  weights[i][j] = random() - 0.5);
        activationFunction = NeuralNetworkMath::sigmoid;
        activationFunctionDerivative = NeuralNetworkMath::sigmoidDerivative;
    }

    @Override
    public String toString() {
        return "NeuronLayer(weights=" + Arrays.deepToString(this.getWeights()) + ")";
    }

    void adjustWeights(double[][] adjustment) {
        weights = matrixAdd(weights, adjustment);
    }
}
