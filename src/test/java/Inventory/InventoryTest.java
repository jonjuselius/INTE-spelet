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
	private Item[] defaultItemset = {new Sword(), new Wand(), new Potion(), new Shield(), new Ring()};
	private Inventory defaultInventory = new Inventory(defaultItemset);
	private Item[] emptyItemset = {};
	private Inventory emptyInventory = new Inventory();
	
	@Test
	void emptyInventoryHasItemSizeEqualToZero() {
		MatcherAssert.assertThat(emptyInventory.getItemSize(), is(equalTo(0)));
	}
	
	@Test
	void fullInventoryHasItemSizeEqualToMaximumCapacity() {
		Item[] swords = new Item[Inventory.CAPACITY];
		for (int i = 0; i < swords.length; i++) {
			swords[i] = new Sword();
		}
		Inventory fullInventory = new Inventory(swords);
		MatcherAssert.assertThat(fullInventory.getItemSize(), is(equalTo(Inventory.CAPACITY)));
	}
	
	@Test
	void inventoryFilledWithExampleItemsetHasInventorySizeEqualToSizeOfExampleItemset() {
		MatcherAssert.assertThat(defaultInventory.getItemSize(), is(equalTo(defaultItemset.length)));
	}
	
	@Test
	void inventoryConstructorWithEmptyItemsetHasItemSizeEqualToZero() {
		MatcherAssert.assertThat(new Inventory(emptyItemset).getItemSize(), is(equalTo(0)));
	}
	
	@Test
	void inventoryConstructorWithTooManyItemsThrowsIAE() {
		Item[] swords = new Item[Inventory.CAPACITY];
		for (int i = 0; i < swords.length; i++) {
			swords[i] = new Sword();
		}
		Inventory fullInventory = new Inventory(swords);
		Item[] itemset = fullInventory.getItems().toArray(new Item[fullInventory.getItemSize() + 1]);
		itemset[itemset.length - 1] = new Sword();
		assertThrows(IllegalArgumentException.class, () -> new Inventory(itemset));
	}
	
	@Test
	void inventorySlotSizeReturnsDefaultInventoryCapacity() {
		assertThat(defaultInventory.getSlotSize(), is(equalTo(Inventory.CAPACITY)));
	}
	
	@Test
	void inventoryConstructorWithDefaultItemsetsCreatesInventoryWithItemsEqualToItemsInTheDefaultItemsets() {
		Item[] actualItems = defaultInventory.getItems().toArray(new Item[defaultInventory.getItemSize()]);
		assertArrayEquals(actualItems, defaultItemset);
	}
	
	@Test
	void addingItemToFullInventoryThrowsIAE() {
		Item[] swords = new Item[Inventory.CAPACITY];
		for (int i = 0; i < swords.length; i++) {
			swords[i] = new Sword();
		}
		Inventory fullInventory = new Inventory(swords);
		assertThrows(IllegalArgumentException.class, () -> fullInventory.add(new Sword()));
	}
	
	@Test
	void emptyInventoryHasDefaultCapacity() {
		MatcherAssert.assertThat(emptyInventory.getSlots().size(), is(equalTo(Inventory.CAPACITY)));
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
		Item potion = new Potion();
		Item shield = new Shield();
		Item ring = new Ring();
		inventory.add(sword);
		inventory.add(wand);
		inventory.add(potion);
		inventory.add(shield);
		inventory.add(ring);
		
		Item[] predicted = {sword, wand, potion, ring};
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
	
	@Test
	void addingItemToInventoryThatIsAlreadyOwnedThrowsISE() {
		Inventory inventory = new Inventory();
		Sword sword = new Sword();
		inventory.add(sword);
		assertThrows(IllegalStateException.class, () -> {
			inventory.add(sword);
		});
	}
}