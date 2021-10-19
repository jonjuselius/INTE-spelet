package Jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameCharacters.Player;
import Races.Elf;
import Races.Human;
import Races.Ogre;

class JobTest {
	void knightConstructor() {

		Knight h = new Knight();
		assertEquals(7, h.getHealing());
		assertEquals(3, h.getMagic());
		assertEquals(10, h.getSwordSkill());
		assertEquals(400, h.getMaxMana());

	}

	@Test
	void healerConstructor() {
		Healer h = new Healer();
		assertEquals(10, h.getHealing());
		assertEquals(3, h.getMagic());
		assertEquals(7, h.getSwordSkill());
		assertEquals(350, h.getMaxMana());

	}

	@Test
	void magicianConstructor() {
		Magician h = new Magician();
		assertEquals(7, h.getHealing());
		assertEquals(10, h.getMagic());
		assertEquals(3, h.getSwordSkill());
		assertEquals(300, h.getMaxMana());

	}
	
//Tdd
	@Test
	void getHealedDependingOnYourOwnHealSkillShouldBeThreehundredTwentyForLevelOne() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
		c1.takeDamage(100);

		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(207, c1.getRemainingHealth());

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

		assertEquals(300, c1.getRemainingHealth());

	}
	
	@Test
	void takeDamageDependingOnYourSwordSkillAndStrengthShouldBeOneHundredTen() {

		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true);
		c1.takeDamageDependingOnYourSwordSkillAndStrength(100);

		assertEquals(120, c1.getRemainingHealth());

	}

}