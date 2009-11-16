package patternstudy.event.agreementdispatcher;

public class Usage extends AccountingEvent {
	private Quantity amount;
	
	public Usage(Quantity amount, DateTime whenOccurred, DateTime whenNoticed, Customer customer) {
		super(EventType.USAGE, whenOccurred, whenNoticed, customer);
		this.amount = amount;
	}
	
	double getRate() {
		return ((Customer)getSubject()).getServiceAgreement().getRate(getWhenOccurred()); 
	}
}
