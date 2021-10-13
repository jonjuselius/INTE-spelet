package Item;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ItemTest {
	
	public static final Item NEW_SWORD = new Sword();
	public static final Item NEW_WAND = new Wand();
	public static final Item NEW_EGG = new Egg();
	
	@Test
	void newItemHasDefaultItemWeight() {
		assertThat(NEW_SWORD.getWeight(), is(equalTo(Sword.SWORD_WEIGHT)));
		assertThat(NEW_WAND.getWeight(), is(equalTo(Wand.WAND_WEIGHT)));
		assertThat(NEW_EGG.getWeight(), is(equalTo(Egg.EGG_WEIGHT)));
	}
	
	@Test
	void newItemHasDefaultItemValue() {
		assertThat(NEW_SWORD.getValue(), is(equalTo(Sword.SWORD_VALUE)));
		assertThat(NEW_WAND.getValue(), is(equalTo(Wand.WAND_VALUE)));
		assertThat(NEW_EGG.getValue(), is(equalTo(Egg.EGG_VALUE)));
	}
}