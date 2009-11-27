package patternstudy.event.agreementdispatcher;

import patternstudy.accounting.account.AccountType;
import patternstudy.accounting.accountingentry.Entry;

public interface Subject {
	void addEntry(Entry entry, AccountType type);
	Subject getAdjuster();
	void reverseEntry(Entry entry);
	void process(AccountingEvent event);
}
