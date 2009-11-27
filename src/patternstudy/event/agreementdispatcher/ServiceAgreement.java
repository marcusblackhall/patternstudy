package patternstudy.event.agreementdispatcher;

import java.util.HashMap;
import java.util.Map;

import patternstudy.accounting.accountingevent.AccountingEvent;
import patternstudy.accounting.postingrule.MissingPostingRuleException;
import patternstudy.accounting.postingrule.PostingRule;
import patternstudy.event.EventType;
import patternstudy.temporal.temporalproperty.SingleTemporalCollection;
import patternstudy.temporal.temporalproperty.TemporalCollection;
import patternstudy.temporal.timepoint.DateTime;

public class ServiceAgreement {
	private Map<String, TemporalCollection<Object>> values = new HashMap<String, TemporalCollection<Object>>();
	private Map<EventType, TemporalCollection<PostingRule>> postingRules = new HashMap<EventType, TemporalCollection<PostingRule>>();
	
	public TemporalCollection<Object> registerValue(String key) {
		TemporalCollection<Object> temporalCollection = new SingleTemporalCollection<Object>();
		values.put(key, temporalCollection);
		return temporalCollection;
	}
	
	public void process(AccountingEvent e) {
		getPostingRule(e).process(e);
	}

	public void addPostingRule(EventType eventType, PostingRule rule, DateTime date) {
		if (postingRules.get(eventType) == null)
			postingRules.put(eventType, new SingleTemporalCollection<PostingRule>());
		getRulesTemporalCollectionFor(eventType).put(date, rule);
	}
	
	private TemporalCollection<PostingRule> getRulesTemporalCollectionFor(EventType eventType) {
		TemporalCollection<PostingRule> result = postingRules.get(eventType);
		assert result != null;
		return result;
	}
	
	private PostingRule getPostingRule(AccountingEvent event) {
		final TemporalCollection<PostingRule> rules = getRulesTemporalCollectionFor(event.getEventType());
		if (rules == null) 
			throw new MissingPostingRuleException();//MissingPostingRuleException(this, event);
		try {
			return rules.get(event.getWhenOccurred());
		} catch (IllegalArgumentException e) {
			throw new MissingPostingRuleException();//MissingPostingRuleException(this, event);
		}
	}
	
	public void setValue(String key, Object value, DateTime effectiveDate) {
		values.get(key).put(effectiveDate, value);
	}
	public Object getValue(String key, DateTime at) {
		return values.get(key).get(at);
	}
}
