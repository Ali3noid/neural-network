package turbo;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class MatrixUtil {

    private MatrixUtil() {
    }

    static double[][] apply(double[][] matrix, Function<Double, Double> function) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty");
        }
        double[][] result = new double[matrix.length][matrix[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = function.apply(matrix[i][j]));
        return result;
    }

    public static void loopThroughMatrix(double[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                consumer.accept(i, j);
            }
        }
    }
}
