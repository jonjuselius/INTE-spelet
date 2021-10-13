package Item;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
	
	@Test
	void newSwordHasDefaultSwordWeight() {
		Item sword = new Sword();
		assertThat(sword.getWeight(), is(equalTo(Sword.SWORD_WEIGHT)));
	}
	
	@Test
	void newSwordHasDefaultSwordValue() {
		Item sword = new Sword();
		assertThat(sword.getValue(), is(equalTo(Sword.SWORD_VALUE)));
	}
}