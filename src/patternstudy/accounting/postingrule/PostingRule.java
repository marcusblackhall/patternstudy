package patternstudy.accounting.postingrule;

import patternstudy.accounting.account.AccountType;
import patternstudy.accounting.accountingentry.Entry;
import patternstudy.accounting.accountingevent.AccountingEvent;
import patternstudy.accounting.accountingevent.TaxEvent;
import patternstudy.base.money.Money;

public abstract class PostingRule {
	private AccountType type; 
	private boolean isTaxable;
	
	protected PostingRule(AccountType type, boolean isTaxable) {
		this.type = type;
		this.isTaxable = isTaxable;
	}
	
	public static PostingRule amountFomula(double multiplier, Money fixedFee, AccountType type, boolean isTaxable) {
		return new AmountFomulaPR(multiplier, fixedFee, type, isTaxable);
	}
	public static PostingRule multiplyByRate(AccountType type, boolean isTaxable) {
		return new MultiplyByRatePR(type, isTaxable);
	}
	public static PostingRule poorCap(AccountType type, boolean isTaxable) {
		return new PoorCapPR(type, isTaxable);
	}
	
	public void process(AccountingEvent evt) {
		makeEntry(evt, calculateAmount(evt));
		if (isTaxable) 
			generateTax(evt);
	}
	private void generateTax(AccountingEvent evt) {
		new TaxEvent(evt, calculateAmount(evt)).process();
	}

	abstract protected Money calculateAmount(AccountingEvent evt);
	
	private void makeEntry(AccountingEvent evt, Money amount) {
		Entry newEntry = new Entry(amount, evt.getWhenNoticed());
		evt.getSubject().addEntry(newEntry, type);
		evt.addResultingEntry(newEntry);
	}
}
