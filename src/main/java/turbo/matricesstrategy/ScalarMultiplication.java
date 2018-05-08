package turbo.matricesstrategy;

import static turbo.MatrixUtil.loopThroughMatrix;

public class ScalarMultiplication implements MatricesStrategy {

    @Override
    public double[][] execute(double[][] a, double[][] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Cannot multiply vectors of unequal length");
        }
        double[][] result = new double[a.length][a[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = a[i][j] * b[i][j]);
        return result;
    }
}
