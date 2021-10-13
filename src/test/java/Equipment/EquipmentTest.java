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
	
	@Test // Same as above, but with Hamcrest
	void newSwordHasFullConditionHamcrest() {
		Equipment sword = new Sword();
		assertThat(sword.getCondition(), is(equalTo(Equipment.MAX_CONDITION)));
	}
	
	@Test
	void newSwordWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		int condition = (Equipment.MAX_CONDITION + Equipment.MIN_CONDITION) / 2;
		Equipment sword = new Sword(condition);
		assertThat(sword.getCondition(), is(equalTo(condition)));
	}
	
	@Test
	void newSwordWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Equipment.MIN_CONDITION - 1));
	}
	
	@Test
	void newSwordWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Equipment.MAX_CONDITION + 1));
	}
	
	@Test
	void equippingSwordMakesItEquipped() {
		Equipment sword = new Sword();
		assertThat(sword.isEquipped(), is(equalTo(false)));
		sword.equip();
		assertThat(sword.isEquipped(), is(equalTo(true)));
	}
	
	@Test
	void unequippingSwordMakesItUnequipped() {
		Equipment sword = new Sword();
		assertThat(sword.isEquipped(), is(equalTo(false)));
		sword.equip();
		assertThat(sword.isEquipped(), is(equalTo(true)));
		sword.unequip();
		assertThat(sword.isEquipped(), is(equalTo(false)));
	}
	
	@Test
	void equippingEquippedSwordThrowsISE() {
		Equipment sword = new Sword();
		assertThat(sword.isEquipped(), is(equalTo(false)));
		sword.equip();
		assertThat(sword.isEquipped(), is(equalTo(true)));
		assertThrows(IllegalStateException.class, () -> sword.equip());
	}
	
	@Test
	void unequippingUnequippedSwordThrowsISE() {
		Equipment sword = new Sword();
		assertThat(sword.isEquipped(), is(equalTo(false)));
		assertThrows(IllegalStateException.class, () -> sword.unequip());
	}
	
}