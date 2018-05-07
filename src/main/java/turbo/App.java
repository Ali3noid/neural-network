package turbo;

public class App {
    public static void main(String[] args) {

        NeuronLayer layer = new NeuronLayer(1, 3);

        NeuronNetwork net = new NeuronNetwork(layer);

        double[][] inputs = new double[][]{
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 1},
                {0, 1, 1},
        };

        double[][] outputs = new double[][]{
                {0},
                {1},
                {1},
                {0},
        };

        net.train(inputs, outputs, 10000);

        System.out.println(layer);

        // 1, 0, 0
        double[][] testInputs = new double[][]{{1, 0, 0}};
        predict(testInputs, net);

        // 0, 1, 0
        testInputs = new double[][]{{0, 1, 0}};
        predict(testInputs, net);

        // 1, 1, 0
        testInputs = new double[][]{{1, 1, 0}};
        predict(testInputs, net);

        // 0, 0, 0
        testInputs = new double[][]{{0, 0, 0}};
        predict(testInputs, net);
    }

    public static void predict(double[][] testInput, NeuronNetwork net) {
        net.think(testInput);

        System.out.println("Prediction on data "
                + testInput[0][0] + " "
                + testInput[0][1] + " "
                + testInput[0][2] + " -> "
                + net.getOutputLayer()[0][0] + " -> "
                + Math.round(net.getOutputLayer()[0][0]));

    }
}
