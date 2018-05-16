package turbo;

import turbo.network.NetworkInput;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

public class InputLoader {
    public NetworkInput read(String fileName) throws URISyntaxException {
        List<String> lines = new ArrayList<>();

        Path path = get(requireNonNull(getClass()
                .getClassLoader()
                .getResource(fileName))
                .toURI());

        try (Stream<String> fileStream = Files.lines(path)) {
            lines = fileStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] paramArray = lines.get(0).split(" ");

        double[][] inputsToTrain = lines.stream()
                .skip(1)
                .limit(4)
                .map(s -> s.split(" "))
                .map(strings -> Arrays.copyOf(strings, 4))
                .map(s -> Arrays.stream(s)
                        .mapToDouble(Double::parseDouble)
                        .toArray())
                .toArray(double[][]::new);

        double[][] outputsToTrain = lines.stream()
                .skip(1)
                .limit(4)
                .map(s -> s.split(" "))
                .map(strings -> Arrays.copyOfRange(strings, 4, 7))
                .map(s -> Arrays.stream(s)
                        .mapToDouble(Double::parseDouble)
                        .toArray())
                .toArray(double[][]::new);

        double[][] inputsToPredicate = lines.stream()
                .skip(5)
                .limit(5)
                .map(s -> s.split(" "))
                .map(s -> Arrays.stream(s)
                        .mapToDouble(Double::parseDouble)
                        .toArray())
                .toArray(double[][]::new);

        //4 3 4 5
        return NetworkInput.builder()
                .numberOfInputsPerNeuron(parseInt(paramArray[0]))
                .numberOfOutputNeurons(parseInt(paramArray[1]))
                .numberOfInputsToTrain(parseInt(paramArray[2]))
                .numberOfOutputToPredict(parseInt(paramArray[3]))
                .inputsToTrain(inputsToTrain)
                .outputsToTrain(outputsToTrain)
                .inputsToPredicate(inputsToPredicate)
                .build();
    }
}
