package patternstudy.temporal.temporalproperty;

import patternstudy.temporal.timepoint.DateTime;

public interface TemporalCollection<E> {
	// get/put at a supplied date
	E get(DateTime when);
	void put(DateTime when, E item);
	// get/put at today
	E get();
	void put(E item);
}
