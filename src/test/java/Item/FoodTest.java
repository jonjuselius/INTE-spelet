package Item;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
	
	@Test
	void eatingFoodMakesItConsumed() {
		Food egg = new Egg();
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
	}
	
	@Test
	void eatingConsumedFoodThrowsISE() {
		Food egg = new Egg();
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
		assertThrows(IllegalStateException.class, () -> egg.consume());
	}
	
}