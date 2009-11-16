package patternstudy.event.agreementdispatcher;

public class PostingRule {
	
	public void process(AccountingEvent evt) {
		makeEntry(evt, calculateAmount(evt));
		if (isTaxable) generateTax(evt);
	}
	
	abstract protected Money calculateAmount(AccountingEvent evt);
	
	private void makeEntry(AccountingEvent evt, Money amount) {
		Entry newEntry = new Entry(amount, evt.getWhenNoticed());
		evt.getSubject().addEntry(newEntry, type);
		evt.addResultingEntry(newEntry);
	}
}
