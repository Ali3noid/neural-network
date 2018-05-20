package turbo.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Matrix2dTest {

    @Test
    public void whenTranspose_thenCalculateResult() {
        // given
        Matrix2d first = getFirstMatrix();

        // when
        Matrix2d result = first.transpose();

        // then
        assertThat(result).isEqualTo(new Matrix2d(new double[][]{
                {0, 3, 6, 9},
                {1, 4, 7, 10},
                {2, 5, 8, 11},
        }));
    }

    @Test
    public void whenAdd_thenCalculateResult() {
        // given
        Matrix2d first = getFirstMatrix();
        Matrix2d second = getSecondMatrix();

        // when
        Matrix2d result = first.add(second);

        // then
        assertThat(result).isEqualTo(new Matrix2d(new double[][]{
                {1, 3, 5},
                {4, 6, 8},
                {7, 9, 11},
                {10, 12, 14},
        }));
    }

    @Test
    public void whenSubtract_thenCalculateResult() {
        // given
        Matrix2d first = getFirstMatrix();
        Matrix2d second = getSecondMatrix();

        // when
        Matrix2d result = first.subtract(second);

        // then
        assertThat(result).isEqualTo(new Matrix2d(new double[][]{
                {-1, -1, -1},
                {2, 2, 2},
                {5, 5, 5},
                {8, 8, 8},
        }));
    }

    @Test
    public void whenMultiplyBy_thenCalculateResult() {
        // given
        Matrix2d first = new Matrix2d(new double[][]{
                {0, 1, 2},
                {3, 4, 5},
        });

        Matrix2d second = new Matrix2d(new double[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 9},
        });

        // when
        Matrix2d result = first.multiplyBy(second);

        // then
        assertThat(result).isEqualTo(new Matrix2d(new double[][]{
                {15, 18, 23},
                {42, 54, 71},
        }));
    }

    @Test
    public void apply() {
        //TODO
    }

    @Test
    public void scalarMultiplyBy() {
        //TODO
    }

    private Matrix2d getFirstMatrix() {
        return new Matrix2d(new double[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {9, 10, 11},
        });
    }

    private Matrix2d getSecondMatrix() {
        return new Matrix2d(new double[][]{
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
        });
    }
}