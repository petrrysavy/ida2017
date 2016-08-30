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
package cz.cvut.fel.ida.reads.readsIDA2016;

import cz.cvut.fel.ida.reads.readsIDA2016.dist.Distance;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.DistanceThreshold;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.EditDistance;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.EditDistanceGraceMargin;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.LinearMarginPenalty;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.MaxSizeDistance;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.MongeElkanDistance;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.MultisetAsReadsBagDistanceAdapter;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.ProductDistance;
import cz.cvut.fel.ida.reads.readsIDA2016.dist.SymmetricMongeElkanDistance;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;

/**
 * This class provides static methods that instantiate {@link Distance}
 * implementations used in the paper. This is the simplest way how to obtain
 * implementation of the methods tested in the paper.
 *
 * @author Petr Ryšavý
 */
public class DistanceProvider {

    /** Length of read. It is assumed that this is constant. */
    private final int readLength;
    /** Coverage is average number of reads that cover a particular position in
     * the true sequence. Denoted {@code alpha} in the paper. It is assumed that
     * this is constant. */
    private final int coverage;
    /** Threshold for missing read detection. See Sect. 2.4. */
    private final double thetaPrime;

    /**
     * Constructs new instance of this class.
     *
     * @param readLength Length of read.
     * @param coverage   Coverage is average number of reads that cover a
     *                   particular position in the true sequence.
     * @param thetaPrime Threshold for missing read detection.
     */
    public DistanceProvider(int readLength, int coverage, double thetaPrime) {
        this.readLength = readLength;
        this.coverage = coverage;
        this.thetaPrime = thetaPrime;
    }

    /**
     * Constructs implementation of Levenshtein distance used in the
     * experiments. Implementation uses standard Fischer-Wagner dynamic
     * programming algorithm with linear memory requirements and qudratic time
     * requirements.
     *
     * @return The Levenshtein distance.
     */
    public Distance<Sequence> getLevenshteinDistance() {
        return new EditDistance();
    }

    /**
     * Constructs implementation of Levenshtein distance that does not penalize
     * margin gaps. See Sect. 2.3. This is part of {@code Dist_MESSG}.
     *
     * @return Levenshtein distance that does not penalize margin gaps.
     */
    public Distance<Sequence> getGraceMarginLevenshteinDistance() {
        return new EditDistanceGraceMargin(0, 1, 1, new LinearMarginPenalty(0.5 * ((double) readLength / coverage - 1.0), 2.0));
    }

    /**
     * A modification to the {@link #getGraceMarginLevenshteinDistance()}. If
     * the distance is greater than threshold set in this class, the inputs are
     * considered different. See Sect. 2.4.
     *
     * @return Levenshtein distance that does not penaliye margin gaps and
     *         implements a threshold.
     */
    public Distance<Sequence> getThresholdedDistance() {
        return new DistanceThreshold<>(getGraceMarginLevenshteinDistance(), thetaPrime * readLength, readLength);
    }

    /**
     * Returns an implementation of distance that provides scaled upper bound on
     * distance between two read bags. It is defined by
     * {@code Dist(RA, RB) = max(|RA|, |RB|)} This is used as baseline in the
     * paper.
     *
     * @return Distance that returns size of the bigger reads bag.
     */
    public Distance<ReadsBag> getMaxSizeDistance() {
        return new MaxSizeDistance();
    }

    /**
     * Returns Monge-Elkan distance. See equation (4) in the paper.
     *
     * @return The Monge-Elkan distance.
     */
    public Distance<ReadsBag> getDistME() {
        return new MultisetAsReadsBagDistanceAdapter(new MongeElkanDistance<>(getLevenshteinDistance()));
    }

    /**
     * Returns a symmetric version of the Monge-Elkan distance. See equation (5)
     * in the paper.
     *
     * @return Symmetric version of the Monge-Elkan distance.
     */
    public Distance<ReadsBag> getDistMES() {
        return new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getLevenshteinDistance()));
    }

    /**
     * Returns Monge-Elkan distance that is denormalized. See euqation (6) in
     * the paper.
     *
     * @return Scaled Monge-Elkan distance.
     */
    public Distance<ReadsBag> getDistMESS() {
        return new ProductDistance<>(new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getLevenshteinDistance())), getMaxSizeDistance());
    }

    /**
     * Returns Monge-Elkan distance that does not penalize margin gaps. See
     * Sect. 2.3 in the paper.
     *
     * @return Monge-Elkan distance that does not penalize margin gaps of the
     *         reads.
     * @see #getGraceMarginLevenshteinDistance()
     */
    public Distance<ReadsBag> getDistMESSG() {
        return new ProductDistance<>(new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getGraceMarginLevenshteinDistance())), getMaxSizeDistance());
    }

    /**
     * Returns Monge-Elkan distance with missing read detection. See Sect. 2.4
     * in the paper.
     *
     * @return Monge-Elkan distance with missing read detection.
     * @see #getThresholdedDistance()
     */
    public Distance<ReadsBag> getDistMESSGM() {
        return new ProductDistance<>(new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getThresholdedDistance())), getMaxSizeDistance());
    }
}
