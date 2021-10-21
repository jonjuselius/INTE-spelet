package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Jobs.Magician;
import Map.*;
import org.junit.jupiter.api.Test;
import Races.*;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

	private Human human = new Human();
	private Magician magician = new Magician();
	private Map testMap = createTestMap();

	private Map createTestMap() {
		Map testMap = new Map(2, 2);
		testMap.put(new MapPosition(0, 0, Terrain.GRASS), 0, 0);
		testMap.put(new MapPosition(0, 1, Terrain.WATER), 0, 1);
		testMap.put(new MapPosition(1, 0, Terrain.GRASS), 1, 0);
		testMap.put(new MapPosition(1, 1, Terrain.LAVA), 1, 1);
		return testMap;
	}

	@Test
	void takeDamageReducesCorrectHealth() {
		Player p = new Player("Player1", human, magician, true, testMap);
		p.takeDamage(100);

		assertEquals(200, p.getRemainingHealth());
	}

	@Test
	void takeDamageKills() {
		Player p = new Player("Player1", human, magician, true, testMap);
		p.takeDamage(300);

		assertFalse(p.isAlive());
	}

	@Test
	void useManaReducesCorrectAmount() {
		Player p = new Player("Player1", human, magician, true, testMap);
		p.useMana(100);

		assertEquals(200, p.getRemainingMana());
	}

	@Test
	void getHealedIncreasesHealth() {
		Player p = new Player("Player1", human, magician, true, testMap);
//        p.setRemainingHealth(100);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}

	@Test
	void getHealedMoreThanMaxSetsHealthToMax() {
		Player p = new Player("Player1", human, magician, true, testMap);
//        p.setRemainingHealth(250);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}

	/////// Tdd Jasmyn////////////
	@Test
	void increaseIntelligenceFromWinningASpellByOne() {
		Player p = new Player("Player1", human, magician, true, testMap);
		p.increaseIntelligenceFromWinningASpell();

		assertEquals(23, p.getIntelligence());
	}

	// boundary value

	@Test
	void increaseIntelligenceFromWinningASpellByOneOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true, testMap);
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
		Player p = new Player("Player1", human, magician, true, testMap);
		p.increaseStrengthFromWinningASpell();

		assertEquals(23, p.getStrength());
	}

	// boundary value

	@Test
	void increaseStrengthFromWinningASpellByOneOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true, testMap);
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

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, testMap);
		assertThrows(IllegalStateException.class, () -> {
			c1.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingHealthOverMaxForHumanThrowsException() {

		Player c2 = new Player("Emma", new Human(), new Knight(), true, testMap);
		assertThrows(IllegalStateException.class, () -> {
			c2.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingHealthOverMaxForElfThrowsException() {

		Player c3 = new Player("Oliver", new Elf(), new Knight(), true, testMap);
		assertThrows(IllegalStateException.class, () -> {
			c3.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingHealthUnderMax() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, testMap);
		c1.takeDamage(10);
		c1.increaseHealth(9);
		assertEquals(299, c1.getRemainingHealth());
	}

	// Tdd
	@Test
	void getHealedDependingOnYourOwnHealSkillShouldBeTwohundreedSeven() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, testMap);
		c1.takeDamage(100);

		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(207, c1.getRemainingHealth());

	}

	@Test
	void getHealedDependingOnYourOwnHealSkillOverMaxLifePointsStaysAtMaxLifePoint() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Healer(), true, testMap);
		c1.getHealedDependingOnYourOwnHealSkill();

		assertEquals(300, c1.getRemainingHealth());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeSixty() {

		Player p = new Player ("jasmyn", new Ogre(), new Knight(), true, testMap);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true, testMap);
		p.dealDamageDependingOnYourSwordSkillAndStrength(100, p1);

		assertEquals(60, p1.getRemainingHealth());

	}
	
	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeZero() {

		Player p = new Player ("jasmyn", new Ogre(), new Knight(), true, testMap);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true, testMap);
		p.dealDamageDependingOnYourSwordSkillAndStrength(500, p1);

		assertEquals(0, p1.getRemainingHealth());

	}

	// tdd
	@Test
	void elfLevelsUpToLevelTwo() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, testMap);
		c1.increaseIntelligenceFromWinningASpell();
		c1.elfLevelsUp();

		assertEquals(2, c1.getLevel());
	}

	@Test
	void elfLevelsUpToLevelTwoStrengthIsThirteen() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, testMap);
		c1.increaseIntelligenceFromWinningASpell();
		c1.elfLevelsUp();

		assertEquals(13, c1.getStrength());
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
		Player human_player = new Player("Human", human, magician, true, testMap);
		MapPosition currentPos = human_player.getPosition();
		MapPosition grassPosToVisit = testMap.getMapTiles()[1][0];

		assertEquals(grassPosToVisit, human_player.moveEast());
	}

	@Test
	void humanCanMoveInWater() {
		Player human_player = new Player("Human", human, magician, true, testMap);
		MapPosition currentPos = human_player.getPosition();
		MapPosition waterPosToVisit = testMap.getMapTiles()[0][1];

		assertEquals(waterPosToVisit, human_player.moveNorth());
	}

	@Test
	void humanDiesInLava() {
		Player human_player = new Player("Human", human, magician, true, testMap);
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