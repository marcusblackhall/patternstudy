package patternstudy.accounting.account;

import java.util.Collection;
import java.util.Currency;
import java.util.HashSet;

import patternstudy.accounting.accountingentry.Entry;
import patternstudy.temporal.timepoint.DateTime;

/**
 * 
 * @url http://www.martinfowler.com/eaaDev/Account.html
 *
 */


public class Account {
	private Collection<Entry> entries = new HashSet<Entry>();
	private Currency currency;
	
	void addEntry(Money amount, DateTime date) {
		assert currency.equals(amount.currency());
		entries.add(new Entry(amount, date));
	}
	
	Money balance(DateRange period) {
		Money result = new Money(0, currency);
		for (Entry each : entries) {
			if (period.includes(each.date()))
				result = result.add(each.amount());
		}
		return result;
	}
	
	Money balance(DateTime date) {
		return balance(DateRuange.upTo(date));
	}
	
	Money balance() {
		return balance(DateTime.today());
	}
}
