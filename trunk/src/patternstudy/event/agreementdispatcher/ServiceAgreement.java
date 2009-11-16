package patternstudy.event.agreementdispatcher;

import java.util.Map;

public class ServiceAgreement {
	private Map<K, V> postingRules = new HashMap();
	
	public void process(AccountingEvent e) {
		getPostingRule(e).process(e);
	}

	public void addPostingRule(EventType eventType, PostingRule rule, DateTime date) {
		if (postingRules.get(eventType) == null)
			postingRules.put(eventType, new SingleTemporalCollection());
		getRulesTemporalCollectionFor(eventType).put(date, rule);
	}
	
	private TemporalCollection getRulesTemporalCollectionFor(EventType eventType) {
		TemporalCollection result = (TemporalCollection)postingRules.get(eventType);
		assert result != null;
		return result;
	}
	
	private PostingRule getPostingRule(AccountingEvent event) {
		final TemporalCollection rules = getRulesTemporalCollectionFor(event.getEventType());
		if (rules == null) throw new MissingPostingRuleException(this, event);
		
		try {
			return (PostingRule)rules.get(event.getWhenOccurred());
		} catch (IllegalArgumentException e) {
			throw new MissingPostingRuleException(this, event);
		}
	}
}
