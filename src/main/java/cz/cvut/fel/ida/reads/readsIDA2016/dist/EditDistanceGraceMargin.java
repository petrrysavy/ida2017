package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils;
import static cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils.min;
import static cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils.min;
import static cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils.min;
import static cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils.min;

/**
 *
 * @author Petr Ryšavý
 */
public class EditDistanceGraceMargin implements Distance<Sequence>
{
    private final double matchPremium;
    private final double mismatchPenalty;
    private final double gapPenalty;
    private final GraceMargin marginGapPenalty;

    public EditDistanceGraceMargin(double matchPremium, double mismatchPenalty, double gapPenalty, GraceMargin marginGapPenalty)
    {
        this.matchPremium = matchPremium;
        this.mismatchPenalty = mismatchPenalty;
        this.gapPenalty = gapPenalty;
        this.marginGapPenalty = marginGapPenalty;
    }

    @Override
    public double getDistance(Sequence a, Sequence b)
    {
        // a goes over rows of the matrix, b over columns
        final char[] aSeq = a.getSequence();
        final char[] bSeq = b.getSequence();
        // create emty table, first string in rows, second to the columns
        double[] scoreMatrixCurrent = new double[bSeq.length + 1];
        double[] scoreMatrixLast = new double[bSeq.length + 1];
        double[] swap;

        // initialize first row
        for (int j = 0; j < bSeq.length; j++)
            scoreMatrixLast[j + 1] = scoreMatrixLast[j] + marginGapPenalty.getMarginGapPenalty(j + 1, bSeq.length);
        // fill the rest of the table
        for (int i = 0; i < aSeq.length - 1; i++)
        { // i goes over rows, i.e. the first word
            scoreMatrixCurrent[0] = scoreMatrixLast[0] + marginGapPenalty.getMarginGapPenalty(i + 1, aSeq.length);
            for (int j = 0; j < bSeq.length - 1; j++)
                scoreMatrixCurrent[j + 1] = min(
                    // look to the left
                    scoreMatrixCurrent[j] + gapPenalty,
                    // look to the top
                    scoreMatrixLast[j + 1] + gapPenalty,
                    // try the diagonal
                    scoreMatrixLast[j] + (aSeq[i] == bSeq[j] ? -matchPremium : mismatchPenalty));
            scoreMatrixCurrent[bSeq.length] = min(
                scoreMatrixCurrent[bSeq.length - 1] + gapPenalty,
                scoreMatrixLast[bSeq.length] + marginGapPenalty.getMarginGapPenalty(aSeq.length - i, aSeq.length),
                scoreMatrixLast[bSeq.length - 1] + (aSeq[i] == bSeq[bSeq.length - 1] ? -matchPremium : mismatchPenalty));

            // swap the score matrix line
            swap = scoreMatrixCurrent;
            scoreMatrixCurrent = scoreMatrixLast;
            scoreMatrixLast = swap;
        }

        scoreMatrixCurrent[0] = scoreMatrixLast[0] + marginGapPenalty.getMarginGapPenalty(aSeq.length, aSeq.length);
        // and the last row
        for (int j = 0; j < bSeq.length - 1; j++)
            scoreMatrixCurrent[j + 1] = min(
                // look to the left
                scoreMatrixCurrent[j] + marginGapPenalty.getMarginGapPenalty(bSeq.length - j, bSeq.length),
                // look to the top
                scoreMatrixLast[j + 1] + gapPenalty,
                // try the diagonal
                scoreMatrixLast[j] + (aSeq[aSeq.length - 1] == bSeq[j] ? -matchPremium : mismatchPenalty));
        scoreMatrixCurrent[bSeq.length] = min(
            scoreMatrixCurrent[bSeq.length - 1] + marginGapPenalty.getMarginGapPenalty(1, bSeq.length),
            scoreMatrixLast[bSeq.length] + marginGapPenalty.getMarginGapPenalty(1, aSeq.length),
            scoreMatrixLast[bSeq.length - 1] + (aSeq[aSeq.length - 1] == bSeq[bSeq.length - 1] ? -matchPremium : mismatchPenalty));

        return scoreMatrixCurrent[bSeq.length];
    }
}
