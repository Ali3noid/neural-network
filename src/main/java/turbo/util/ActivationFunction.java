package turbo.util;

public class ActivationFunction {

    private ActivationFunction() {
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoidDerivative(double sigmoid) {
        return sigmoid * (1 - sigmoid);
    }
}
