package patternstudy.event.agreementdispatcher;

import patternstudy.temporal.timepoint.DateTime;

public class AccountingEvent {
	private EventType type;
	private DateTime whenOccurred;
	private DateTime whenNoticed;
	private Subject subject;
	
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
