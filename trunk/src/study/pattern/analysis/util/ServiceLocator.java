package patternstudy.util;

public interface ServiceLocator {

	<T> T getInstance(Class<T> serviceType);
	
}
