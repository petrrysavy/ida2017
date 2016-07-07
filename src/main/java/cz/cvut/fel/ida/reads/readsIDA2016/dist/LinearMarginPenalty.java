package cz.cvut.fel.ida.reads.readsIDA2016.dist;

/**
 * This method implements linear border gap penaulty. The gap penaulty is zero
 * for any x from interval [0, a], then linearly increases on interval [a, b]
 * and is maximal on interval [b, 1]. The x value represents distance of x from
 * the beginning of the word.
 *
 * @author Petr Ryšavý
 */
public class LinearMarginPenalty implements GraceMargin {

    private final double freeGaps;
    private final double maximalGapPenaulty;

    public LinearMarginPenalty(double freeGaps, double maximalGapPenalty) {
        this.freeGaps = freeGaps;
        this.maximalGapPenaulty = maximalGapPenalty;
    }

    @Override
    public double getMarginGapPenalty(int gapIndex, int readLength) {
        if (gapIndex < 0 || gapIndex >= readLength)
            throw new IndexOutOfBoundsException("It is impossible to index gap : " + gapIndex + " on read of length " + readLength);
        if (gapIndex <= freeGaps - 1)
            return 0;
        else if (gapIndex >= readLength - freeGaps)
            return maximalGapPenaulty;
        else
            return maximalGapPenaulty * (gapIndex - freeGaps + 1) / (readLength + 1 - 2 * freeGaps);
    }
}
