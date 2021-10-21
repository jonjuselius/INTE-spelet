package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Jobs.Magician;
import org.junit.jupiter.api.Test;
import Races.*;

//import static org.junit.Assert.assertEquals;
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
	void increasingHealthOverMaxForElfDoesNotSurpassMaxHealthTwohundred() {
		Player c3 = new Player("Oliver", new Elf(), new Knight(), true);
		
		c3.increaseHealth(10);

		assertEquals(200, c3.getRemainingHealth());

	}

	@Test
	void increasingHealthUnderMaxtoTwohundrednintynine() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
		c1.takeDamage(10);
		c1.increaseHealth(9);
		assertEquals(299, c1.getRemainingHealth());
	}

	@Test
	void increaseIntelligenceFromWinningASpellByThree() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseIntelligenceFromWinningASpell();

		assertEquals(23, p.getIntelligence());
	}


	@Test
	void increaseIntelligenceFromWinningASpellByThreeOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();

		assertEquals(35, p.getIntelligence());

	}

	@Test
	void increaseStrengthFromWinningASpellByThree() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseStrengthFromWinningASpell();

		assertEquals(23, p.getStrength());
	}

	@Test
	void increaseStrengthFromWinningASpellByThreeOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true);
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();

		assertEquals(35, p.getStrength());

	}
	
	
	@Test
	void healDependingOnYourOwnHealSkillShouldBeTwohundreedSeven() {

		Player p = new Player("Jasmyn", new Ogre(), new Knight(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);

		p.takeDamage(100);

		p1.healDependingOnYourOwnHealSkill(p);

		assertEquals(210, p.getRemainingHealth());

	}

	@Test
	void healDependingOnYourOwnHealSkillOverMaxLifePointsStaysAtMaxLifePoint() {

		Player p = new Player("Jasmyn", new Ogre(), new Healer(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);

		p1.healDependingOnYourOwnHealSkill(p);

		assertEquals(300, p.getRemainingHealth());

	}
	
	@Test
	void healDependingOnYourOwnHealSkillCloseToMaxLifePointsStaysAtMaxLifePoint() {

		Player p = new Player("Jasmyn", new Ogre(), new Healer(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);
		
		p.takeDamage(1);

		p1.healDependingOnYourOwnHealSkill(p);

		assertEquals(300, p.getRemainingHealth());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeSixtyForOgreKnight() {

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);
		p.dealDamageDependingOnYourSwordSkillAndStrength(100, p1);

		assertEquals(60, p1.getRemainingHealth());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeZeroForAttackBiggerThanHealth() {

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);
		p.dealDamageDependingOnYourSwordSkillAndStrength(500, p1);

		assertEquals(0, p1.getRemainingHealth());

	}
	
	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeZeroForAttackEqualtoHealth() {

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);
		p.dealDamageDependingOnYourSwordSkillAndStrength(160, p1);

		assertEquals(0, p1.getRemainingHealth());

	}
	
	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthCharacterDiesAtZeroLife(){

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);
		p.dealDamageDependingOnYourSwordSkillAndStrength(160, p1);

		assertFalse(p1.isAlive());

	}
	
	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthCharacterisAlreadyDead(){

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true);
		
		p1.takeDamage(200);
		p.dealDamageDependingOnYourSwordSkillAndStrength(10, p1);

		assertFalse(p1.isAlive());
		

	}

	// Tdd jas
	@Test
	void humanLevelsTriesToLevelUpToLevelTwoButCant() {
		Player x = new Player("Jasmyn", new Human(), new Knight(), true);
		x.levelsUp();

		assertEquals(1, x.getLevel());
	}

	// Tdd jas
	@Test
	void humanLevelsUpTwoTimesFromLevelOneToLevelThreeFomIntelligence() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}
	
	@Test
	void humanLevelsUpTwoTimesFromLevelOneToLevelThreeFomStrength() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	@Test
	void humanLevelsUpToLevelThreeStrengthIsSixteen() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();

		assertEquals(26, c1.getStrength());
	}
	
	@Test
	void elfLevelsTriesToLevelUpToLevelTwoButCant() {
		Player x = new Player("Jasmyn", new Elf(), new Knight(), true);
		x.levelsUp();

		assertEquals(1, x.getLevel());
	}

	@Test
	void elfLevelsUpTwoTimesFromLevelOneToLevelThreeFomIntelligence() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}
	
	@Test
	void elfLevelsUpTwoTimesFromLevelOneToLevelThreeFomStrength() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	// Tdd jas
	@Test
	void elfLevelsUpToLevelThreeStrengthIsSixteen() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();

		assertEquals(16, c1.getStrength());
	}
	
	
	@Test
	void ogreLevelsTriesToLevelUpToLevelTwoButCant() {
		Player x = new Player("Jasmyn", new Ogre(), new Knight(), true);
		x.levelsUp();

		assertEquals(1, x.getLevel());
	}

	@Test
	void ogreLevelsUpTwoTimesFromLevelOneToLevelThreeFromIntelligence() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}
	
	@Test
	void ogreLevelsUpTwoTimesFromLevelOneToLevelThreeFromStrength() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	@Test
	void ogreLevelsUpToLevelThreeIntelligenceIsSixteen() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();

		assertEquals(16, c1.getIntelligence());
	}
	
	@Test 
	void HumanCanFlyAtLevelFive() {
		Player c1 = new Player("Jasmyn", new Human(), new Magician(), true);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.levelsUp();
		c1.levelsUp();


		assertTrue(c1.getIfCanFly());
	}
	
	@Test 
	void elfCanSwimAtLevelThree() {
		Player c1 = new Player("Jasmyn", new Elf(), new Magician(), true);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.levelsUp();
		c1.levelsUp();


		assertTrue(c1.getIfCanSwim());
	}
	
	@Test 
	void ogreCanSwimAtLevelThree() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Magician(), true);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.levelsUp();
		c1.levelsUp();


		assertTrue(c1.getIfCanSwim());
	}
}
	
	
