package Inventory;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class InventoryTest {
	
	@Test
	void newInventoryHasDefaultCapacity() {
		Inventory inventory = new Inventory();
		assertThat(inventory.getSlots().size(), is(equalTo(Inventory.DEFAULT_CAPACITY)));
	}
	
	@Test
	void newInventoryHasZeroItems() {
		Inventory inventory = new Inventory();
		assertThat(inventory.getItems().size(), is(equalTo(0)));
	}
	
}