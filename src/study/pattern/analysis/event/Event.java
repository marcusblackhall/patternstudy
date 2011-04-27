package patternstudy.event;

import patternstudy.temporal.timepoint.DateTime;

public interface Event<T extends EventType, S>
{
	T getType();
	S getSubject();
	DateTime getWhenOccurred();
	DateTime getWhenNoticed();
	
}
