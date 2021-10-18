package Inventory;

import Item.*;

import java.util.*;

public class Inventory {
	
	public static final int CAPACITY = 100;
	private Item[] slots;
	
	public Inventory() {
		slots = new Item[CAPACITY];
	}
	
	public Inventory(Item... items) {
		if (items.length > CAPACITY) {
			throw new IllegalArgumentException("Too many items specified in constructor");
		}
		slots = new Item[CAPACITY];
		System.arraycopy(items, 0, slots, 0, items.length);
		//for (int i = 0; i < items.length; i++) {
		//	slots[i] = items[i];
		//}
	}
	
	public List<Item> getSlots() {
		return Collections.unmodifiableList(Arrays.asList(slots.clone()));
	}
	
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		for (Item item : slots) if (item != null) items.add(item);
		return Collections.unmodifiableList(items);
	}
	
	public int getItemSize() {
		List<Item> items = this.getItems();
		return items.size();
	}
	
	public int getSlotSize() {
		List<Item> slots = this.getSlots();
		return slots.size();
	}
	
	public void add(Item item) {
		if (this.getItemSize() == CAPACITY) {
			throw new IllegalArgumentException("Inventory is full!");
		}
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
			throw new IndexOutOfBoundsException("Position index is out of inventory boundaries");
		}
		if (slots[position] != null) {
			throw new IllegalStateException("There is already an item on this slot");
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
	
	public Item remove(int position) {
		if (position < 0 || position >= CAPACITY) {
			throw new IndexOutOfBoundsException("Position is out of inventory bounds!");
		}
		if (slots[position] == null) {
			throw new IllegalArgumentException("No item exists at the position!");
		}
		Item removedItem = slots[position];
		slots[position] = null;
		return removedItem;
	}
	
	public boolean contains(Item item) {
		for (Item slot : slots) {
			if (slot == item) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String output = "";
		output += this.getItems().toString();
		return output;
	}
}
