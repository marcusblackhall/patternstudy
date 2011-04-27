package patternstudy.temporal.temporalproperty;

import patternstudy.temporal.timepoint.DateTime;

public interface TemporalCollection<E> {
	// get/put at a supplied date
	E get(DateTime when);
	TemporalCollection<E> put(DateTime when, E item);
	// get/put at today
	E get();
	TemporalCollection<E> put(E item);
}
