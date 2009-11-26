package patternstudy.base.unit;

import patternstudy.base.NamedObject;
import patternstudy.base.quantity.Quantity;
import patternstudy.base.registry.Registry;

public class Unit extends NamedObject {
	public static Unit KWH = new Unit("kwh");
	
	public Unit(String name) {
		super(name);
	}
	
	public Quantity amount(double amount) {
		return new Quantity(amount, this);
	}
	
	public boolean equals(Unit unit) {
		return this == unit;
	}
	
	public static Unit get(String name) {
		return (Unit)Registry.get("Unit", name);
	}
	
	public Unit register() {
		Registry.add("Unit", this);
		return this;
	}
}
