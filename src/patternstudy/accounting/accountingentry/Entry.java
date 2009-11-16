package patternstudy.accounting.accountingentry;

/**
 * 
 * @url http://www.martinfowler.com/eaaDev/AccountingEntry.html
 *
 */

public interface Entry {
	Entry newEntry(CostType costType, Project project, Money amount, Date date);
	CostType getCostType();
	Project getProject();
	Money getAmount();
	DateTime getBookingDate();
}
