package GameCharacters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import Jobs.Healer;
import Jobs.Knight;
import Jobs.Magician;
import Races.*;
import Map.*;

class CharacterTest {
	private GameMap testMap = createTestMap();
	private Human human = new Human();
	private Magician magician = new Magician();
	private GameMapPosition defaultPosition = testMap.getMapTiles()[0][0];

	private GameMap createTestMap() {
		GameMap map = new GameMap(2, 2);
		GameMapPosition firstPos = new GameMapPosition(0, 0);
		firstPos.setTerrain(Terrain.GRASS);
		GameMapPosition secondPos = new GameMapPosition(0, 1);
		secondPos.setTerrain(Terrain.WATER);
		GameMapPosition thirdPos = new GameMapPosition(1, 0);
		thirdPos.setTerrain(Terrain.GRASS);
		GameMapPosition fourthPos = new GameMapPosition(1, 1);
		fourthPos.setTerrain(Terrain.LAVA);

		firstPos.setNeighbors(null, thirdPos, secondPos, null);
		secondPos.setNeighbors(null, fourthPos, null, firstPos);
		thirdPos.setNeighbors(firstPos, null, fourthPos, null);
		fourthPos.setNeighbors(secondPos, null, null, thirdPos);

		map.putTileOnMap(firstPos, firstPos.getXPos(), firstPos.getYPos());
		map.putTileOnMap(secondPos, secondPos.getXPos(), secondPos.getYPos());
		map.putTileOnMap(thirdPos, thirdPos.getXPos(), thirdPos.getYPos());
		map.putTileOnMap(fourthPos, fourthPos.getXPos(), fourthPos.getYPos());
		return map;
	}

	@Test
	void takeDamageReducesCorrectHealth() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
		p.takeDamage(100);

		assertEquals(200, p.getRemainingHealth());
	}

	@Test
	void takeDamageKills() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
		p.takeDamage(300);

		assertFalse(p.isAlive());
	}

	@Test
	void useManaReducesCorrectAmount() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
		p.useMana(100);

		assertEquals(200, p.getRemainingMana());
	}

	@Test
	void getHealedIncreasesHealth() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
//        p.setRemainingHealth(100);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}

	@Test
	void getHealedMoreThanMaxSetsHealthToMax() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
//        p.setRemainingHealth(250);
		p.getHealed(100);

		assertEquals(300, p.getRemainingHealth());
	}

/////// Tdd Jasmyn////////////

	@Test
	void increasingHealthOverMaxForElfDoesNotSurpassMaxHealthTwohundred() {
		Player c3 = new Player("Oliver", new Elf(), new Knight(), true, defaultPosition);

		c3.increaseHealth(10);

		assertEquals(200, c3.getRemainingHealth());

	}

	@Test
	void increasingHealthUnderMaxtoTwohundrednintynine() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		c1.takeDamage(10);
		c1.increaseHealth(9);
		assertEquals(299, c1.getRemainingHealth());
	}

	@Test

	void increaseIntelligenceFromWinningASpellByThree() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);

		p.increaseIntelligenceFromWinningASpell();

		assertEquals(23, p.getIntelligence());
	}

	@Test
	void increaseIntelligenceFromWinningASpellByThreeOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
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
		Player p = new Player("Player1", human, magician, true, defaultPosition);
		p.increaseStrengthFromWinningASpell();

		assertEquals(23, p.getStrength());
	}

	@Test

	void increaseStrengthFromWinningASpellByThreeOverMethodBoundary() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
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

		Player p = new Player("Jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);

		p.takeDamage(100);

		p1.healDependingOnYourOwnHealSkill(p);

		assertEquals(210, p.getRemainingHealth());

	}

	@Test
	void healDependingOnYourOwnHealSkillOverMaxLifePointsStaysAtMaxLifePoint() {

		Player p = new Player("Jasmyn", new Ogre(), new Healer(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);

		p1.healDependingOnYourOwnHealSkill(p);

		assertEquals(300, p.getRemainingHealth());

	}

	@Test
	void healDependingOnYourOwnHealSkillCloseToMaxLifePointsStaysAtMaxLifePoint() {

		Player p = new Player("Jasmyn", new Ogre(), new Healer(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);

		p.takeDamage(1);

		p1.healDependingOnYourOwnHealSkill(p);

		assertEquals(300, p.getRemainingHealth());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeSixtyForOgreKnight() {

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);
		p.dealDamageDependingOnYourSwordSkillAndStrength(100, p1);

		assertEquals(60, p1.getRemainingHealth());
	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeZeroForAttackBiggerThanHealth() {

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);
		p.dealDamageDependingOnYourSwordSkillAndStrength(500, p1);

		assertEquals(0, p1.getRemainingHealth());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthShouldBeZeroForAttackEqualtoHealth() {

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);
		p.dealDamageDependingOnYourSwordSkillAndStrength(160, p1);

		assertEquals(0, p1.getRemainingHealth());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthCharacterDiesAtZeroLife(){
        Player p = new Player ("jasmyn", new Ogre(), new Knight(), true, defaultPosition);
        Player p1 = new Player ("Michael", new Elf(), new Healer(), true, defaultPosition);
		p.dealDamageDependingOnYourSwordSkillAndStrength(160, p1);

		assertFalse(p1.isAlive());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthCharacterisAlreadyDead(){

		Player p = new Player ("jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true, defaultPosition);
		
		p1.takeDamage(200);
		p.dealDamageDependingOnYourSwordSkillAndStrength(10, p1);

		assertFalse(p1.isAlive());
		

	}

	// Tdd jas
	@Test
	void humanLevelsTriesToLevelUpToLevelTwoButCant() {
		Player x = new Player("Jasmyn", new Human(), new Knight(), true, defaultPosition);

		x.levelsUp();

		assertEquals(1, x.getLevel());
	}

	// Tdd jas
	@Test
	void humanLevelsUpTwoTimesFromLevelOneToLevelThreeFomIntelligence() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, defaultPosition);

		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	@Test

	void humanLevelsUpTwoTimesFromLevelOneToLevelThreeFomStrength() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	@Test
	void humanLevelsUpToLevelThreeStrengthIsSixteen() {
		Player c1 = new Player("Jasmyn", new Human(), new Knight(), true, defaultPosition);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();

		assertEquals(26, c1.getStrength());
	}

	@Test
	void elfLevelsTriesToLevelUpToLevelTwoButCant() {
		Player x = new Player("Jasmyn", new Elf(), new Knight(), true, defaultPosition);
		x.levelsUp();

		assertEquals(1, x.getLevel());
	}

	@Test
	void elfLevelsUpTwoTimesFromLevelOneToLevelThreeFomIntelligence() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, defaultPosition);
		c1.increaseIntelligenceFromWinningASpell();


		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	@Test
	void elfLevelsUpTwoTimesFromLevelOneToLevelThreeFomStrength() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	// Tdd jas
	@Test
	void elfLevelsUpToLevelThreeStrengthIsSixteen() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, defaultPosition);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();

		assertEquals(16, c1.getStrength());
	}

	@Test
	void ogreLevelsTriesToLevelUpToLevelTwoButCant() {
		Player x = new Player("Jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		x.levelsUp();

		assertEquals(1, x.getLevel());
	}

	@Test
	void ogreLevelsUpTwoTimesFromLevelOneToLevelThreeFromIntelligence() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();

		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	@Test
	void ogreLevelsUpTwoTimesFromLevelOneToLevelThreeFromStrength() {
		Player c1 = new Player("Jasmyn", new Elf(), new Knight(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();

		c1.levelsUp();


		assertEquals(3, c1.getLevel());
	}

	@Test
	void ogreLevelsUpToLevelThreeIntelligenceIsSixteen() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();

		assertEquals(16, c1.getIntelligence());
	}


	@Test 
	void HumanCanFlyAtLevelFive() {
		Player c1 = new Player("Jasmyn", new Human(), new Magician(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.levelsUp();
		c1.levelsUp();
		assertTrue(c1.getIfCanFly());
	}

	@Test 
	void elfCanSwimAtLevelThree() {
		Player c1 = new Player("Jasmyn", new Elf(), new Magician(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.levelsUp();
		c1.levelsUp();
		assertTrue(c1.getIfCanSwim());
	}

	@Test 
	void ogreCanSwimAtLevelThree() {
		Player c1 = new Player("Jasmyn", new Ogre(), new Magician(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.levelsUp();
		c1.levelsUp();
		assertTrue(c1.getIfCanSwim());
	}


	/*
	 * Test driven development for players walking on the map (Emma)
	 */
	@Test
	void positionChangesWhenPlayerMovesNorth() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition newPos = human_player.moveNorth();
		assertThat(newPos.getXPos(), equalTo(human_player.getPosition().getXPos()));
		assertThat(newPos.getYPos(), equalTo(human_player.getPosition().getYPos()));
	}

	@Test
	void positionChangesWhenPlayerMovesSouth() {
		Player human_player = new Player("Human", human, magician, true, testMap.getMapTiles()[0][1]);
		GameMapPosition newPos = human_player.moveSouth();
		assertThat(newPos.getXPos(), equalTo(human_player.getPosition().getXPos()));
		assertThat(newPos.getYPos(), equalTo(human_player.getPosition().getYPos()));
	}

	@Test
	void positionChangesWhenPlayerMovesWest() {
		Player human_player = new Player("Human", human, magician, true, testMap.getMapTiles()[1][0]);
		GameMapPosition newPos = human_player.moveWest();
		assertThat(newPos.getXPos(), equalTo(human_player.getPosition().getXPos()));
		assertThat(newPos.getYPos(), equalTo(human_player.getPosition().getYPos()));
	}

	@Test
	void positionChangesWhenPlayerMovesEast() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition newPos = human_player.moveEast();
		assertThat(newPos.getXPos(), equalTo(human_player.getPosition().getXPos()));
		assertThat(newPos.getYPos(), equalTo(human_player.getPosition().getYPos()));
	}

//	public final class FailsWithException<Ex extends Throwable> {
//
//		private final Ex matcher;
//
//		public FailsWithException(final Ex matcher) {
//			this.matcher = matcher;
//		}
//
////		public Ex failsWith() {
////			return new FailsWithException<Ex>(new IllegalArgumentException);
////		}
//
//		public String message() {
//			return "You can't move out of the map!";
//		}
//	}





	@Test
	void IAEThrownWhenTryingToWalkOutsideMapNorth() {
		Player human_player = new Player("Human", human, magician, true, new GameMapPosition(0, testMap.getHeight() - 1));
		//assertThat({human_player.moveNorth()}, throws());
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapSouth() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapWest() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapEast() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
	}

	@Test
	void IAEThrownWhenTryingToWalkDeadPlayer() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
	}

	@Test
	void humanCanMoveOnGrass() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition currentPos = human_player.getPosition();
		GameMapPosition grassPosToVisit = testMap.getMapTiles()[1][0];

		assertEquals(grassPosToVisit, human_player.moveEast());
	}

	@Test
	void humanCanMoveInWater() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition currentPos = human_player.getPosition();
		GameMapPosition waterPosToVisit = testMap.getMapTiles()[0][1];

		assertEquals(waterPosToVisit, human_player.moveNorth());
	}

	@Test
	void humanDiesInLava() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		human_player.moveEast();
		human_player.moveNorth();
		assertFalse(human_player.isAlive());
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