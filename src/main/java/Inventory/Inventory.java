package Inventory;

import Item.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inventory {
	/**
	 * Inventory represents the storage of items that a character has. The purpose
	 * of an inventory is to keep track of which items a character has stored.
	 *
	 * Every character has one inventory. An inventory consists of several items, stored
	 * on different slots. A slot represents a location in the inventory where an item can
	 * be added to or removed from. The default capacity of an inventory is 100 slots. Besides
	 * adding and removing items to and from the inventory, there is also possible to check if
	 * the inventory contains a specific item. Some additional functionality include adding
	 * an item to a predefined position, as long as this position is available and not occupied
	 * by another item, and removing an item from a specified position, which is only possible
	 * if there actually exists an item on that position that actually can be removed.
	 *
	 * CAPACITY: How many slots a default inventory has.
	 * slots: Available locations in the inventory where items can be stored, one item per slot.
	 * add: Adds an item to a slot. The position can be specified in a related method.
	 * remove: Removes an item from a slot, and returns it. The position can be specified in a related method.
	 * contains: Checks if the inventory contains an item.
	 */
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
