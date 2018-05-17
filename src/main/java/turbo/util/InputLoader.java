package turbo.util;

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
        double[][] inputsToTrain = parseMatrixFromFile(lines, 1, 4, 0, 4);
        double[][] outputsToTrain = parseMatrixFromFile(lines, 1, 4, 4, 7);
        double[][] inputsToPredicate = parseMatrixFromFile(lines, 5, 5, 0, 4);

        return NetworkInput.builder()
                .numberOfInputsPerNeuron(parseInt(paramArray[0]))
                .numberOfOutputNeurons(parseInt(paramArray[1]))
                .numberOfInputsToTrain(parseInt(paramArray[2]))
                .numberOfOutputToPredict(parseInt(paramArray[3]))
                .numberOfIterations(parseInt(paramArray[4]))
                .inputsToTrain(inputsToTrain)
                .outputsToTrain(outputsToTrain)
                .inputsToPredicate(inputsToPredicate)
                .build();
    }

    private double[][] parseMatrixFromFile(List<String> lines, Integer skipLines, Integer limitLines, Integer fromColumn, Integer toColumn) {
        return lines.stream()
                .skip(skipLines)
                .limit(limitLines)
                .map(s -> s.split(" "))
                .map(strings -> Arrays.copyOfRange(strings, fromColumn, toColumn))
                .map(s -> Arrays.stream(s)
                        .mapToDouble(Double::parseDouble)
                        .toArray())
                .toArray(double[][]::new);
    }
}
