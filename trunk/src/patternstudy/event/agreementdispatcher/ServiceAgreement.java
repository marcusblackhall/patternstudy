package patternstudy.event.agreementdispatcher;

import java.util.HashMap;
import java.util.Map;

import patternstudy.event.EventType;
import patternstudy.temporal.temporalproperty.SingleTemporalCollection;
import patternstudy.temporal.temporalproperty.TemporalCollection;
import patternstudy.temporal.timepoint.DateTime;

public class ServiceAgreement {
	private Map<String, TemporalCollection> values = new HashMap<String, TemporalCollection>();
	private Map<EventType, TemporalCollection> postingRules = new HashMap<EventType, TemporalCollection>();
	
	public void registerValue(String key) {
		values.put(key, new SingleTemporalCollection());
	}
	
	public void process(AccountingEvent e) {
		getPostingRule(e).process(e);
	}

	public void addPostingRule(EventType eventType, PostingRule rule, DateTime date) {
		if (postingRules.get(eventType) == null)
			postingRules.put(eventType, new SingleTemporalCollection());
		getRulesTemporalCollectionFor(eventType).put(date, rule);
	}
	
	private SingleTemporalCollection getRulesTemporalCollectionFor(EventType eventType) {
		SingleTemporalCollection result = (SingleTemporalCollection)postingRules.get(eventType);
		assert result != null;
		return result;
	}
	
	private PostingRule getPostingRule(AccountingEvent event) {
		final SingleTemporalCollection rules = getRulesTemporalCollectionFor(event.getEventType());
		if (rules == null) throw new MissingPostingRuleException(this, event);
		
		try {
			return (PostingRule)rules.get(event.getWhenOccurred());
		} catch (IllegalArgumentException e) {
			throw new MissingPostingRuleException(this, event);
		}
	}
}
