package Inventory;

import Item.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
	
	@Test
	void newInventoryHasDefaultCapacity() {
		Inventory inventory = new Inventory();
		assertThat(inventory.getSlots().size(), is(equalTo(Inventory.CAPACITY)));
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
		assertThrows(IndexOutOfBoundsException.class, () -> {
			inventory.add(sword, -1);
		});
	}
	
	@Test
	void addingNewItemToEmptyInventoryOnPositionOverInventoryBoundsThrowsIOOBE() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			inventory.add(sword, Inventory.CAPACITY);
		});
	}
	
	@Test
	void addingNewItemToInventoryOnPositionWhereThereAlreadyExistsAnItemThrowsISE() {
		Inventory inventory = new Inventory();
		Item sword1 = new Sword();
		Item sword2 = new Sword();
		inventory.add(sword1, 2);
		assertThrows(IllegalStateException.class, () -> {
			inventory.add(sword2, 2);
		});
	}
	
	@Test
	void removingItemFromInventoryThatContainsThatItemActuallyRemovesThatItemFromInventory() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		Item wand = new Wand();
		Item egg = new Egg();
		Item shield = new Shield();
		Item ring = new Ring();
		inventory.add(sword);
		inventory.add(wand);
		inventory.add(egg);
		inventory.add(shield);
		inventory.add(ring);
		
		Item[] predicted = {sword, wand, egg, ring};
		inventory.remove(shield);
		Item[] actual = inventory.getItems().toArray(new Item[inventory.getItems().size()]);
		assertArrayEquals(predicted, actual);
	}
	
	@Test
	void removingItemFromInventoryThatDoesntContainThatItemThrowsIAE() {
		Inventory inventory = new Inventory();
		Item sword = new Sword();
		Item ring = new Ring();
		inventory.add(sword);
		
		assertFalse(inventory.contains(ring));
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.remove(ring);
		});
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
}