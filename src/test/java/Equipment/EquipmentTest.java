package Equipment;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
	
	@Test
	void newSwordHasFullCondition() {
		Equipment sword = new Sword();
		assertEquals(Equipment.MAX_CONDITION, sword.getCondition());
	}
	
	@Test
	void newSwordHasFullConditionHamcrest() {
		Equipment sword = new Sword();
		assertThat(sword.getCondition(), is(equalTo(Equipment.MAX_CONDITION)));
	}
}