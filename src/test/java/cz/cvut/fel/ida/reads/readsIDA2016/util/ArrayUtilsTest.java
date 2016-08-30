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
package cz.cvut.fel.ida.reads.readsIDA2016.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Ryšavý
 */
public class ArrayUtilsTest {

    @Test
    public void testNTimes() {
        assertThat(ArrayUtils.nTimes(0.0, 0), is(equalTo(new double[]{})));
        assertThat(ArrayUtils.nTimes(0.0, 1), is(equalTo(new double[]{0.0})));
        assertThat(ArrayUtils.nTimes(0.0, 3), is(equalTo(new double[]{0.0, 0.0, 0.0})));
        assertThat(ArrayUtils.nTimes(Double.MAX_VALUE, 3), is(equalTo(new double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE})));
        assertThat(ArrayUtils.nTimes(0.8519018499, 3), is(equalTo(new double[]{0.8519018499, 0.8519018499, 0.8519018499})));
    }

}
