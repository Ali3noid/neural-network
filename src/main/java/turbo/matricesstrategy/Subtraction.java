package turbo.matricesstrategy;

import static turbo.MatrixUtil.loopThroughMatrix;

public class Subtraction implements MatricesStrategy {

    @Override
    public double[][] execute(double[][] a, double[][] b) {
        if (a.length == 0 || b.length == 0 || a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Cannot perform this operation on unequal matrices");
        }
        double[][] result = new double[a.length][a[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = a[i][j] - b[i][j]);
        return result;
    }
}
