package patternstudy.accounting.postingrule;

import patternstudy.accounting.AccountingException;

public class MissingPostingRuleException extends AccountingException {
	private static final long serialVersionUID = 1L;
	
	public MissingPostingRuleException() {
		super();
	}
	public MissingPostingRuleException(String s) {
		super(s);
	}

}
