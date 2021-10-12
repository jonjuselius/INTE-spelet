package Equipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
	
	@Test
	void newSwordHasFullCondition() {
		Equipment sword = new Sword();
		assertEquals(100, sword.getCondition());
	}
}