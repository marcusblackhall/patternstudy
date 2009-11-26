package patternstudy.accounting.accountingtransaction;

import patternstudy.accounting.account.Account;
import patternstudy.accounting.accountingentry.Entry;
import patternstudy.base.money.Money;
import patternstudy.temporal.timepoint.DateTime;

public class AccountingTransaction {
	public AccountingTransaction(Money amount, Account from, Account to, DateTime date) {
		Entry fromEntry = new Entry(amount.negate(), date);
		from.addEntry(fromEntry);
		Entry toEntry = new Entry(amount, date);
		to.addEntry(toEntry);
	}

}
