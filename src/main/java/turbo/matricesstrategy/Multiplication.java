package turbo.matricesstrategy;

public class Multiplication implements MatricesStrategy {

    @Override
    public double[][] execute(double[][] a, double[][] b) {
        if (a.length == 0 || b.length == 0 || a[0].length != b.length) {
            throw new IllegalArgumentException("Cannot multiply non n x m and m x p matrices");
        }

        int n = a.length;
        int m = a[0].length;
        int p = b[0].length;

        double[][] result = new double[n][p];

        for (int nIterator = 0; nIterator < n; ++nIterator) {
            for (int pIterator = 0; pIterator < p; ++pIterator) {

                double sum = 0;
                for (int mIterator = 0; mIterator < m; ++mIterator) {
                    sum += (a[nIterator][mIterator] * b[mIterator][pIterator]);
                }
                result[nIterator][pIterator] = sum;
            }
        }
        return result;
    }
}
