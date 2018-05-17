package turbo;

import turbo.model.Matrix2d;
import turbo.network.NetworkInput;
import turbo.network.NeuronNetwork;
import turbo.util.InputLoader;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class App {
    public static void main(String[] args) throws URISyntaxException {

        InputLoader loader = new InputLoader();
        NetworkInput input = loader.read("example-input.csv");

        NeuronNetwork net = new NeuronNetwork(input.getNumberOfOutputNeurons(), input.getNumberOfInputsPerNeuron());
        Matrix2d trainInputs = new Matrix2d(input.getInputsToTrain());
        Matrix2d trainOutputs = new Matrix2d(input.getOutputsToTrain());

        net.train(trainInputs, trainOutputs, input.getNumberOfIterations());

        System.out.println("Weights:");
        System.out.println(net.getWeights());
        System.out.println("Prediction on data:");

        Matrix2d testInputs = new Matrix2d(input.getInputsToPredicate());
        Arrays.stream(testInputs.getValues())
                .forEach(toPredicate -> predict(new Matrix2d(toPredicate), net));
    }

    private static void predict(Matrix2d predictInput, NeuronNetwork net) {
        net.think(predictInput);
        String input = Arrays.toString(predictInput.getValues()[0]);
        String predictedOutput = Arrays.toString(net.getOutputLayer().getValues()[0]);
        List formattedOutput = Arrays.stream(net.getOutputLayer().getValues())
                .map(s -> Arrays.stream(s)
                        .map(Math::round)
                        .toArray())
                .collect(Collectors.toList());

        String outputInfo = format("%s -> %s -> %s", input, predictedOutput, Arrays.toString((double[]) formattedOutput.get(0)));
        System.out.println(outputInfo);
    }
}
