package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Jobs.Magician;
import org.junit.jupiter.api.Test;
import Races.*;
import Map.*;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
	private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
	public static final Map MAP = MAP_GENERATOR.generate(1);
	Human human = new Human();
	Magician magician = new Magician();

	@Test
	void takeDamageReducesCorrectHealth() {
		Player p = new Player("Player1", human, magician, true, MAP);
		p.takeDamage(100);

		assertEquals(200, p.getRemainingHealth());
	}

	@Test
	void takeDamageKills() {
		Player p = new Player("Player1", human, magician, true, MAP);
		p.takeDamage(300);

		assertFalse(p.isAlive());
	}

	@Test
	void useManaReducesCorrectAmount() {
		Player p = new Player("Player1", human, magician, true, MAP);
		p.useMana(100);

		assertEquals(200, p.getRemainingMana());
	}

	@Test
	void getHealedIncreasesHealth() {
		Player p = new Player("Player1", human, magician, true, MAP);
//        p.setRemainingHealth(100);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}

	@Test
	void getHealedMoreThanMaxSetsHealthToMax() {
		Player p = new Player("Player1", human, magician, true, MAP);
//        p.setRemainingHealth(250);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}

	/////// Tdd Jasmyn////////////
	@Test
	void increaseIntelligenceFromWinningASpellByOne() {
		Player p = new Player("Player1", human, magician, true, MAP);
		p.increaseIntelligenceFromWinningASpell();

		assertEquals(23, p.getIntelligence());
	}

	// boundary value

	@Test
	void increaseIntelligenceFromWinningASpellByOneOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true, MAP);
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();
		p.increaseIntelligenceFromWinningASpell();

		assertEquals(35, p.getIntelligence());

	}

	// tdd Jas
	@Test
	void increaseStrengthFromWinningASpellByOne() {
		Player p = new Player("Player1", human, magician, true, MAP);
		p.increaseStrengthFromWinningASpell();

		assertEquals(23, p.getStrength());
	}

	// boundary value

	@Test
	void increaseStrengthFromWinningASpellByOneOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true, MAP);
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();
		p.increaseStrengthFromWinningASpell();

		assertEquals(35, p.getStrength());

	}

	@Test
	void increasingHealthOverMaxForOgreThrowsException() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, MAP);
		assertThrows(IllegalStateException.class, () -> {
			c1.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingHealthOverMaxForHumanThrowsException() {

		Player c2 = new Player("Emma", new Human(), new Knight(), true, MAP);
		assertThrows(IllegalStateException.class, () -> {
			c2.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingHealthOverMaxForElfThrowsException() {

		Player c3 = new Player("Oliver", new Elf(), new Knight(), true, MAP);
		assertThrows(IllegalStateException.class, () -> {
			c3.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingHealthUnderMax() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, MAP);
		c1.takeDamage(10);
		c1.increaseHealth(9);
		assertEquals(299, c1.getRemainingHealth());
	}

	//Tdd jas
	@Test
	void getHealedDependingOnYourOwnHealSkillShouldBeTwohundreedSeven() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, MAP);
		c1.takeDamage(100);

		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(207, c1.getRemainingHealth());

	}
	
	//Tdd jas
	@Test
	void getHealedDependingOnYourOwnHealSkillOverMaxLifePointsStaysAtMaxLifePoint() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Healer(), true, MAP);
		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(300, c1.getRemainingHealth());

	}
	
	//Tdd jas
	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeSixty() {

		Player p = new Player ("jasmyn", new Ogre(), new Knight(), true, MAP);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true, MAP);
		p.dealDamageDependingOnYourSwordSkillAndStrength(100, p1);

		assertEquals(60, p1.getRemainingHealth());

	}
	
	//Tdd jas
	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeZero() {

		Player p = new Player ("jasmyn", new Ogre(), new Knight(), true, MAP);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true, MAP);
		p.dealDamageDependingOnYourSwordSkillAndStrength(500, p1);

		assertEquals(0, p1.getRemainingHealth());

	}



	//Tdd jas
	@Test
	void elfLevelsTriesToLevelUpTooTwoButCant() {
		Player x = new Player("Jasmyn", new Elf(), new Knight(), true, MAP);
		x.levelsUp();

		assertEquals(1, x.getLevel());
	}
	
	//Tdd jas
	@Test
	void elfLevelsUpToLevelTwo() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, MAP);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();


		assertEquals(2, c1.getLevel());
	}
	
	//Tdd jas
	@Test
	void elfLevelsUpToLevelTwoStrengthIsThirteen() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, MAP);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		assertEquals(13, c1.getStrength());
	}
	
	
	//Tdd jas
	@Test
	void ogreLevelsTriesToLevelUpTwoTwoButCant() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, MAP);
		c1.levelsUp();

		assertEquals(1, c1.getLevel());
	}
	
	//Tdd jas
	@Test
	void ogreLevelsUpToLevelTwo() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, MAP);
		c1.increaseStrengthFromWinningASpell();;
		c1.levelsUp();

		assertEquals(2, c1.getLevel());
	}
	
	//Tdd jas
	@Test
	void ogreLevelsUpToLevelTwoIntelligenceIsThirteen() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, MAP);
		c1.increaseStrengthFromWinningASpell();;
		c1.levelsUp();;

		assertEquals(13, c1.getIntelligence());
	}
	
	//Tdd jas
	@Test
	void humanLevelsTriesToLevelUpToTwoButCant() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, MAP);
		c1.levelsUp();

		assertEquals(1, c1.getLevel());
	}
	
	//Tdd jas
	@Test
	void humanLevelsUpToLevelTwo() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, MAP);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();

		assertEquals(2, c1.getLevel());
	}
	//Tdd jas
	@Test
	void humanLevelsUpToLevelTwoWithIntelligenceNewStrengthIsTwentythree() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, MAP);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();

		assertEquals(23, c1.getStrength());
	}
	
	//Tdd jas
	@Test
	void humanLevelsUpToLevelTwoWithStrength() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, MAP);
		c1.increaseStrengthFromWinningASpell();;
		c1.levelsUp();

		assertEquals(2, c1.getLevel());
	}
	
	//Tdd jas
	@Test 
	void humanLevelsUpToLevelTwoWithStrengthNewStrengthIsTwentysix() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, MAP);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();

		assertEquals(26, c1.getStrength());
	}

	/*
	Test driven development for players walking on the map (Emma)
	 */
	@Test
	void playerMovesNorth() {

	}

	@Test
	void playerMovesSouth() {

	}

	@Test
	void playerMovesWest() {

	}

	@Test
	void playerMovesEast() {

	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapNorth() {

	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapSouth() {

	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapWest() {

	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapEast() {

	}

	@Test
	void IAEThrownWhenTryingToWalkDeadPlayer() {

	}

	@Test
	void humanCanMoveOnGrass() {
		Player human_player = new Player("Human", human, magician, true, MAP);
		MapPosition currentPos = human_player.getPosition();
		MapPosition grassPosToVisit = MAP.getMapTiles()[1][0];

		assertEquals(grassPosToVisit, human_player.moveEast());
	}

	@Test
	void humanCanMoveInWater() {
		Player human_player = new Player("Human", human, magician, true, MAP);
		MapPosition currentPos = human_player.getPosition();
		MapPosition waterPosToVisit = MAP.getMapTiles()[0][1];

		assertEquals(waterPosToVisit, human_player.moveNorth());
	}

	@Test
	void humanDiesInLava() {
		Player human_player = new Player("Human", human, magician, true, MAP);
		human_player.moveEast();
		human_player.moveNorth();

		assertEquals(false, human_player.isAlive());
	}

	@Test
	void ogreCanMoveOnGrass() {

	}

	@Test
	void ogreMovesOutOfWaterWhenInWater() {

	}

	@Test
	void ogreDiesInLava() {

	}

	@Test
	void elfCanMoveOnGrass() {

	}

	@Test
	void elfCanMoveOnWater() {

	}

	@Test
	void elfCanMoveOnLava() {

	}

}