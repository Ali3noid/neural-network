package turbo.matricesstrategy;


public class Operation {
    private static MatricesStrategy addition = new Addition();
    private static MatricesStrategy subtraction = new Subtraction();
    private static MatricesStrategy multiplication = new Multiplication();
    private static MatricesStrategy scalarMultiplication = new ScalarMultiplication();

    private Operation() {
    }

    public static MatricesStrategy getAddition() {
        return addition;
    }

    public static MatricesStrategy getSubtraction() {
        return subtraction;
    }

    public static MatricesStrategy getMultiplication() {
        return multiplication;
    }

    public static MatricesStrategy getScalarMultiplication() {
        return scalarMultiplication;
    }
}
