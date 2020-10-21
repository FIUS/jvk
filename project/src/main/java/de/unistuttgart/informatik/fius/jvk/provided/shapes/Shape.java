package de.unistuttgart.informatik.fius.jvk.provided.shapes;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;


/**
 * An interface for shapes that can be used with the {@link PlayfieldModifier} api.
 * <p>
 * A shape is a collection of {@link Position} objects thet represent filled in pixels of the shape.
 * A {@link Position} may be present multiple time to cont the number of times the pixel should be painted.
 */
public interface Shape extends Collection<Position> {

    @Override
    default int size() {
        Integer size = 0;
        for (Position pos : this) {
            size++;
        }
        return size();
    }

    @Override
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }
    
    @Override
    default boolean contains(Object o) {
        if (!(o instanceof Position)) return false;
        for (Position pos : this) {
            if (pos.equals(o)) return true;
        }
        return false;
    }
    
    @Override
    default Object[] toArray() {
        List<Position> positions = new ArrayList<>();
        for (Position pos : this) {
            positions.add(pos);
        }
        return positions.toArray();
    }
    
    @Override
    default <T> T[] toArray(T[] a) {
        Integer size = this.size();
        if (a.length <= size) {
            Iterator<Position> iter = this.iterator();
            for (Integer i = 0; i < a.length; i++) {
                if (iter.hasNext()) {
                    Position pos = iter.next();
                    a[i] = (T) pos;
                } else {
                    a[i] = null;
                }
            }
            return a;
        } else {
            T[] b = (T[]) new Position[size];
            Iterator<Position> iter = this.iterator();
            for (Integer i = 0; i < a.length; i++) {
                Position pos = iter.next();
                b[i] = (T) pos;
            }
            return b;
        }
    }
    
    @Override
    default boolean add(Position e) {
        throw new UnsupportedOperationException("This shape cannot be modified.");
    }
    
    @Override
    default boolean remove(Object o) {
        throw new UnsupportedOperationException("This shape cannot be modified.");
    }
    
    @Override
    default boolean containsAll(Collection<?> c) {
        Set<Position> self = new HashSet<>(this);
        return self.containsAll(c);
    }
    
    @Override
    default boolean addAll(Collection<? extends Position> c) {
        throw new UnsupportedOperationException("This shape cannot be modified.");
    }
    
    @Override
    default boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This shape cannot be modified.");
    }
    
    @Override
    default boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This shape cannot be modified.");
    }
    
    @Override
    default void clear() {
        throw new UnsupportedOperationException("This shape cannot be modified.");
    }
}
