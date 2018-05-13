package turbo;

import turbo.model.Matrix2d;
import turbo.network.NeuronNetwork;

public class App {
    public static void main(String[] args) {

        NeuronNetwork net = new NeuronNetwork(1, 3);

        Matrix2d inputs = new Matrix2d(new double[][]{
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 1},
                {0, 1, 1},
        });

        Matrix2d outputs = new Matrix2d(new double[][]{
                {0},
                {1},
                {1},
                {0},
        });

        net.train(inputs, outputs, 10000);

        System.out.println(net.getWeights());

        // 1, 0, 0
        Matrix2d testInputs = new Matrix2d(new double[][]{{1, 0, 0}});
        testInputs.setValues(new double[][]{{1, 0, 0}});
        predict(testInputs, net);

        // 0, 1, 0
        testInputs.setValues(new double[][]{{0, 1, 0}});
        predict(testInputs, net);

        // 1, 1, 0
        testInputs.setValues(new double[][]{{1, 1, 0}});
        predict(testInputs, net);

        // 0, 0, 0
        testInputs.setValues(new double[][]{{0, 0, 0}});
        predict(testInputs, net);
    }

    private static void predict(Matrix2d testInput, NeuronNetwork net) {
        net.think(testInput);

        System.out.println("Prediction on data "
                + testInput.getValues()[0][0] + " "
                + testInput.getValues()[0][1] + " "
                + testInput.getValues()[0][2] + " -> "
                + net.getOutputLayer().getValues()[0][0] + " -> "
                + Math.round(net.getOutputLayer().getValues()[0][0]));

    }
}
