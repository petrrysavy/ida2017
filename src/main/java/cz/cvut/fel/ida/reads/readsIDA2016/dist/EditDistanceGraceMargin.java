/*
 * Copyright (c) 2016 Petr Rysavy <rysavpe1@fel.cvut.cz>
 *
 * This file is part of the project readsIDA2016, which is available on 
 * <https://github.com/petrrysavy/readsIDA2016/>.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with This program.  If not, see <http ://www.gnu.org/licenses/>.
 */
package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import static cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils.min;

/**
 * Modification to edit distance that sets arbitrary scoring scheme of the
 * margin gaps. User can set that the margin gaps are more or less penalized
 * than the gaps in the middle of the word.
 *
 * @author Petr Ryšavý
 */
public class EditDistanceGraceMargin implements Distance<Sequence> {

    /** Premium for matching characters. */
    private final double matchPremium;
    /** This is cost for editing a letter. */
    private final double mismatchPenalty;
    /** This is cost for delete and insert operation. */
    private final double gapPenalty;
    /** The margin gap scoring scheme. */
    private final GraceMargin marginGapPenalty;

    /**
     * Constructs new edit distance object based on the costs.
     *
     * @param matchPremium     Premium for matching characters.
     * @param mismatchPenalty  This is cost for editing a letter.
     * @param gapPenalty       This is cost for delete and insert operation.
     * @param marginGapPenalty Scoring scheme for the margin gaps.
     */
    public EditDistanceGraceMargin(double matchPremium, double mismatchPenalty, double gapPenalty, GraceMargin marginGapPenalty) {
        this.matchPremium = matchPremium;
        this.mismatchPenalty = mismatchPenalty;
        this.gapPenalty = gapPenalty;
        this.marginGapPenalty = marginGapPenalty;
    }

    /**
     * {@inheritDoc }
     *
     * @return Calculates the cost based on number of insert, edit and delete
     *         operations to turn one string into the other. The margin gaps are
     *         scored differently.
     */
    @Override
    public double getDistance(Sequence a, Sequence b) {
        // a goes over rows of the matrix, b over columns
        final char[] aSeq = a.getSequence();
        final char[] bSeq = b.getSequence();
        // create emty table, first string in rows, second to the columns
        double[] scoreMatrixCurrent = new double[bSeq.length + 1];
        double[] scoreMatrixLast = new double[bSeq.length + 1];
        double[] swap;

        // initialize first row
        for (int j = 0; j < bSeq.length; j++)
            scoreMatrixLast[j + 1] = scoreMatrixLast[j] + marginGapPenalty.getMarginGapPenalty(j, bSeq.length);
        // fill the rest of the table
        for (int i = 0; i < aSeq.length - 1; i++) { // i goes over rows, i.e. the first word
            scoreMatrixCurrent[0] = scoreMatrixLast[0] + marginGapPenalty.getMarginGapPenalty(i, aSeq.length);
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
                    scoreMatrixLast[bSeq.length] + marginGapPenalty.getMarginGapPenalty(aSeq.length - i - 1, aSeq.length),
                    scoreMatrixLast[bSeq.length - 1] + (aSeq[i] == bSeq[bSeq.length - 1] ? -matchPremium : mismatchPenalty));

            // swap the score matrix line
            swap = scoreMatrixCurrent;
            scoreMatrixCurrent = scoreMatrixLast;
            scoreMatrixLast = swap;
        }

        scoreMatrixCurrent[0] = scoreMatrixLast[0] + marginGapPenalty.getMarginGapPenalty(aSeq.length - 1, aSeq.length);
        // and the last row
        for (int j = 0; j < bSeq.length - 1; j++)
            scoreMatrixCurrent[j + 1] = min(
                    // look to the left
                    scoreMatrixCurrent[j] + marginGapPenalty.getMarginGapPenalty(bSeq.length - j - 1, bSeq.length),
                    // look to the top
                    scoreMatrixLast[j + 1] + gapPenalty,
                    // try the diagonal
                    scoreMatrixLast[j] + (aSeq[aSeq.length - 1] == bSeq[j] ? -matchPremium : mismatchPenalty));
        scoreMatrixCurrent[bSeq.length] = min(
                scoreMatrixCurrent[bSeq.length - 1] + marginGapPenalty.getMarginGapPenalty(0, bSeq.length),
                scoreMatrixLast[bSeq.length] + marginGapPenalty.getMarginGapPenalty(0, aSeq.length),
                scoreMatrixLast[bSeq.length - 1] + (aSeq[aSeq.length - 1] == bSeq[bSeq.length - 1] ? -matchPremium : mismatchPenalty));

        return scoreMatrixCurrent[bSeq.length];
    }
}
