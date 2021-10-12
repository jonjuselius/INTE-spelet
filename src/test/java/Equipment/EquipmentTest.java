package Equipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
	
	@Test
	void newSwordHasFullCondition() {
		Equipment sword = new Sword();
		assertEquals(Equipment.MAX_CONDITION, sword.getCondition());
	}
}