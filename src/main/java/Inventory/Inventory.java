package Inventory;

import Item.*;
import java.util.*;

public class Inventory {
	
	public static final int DEFAULT_CAPACITY = 100;
	private Item[] items;
	
	public Inventory() {
		items = new Item[DEFAULT_CAPACITY];
	}
	
	public List<Item> getItems() {
		return Collections.unmodifiableList(Arrays.asList(items));
	}
}
