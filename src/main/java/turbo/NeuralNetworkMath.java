package turbo;

class NeuralNetworkMath {

    private NeuralNetworkMath() {
    }

    static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    static double sigmoidDerivative(double sigmoid) {
        return sigmoid * (1 - sigmoid);
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
}
