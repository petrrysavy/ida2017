package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.HashMultiset;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * @author Petr Ryšavý
 */
public class SymmetricMongeElkanDistanceTest {

    @Test
    public void testGetSimilarity() {
        SymmetricMongeElkanDistance<Double> instance = new SymmetricMongeElkanDistance<>((Double a, Double b) -> 1.0 - Math.exp(-Math.abs(a - b)));
        Multiset<Double> a = new HashMultiset<>(1.0, 2.0, 3.0, 2.0, 5.0);
        Multiset<Double> b = new HashMultiset<>(1.0, 1.0, 2.5);
        assertThat(instance.getDistance(a, b), is(closeTo(1.0 - 0.72458947439495214386, 1e-10)));
    }

    @Test
    public void testSimilarity4() {
        // TRIANGLE INEQUALITY IS BROKEN
        SymmetricMongeElkanDistance<Sequence> similarity = new SymmetricMongeElkanDistance<>(new EditDistance(0, 1, 1));
        final ReadsBag a = ReadsBag.fromString("ATC", "ATC", "GGG");
        final ReadsBag b = ReadsBag.fromString("ATA", "GGG");
        final ReadsBag c = ReadsBag.fromString("CTA", "GGG");
        assertThat(similarity.getDistance(a, b), is(closeTo(7.0 / 12.0, 1e-10)));
        assertThat(similarity.getDistance(b, c), is(closeTo(0.5, 1e-10)));
        assertThat(similarity.getDistance(a, c), is(closeTo(14.0 / 12.0, 1e-10)));
        assertThat(similarity.getDistance(a, b) + similarity.getDistance(b, c), is(lessThan(similarity.getDistance(a, c))));
    }

    @Test
    public void testIsSymmetric() {
        SymmetricMongeElkanDistance<Sequence> similarity = new SymmetricMongeElkanDistance<>(new EditDistance(0, 1, 1));
        assertThat(similarity.isSymmetric(), is(true));
    }
}
