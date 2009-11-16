package patternstudy.event.agreementdispatcher;

public class MultiplyByRatePR extends PostingRule {
	
	public MultiplyByRatePR(AccountType type, boolean isTaxable) {
		super(type, isTaxable);
	}
	
	protected Money calculateAmount(AccountingEvent evt) {
		Usage usageEvent = (Usage)evt;
		return Money.dollars(usageEvent.getAmount().getAmount() *  usageEvent.getRate());
	}
}
