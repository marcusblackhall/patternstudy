package patternstudy.accounting.postingrule;

import patternstudy.accounting.account.AccountType;
import patternstudy.accounting.accountingevent.AccountingEvent;
import patternstudy.accounting.accountingevent.MonetaryEvent;
import patternstudy.base.money.Money;

public class AmountFomulaPR extends PostingRule {
	private double multiplier;
	private Money fixedFee;
	
	public AmountFomulaPR(double multiplier, Money fixedFee, AccountType type, boolean isTaxable) {
		super(type, isTaxable);
		this.multiplier = multiplier;
		this.fixedFee = fixedFee;
	}
	
	@Override
	protected Money calculateAmount(AccountingEvent evt) {
		Money eventAmount = ((MonetaryEvent)evt).getAmount();
		return eventAmount.multiply(multiplier).add(fixedFee);
	}

}
