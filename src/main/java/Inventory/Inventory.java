package Inventory;

import Item.*;

import java.util.*;

public class Inventory {
	
	public static final int CAPACITY = 100;
	private Item[] slots;
	
	public Inventory() {
		slots = new Item[CAPACITY];
	}
	
	public List<Item> getSlots() {
		return Collections.unmodifiableList(Arrays.asList(slots.clone()));
	}
	
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		for (Item item : slots) if (item != null) items.add(item);
		return Collections.unmodifiableList(items);
	}
	
	public void add(Item item) {
		int position = -1;
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] == null) {
				position = i;
				break;
			}
		}
		add(item, position);
	}
	
	public void add(Item item, int position) {
		if (position < 0 || position >= CAPACITY) {
			throw new IndexOutOfBoundsException();
		}
		if (slots[position] != null) {
			throw new IllegalStateException();
		}
		slots[position] = item;
	}
	
}
