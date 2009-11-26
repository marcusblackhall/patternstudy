package patternstudy.event.agreementdispatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import patternstudy.accounting.accountingentry.Entry;
import patternstudy.event.EventType;
import patternstudy.temporal.timepoint.DateTime;

public class AccountingEvent {
	private EventType type;
	private DateTime whenOccurred;
	private DateTime whenNoticed;
	private Subject subject;
	
	protected List secondaryEvents = new ArrayList();
	protected Collection<Entry> resultingEntries = new HashSet<Entry>();
	protected boolean isProcessed = false;
	private AccountingEvent replacementEvent;
	
	public AccountingEvent(EventType type, DateTime whenOccurred, DateTime whenNoticed, Subject subject) {
		this.type = type;
	}
	
	public void process() {
		assert !isProcessed;
		if (adjustedEvent != null) adjustedEvent.reverse();
		subject.process(this);
		markProcessed();
	}
	
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public DateTime getWhenOccurred() {
		return whenOccurred;
	}
	public void setWhenOccurred(DateTime whenOccurred) {
		this.whenOccurred = whenOccurred;
	}
	public DateTime getWhenNoticed() {
		return whenNoticed;
	}
	public void setWhenNoticed(DateTime whenNoticed) {
		this.whenNoticed = whenNoticed;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
