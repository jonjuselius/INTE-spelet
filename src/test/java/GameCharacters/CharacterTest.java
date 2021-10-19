package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Jobs.Magician;
import org.junit.jupiter.api.Test;
import Races.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

	Human human = new Human();
	Magician magician = new Magician();

	@Test
	void takeDamageReducesCorrectHealth() {
		Player p = new Player("Player1", human, magician, true);
		p.takeDamage(100);

		assertEquals(200, p.getRemainingHealth());
	}

	@Test
	void takeDamageKills() {
		Player p = new Player("Player1", human, magician, true);
		p.takeDamage(300);

		assertFalse(p.isAlive());
	}

	@Test
	void useManaReducesCorrectAmount() {
		Player p = new Player("Player1", human, magician, true);
		p.useMana(100);

		assertEquals(200, p.getRemainingMana());
	}

	@Test
	void getHealedIncreasesHealth() {
		Player p = new Player("Player1", human, magician, true);
//        p.setRemainingHealth(100);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}

	@Test
	void getHealedMoreThanMaxSetsHealthToMax() {
		Player p = new Player("Player1", human, magician, true);
//        p.setRemainingHealth(250);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}
	

	/////// Tdd Jasmyn////////////
	@Test
	void increaseIntelligenceFromWinningASpellByOne() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseIntelligenceFromWinningASpell();

		assertEquals(21, p.getIntelligence());
	}

	// boundary value

	@Test
	void increaseIntelligenceFromWinningASpellByOneOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();

		assertEquals(25, p.getIntelligence());

	}

	// tdd Jas
	@Test
	void increaseStrengthFromWinningASpellByOne() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseStrengthFromWinningASpell();

		assertEquals(21, p.getStrength());
	}

	// boundary value

	@Test
	void increaseStrengthFromWinningASpellByOneOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();

		assertEquals(25, p.getStrength());

	}

	@Test
	void increasingHealthOverMaxForOgreThrowsException() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
		assertThrows(IllegalStateException.class, () -> {
			c1.increaseHealth(100);
			;
		});
	}
	
	

	@Test
	void increasingHealthOverMaxForHumanThrowsException() {

		Player c2 = new Player("Emma", new Human(), new Knight(), true);
		assertThrows(IllegalStateException.class, () -> {
			c2.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingHealthOverMaxForElfThrowsException() {

		Player c3 = new Player("Oliver", new Elf(), new Knight(), true);
		assertThrows(IllegalStateException.class, () -> {
			c3.increaseHealth(100);
			;
		});
	}
	
	@Test
	void increasingHealthUnderMax() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
		c1.takeDamage(10);
		c1.increaseHealth(9);
		assertEquals(299, c1.getRemainingHealth());
		
		
	}//förCoverage

	// Tdd
	@Test
	void getHealedDependingOnYourOwnHealSkillShouldBeTwohundreedSeven() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
		c1.takeDamage(100);

		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(207, c1.getRemainingHealth());

	}

	@Test
	void getHealedDependingOnYourOwnHealSkillOverMaxLifePointsStaysAtMaxLifePoint() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Healer(), true);
		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(300, c1.getRemainingHealth());

	}

	@Test
	void takeDamageDependingOnYourSwordSkillAndStrengthShouldBeOneHundredTwenty() {

		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true);
		c1.takeDamageDependingOnYourSwordSkillAndStrength(100);

		assertEquals(120, c1.getRemainingHealth());

	}
	
	@Test
	void takeDamageDependingOnYourSwordSkillAndStrengthShouldBeZeroUnderHealthOne() {

		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true);
		c1.takeDamageDependingOnYourSwordSkillAndStrength(300);
		c1.takeDamageDependingOnYourSwordSkillAndStrength(10);
		assertEquals(0, c1.getRemainingHealth());

	}

}