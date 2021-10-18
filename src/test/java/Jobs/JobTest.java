package Jobs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameCharacters.Player;
import Races.Human;
import Races.Ogre;

class JobTest {
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
