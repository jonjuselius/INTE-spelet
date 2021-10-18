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
	
	public Item remove(Item item) {
		int position = -1;
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] == item) {
				position = i;
				break;
			}
		}
		if (position < 0) {
			throw new IllegalArgumentException("Item does not exist in inventory!");
		}
		return remove(position);
	}
	
	private Item remove(int position) {
		Item removedItem = null;
		if (position < 0 || position >= CAPACITY) {
			throw new IndexOutOfBoundsException("Position is out of inventory bounds!");
		}
		if (slots[position] == null) {
			throw new IllegalStateException("No item exists at the position!");
		}
		removedItem = slots[position];
		slots[position] = null;
		return removedItem;
	}
	
	public boolean contains(Item item) {
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] == item) {
				return true;
			}
		}
		return false;
	}
	
}
