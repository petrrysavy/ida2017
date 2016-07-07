package cz.cvut.fel.ida.reads.readsIDA2016.model;

import java.util.Collection;
import java.util.Set;

/**
 * Bag interface.
 *
 * @author Petr Ryšavý
 * @param <T>
 */
public interface Multiset<T> extends Collection<T>
{
    public int count(Object o);

    public boolean add(T object, int count);

    public Multiset<T> union(Multiset<T> t);

    public Set<T> toSet();

    @Override
    public default boolean contains(Object o)
    {
        return count(o) != 0;
    }
}
