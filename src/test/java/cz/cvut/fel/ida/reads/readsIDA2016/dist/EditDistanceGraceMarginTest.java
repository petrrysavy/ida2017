package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Petr Ryšavý
 */
public class EditDistanceGraceMarginTest {

    private EditDistanceGraceMargin editDistance;

    @Before
    public void init() {
        editDistance = new EditDistanceGraceMargin(0, 1, 1, (int distanceFromEnd, int wordLength) -> {
            switch (distanceFromEnd) {
                case 0:
                case 1:
                    return 0;
                case 2:
                    return 1;
                case 3:
                    return 2;
                default:
                    throw new RuntimeException();
            }
        });
    }

    @Test
    public void testGetDistance1() {
        assertThat(editDistance.getDistance(Sequence.fromString("ABC"), Sequence.fromString("CDA")), is(equalTo(2.0)));
    }

    @Test
    public void testGetDistance2() {
        assertThat(editDistance.getDistance(Sequence.fromString("ABC"), Sequence.fromString("BCD")), is(equalTo(0.0)));
    }

    @Test
    public void testGetDistance3() {
        assertThat(editDistance.getDistance(Sequence.fromString("BCD"), Sequence.fromString("CDA")), is(equalTo(0.0)));
    }

    @Test
    public void testGetDistance4() {
        assertThat(editDistance.getDistance(Sequence.fromString("ABC"), Sequence.fromString("ABC")), is(equalTo(0.0)));
    }

    @Test
    public void testFigure2() {
        editDistance = new EditDistanceGraceMargin(0, 1, 1, (int distanceFromEnd, int wordLength) -> {
            switch (distanceFromEnd) {
                case 0:
                case 1:
                    return 0;
                case 2:
                    return 1;
                case 3:
                    return 2;
                case 4:
                    return 3;
                default:
                    throw new RuntimeException();
            }
        });
        assertThat(editDistance.getDistance(Sequence.fromString("TCGC"), Sequence.fromString("CGCT")), is(equalTo(0.0)));
    }
    
    @Test
    public void testSymmetric() {
        assertThat(editDistance.isSymmetric(), is(true));
    }

    @Test
    public void testNormalizedFalse() {
        assertThat(editDistance.isZeroOneNormalized(), is(false));
    }
}
