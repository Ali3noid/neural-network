package turbo;

import turbo.model.Matrix2d;
import turbo.network.NetworkInput;
import turbo.network.NeuronNetwork;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws URISyntaxException {

        InputLoader loader = new InputLoader();
        NetworkInput input = loader.read("example-input.csv");

        NeuronNetwork net = new NeuronNetwork(input.getNumberOfOutputNeurons(), input.getNumberOfInputsPerNeuron());
        Matrix2d inputs = new Matrix2d(input.getInputsToTrain());
        Matrix2d outputs = new Matrix2d(input.getOutputsToTrain());

        net.train(inputs, outputs, 10000);

        System.out.println("Weights:");
        System.out.println(net.getWeights());
        System.out.println("Prediction on data:");

        Matrix2d testInputs = new Matrix2d(input.getInputsToPredicate());
        Arrays.stream(testInputs.getValues())
                .forEach(toPredicate -> predict(new Matrix2d(toPredicate), net));
    }

    private static void predict(Matrix2d testInput, NeuronNetwork net) {
        net.think(testInput);
        String input = Arrays.toString(testInput.getValues()[0]);
        List obj = Arrays.stream(net.getOutputLayer().getValues())
                .map(s -> Arrays.stream(s)
                        .map(Math::round)
                        .toArray())
                .collect(Collectors.toList());

        String outputInfo = String.format("%s -> %s -> %s", input, Arrays.toString(net.getOutputLayer().getValues()[0]), Arrays.toString((double[]) obj.get(0)));
        System.out.println(outputInfo);
    }
}
