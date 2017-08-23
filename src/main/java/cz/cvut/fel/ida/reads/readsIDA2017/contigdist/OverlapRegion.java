/* 
 * Copyright (C) 2017 Petr Ryšavý <petr.rysavy@fel.cvut.cz>
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
