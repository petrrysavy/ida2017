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

/**
 * Interval has a start time, end time and a value.
 *
 * @author Petr Ryšavý
 */
public interface Interval {

    /** Gets the end time.
     * @return The end time. */
    public int getEnd();

    /** Gets the start time.
     * @return The start time. */
    public int getStart();

    /**
     * Gets the value
     * @return The value.
     */
    public double getValue();
}
