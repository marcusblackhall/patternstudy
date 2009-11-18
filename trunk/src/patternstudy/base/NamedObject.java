package patternstudy.base;

public class NamedObject {
	protected String name = "noname";
	
	public NamedObject() {
	}
	public NamedObject(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
}
