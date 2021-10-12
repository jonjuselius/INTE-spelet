package Equipment;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
	
	@Test
	void EC1NewSwordHasFullCondition() {
		Equipment sword = new Sword();
		assertEquals(Equipment.MAX_CONDITION, sword.getCondition());
	}
	
	@Test // Upprepning av samma testfall som ovan, fast med Hamcrest
	void EC1NewSwordHasFullConditionHamcrest() {
		Equipment sword = new Sword();
		assertThat(sword.getCondition(), is(equalTo(Equipment.MAX_CONDITION)));
	}
	
	@Test
	void EC2NewSwordWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		int condition = (Equipment.MAX_CONDITION + Equipment.MIN_CONDITION) / 2;
		Equipment sword = new Sword(condition);
		assertThat(sword.getCondition(), is(equalTo(condition)));
	}
	
	@Test
	void EC3NewSwordWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Equipment.MIN_CONDITION - 1));
	}
	
	@Test
	void EC4NewSwordWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Equipment.MAX_CONDITION + 1));
	}
}