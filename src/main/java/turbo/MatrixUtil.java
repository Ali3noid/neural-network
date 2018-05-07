package turbo;

import java.util.function.BiConsumer;
import java.util.function.Function;

class MatrixUtil {

    private MatrixUtil() {
    }

    static double[][] apply(double[][] matrix, Function<Double, Double> function) {
        validateNoEmptyMatrix(matrix);
        double[][] result = new double[matrix.length][matrix[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = function.apply(matrix[i][j]));
        return result;
    }

    static void loopThroughMatrix(double[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                consumer.accept(i, j);
            }
        }
    }

    private static void validateNoEmptyMatrix(double[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty");
        }
    }
}
