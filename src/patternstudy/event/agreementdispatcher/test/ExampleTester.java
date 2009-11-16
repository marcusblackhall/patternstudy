package patternstudy.event.agreementdispatcher.test;

import patternstudy.event.agreementdispatcher.AccountingEvent;
import patternstudy.event.agreementdispatcher.Customer;
import patternstudy.event.agreementdispatcher.EventList;
import patternstudy.event.agreementdispatcher.MultiplyByRatePR;
import patternstudy.event.agreementdispatcher.ServiceAgreement;
import patternstudy.event.agreementdispatcher.Usage;
import patternstudy.temporal.timepoint.DateTime;
import junit.framework.TestCase;

public class ExampleTester extends TestCase {

	public void testSimpleRule() {
		Customer mycroft = new Customer("Mycroft Homes");
		mycroft.setServiceAgreement(simpleAgreemneet());
		AccountingEvent usageEvent = new Usage(Unit.KWH.amount(50),
			DateTime.on(1999, 10, 1),
			DateTime.on(1999, 10, 15),
			mycroft);
		EventList eventList = new EventList();
		eventList.add(usageEvent);
		eventList.process();
		
		assertEquals(Money.dollars(500), mycroft.balanceFor(AccountType.BASE_USAGE));
		assertEquals(Money.dollars(0), mycroft.balanceFor(AccountType.SERVICE));
		assertEquals(Money.dollars(0), mycroft.balanceFor(AccountType.TAX));
	}
	
	private ServiceAgreement simpleAgreement() {
		ServiceAgreement result = new ServiceAgreement();
		result.setRate(10, DateTime.PAST);
		result.addPostingRule(EventType.USAGE,
				new MultiplyByRatePR(AccountType.BASE_USAGE, false),
				DateTime.on(1999, 10, 1));
		return result;
	}
}
