package patternstudy.temporal.temporalproperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import patternstudy.temporal.timepoint.DateTime;

public class SingleTemporalCollection {
	private Map<DateTime, Object> contents = new HashMap<DateTime, Object>();
	private List<DateTime> milestoneCache;
	
	public Object get(DateTime when) {
		for (DateTime thisDate : milestones()) {
			if (thisDate.before(when) || thisDate.equals(when))
				return contents.get(thisDate);
		}
		throw new IllegalArgumentException("no records that early");
	}
	
	private List<DateTime> milestones() {
		/**
		 * a list of all the dates where the value changed, return in order
		 * latest first. 
		 */
		if (milestoneCache == null)
			calculateMilestones();
		return milestoneCache;
	}
	
	private void calculateMilestones() {
		milestoneCache = new ArrayList<DateTime>(contents.size());
		milestoneCache.addAll(contents.keySet());
		Collections.sort(milestoneCache, Collections.reverseOrder());
	}

	public void put(DateTime at, Object item) {
		contents.put(at, item);
		clearMilestoneCache();
	}

	private void clearMilestoneCache() {
		milestoneCache = null;
	}
	
	
}
