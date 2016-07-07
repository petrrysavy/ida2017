package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.HashMultiset;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import org.hamcrest.number.IsCloseTo;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Petr Ryšavý
 */
public class MongeElkanSimilarityTest {

    /**
     * Test of getSimilarity method, of class MongeElkanDistance.
     */
    @Test
    public void testGetSimilarity() {
        MongeElkanDistance instance = new MongeElkanDistance<>((Double a, Double b) -> 1.0 - Math.exp(-Math.abs(a - b)));
        Multiset<Double> a = new HashMultiset<>(1.0, 2.0, 3.0, 2.0, 5.0);
        Multiset<Double> b = new HashMultiset<>(1.0, 1.0, 2.5);
        assertThat(instance.getDistance(a, b), is(closeTo(1.0 - 0.5803353955523598132, 1e-11)));
    }

    /**
     * Test of getSimilarity method, of class MongeElkanDistance.
     */
    @Test
    public void testGetSimilarity2() {
        MongeElkanDistance instance = new MongeElkanDistance<>((Double a, Double b) -> 1.0 - Math.exp(-Math.abs(a - b)));
        Multiset<Double> a = new HashMultiset<>(1.0, 2.0, 3.0, 2.0, 5.0);
        Multiset<Double> b = new HashMultiset<>(1.0, 1.0, 2.5);
        assertThat(instance.getDistance(b, a), is(closeTo(1.0 - 0.86884355323754447453, 1e-11)));
    }

    @Test
    public void testSimilarity2() {
        MongeElkanDistance<Sequence> distance = new MongeElkanDistance<>(new EditDistance(0, 1, 1));
        assertThat(distance.getDistance(ReadsBag.fromString("ATC", "ATG"), ReadsBag.fromString("ATA")), is(closeTo(1.0, 1e-10)));
        assertThat(distance.getDistance(ReadsBag.fromString("ATA"), ReadsBag.fromString("GCA")), is(closeTo(2.0, 1e-10)));
        assertThat(distance.getDistance(ReadsBag.fromString("ATC", "ATG"), ReadsBag.fromString("GCA")), is(closeTo(3.0, 1e-10)));
    }

    @Test
    public void testSimilarity3() {
        MongeElkanDistance<Sequence> distance = new MongeElkanDistance<>(new EditDistance(0, 1, 1));
        assertThat(distance.getDistance(ReadsBag.fromString("ATA", "ATC", "ATG"), ReadsBag.fromString("ATA")), is(closeTo(2.0 / 3.0, 1e-10)));
        assertThat(distance.getDistance(ReadsBag.fromString("ATA"), ReadsBag.fromString("GCA")), is(closeTo(2.0, 1e-10)));
        assertThat(distance.getDistance(ReadsBag.fromString("ATA", "ATC", "ATG"), ReadsBag.fromString("GCA")), is(closeTo(8.0 / 3.0, 1e-10)));
    }

    @Test
    public void testSimilarity4() {
        // TRIANGLE INEQUALITY IS BROKEN
        MongeElkanDistance<Sequence> distance = new MongeElkanDistance<>(new EditDistance(0, 1, 1));
        final ReadsBag a = ReadsBag.fromString("ATC", "ATC", "GGG");
        final ReadsBag b = ReadsBag.fromString("ATA", "GGG");
        final ReadsBag c = ReadsBag.fromString("CTA", "GGG");
        assertThat(distance.getDistance(a, b), is(closeTo(2.0 / 3.0, 1e-10)));
        assertThat(distance.getDistance(b, c), is(closeTo(0.5, 1e-10)));
        assertThat(distance.getDistance(a, c), is(closeTo(4.0 / 3.0, 1e-10)));
        assertThat(distance.getDistance(a, b) + distance.getDistance(b, c), is(lessThan(distance.getDistance(a, c))));
    }

    @Test
    @SuppressWarnings("UnnecessaryBoxing")
    public void testDistance5() {
        Distance<Integer> dist = (Integer a, Integer b) -> {
            if (a == 0 && b == 0)
                return 1.0;
            if (a == 0 && b == 1)
                return 0.04;
            if (a == 1 && b == 0)
                return 1.0;
            if (a == 1 && b == 1)
                return 0.08;
            throw new IllegalArgumentException();
        };
        MongeElkanDistance<Integer> Dist = new MongeElkanDistance<>(dist);
        assertThat(Dist.getDistance(new HashMultiset<>(0, 0, 1, 1), new HashMultiset<>(0, 0, 1, 1)), is(equalTo(0.06)));
    }

    @Test
    public void testSymmetric() {
        MongeElkanDistance<Sequence> distance = new MongeElkanDistance<>(new EditDistance(0, 1, 1));
        assertThat(distance.isSymmetric(), is(false));
    }

    @Test
    public void testNormalizedFalse() {
        MongeElkanDistance<Sequence> distance = new MongeElkanDistance<>(new EditDistance(0, 1, 1));
        assertThat(distance.isZeroOneNormalized(), is(false));
    }
}
