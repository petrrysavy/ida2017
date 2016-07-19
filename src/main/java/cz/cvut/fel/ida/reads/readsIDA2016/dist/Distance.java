package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import java.util.Arrays;
import java.util.List;

/**
 * Interface that defines distance between two objects.
 *
 * @author Petr Ryšavý
 * @param <T> Compared type.
 */
public interface Distance<T> {

    /**
     * Calculates the distance. Should be non-negative.
     *
     * @param a The first object to compare.
     * @param b The second object to comapare.
     * @return Distance between {@code a} and {@code b}.
     */
    public abstract double getDistance(T a, T b);

    /**
     * Calculates distance matrix between two lists of objects.
     *
     * @param a First list to compare.
     * @param b Second list to compare.
     * @return Matrix where i-th row and j-th column shows distance between i-th
     *         object of {@code a} and j-th object of {@code b}.
     */
    public default double[][] getDistanceMatrix(T[] a, T[] b) {
        return getDistanceMatrix(Arrays.asList(a), Arrays.asList(b));
    }

    /**
     * Calculates distance matrix between objects of a lists of objects.
     *
     * @param values List to compare.
     * @return Matrix where i-th row and j-th column shows distance between i-th
     *         object of {@code values} and j-th object of {@code values}.
     */
    public default double[][] getDistanceMatrix(T[] values) {
        final boolean isSymmetric = isSymmetric();

        final double[][] distance = new double[values.length][values.length];
        for (int i = 0; i < distance.length; i++)
            if (isSymmetric)
                for (int j = i + 1; j < values.length; j++)
                    distance[j][i] = distance[i][j] = getDistance(values[i], values[j]);
            else
                for (int j = 0; j < values.length; j++)
                    distance[i][j] = getDistance(values[i], values[j]);
        return distance;
    }

    /**
     * Calculates distance matrix between two lists of objects.
     *
     * @param aList First list to compare.
     * @param bList Second list to compare.
     * @return Matrix where i-th row and j-th column shows distance between i-th
     *         object of {@code a} and j-th object of {@code b}.
     */
    public default double[][] getDistanceMatrix(List<T> aList, List<T> bList) {
        final boolean isSymmetric = isSymmetric() && aList.equals(bList);

        final double[][] distance = new double[aList.size()][bList.size()];
        for (int i = 0; i < distance.length; i++)
            if (isSymmetric)
                for (int j = i + 1; j < bList.size(); j++)
                    distance[j][i] = distance[i][j] = getDistance(aList.get(i), bList.get(j));
            else
                for (int j = 0; j < bList.size(); j++)
                    distance[i][j] = getDistance(aList.get(i), bList.get(j));
        return distance;
    }

    /**
     * Returns whether the distance is symmetric. Distance is symmetric iff for
     * all {@code a} and {@code b} holds that {@code dist(a,b) = dist(b,a)}.
     * Default implementation returns {@code true}.
     *
     * @return Whether the distance is symmetric.
     */
    public default boolean isSymmetric() {
        return true;
    }

    /**
     * Returns whether the range of the distance is {@code [0,1]} interval.
     * Default implementation returns {@code false}.
     *
     * @return Whether the values are normalized.
     */
    public default boolean isZeroOneNormalized() {
        return false;
    }
}
