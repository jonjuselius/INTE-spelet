package Inventory;

import Item.*;

import java.util.*;

public class Inventory {
	
	public static final int DEFAULT_CAPACITY = 100;
	private Item[] slots;
	
	public Inventory() {
		slots = new Item[DEFAULT_CAPACITY];
	}
	
	public List<Item> getSlots() {
		return Collections.unmodifiableList(Arrays.asList(slots));
	}
	
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		for (Item item : slots) if (item != null) items.add(item);
		return Collections.unmodifiableList(items);
	}
	
}
