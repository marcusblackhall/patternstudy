package patternstudy.accounting.postingrule;

import patternstudy.accounting.account.AccountType;
import patternstudy.accounting.accountingevent.AccountingEvent;
import patternstudy.accounting.accountingevent.Usage;
import patternstudy.base.money.Money;
import patternstudy.base.quantity.Quantity;

public class PoorCapPR extends PostingRule {
	public PoorCapPR(AccountType type, boolean isTaxable) {
		super(type, isTaxable);
	}

	@Override
	protected Money calculateAmount(AccountingEvent evt) {
		Quantity amountUsed = ((Usage)evt).getAmount();
		Quantity usageLimit = (Quantity)evt.getAgreement().getValue("CAP", evt.getWhenOccurred());
		final double reduceRate = (Double)evt.getAgreement().getValue("REDUCED_RATE", evt.getWhenOccurred());
		Money result = amountUsed.isGreaterThan(usageLimit) ?
				Money.dollars(amountUsed.getAmount() * (Double)evt.getAgreement().getValue("BASE_RATE", evt.getWhenOccurred())) :
				Money.dollars(amountUsed.getAmount() * reduceRate);
				
		return result;
	}


}
