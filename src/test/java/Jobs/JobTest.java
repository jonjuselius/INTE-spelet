package Jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameCharacters.Player;
import Races.Elf;
import Races.Human;
import Races.Ogre;

class JobTest {
	void elfConstructor() {

		Knight h = new Knight();
		assertEquals(7, h.getHealing());
		assertEquals(3, h.getMagic());
		assertEquals(10, h.getSwordSkill());
		assertEquals(400, h.getMaxMana());

	}

	@Test
	void humanConstructor() {
		Healer h = new Healer();
		assertEquals(10, h.getHealing());
		assertEquals(3, h.getMagic());
		assertEquals(7, h.getSwordSkill());
		assertEquals(350, h.getMaxMana());

	}

	@Test
	void ogreConstructor() {
		Magician h = new Magician();
		assertEquals(7, h.getHealing());
		assertEquals(10, h.getMagic());
		assertEquals(3, h.getSwordSkill());
		assertEquals(300, h.getMaxMana());

	}
	
//Tdd
	@Test
	void getHealedDependingOnYourOwnHealSkillShouldBeThreehundredTwentyForLevelOne() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Healer(), true);
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(320, c1.getRemainingHealth());

	}

	@Test
	void getHealedDependingOnYourOwnHealSkillOverMaxLifePointsStaysAtMaxLifePoint() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Healer(), true);
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();
		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(400, c1.getRemainingHealth());

	}

}
