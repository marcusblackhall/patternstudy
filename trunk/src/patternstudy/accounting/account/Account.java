package patternstudy.accounting.account;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import patternstudy.accounting.accountingentry.Entry;
import patternstudy.base.money.Money;
import patternstudy.base.range.DateRange;
import patternstudy.temporal.timepoint.DateTime;

/**
 * @url http://www.martinfowler.com/eaaDev/Account.html
 */
public class Account {
	private List<Entry> entries = new ArrayList<Entry>();
	private Currency currency;
	private AccountType type;

	public Account(Currency currency) {
		this(currency, null);
	}
	public Account(Currency currency, AccountType type) {
		this.currency = currency;
		this.type = type;
	}
	
	public void addEntry(Entry entry) {
		
	}
	void addEntry(Money amount, DateTime date) {
		ensureSameCurrency(amount);
		entries.add(new Entry(amount, date));
	}
	private void ensureSameCurrency(Money money) {
		if (!money.getCurrency().equals(this.currency))
			throw new IllegalArgumentException("New item has incompatable currency");
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
