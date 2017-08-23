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
package cz.cvut.fel.ida.reads.readsIDA2017.wis;

import java.util.Comparator;

/**
 * Comparator that can be used to sort interval instances by their terminal
 * time.
 *
 * @author Petr Ryšavý
 */
public class IntervalEndComparator implements Comparator<Interval> {

    /** An instance of the comparator. */
    public static final IntervalEndComparator INSTANCE = new IntervalEndComparator();

    /** {@inheritDoc} @return {@code o1.end.compareTo(o2.end)}. */
    @Override
    public int compare(Interval o1, Interval o2) {
        return Integer.compare(o1.getEnd(), o2.getEnd());
    }

}
