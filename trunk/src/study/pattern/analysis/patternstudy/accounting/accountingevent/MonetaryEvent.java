package patternstudy.accounting.accountingevent;

import patternstudy.base.money.Money;
import patternstudy.event.EventType;
import patternstudy.event.agreementdispatcher.Subject;
import patternstudy.temporal.timepoint.DateTime;

public class MonetaryEvent extends AccountingEvent {
	private Money amount;
	
	public MonetaryEvent(Money amount, EventType type, DateTime whenOccurred, DateTime whenNoticed, Subject customer) {
		super(type, whenOccurred, whenNoticed, customer);
		this.amount = amount;
	}
	
	public Money getAmount() {
		return amount;
	}
}
