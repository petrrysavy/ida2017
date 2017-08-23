package cz.cvut.fel.ida.reads.readsIDA2017.contigdist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;

/**
 * This class is used to find overlap between two contigs. In other words this
 * class implements Algorithm 1 from the paper.
 *
 * @author Petr Ryšavý
 */
public class OverlapFinder {

    private final int minLength;

    public OverlapFinder(int minLength) {
        this.minLength = minLength;
    }

    public OverlapRegion getOverlap(Sequence a, Sequence b) {
        final char[] aSeq = a.getSequence();
        final char[] bSeq = b.getSequence();
        // create empty table, first string in rows, second to the columsn
        int[] distanceCurrent = new int[bSeq.length + 1];
        int[] distanceLast = new int[bSeq.length + 1];
        int[] lengthACurrent = new int[bSeq.length + 1];
        int[] lengthALast = new int[bSeq.length + 1];
        int[] lengthBCurrent = new int[bSeq.length + 1];
        int[] lengthBLast = new int[bSeq.length + 1];
        int[] swap;
        // first row does not need to be initialized - it is all zeroes (thanks Java)
        OverlapRegion region = OverlapRegion.DUMMY;

        for (int i = 0; i < aSeq.length; i++) {
            for (int j = 0; j < bSeq.length; j++) {
                final int matchCost = aSeq[i] == bSeq[j] ? 0 : 1;
                final double gapInA = ((double) distanceCurrent[j] + 1.0) / Math.max(lengthACurrent[j], lengthBCurrent[j] + 1);
                final double gapInB = ((double) distanceLast[j + 1] + 1.0) / Math.max(lengthALast[j + 1] + 1, lengthBLast[j + 1]);
                final double diagonal = ((double) distanceLast[j] + matchCost) / (Math.max(lengthALast[j], lengthBLast[j]) + 1);

                if (diagonal < gapInA && diagonal < gapInB) { // diagonal wins
                    distanceCurrent[j + 1] = distanceLast[j] + matchCost;
                    lengthACurrent[j + 1] = lengthALast[j] + 1;
                    lengthBCurrent[j + 1] = lengthBLast[j] + 1;
                } else if (gapInA < gapInB) { // gap in A is the smallest
                    distanceCurrent[j + 1] = distanceCurrent[j] + 1;
                    lengthACurrent[j + 1] = lengthACurrent[j];
                    lengthBCurrent[j + 1] = lengthBCurrent[j] + 1;
                } else { // gap in B is the right choice
                    distanceCurrent[j + 1] = distanceLast[j + 1] + 1;
                    lengthACurrent[j + 1] = lengthALast[j + 1] + 1;
                    lengthBCurrent[j + 1] = lengthBLast[j + 1];
                }
            }

            // we need to check whether we did find an optimum
            if (((double) distanceCurrent[bSeq.length]) / Math.max(lengthACurrent[bSeq.length], lengthBCurrent[bSeq.length]) < region.distanceToLengthRatio()
                    && lengthACurrent[bSeq.length] >= minLength && lengthBCurrent[bSeq.length] >= minLength) {
                region = new OverlapRegion(distanceCurrent[bSeq.length], lengthACurrent[bSeq.length], i + 1, lengthBCurrent[bSeq.length], bSeq.length);
            }

            // swap the last
            swap = distanceCurrent;
            distanceCurrent = distanceLast;
            distanceLast = swap;
            swap = lengthACurrent;
            lengthACurrent = lengthALast;
            lengthALast = swap;
            swap = lengthBCurrent;
            lengthBCurrent = lengthBLast;
            lengthBLast = swap;
        }

        // and also we need to check whether we did find an optimum in the last row
        for (int j = 1; j < bSeq.length; j++) // <, not <=, bSeq.lenght was already done
            if (((double) distanceLast[j]) / Math.max(lengthALast[j], lengthBLast[j]) < region.distanceToLengthRatio()
                    && lengthALast[j] >= minLength && lengthBLast[j] >= minLength) {
                region = new OverlapRegion(distanceLast[j], lengthALast[j], aSeq.length, lengthBLast[j], j);
            }

        return region == OverlapRegion.DUMMY ? null : region;
    }

}
