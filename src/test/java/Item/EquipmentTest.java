package Item;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
	
	public static final Equipment DEFAULT_SWORD = new Sword();
	
	@Test
	void newSwordHasDefaultCondition() {
		assertThat(DEFAULT_SWORD.getCondition(), is(equalTo(Sword.DEFAULT_CONDITION)));
	}
	
	@Test
	void newSwordWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		int condition = (Sword.DEFAULT_CONDITION + Sword.DEFAULT_CONDITION) / 2;
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
		assertThat(DEFAULT_SWORD.isEquipped(), is(equalTo(false)));
		DEFAULT_SWORD.equip();
		assertThat(DEFAULT_SWORD.isEquipped(), is(equalTo(true)));
		DEFAULT_SWORD.unequip();
		assertThat(DEFAULT_SWORD.isEquipped(), is(equalTo(false)));
	}
	
	@Test
	void equippingEquippedSwordThrowsISE() {
		assertThat(DEFAULT_SWORD.isEquipped(), is(equalTo(false)));
		DEFAULT_SWORD.equip();
		assertThat(DEFAULT_SWORD.isEquipped(), is(equalTo(true)));
		assertThrows(IllegalStateException.class, DEFAULT_SWORD::equip);
	}
	
	@Test
	void unequippingUnequippedSwordThrowsISE() {
		assertThat(DEFAULT_SWORD.isEquipped(), is(equalTo(false)));
		assertThrows(IllegalStateException.class, DEFAULT_SWORD::unequip);
	}
	
	@Test
	void newSwordIsAWeapon() {
		assertThat(DEFAULT_SWORD.getType(), is(equalTo(Sword.TYPE)));
	}
	
}