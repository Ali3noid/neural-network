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
}
