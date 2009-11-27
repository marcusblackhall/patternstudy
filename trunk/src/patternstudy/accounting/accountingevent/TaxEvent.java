package patternstudy.accounting.accountingevent;

import patternstudy.base.money.Money;
import patternstudy.event.EventType;

public class TaxEvent extends MonetaryEvent {
	public TaxEvent(AccountingEvent base, Money taxableAmount) {
		super(taxableAmount, EventType.TAX, base.getWhenOccurred(), base.getWhenNoticed(), base.getSubject());
		base.friendAddSecondaryEvent(this);
		assert base.getEventType() != getEventType() : "Probale endless recursion";
	}
}
