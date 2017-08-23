package cz.cvut.fel.ida.reads.readsIDA2017.contigdist;

/**
 * Class that holds information about overlap of two sequences.
 *
 * @author Petr Ryšavý
 */
public class OverlapRegion {

    public static final OverlapRegion DUMMY = new OverlapRegion(Double.MAX_VALUE, 0, 0, 0, 0);

    private final double distance;
    private final int lengthA, lengthB;
    private final int endA, endB;

    public OverlapRegion(double distance, int lengthA, int endA, int lengthB, int endB) {
        this.distance = distance;
        this.lengthA = lengthA;
        this.endA = endA;
        this.lengthB = lengthB;
        this.endB = endB;
    }

    public double getDistance() {
        return distance;
    }

    public int getLengthA() {
        return lengthA;
    }

    public int getStartA() {
        return endA - lengthA;
    }

    public int getEndA() {
        return endA;
    }

    public int getLengthB() {
        return lengthB;
    }

    public int getStartB() {
        return endB - lengthB;
    }

    public int getEndB() {
        return endB;
    }

    @Override
    public String toString() {
        return "SimpleOverlapRegion{start=" + getStartA() + "/" + getStartB() + ", end=" + endA + "/" + endB + ", distance=" + distance + ", length=" + lengthA + '/' + lengthB + '}';
    }

    public int getLengthMax() {
        return Math.max(getLengthA(), getLengthB());
    }

    public double distanceToLengthRatio() {
        return getDistance() / getLengthMax();
    }

}
