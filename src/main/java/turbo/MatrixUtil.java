package turbo;

import java.util.function.Function;

public class MatrixUtil {
    public static double[][] apply(double[][] matrix, Function<Double, Double> function) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Invalid matrix");
        }

        double[][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                result[i][j] = function.apply(matrix[i][j]);
            }
        }

        return result;
    }
}
