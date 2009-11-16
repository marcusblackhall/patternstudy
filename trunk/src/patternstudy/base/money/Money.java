package patternstudy.base.money;

import java.math.BigDecimal;
import java.util.Currency;

// http://www.google.com/codesearch/p?hl=en&sa=N&cd=1&ct=rc#DQyTOAr92ZM/trunk/generic-timeandmoney/src/main/java/com/domainlanguage/money/Money.java&q=timeandmoney
// http://sourceforge.net/projects/timeandmoney/files/
public class Money {
	private static final Currency RMB = Currency.getInstance("CNY");
	private static final Currency USD = Currency.getInstance("USD");
	private static final Currency EUR = Currency.getInstance("EUR");
	
	private BigDecimal amount;
	private Currency currency;
	
	public Money(BigDecimal amount, Currency currency) {
		if (amount.scale() != currency.getDefaultFractionDigits())
			throw new IllegalArgumentException("Scale of amount does not match currency");
		this.currency = currency;
		this.amount = amount;
	}
	
	
    BigDecimal getAmount() {
        return amount;
    }

    Currency getCurrency() {
        return currency;
    }
}
