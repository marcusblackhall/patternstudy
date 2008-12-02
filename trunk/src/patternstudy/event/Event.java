package patternstudy.event;

import com.qihoo.ecp.lang.DateTime;

public interface Event<T extends EventType, S>
{
	T getType();
	S getSubject();
	DateTime getWhenOccurred();
	DateTime getWhenNoticed();
	
}
