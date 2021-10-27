package Inventory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.hamcrest.MatcherAssert;
import Item.Item;
import Item.Sword;
import Item.Wand;
import Item.Potion;
import Item.Shield;
import Item.Ring;

class InventoryTest {
	public static final Item[] DEFAULT_ITEMSET = {new Sword(), new Wand(), new Potion(), new Shield(), new Ring()};
	public static final Inventory DEFAULT_INVENTORY = new Inventory(DEFAULT_ITEMSET);
	public static final Item[] EMPTY_ITEMSET = {};
	public static final Inventory EMPTY_INVENTORY = new Inventory();
	public static Inventory FULL_INVENTORY;
	
	@BeforeAll
	static void beforeAll() {
		Item[] swords = new Item[Inventory.CAPACITY];
		for (int i = 0; i < swords.length; i++) {
			swords[i] = new Sword();
		}
		FULL_INVENTORY = new Inventory(swords);
	}
	
	@Test
	void emptyInventoryHasItemSizeEqualToZero() {
		MatcherAssert.assertThat(EMPTY_INVENTORY.getItemSize(), is(equalTo(0)));
	}
	
	@Test
	void fullInventoryHasItemSizeEqualToMaximumCapacity() {
		MatcherAssert.assertThat(FULL_INVENTORY.getItemSize(), is(equalTo(Inventory.CAPACITY)));
	}
	
	@Test
	void inventoryFilledWithExampleItemsetHasInventorySizeEqualToSizeOfExampleItemset() {
		MatcherAssert.assertThat(DEFAULT_INVENTORY.getItemSize(), is(equalTo(DEFAULT_ITEMSET.length)));
	}
	
	@Test
	void inventoryConstructorWithEmptyItemsetHasItemSizeEqualToZero() {
		MatcherAssert.assertThat(new Inventory(EMPTY_ITEMSET).getItemSize(), is(equalTo(0)));
	}
	
	@Test
	void inventoryConstructorWithTooManyItemsThrowsIAE() {
		Item[] itemset = FULL_INVENTORY.getItems().toArray(new Item[FULL_INVENTORY.getItemSize() + 1]);
		itemset[itemset.length - 1] = new Sword();
		assertThrows(IllegalArgumentException.class, () -> new Inventory(itemset));
	}
	
	@Test
	void inventorySlotSizeReturnsDefaultInventoryCapacity() {
		assertThat(DEFAULT_INVENTORY.getSlotSize(), is(equalTo(Inventory.CAPACITY)));
	}
	
	@Test
	void inventoryConstructorWithDefaultItemsetsCreatesInventoryWithItemsEqualToItemsInTheDefaultItemsets() {
		Item[] actualItems = DEFAULT_INVENTORY.getItems().toArray(new Item[DEFAULT_INVENTORY.getItemSize()]);
		assertArrayEquals(actualItems, DEFAULT_ITEMSET);
	}
	
	@Test
	void addingItemToFullInventoryThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> FULL_INVENTORY.add(new Sword()));
	}
	
	@Test
	void emptyInventoryHasDefaultCapacity() {
		MatcherAssert.assertThat(EMPTY_INVENTORY.getSlots().size(), is(equalTo(Inventory.CAPACITY)));
	}
	
	@Test
	void newInventoryHasZeroItems() {
		Inventory inventory = new Inventory();
		assertThat(inventory.getItems().size(), is(equalTo(0)));
	}
	
	@Test
	void addingNewItemIncreasesAmountOfItemsInInventoryByOne() {
		Inventory inventory = new Inventory();
		int nBefore = inventory.getItems().size();
		assertThat(nBefore, is(equalTo(0)));
		inventory.add(new Sword());
		int nAfter = inventory.getItems().size();
		assertThat(nAfter, is(equalTo(nBefore + 1)));
	}
	
	@Test
	void addingNewItemToEmptyInventoryPutsInOnFirstSlot() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		Item[] expected = inventory.getSlots().toArray(new Item[Inventory.CAPACITY]);
		expected[0] = sword;
		inventory.add(sword);
		assertArrayEquals(expected, inventory.getSlots().toArray(new Item[Inventory.CAPACITY]));
	}
	
	@Test
	void addingNewItemToEmptyInventoryOnPositionOnePutsInOnSecondSlot() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		Item[] expected = inventory.getSlots().toArray(new Item[Inventory.CAPACITY]);
		expected[1] = sword;
		inventory.add(sword, 1);
		assertArrayEquals(expected, inventory.getSlots().toArray(new Item[Inventory.CAPACITY]));
	}
	
	@Test
	void addingNewItemToEmptyInventoryOnPositionUnderInventoryBoundsThrowsIOOBE() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		assertThrows(IndexOutOfBoundsException.class, () -> inventory.add(sword, -1));
	}
	
	@Test
	void addingNewItemToEmptyInventoryOnPositionOverInventoryBoundsThrowsIOOBE() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		assertThrows(IndexOutOfBoundsException.class, () -> inventory.add(sword, Inventory.CAPACITY));
	}
	
	@Test
	void addingNewItemToInventoryOnPositionWhereThereAlreadyExistsAnItemThrowsISE() {
		Inventory inventory = new Inventory();
		Item sword1 = new Sword();
		Item sword2 = new Sword();
		inventory.add(sword1, 2);
		assertThrows(IllegalStateException.class, () -> inventory.add(sword2, 2));
	}
	
	@Test
	void removingItemFromInventoryThatContainsThatItemActuallyRemovesThatItemFromInventory() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		Item wand = new Wand();
		Item egg = new Potion();
		Item shield = new Shield();
		Item ring = new Ring();
		inventory.add(sword);
		inventory.add(wand);
		inventory.add(egg);
		inventory.add(shield);
		inventory.add(ring);
		
		Item[] predicted = {sword, wand, egg, ring};
		inventory.remove(shield);
		Item[] actual = inventory.getItems().toArray(new Item[inventory.getItemSize()]);
		assertArrayEquals(predicted, actual);
	}
	
	@Test
	void removingItemFromInventoryThatDoesntContainThatItemThrowsIAE() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		Item ring = new Ring();
		inventory.add(sword);
		
		assertFalse(inventory.contains(ring));
		assertThrows(IllegalArgumentException.class, () -> inventory.remove(ring));
	}
	
	@Test
	void addingShieldToInventoryMakesInventoryContainThatShield() {
		Inventory inventory = new Inventory();
		Item shield = new Shield();
		inventory.add(shield);
		assertTrue(inventory.contains(shield));
	}
	
	@Test
	void inventoryContainingARingDoesNotContainAnotherRing() {
		Inventory inventory = new Inventory();
		Item ring = new Ring();
		inventory.add(ring);
		assertTrue(inventory.contains(ring));
		Item anotherRing = new Ring();
		assertFalse(inventory.contains(anotherRing));
	}
	
	@Test
	void removingItemFromInventoryOnNegativePositionThrowsIOOBE() {
		Inventory inventory = new Inventory();
		inventory.add(new Wand());
		assertThrows(IndexOutOfBoundsException.class, () -> inventory.remove(-1));
	}
	
	@Test
	void removingItemFromInventoryOnPositionOverInventoryPositionBoundaryThrowsIOOBE() {
		Inventory inventory = new Inventory();
		inventory.add(new Wand());
		assertThrows(IndexOutOfBoundsException.class, () -> inventory.remove(Inventory.CAPACITY));
	}
	
	@Test
	void removingItemFromInventoryOnPositionWhereNoItemExistsThrowsIAE() {
		Inventory inventory = new Inventory();
		inventory.add(new Wand());
		assertThrows(IllegalArgumentException.class, () -> inventory.remove(1));
	}
}