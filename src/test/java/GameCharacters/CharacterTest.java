package GameCharacters;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import Item.*;
import Jobs.Healer;
import Jobs.Knight;
import Jobs.Magician;
import Races.*;
import Map.*;

class CharacterTest {
	private GameMap testMap = createTestMap();
	private Human human = new Human();
	private Magician magician = new Magician();
	private Knight knight = new Knight();
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
	private Character character = new Player("Default character", human, knight, true, defaultPosition);
	private Character[] players = {
			new Player("Player 1", human, knight, true, defaultPosition),
			new Player("Player 2", human, knight, true, defaultPosition)
	};
	private Item[] defaultItems = {new Sword(), new Wand(), new Potion(), new Shield(), new Ring()};
	private Item[] swordSizes = {new Sword(Size.SMALL), new Sword(Size.MEDIUM), new Sword(Size.LARGE)};
	private Item sword = defaultItems[0];
	private Item wand = defaultItems[1];
	private Item potion = defaultItems[2];
	private Item shield = defaultItems[3];
	private Item ring = defaultItems[4];
	private Item smallSword = swordSizes[0];
	private Item mediumSword = swordSizes[1];
	private Item largeSword = swordSizes[2];
	
	public static class ExceptionMatcher extends TypeSafeMatcher<IllegalArgumentException> {
		private String errorMessage;
		
		public ExceptionMatcher(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		@Override
		public boolean matchesSafely(IllegalArgumentException exception) {
			return exception.getClass().equals(IllegalArgumentException.class) && exception.getMessage().equals(errorMessage);
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("Didn't throw expected exception");
		}
		
		public static Matcher<IllegalArgumentException> isCantWalkOutsideMapException() {
			return new ExceptionMatcher("Can't move out of the map");
		}
		
		public static Matcher<IllegalArgumentException> isEatException() {
			return new ExceptionMatcher("Item can't be eaten!");
		}
		
		public static Matcher<IllegalArgumentException> isEquipException() {
			return new ExceptionMatcher("The item can't be equipped!");
		}
		
		public static Matcher<IllegalArgumentException> isUnequipException() {
			return new ExceptionMatcher("The item can't be unequipped!");
		}
		
		public static Matcher<IllegalArgumentException> isGiveException() {
			return new ExceptionMatcher("The item can't be given!");
		}
		
		public static Matcher<IllegalArgumentException> isSellException() {
			return new ExceptionMatcher("The item can't be sold!");
		}
		
		public static Matcher<IllegalArgumentException> isBuyException() {
			return new ExceptionMatcher("The item can't be bought!");
		}
		
		public static Matcher<IllegalArgumentException> isUseException() {
			return new ExceptionMatcher("Item can't be used!");
		}
		
		public static Matcher<IllegalArgumentException> isEnhanceException() {
			return new ExceptionMatcher("Item can't be enhanced!");
		}
	}
	
	@Test
	void constructorSetsGameMapPosition() {
		Player player = new Player("Player", human, magician, true, defaultPosition);
		assertThat(player.getPosition(), is(defaultPosition));
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
        p.setRemainingHealth(100);
		p.getHealed(100);

		assertEquals(200, p.getRemainingHealth());
	}

	@Test
	void getHealedMoreThanMaxSetsHealthToMax() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);
        p.setRemainingHealth(250);
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


	// beslutstbell som t�cker alla fallen om 
		@Test 
		void HumanCanFlyAtLevelThreeMagicskillMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Human(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			c1.levelsUp();
			assertTrue(c1.getIfCanFly());
		}
		
		@Test 
		void HumanCantFlyAtLevelThreeMagicskillNotMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Human(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			c1.loseMagicSkillFromLoss(10);
			c1.levelsUp();
			assertFalse(c1.getIfCanFly());
		}
		
		@Test 
		void HumanCantFlyAtLevelUnderThreeMagicskillMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Human(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			assertFalse(c1.getIfCanFly());
		}

		@Test 
		void HumanCantFlyAtLevelUnderThreeMagicskillNotMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Human(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.loseMagicSkillFromLoss(10);
			c1.levelsUp();
			assertFalse(c1.getIfCanFly());
		}
		
		@Test 
		void elfCanSwimAtLevelThreeMagicskillMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Elf(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			c1.levelsUp();
			assertTrue(c1.getIfCanSwim());
		}
		
		@Test 
		void elfCantFlyAtLevelThreeMagicskillNotMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Elf(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			c1.loseMagicSkillFromLoss(10);
			c1.levelsUp();
			assertFalse(c1.getIfCanSwim());
		}
		
		@Test 
		void elfCantFlyAtLevelUnderThreeMagicskillMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Elf(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			assertFalse(c1.getIfCanSwim());
		}

		@Test 
		void elfCantFlyAtLevelUnderThreeMagicskillNotMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Elf(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.loseMagicSkillFromLoss(10);
			c1.levelsUp();
			assertFalse(c1.getIfCanSwim());
		}

		@Test 
		void ogreCanSwimAtLevelThreeMagicskillMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Ogre(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			c1.levelsUp();
			assertTrue(c1.getIfCanSwim());
		}
		
		
		@Test 
		void ogreCantSwimAtLevelThreeMagicskillNotMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Ogre(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			c1.loseMagicSkillFromLoss(10);
			c1.levelsUp();
			assertFalse(c1.getIfCanSwim());
		}
		
		@Test 
		void ogreCantSwimAtLevelUnderThreeMagicskillMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Ogre(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.levelsUp();
			assertFalse(c1.getIfCanSwim());
		}

		@Test 
		void ogreCantSwimAtLevelUnderThreeMagicskillNotMoreThanInitial() {
			Player c1 = new Player("Jasmyn", new Ogre(), new Magician(), true, defaultPosition);
			c1.increaseStrengthFromWinningASpell();//
			c1.loseMagicSkillFromLoss(10);
			c1.levelsUp();
			assertFalse(c1.getIfCanSwim());
		}


	/*
	 * Test driven development for players walking on the map (Emma)
	 */
	@Test
	void positionChangesWhenPlayerMovesNorth() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		GameMapPosition newPos = testMap.getMapTiles()[0][1];
		assertThat(newPos, equalTo(human_player.moveNorth()));
	}

	@Test
	void positionChangesWhenPlayerMovesSouth() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, testMap.getMapTiles()[0][1]);
		GameMapPosition newPos = testMap.getMapTiles()[0][0];
		assertThat(newPos, equalTo(human_player.moveSouth()));
	}

	@Test
	void positionChangesWhenPlayerMovesWest() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, testMap.getMapTiles()[1][0]);
		GameMapPosition newPos = testMap.getMapTiles()[0][0];
		assertThat(newPos, equalTo(human_player.moveWest()));
	}

	@Test
	void positionChangesWhenPlayerMovesEast() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		GameMapPosition newPos = testMap.getMapTiles()[1][0];
		assertThat(newPos, equalTo(human_player.moveEast()));
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapNorth() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, testMap.getMapTiles()[0][1]);
		try {
			human_player.moveNorth();
		} catch (IllegalArgumentException e) {
			assertThat(e, new ExceptionMatcher(e.getMessage()));
		}
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapSouth() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		try {
			human_player.moveSouth();
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isCantWalkOutsideMapException());
		}
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapWest() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		try {
			human_player.moveWest();
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isCantWalkOutsideMapException());
		}
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapEast() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, testMap.getMapTiles()[1][0]);
		try {
			human_player.moveEast();
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isCantWalkOutsideMapException());
		}
	}

	@Test
	void IAEThrownWhenTryingToMoveDeadPlayer() {
		Player human_player = new Player("Human", new Human(), new Magician(), false, defaultPosition);
		assertThrows(IllegalArgumentException.class, human_player::moveEast);
	}

	@Test
	void humanCanMoveOnGrass() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		GameMapPosition grassPosToVisit = testMap.getMapTiles()[1][0];
		assertThat(grassPosToVisit, is(human_player.moveEast()));
	}

	@Test
	void humanCanMoveInWater() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		GameMapPosition waterPosToVisit = testMap.getMapTiles()[0][1];
		assertThat(waterPosToVisit, is(human_player.moveNorth()));
	}

	@Test
	void humanDiesInLava() {
		Player human_player = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		human_player.moveEast();
		human_player.moveNorth();
		assertFalse(human_player.isAlive());
	}

	@Test
	void ogreCanMoveOnGrass() {
		Player ogre = new Player("Ogre", new Ogre(), new Magician(), true, defaultPosition);
		GameMapPosition grassPosToVisit = testMap.getMapTiles()[1][0];
		assertThat(grassPosToVisit, is(ogre.moveEast()));
	}

	@Test
	void ogreMovesOutOfWaterWhenInWater() {
		Player ogre = new Player("Ogre", new Ogre(), new Magician(), true, defaultPosition);
		assertThat(ogre.getPosition(), is(ogre.moveNorth()));
	}

	@Test
	void ogreDiesInLava() {
		Player ogre = new Player("Ogre", new Ogre(), new Magician(), true, defaultPosition);
		ogre.moveEast();
		ogre.moveNorth();
		assertFalse(ogre.isAlive());
	}

	@Test
	void elfCanMoveOnGrass() {
		Player elf = new Player("Elf", new Elf(), new Magician(), true, defaultPosition);
		GameMapPosition grassPosToVisit = testMap.getMapTiles()[1][0];
		assertThat(grassPosToVisit, is(elf.moveEast()));
	}

	@Test
	void elfCanMoveOnWater() {
		Player elf = new Player("Elf", new Elf(), new Magician(), true, defaultPosition);
		GameMapPosition waterPosToVisit = testMap.getMapTiles()[0][1];
		assertThat(waterPosToVisit, is(elf.moveNorth()));
	}

	@Test
	void elfCanMoveOnLava() {
		Player elf = new Player("Elf", new Elf(), new Magician(), true, defaultPosition);
		GameMapPosition lavaPosToVisit = testMap.getMapTiles()[1][1];
		elf.moveEast();
		assertThat(lavaPosToVisit, is(elf.moveNorth()));
	}
	//Emma
	
	/** Money **/
	@Test
	void characterCanGainAndLoseMoney() {
		assertThat(character.getMoney(), is(equalTo(0)));
		character.gainMoney(1000);
		assertThat(character.getMoney(), is(equalTo(1000)));
		character.loseMoney(2000);
		assertThat(character.getMoney(), is(equalTo(-1000)));
		character.gainMoney(1000);
		assertThat(character.getMoney(), is(equalTo(0)));
	}
	
	@Test
	void characterCantAffordAnItemIfHasNotEnoughMoney() {
		assertThat(character.getMoney(), is(equalTo(0)));
		assertThat(character.canAfford(ring), is(equalTo(false)));
	}
	
	@Test
	void characterCanAffordARingIfHasEnoughMoney() {
		assertThat(character.getMoney(), is(equalTo(0)));
		character.gainMoney(5000);
		assertThat(character.getMoney(), is(equalTo(5000)));
		assertThat(character.canAfford(ring), is(equalTo(true)));
		character.loseMoney(5000);
	}
	
	/** Eat **/
	@Test
	void characterCantEatNonFood() {
		character.gain(ring);
		assertThat(character.canEat(ring), is(equalTo(false)));
	}
	
	@Test
	void characterCanEatFoodThatIsOwneAndFoodThatIsNotOwned() {
		assertThat(character.owns(potion), is(false));
		assertThat(potion.isFood(), is(true));
		assertThat(character.canEat(potion), is(true));
		//character.gain(egg);
		//assertThat(character.owns(egg), is(equalTo(true)));
		//assertThat(character.canEat(egg), is(equalTo(true)));
	}
	
	@Test
	void eatingFoodMakesItDestroyed() {
		Item egg = new Potion();
		character.gain(egg);
		character.eat(egg);
		assertThat(egg.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void tryingToEatNonFoodThrowsException() {
		character.gain(sword);
		try {
			character.eat(sword);
		} catch (IllegalArgumentException exception) {
			assertThat(exception, ExceptionMatcher.isEatException());
		}
	}
	
	/** Use **/
	@Test
	void usingWeaponMakesItDamaged() {
		character.gain(sword);
		character.use(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	
	@Test
	void characterTryingToUseAnItemThatCantBeUsedThrowsIAE() {
		character.gain(wand);
		try {
			character.use(wand);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isUseException());
		}
	}
	
	/** Enhance **/
	@Test
	void characterThatEnhancesSmallSwordMakesTheItemEnhanced() {
		assertThat(smallSword.isEnhancable(), is(true));
		assertThat(smallSword.isEnhanced(), is(false));
		character.enhance(smallSword);
		assertThat(smallSword.isEnhanced(), is(true));
	}
	
	@Test
	void characterTryingToEnhanceMediumSwordThrowsIAE() {
		character.gain(mediumSword);
		assertThat(mediumSword.isEnhancable(), is(false));
		try {
			character.enhance(mediumSword);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isEnhanceException());
		}
	}
	
	@Test
	void characterTryingToEnhanceLargeSwordThrowsIAE() {
		character.gain(largeSword);
		assertThat(largeSword.isEnhancable(), is(false));
		try {
			character.enhance(largeSword);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isEnhanceException());
		}
	}
	
	/** Damage, destroy, recover, restore **/
	@Test
	void damagingAnItemDecreasesItsCondition() {
		character.damage(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	@Test
	void restoringAnItemIncreasesItsCondition() {
		character.destroy(sword);
		character.restore(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION + 10)));
	}
	
	@Test
	void destroyingAnItemDecreasesItsConditionToMinCondition() {
		character.destroy(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void recoveringAnItemIncreasesItsConditionToMaxCondition() {
		character.destroy(sword);
		character.recover(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
	}
	
	@Test
	void characterTryingToDestroyAnItemThatIsntDestroyableThrowsIAE() {
		character.destroy(sword);
		assertThat(sword.isDestroyable(), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			character.destroy(sword);
		});
	}
	
	@Test
	void characterTryingToRecoverAnItemThatIsntRecoverableThrowsIAE() {
		assertThat(sword.isRecoverable(), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			character.recover(sword);
		});
	}
	
	/** Gain/lose **/
	@Test
	void characterGainingAnItemsOwnsTheItem() {
		assertThat(character.owns(sword), is(equalTo(false)));
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
	}
	
	@Test
	void characterLosingAnItemsDoesntOwnTheItem() {
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		character.lose(sword);
		assertThat(character.owns(sword), is(equalTo(false)));
	}
	
	@Test
	void characterLosingAnItemMakesItDisappearFromItsInventory() {
		assertThat(character.getItems().contains(sword), is(equalTo(false)));
		assertThat(character.getInventory().contains(sword), is(equalTo(false)));
		character.gain(sword);
		assertThat(character.getItems().contains(sword), is(equalTo(true)));
		assertThat(character.getInventory().contains(sword), is(equalTo(true)));
		character.lose(sword);
		assertThat(character.getItems().contains(sword), is(equalTo(false)));
		assertThat(character.getInventory().contains(sword), is(equalTo(false)));
	}
	
	@Test
	void characterLosingAnEquippedItemMakesItUnequipped() {
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(false)));
		assertThat(sword.isEquipped(), is(equalTo(false)));
		character.equip(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.hasEquipped(sword), is(equalTo(true)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(true)));
		assertThat(sword.isEquipped(), is(equalTo(true)));
		character.lose(sword);
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(false)));
		assertThat(sword.isEquipped(), is(equalTo(false)));
	}
	
	/** Equip/unequip **/
	@Test
	void characterEquippingASwordHasTheSwordEquipped() {
		assertThat(character.hasEquipped(sword), is(false));
		assertThat(character.getEquippedItems().contains(sword), is(false));
		assertThat(sword.isEquipped(), is(false));
		character.gain(sword);
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.getEquippedItems().contains(sword), is(false));
		assertThat(sword.isEquipped(), is(false));
		character.equip(sword);
		assertThat(character.hasEquipped(sword), is(true));
		assertThat(character.getEquippedItems().contains(sword), is(true));
		assertThat(sword.isEquipped(), is(true));
	}
	
	@Test
	void characterTryingToEquipAnItemThatCantBeEquippedThrowsIAE() {
		assertThat(character.owns(sword), is(false));
		try {
			character.equip(sword);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isEquipException());
		}
	}
	
	@Test
	void characterUnequippingAnItemMakesItUnequipped() {
		character.gain(sword);
		character.equip(sword);
		assertThat(character.hasEquipped(sword), is(equalTo(true)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(true)));
		assertThat(sword.isEquipped(), is(equalTo(true)));
		character.unequip(sword);
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(false)));
		assertThat(sword.isEquipped(), is(equalTo(false)));
	}
	
	@Test
	void characterTryingToUnequipAnItemThatIsNotEquippedThrowsIAE() {
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		try {
			character.unequip(sword);
		} catch(IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isUnequipException());
		}
	}
	
	@Test
	void characterCanEquipThatIsOwned() {
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.canEquip(sword), is(equalTo(true)));
	}
	
	@Test
	void characterCantEquipThatIsNotOwned() {
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.canEquip(sword), is(equalTo(false)));
	}
	
	@Test
	void foodCantBeEquipped() {
		assertThat(character.owns(potion), is(equalTo(false)));
		assertThat(character.canEquip(potion), is(equalTo(false)));
		character.gain(potion);
		assertThat(character.owns(potion), is(equalTo(true)));
		assertThat(character.canEquip(potion), is(equalTo(false)));
	}
	
	/** Give/receive **/
	@Test
	void characterCanGiveItemThatIsOwnedAndUnequipped() {
		character.gain(sword);
		character.equip(sword);
		character.unequip(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.canGive(sword), is(equalTo(true)));
	}
	
	@Test
	void characterCantGiveItemThatIsNotOwned() {
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.canGive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantGiveItemThatIsEquipped() {
		character.gain(sword);
		character.equip(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.hasEquipped(sword), is(equalTo(true)));
		assertThat(character.canGive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantReceiveItemThatCharacterAlreadyOwns() {
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.getInventory().isFull(), is(equalTo(false)));
		assertThat(character.canReceive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantReceiveItemWhenInventoryIsFull() {
		while (character.getInventory().hasAvailableSpace()) {
			character.getInventory().add(new Sword());
		}
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.getInventory().isFull(), is(equalTo(true)));
		assertThat(character.canReceive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterGivingAnItemToAnotherCharacterMakesTheItemBecomeOwnedByTheOtherCharacter() {
		Character giver = players[0];
		Character receiver = players[1];
		giver.gain(sword);
		assertThat(giver.owns(sword), is(equalTo(true)));
		assertThat(receiver.owns(sword), is(equalTo(false)));
		assertThat(sword.canBeGiven(giver, receiver), is(equalTo(true)));
		giver.give(sword, receiver);
		assertThat(giver.owns(sword), is(equalTo(false)));
		assertThat(receiver.owns(sword), is(equalTo(true)));
	}
	
	@Test
	void characterTryingToGiveAnItemThatCantBeGivenToAnotherCharacterThrowsIAE() {
		Character giver = players[0];
		Character receiver = players[1];
		receiver.gain(sword);
		assertThat(giver.owns(sword), is(equalTo(false)));
		assertThat(receiver.owns(sword), is(equalTo(true)));
		assertThat(sword.canBeGiven(giver, receiver), is(equalTo(false)));
		try {
			giver.give(sword, receiver);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isGiveException());
		}
	}
	
	/** Buy/sell **/
	@Test
	void characterThatCanReceiveAndCanAffordItemCanBuyAnItem() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		buyer.gainMoney(1000);
		assertThat(buyer.canReceive(sword), is(equalTo(true)));
		assertThat(buyer.canAfford(sword), is(equalTo(true)));
		assertThat(buyer.canBuy(sword), is(equalTo(true)));
	}
	
	@Test
	void characterThatCanReceiveButCantAffordItemCantBuyAnItem() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		buyer.gainMoney(50);
		assertThat(buyer.canReceive(sword), is(equalTo(true)));
		assertThat(buyer.canAfford(sword), is(equalTo(false)));
		assertThat(buyer.canBuy(sword), is(equalTo(false)));
	}
	
	@Test
	void characterThatCanGiveAnItemCanSellTheItem() {
		Character seller = players[0];
		seller.gain(sword);
		assertThat(seller.canGive(sword), is(equalTo(true)));
		assertThat(seller.canSell(sword), is(equalTo(true)));
	}
	
	@Test
	void characterThatCantGiveAnItemCantSellTheItem() {
		Character seller = players[0];
		seller.gain(sword);
		seller.equip(sword);
		assertThat(seller.canGive(sword), is(equalTo(false)));
		assertThat(seller.canSell(sword), is(equalTo(false)));
	}
	
	@Test
	void characterBuyingAnItemMakesBuyersAmountOfMoneyDecrease() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(sword.canBeSold(seller, buyer), is(equalTo(true)));
		buyer.buy(sword, seller);
		assertThat(buyer.getMoney(), is(equalTo(1000 - sword.getValue())));
	}
	
	@Test
	void characterBuyingAnItemMakesSellersAmountOfMoneyIncrease() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(sword.canBeSold(seller, buyer), is(equalTo(true)));
		buyer.buy(sword, seller);
		assertThat(seller.getMoney(), is(equalTo(5000 + sword.getValue())));
	}
	
	@Test
	void characterSellingAnItemMakesSellersAmountOfMoneyIncrease() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(sword.canBeSold(seller, buyer), is(equalTo(true)));
		seller.sell(sword, buyer);
		assertThat(seller.getMoney(), is(equalTo(5000 + sword.getValue())));
	}
	
	@Test
	void characterSellingAnItemMakesBuyersAmountOfMoneyDecrease() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(sword.canBeSold(seller, buyer), is(equalTo(true)));
		seller.sell(sword, buyer);
		assertThat(buyer.getMoney(), is(equalTo(1000 - sword.getValue())));
	}
	
	@Test
	void characterWhoHasBoughtAnItemOwnsTheItemAfterTransaction() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(seller.owns(sword), is(equalTo(true)));
		assertThat(buyer.owns(sword), is(equalTo(false)));
		buyer.buy(sword, seller);
		assertThat(seller.owns(sword), is(equalTo(false)));
		assertThat(buyer.owns(sword), is(equalTo(true)));
	}
	
	@Test
	void characterWhoHasSoldAnItemDoesntTheItemAfterTransaction() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(buyer.owns(sword), is(equalTo(false)));
		assertThat(seller.owns(sword), is(equalTo(true)));
		seller.sell(sword, buyer);
		assertThat(buyer.owns(sword), is(equalTo(true)));
		assertThat(seller.owns(sword), is(equalTo(false)));
	}
	
	@Test
	void characterTryingToBuyAnItemThatCantBeBoughtThrowsIAE() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.equip(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(sword.canBeSold(seller, buyer), is(equalTo(false)));
		try {
			buyer.buy(sword, seller);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isBuyException());
		}
	}
	
	@Test
	void characterTryingToSellAnItemThatCantBeBoughtThrowsIAE() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.equip(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		assertThat(sword.canBeSold(seller, buyer), is(equalTo(false)));
		try {
			seller.sell(sword, buyer);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isSellException());
		}
	}
	//Jon

}