package Inventory;

import Item.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inventory {
	public static final int CAPACITY = 100;
	private Item[] slots = new Item[CAPACITY];
	
	public Inventory() {
		this(new Item[0]);
	}
	
	public Inventory(Item... items) {
		if (items.length > CAPACITY) {
			throw new IllegalArgumentException("Too many items specified in constructor");
		}
		System.arraycopy(items, 0, slots, 0, items.length);
	}
	
	public List<Item> getSlots() {
		return Collections.unmodifiableList(Arrays.asList(slots.clone()));
	}
	
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		for (Item item : slots) {
			if (item != null) {
				items.add(item);
			}
		}
		return Collections.unmodifiableList(items);
	}
	
	public int getItemSize() {
		return this.getItems().size();
	}
	
	public int getSlotSize() {
		return this.getSlots().size();
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
		if (item.isOwned()) {
			throw new IllegalStateException("The item is already owned!");
		}
		if (isFull()) {
			throw new IllegalArgumentException("Inventory is full!");
		}
		if (position < 0 || position >= CAPACITY) {
			throw new IndexOutOfBoundsException("Position index is out of inventory boundaries");
		}
		if (slots[position] != null) {
			throw new IllegalStateException("There is already an item on this slot");
		}
		slots[position] = item;
		item.setOwned(true);
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
		removedItem.setOwned(false);
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
	
	public boolean hasAvailableSpace() {
		return getItemSize() < CAPACITY;
	}
	
	public boolean isFull() {
		return !hasAvailableSpace();
	}
}
