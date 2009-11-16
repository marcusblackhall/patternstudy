package patternstudy.event.agreementdispatcher;

public class EventList {

	public void process() {
		for (AccountingEvent event : unprocessedEvents()) {
			try {
				event.process();
			} catch (Exception e) {
				if (shouldOnlyLogProcessingErrors) logProcessingError(event, e);
				else throw new RuntimeException(e);
			}
		}
	}
	
}
