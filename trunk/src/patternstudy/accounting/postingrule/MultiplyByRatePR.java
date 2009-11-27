package patternstudy.accounting.postingrule;

import patternstudy.accounting.account.AccountType;
import patternstudy.accounting.accountingevent.AccountingEvent;
import patternstudy.accounting.accountingevent.Usage;
import patternstudy.base.money.Money;

public class MultiplyByRatePR extends PostingRule {
	public MultiplyByRatePR(AccountType type, boolean isTaxable) {
		super(type, isTaxable);
	}
	
	protected Money calculateAmount(AccountingEvent evt) {
		Usage usageEvent = (Usage)evt;
		double baseRate = (Double)usageEvent.getAgreement().getValue("BASE_RATE", usageEvent.getWhenOccurred());
		return Money.dollars(usageEvent.getAmount().getAmount() * baseRate);
	}
}
