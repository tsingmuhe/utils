package com.sunchangpeng.utils.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator that combines multiple iterators.
 */
public class CompositeIterator<T> implements Iterator<T> {

	protected final List<Iterator<T>> allIterators = new ArrayList<>();

	/**
	 * Creates new composite iterator.
	 * Iterators may be added using the {@link #add(Iterator)} method.
	 */
	public CompositeIterator() {
	}

	/**
	 * Creates new composite iterator with provided iterators.
	 */
	public CompositeIterator(final Iterator<T>... iterators) {
		for (Iterator<T> iterator : iterators) {
			add(iterator);
		}
	}

	/**
	 * Adds an iterator to this composite.
	 */
	public void add(final Iterator<T> iterator) {
		if (allIterators.contains(iterator)) {
			throw new IllegalArgumentException("Duplicate iterator");
		}
		allIterators.add(iterator);
	}

	// ---------------------------------------------------------------- interface

	protected int currentIterator = -1;

	/**
	 * Returns <code>true</code> if next element is available.
	 */
	@Override
	public boolean hasNext() {
		if (currentIterator == -1) {
			currentIterator = 0;
		}
		for (int i = currentIterator; i < allIterators.size(); i++) {
			Iterator iterator = allIterators.get(i);
			if (iterator.hasNext()) {
				currentIterator = i;
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		return allIterators.get(currentIterator).next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove() {
		if (currentIterator == -1) {
			throw new IllegalStateException("next() has not yet been called");
		}

		allIterators.get(currentIterator).remove();
	}

}