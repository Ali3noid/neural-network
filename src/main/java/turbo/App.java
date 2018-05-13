package turbo;

import turbo.model.Matrix2d;

public class App {
    public static void main(String[] args) {

        NeuronLayer layer = new NeuronLayer(1, 3);

        Matrix2d outputs = new Matrix2d(new double[][]{
                {0},
                {1},
                {1},
                {0},
        });

        NeuronNetwork net = new NeuronNetwork(layer, outputs);

        Matrix2d inputs = new Matrix2d(new double[][]{
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 1},
                {0, 1, 1},
        });

        net.train(inputs, outputs, 10000);

        System.out.println(layer);

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

    public static void predict(Matrix2d testInput, NeuronNetwork net) {
        net.think(testInput);

        System.out.println("Prediction on data "
                + testInput.getValues()[0][0] + " "
                + testInput.getValues()[0][1] + " "
                + testInput.getValues()[0][2] + " -> "
                + net.getOutputLayer().getValues()[0][0] + " -> "
                + Math.round(net.getOutputLayer().getValues()[0][0]));

    }
}
