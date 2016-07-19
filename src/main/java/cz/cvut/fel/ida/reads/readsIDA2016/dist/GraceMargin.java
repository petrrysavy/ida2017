package cz.cvut.fel.ida.reads.readsIDA2016.dist;

/**
 * This interface defines schema how the margin gaps are penalized. See Sect.
 * 2.3 for more details.
 *
 * @author Petr Ryšavý
 */
public interface GraceMargin {

    /**
     * Calculates the cost of the margin gap.
     *
     * @param distanceFromEnd The index of the gap. Starts with 0, which means
     *                        that the maximal possible index is
     *                        {@code wordLength - 1}.
     * @param wordLength      Lenght of the read.
     * @return Cost for margin gap at index {@code distanceFromEnd}.
     */
    public double getMarginGapPenalty(int distanceFromEnd, int wordLength);
}
