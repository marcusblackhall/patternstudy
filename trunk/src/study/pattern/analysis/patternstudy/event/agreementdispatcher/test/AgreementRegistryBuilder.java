package patternstudy.event.agreementdispatcher.test;

import patternstudy.accounting.account.AccountType;
import patternstudy.accounting.postingrule.PostingRule;
import patternstudy.base.money.Money;
import patternstudy.base.unit.Unit;
import patternstudy.event.EventType;
import patternstudy.event.agreementdispatcher.AgreementRegistry;
import patternstudy.event.agreementdispatcher.ServiceAgreement;
import patternstudy.temporal.timepoint.DateTime;

public class AgreementRegistryBuilder {
	public void setUp(AgreementRegistry registry) {
		registry.register("lowPay", setUpLowPay());
		registry.register("regular", setUpRegular());
	}

	private ServiceAgreement setUpRegular() {
		ServiceAgreement result = new ServiceAgreement();
		result.registerValue("BASE_RATE")
			.put(DateTime.PAST, 10.0)
			.put(DateTime.on(1999, 12, 1), 12.0);
		
		result.addPostingRule(EventType.USAGE, 
				PostingRule.multiplyByRate(AccountType.BASE_USAGE, true),
				DateTime.on(1999, 10, 1));
		result.addPostingRule(EventType.SERVICE_CALL, 
				PostingRule.amountFomula(0.5, Money.dollars(10), AccountType.SERVICE, true),
				DateTime.on(1999, 10, 1));
		result.addPostingRule(EventType.SERVICE_CALL, 
				PostingRule.amountFomula(0.5, Money.dollars(15), AccountType.SERVICE, true),
				DateTime.on(1999, 12, 1));
		result.addPostingRule(EventType.TAX, 
				PostingRule.amountFomula(0.055, Money.dollars(0), AccountType.TAX, false),
				DateTime.on(1999, 12, 1));
		
		return result;
	}

	private ServiceAgreement setUpLowPay() {
		ServiceAgreement result = new ServiceAgreement();
		result.registerValue("BASE_RATE")
			.put(DateTime.PAST, 10.0);
		result.registerValue("CAP")
			.put(DateTime.PAST, Unit.KWH.amount(50))
			.put(DateTime.on(1999, 12, 1), Unit.KWH.amount(60));
		result.registerValue("REDUCED_RATE")
			.put(DateTime.PAST, 5.0);
		
		result.addPostingRule(EventType.USAGE, 
				PostingRule.poorCap(AccountType.BASE_USAGE, true),
				DateTime.on(1999, 10, 1));
		result.addPostingRule(EventType.SERVICE_CALL, 
				PostingRule.amountFomula(0, Money.dollars(10), AccountType.SERVICE, true),
				DateTime.on(1999, 10, 1));
		result.addPostingRule(EventType.TAX, 
				PostingRule.amountFomula(0.055, Money.dollars(0), AccountType.TAX, false),
				DateTime.on(1999, 12, 1));
		
		return result;
	}
}
