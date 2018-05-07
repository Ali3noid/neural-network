package turbo;

import static turbo.MatrixUtil.loopThroughMatrix;

class NeuralNetworkMath {

    private NeuralNetworkMath() {
    }

    static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    static double sigmoidDerivative(double sigmoid) {
        return sigmoid * (1 - sigmoid);
    }

    static double[][] matrixMultiply(double[][] a, double[][] b) {
        if (a.length == 0 || b.length == 0 || a[0].length != b.length) {
            throw new IllegalArgumentException("Cannot multiply non n x m and m x p matrices");
        }

        int n = a.length;
        int m = a[0].length;
        int p = b[0].length;

        double[][] result = new double[n][p];

        for (int nIter = 0; nIter < n; ++nIter) {
            for (int pIter = 0; pIter < p; ++pIter) {

                double sum = 0;
                for (int mIter = 0; mIter < m; ++mIter) {
                    sum += (a[nIter][mIter] * b[mIter][pIter]);
                }

                result[nIter][pIter] = sum;
            }
        }

        return result;
    }

    static double[][] scalarMultiply(double[][] a, double[][] b) {
        validateEqualLengthMatrices(a, b);
        double[][] result = new double[a.length][a[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = a[i][j] * b[i][j]);
        return result;
    }

    static double[][] matrixSubtract(double[][] a, double[][] b) {
        validateEqualSizeMatrices(a,b);
        double[][] result = new double[a.length][a[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = a[i][j] - b[i][j]);
        return result;
    }

    static double[][] matrixAdd(double[][] a, double[][] b) {
        validateEqualSizeMatrices(a, b);
        double[][] result = new double[a.length][a[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = a[i][j] + b[i][j]);
        return result;
    }

    static double[][] matrixTranspose(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    private static void validateEqualLengthMatrices(double[][] v1, double[][] v2) {
        if (v1.length != v2.length) {
            throw new IllegalArgumentException("Cannot multiply vectors of unequal length");
        }
    }

    private static void validateEqualSizeMatrices(double[][] a, double[][] b) {
        if (a.length == 0 || b.length == 0 || a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Cannot perform this operation on unequal matrices");
        }
    }
}
