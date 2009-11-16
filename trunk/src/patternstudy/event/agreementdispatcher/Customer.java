package patternstudy.event.agreementdispatcher;

import java.util.Map;

public class Customer implements Subject {
	private ServiceAgreement serviceAgreement;
	
	private Map<AccountType, Account> accounts;
	private Map<AccountType, Account> savedRealAccounts;
	
	public Customer(String name) {
		_name = name;
		setUpAccounts();
	}
	
	public void process(AccountingEvent e) {
		serviceAgreement.process(e);
	}

	void setUpAccounts() {
		accounts = new HashMap<AccountType, Account>();
		for (AccountType type : AccountType.values())
			accounts.put(type, new Account(Currency.USD, type));
	}
	
	public Account accountFor(AccountType type) {
		assert accounts.containsKey(type);
		return accounts.get(type);
	}
	
	public void addEntry(Entry arg, AccountType type) {
		accountFor(type).post(arg);
	}
	
	public Money balanceFor(AccountType key) {
		return accountFor(key).balance();
	}
}
