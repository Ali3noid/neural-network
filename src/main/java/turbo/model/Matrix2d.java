package turbo.model;

import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.lang.Math.random;

@Data
public class Matrix2d {

    private double[][] values;

    public Matrix2d(double[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Cannot create zero length matrix");
        }
        values = matrix;
    }

    public Matrix2d transpose() {
        double[][] result = new double[values[0].length][values.length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                result[j][i] = values[i][j];
            }
        }
        return new Matrix2d(result);
    }

    public Matrix2d add(Matrix2d matrix) {
        checkMatricesAreNotEmpty(matrix);
        checkEqualDimensions(matrix);
        double[][] result = new double[values.length][values[0].length];
        double[][] valuesToAdd = matrix.getValues();
        loopThroughMatrix(result, (i, j) -> result[i][j] = this.values[i][j] + valuesToAdd[i][j]);
        return new Matrix2d(result);
    }

    public Matrix2d subtract(Matrix2d matrix) {
        checkMatricesAreNotEmpty(matrix);
        checkEqualDimensions(matrix);
        double[][] result = new double[values.length][values[0].length];
        double[][] valuesToSubtract = matrix.getValues();
        loopThroughMatrix(result, (i, j) -> result[i][j] = this.values[i][j] - valuesToSubtract[i][j]);
        return new Matrix2d(result);
    }

    public Matrix2d multiplyBy(Matrix2d matrix) {
        checkMatricesAreNotEmpty(matrix);
        checkCompatibilityForMultiplication(matrix);

        int n = values.length;
        int m = values[0].length;
        int p = matrix.getValues()[0].length;

        double[][] result = new double[n][p];
        for (int nIterator = 0; nIterator < n; ++nIterator) {
            for (int pIterator = 0; pIterator < p; ++pIterator) {
                double sum = 0;
                for (int mIterator = 0; mIterator < m; ++mIterator) {
                    sum += (values[nIterator][mIterator] * matrix.getValues()[mIterator][pIterator]);
                }
                result[nIterator][pIterator] = sum;
            }
        }
        return new Matrix2d(result);
    }

    public Matrix2d apply(Function<Double, Double> function) {
        double[][] result = new double[values.length][values[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = function.apply(values[i][j]));
        return new Matrix2d(result);
    }

    public Matrix2d scalarMultiplyBy(Matrix2d matrix) {
        checkEqualDimensions(matrix);
        double[][] result = new double[values.length][values[0].length];
        loopThroughMatrix(result, (i, j) -> result[i][j] = values[i][j] * matrix.getValues()[i][j]);
        return new Matrix2d(result);
    }

    public void fillRandomValues(){
        loopThroughMatrix(values, (i, j) -> values[i][j] = random() - 0.5);
    }

    private void loopThroughMatrix(double[][] values, BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                consumer.accept(i, j);
            }
        }
    }

    private void checkMatricesAreNotEmpty(Matrix2d matrix) {
        if (this.values.length == 0 || matrix.getValues().length == 0) {
            throw new IllegalArgumentException("Cannot perform operation on empty matrices");
        }
    }

    private void checkCompatibilityForMultiplication(Matrix2d matrix) {
        if (this.values[0].length != matrix.getValues().length) {
            throw new IllegalArgumentException("Cannot multiply non n x m and m x p matrices");
        }
    }

    private void checkEqualDimensions(Matrix2d matrix) {
        double[][] newValues = matrix.getValues();
        if (this.values.length != newValues.length || this.values[0].length != newValues[0].length) {
            throw new IllegalArgumentException("Cannot perform this operation on unequal matrices");
        }

    }
}
