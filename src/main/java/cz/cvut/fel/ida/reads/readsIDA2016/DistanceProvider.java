/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Petr Ryšavý
 */
public class DistanceProvider {

    private final int readLength;
    private final int coverage;
    private final double thetaPrime;

    public DistanceProvider(int readLength, int coverage, double thetaPrime) {
        this.readLength = readLength;
        this.coverage = coverage;
        this.thetaPrime = thetaPrime;
    }

    public Distance<Sequence> getLevenshteinDistance() {
        return new EditDistance();
    }
    
    public Distance<Sequence> getGraceMarginLevenshteinDistance() {
        return new EditDistanceGraceMargin(0, 1, 1, new LinearMarginPenalty(0.5 * ((double) readLength / coverage - 1.0), 2.0));
    }
    
    public Distance<Sequence> getThresholdedDistance() {
        return new DistanceThreshold<>(getGraceMarginLevenshteinDistance(), thetaPrime * readLength, readLength);
    }
    
    public Distance<ReadsBag> getMaxSizeDistance() {
        return new MaxSizeDistance();
    }

    public Distance<ReadsBag> getDistME() {
        return new MultisetAsReadsBagDistanceAdapter(new MongeElkanDistance<>(getLevenshteinDistance()));
    }

    public Distance<ReadsBag> getDistMES() {
        return new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getLevenshteinDistance()));
    }

    public Distance<ReadsBag> getDistMESS() {
        return new ProductDistance<>(new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getLevenshteinDistance())), getMaxSizeDistance());
    }

    public Distance<ReadsBag> getDistMESSG() {
        return new ProductDistance<>(new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getGraceMarginLevenshteinDistance())), getMaxSizeDistance());
    }
    
    public Distance<ReadsBag> getDistMESSGM() {
        return new ProductDistance<>(new MultisetAsReadsBagDistanceAdapter(new SymmetricMongeElkanDistance<>(getThresholdedDistance())), getMaxSizeDistance());
    }
}
