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
import cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils;

/**
 * Implementation of the edit distance between the sequences. This code uses
 * implementation of Wagner-Fischer algorithm.
 *
 * For more details refer to: Wagner, Robert A., and Michael J. Fischer. "The
 * string-to-string correction problem." Journal of the ACM (JACM) 21.1 (1974):
 * 168-173.
 *
 * @author Petr Ryšavý
 */
public class EditDistance implements Distance<Sequence> {

    /** Premium for matching characters. */
    private final int matchPremium;
    /** This is cost for editing a letter. */
    private final int mismatchPenalty;
    /** This is cost for delete and insert operation. */
    private final int gapPenalty;

    /** Constructs new object of this class as Levenshtein distance. */
    public EditDistance() {
        this(0, 1, 1);
    }

    /**
     * Constructs new edit distance object based on the costs.
     *
     * @param matchPremium    Premium for matching characters.
     * @param mismatchPenalty This is cost for editing a letter.
     * @param gapPenalty      This is cost for delete and insert operation.
     */
    public EditDistance(int matchPremium, int mismatchPenalty, int gapPenalty) {
        this.matchPremium = matchPremium;
        this.mismatchPenalty = mismatchPenalty;
        this.gapPenalty = gapPenalty;
    }

    /**
     * {@inheritDoc }
     *
     * @return Calculates the cost based on number of insert, edit and delete
     *         operations to turn one string into the other.
     */
    @Override
    public double getDistance(Sequence a, Sequence b) {
        final char[] aSeq = a.getSequence();
        final char[] bSeq = b.getSequence();
        // create emty table, first string in rows, second to the columns
        int[] scoreMatrixCurrent = new int[bSeq.length + 1];
        int[] scoreMatrixLast = new int[bSeq.length + 1];
        int[] swap;

        // initialize first row
        for (int j = 0; j < bSeq.length; j++)
            scoreMatrixLast[j + 1] = scoreMatrixLast[j] + gapPenalty;
        // fill the rest of the table
        for (int i = 0; i < aSeq.length; i++) { // i goes over rows, i.e. the first word
            scoreMatrixCurrent[0] = scoreMatrixLast[0] + gapPenalty;
            for (int j = 0; j < bSeq.length; j++)
                scoreMatrixCurrent[j + 1] = MathUtils.min(
                        // look to the left
                        scoreMatrixCurrent[j] + gapPenalty,
                        // look to the top
                        scoreMatrixLast[j + 1] + gapPenalty,
                        // try the diagonal
                        scoreMatrixLast[j] + (aSeq[i] == bSeq[j] ? -matchPremium : mismatchPenalty));

            // swap the score matrix line
            swap = scoreMatrixCurrent;
            scoreMatrixCurrent = scoreMatrixLast;
            scoreMatrixLast = swap;
        }

        return scoreMatrixLast[bSeq.length];
    }
}
