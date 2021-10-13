package Inventory;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
	
	@Test
	void newInventoryHasDefaultCapacity() {
		Inventory inventory = new Inventory();
		assertThat(inventory.getItems().size(), is(equalTo(100)));
	}
}