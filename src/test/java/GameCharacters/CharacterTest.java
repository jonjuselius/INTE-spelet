package GameCharacters;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import Inventory.Inventory;
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

	public static class ExceptionMatcher extends TypeSafeMatcher<IllegalArgumentException> {
		private String cantMoveOutOfTheMap = "Can't move out of the map";

		@Override
		public boolean matchesSafely(IllegalArgumentException exception) {
			if (exception.equals(IllegalArgumentException.class) && exception.getMessage().equals(cantMoveOutOfTheMap)) {
				return true;
			}
			return false;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("Didn't throw expected exception");
		}

		public static Matcher<IllegalArgumentException> throwsCantMoveOutOfTheMapException() {
			return new ExceptionMatcher();
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


	// beslutstbell som täcker alla fallen om 
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
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition newPos = testMap.getMapTiles()[0][1];
		assertThat(newPos, equalTo(human_player.moveNorth()));
	}

	@Test
	void positionChangesWhenPlayerMovesSouth() {
		Player human_player = new Player("Human", human, magician, true, testMap.getMapTiles()[0][1]);
		GameMapPosition newPos = testMap.getMapTiles()[0][0];
		assertThat(newPos, equalTo(human_player.moveSouth()));
	}

	@Test
	void positionChangesWhenPlayerMovesWest() {
		Player human_player = new Player("Human", human, magician, true, testMap.getMapTiles()[1][0]);
		GameMapPosition newPos = testMap.getMapTiles()[0][0];
		assertThat(newPos, equalTo(human_player.moveWest()));
	}

	@Test
	void positionChangesWhenPlayerMovesEast() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition newPos = testMap.getMapTiles()[1][0];
		assertThat(newPos, equalTo(human_player.moveEast()));
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapNorth() {
		Player human_player = new Player("Human", human, magician, true, testMap.getMapTiles()[0][1]);
		try {
			human_player.moveNorth();
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.throwsCantMoveOutOfTheMapException());
		}
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapSouth() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		try {
			human_player.moveNorth();
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.throwsCantMoveOutOfTheMapException());
		}
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapWest() {
		fail("Not implemented yet");
		//Player human_player = new Player("Human", human, magician, true, defaultPosition);
	}

	@Test
	void IAEThrownWhenTryingToWalkOutsideMapEast() {
		fail("Not implemented yet");
		//Player human_player = new Player("Human", human, magician, true, defaultPosition);
	}

	@Test
	void IAEThrownWhenTryingToWalkDeadPlayer() {
		fail("Not implemented yet");
		//Player human_player = new Player("Human", human, magician, true, defaultPosition);
	}

	@Test
	void humanCanMoveOnGrass() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition grassPosToVisit = testMap.getMapTiles()[1][0];
		assertThat(grassPosToVisit, is(human_player.moveEast()));
	}

	@Test
	void humanCanMoveInWater() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		GameMapPosition waterPosToVisit = testMap.getMapTiles()[0][1];
		assertThat(waterPosToVisit, is(human_player.moveNorth()));
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
		Player ogre = new Player("Ogre", new Ogre(), magician, true, defaultPosition);
		GameMapPosition grassPosToVisit = testMap.getMapTiles()[1][0];
		assertThat(grassPosToVisit, is(ogre.moveEast()));
	}

	@Test
	void ogreMovesOutOfWaterWhenInWater() {
		Player ogre = new Player("Ogre", new Ogre(), magician, true, defaultPosition);
		assertThat(ogre.getPosition(), is(ogre.moveNorth()));
	}

	@Test
	void ogreDiesInLava() {
		Player ogre = new Player("Ogre", new Ogre(), magician, true, defaultPosition);
		ogre.moveEast();
		ogre.moveNorth();
		assertFalse(ogre.isAlive());
	}

	@Test
	void elfCanMoveOnGrass() {
		Player elf = new Player("Elf", new Elf(), magician, true, defaultPosition);
		GameMapPosition grassPosToVisit = testMap.getMapTiles()[1][0];
		assertThat(grassPosToVisit, is(elf.moveEast()));
	}

	@Test
	void elfCanMoveOnWater() {
		Player elf = new Player("Elf", new Elf(), magician, true, defaultPosition);
		GameMapPosition waterPosToVisit = testMap.getMapTiles()[0][1];
		assertThat(waterPosToVisit, is(elf.moveNorth()));
	}

	@Test
	void elfCanMoveOnLava() {
		Player elf = new Player("Elf", new Elf(), magician, true, defaultPosition);
		GameMapPosition lavaPosToVisit = testMap.getMapTiles()[1][1];
		elf.moveEast();
		assertThat(lavaPosToVisit, is(elf.moveNorth()));
	}
	
	@Test
	void newCharacterHasNoMoney() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		assertThat(character.getMoney(), is(equalTo(0)));
	}
	
	@Test
	void characterCanGainMoney() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(1000);
		assertThat(character.getMoney(), is(equalTo(1000)));
	}
	
	@Test
	void characterCanLoseMoney() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(1000);
		character.loseMoney(500);
		assertThat(character.getMoney(), is(equalTo(500)));
	}
	
	@Test
	void characterCanLoseMoreMoneyThanIsOwned() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(500);
		character.loseMoney(1000);
		assertThat(character.getMoney(), is(equalTo(-500)));
	}
	
	@Test
	void characterWithMoneyUnder5000CantAffordARing() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(4000);
		assertFalse(character.canAfford(new Ring()));
	}
	
	@Test
	void characterWithMoneyOver5000CantAffordARing() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(6000);
		assertTrue(character.canAfford(new Ring()));
	}
	
	@Test
	void characterCanEquipOwnedWeapon() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertTrue(character.canEquip(sword));
	}
	
	@Test
	void characterCantEquipUnownedWeapon() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertFalse(character.canEquip(sword));
	}
	
	@Test
	void characterCantEquipFood() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item egg = new Egg();
		character.gain(egg);
		assertFalse(character.canEquip(egg));
	}
	
	@Test
	void characterCantEatJewewllery() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item ring = new Ring();
		character.gain(ring);
		assertFalse(character.canEat(ring));
	}
	
	@Test
	void characterCanEatFood() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item egg = new Egg();
		character.gain(egg);
		assertTrue(character.canEat(egg));
	}
	
	@Test
	void usingWeaponMakesItDamaged() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		character.use(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	@Test
	void eatingFoodMakesItDestroyed() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item egg = new Egg();
		character.gain(egg);
		character.eat(egg);
		assertThat(egg.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void tryingToEatNonFoodThrowsException() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThrows(IllegalArgumentException.class, () -> {
			character.eat(sword);
		});
	}
	
	@Test
	void damagingAnItemDecreasesItsCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.damage(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	@Test
	void restoringAnItemIncreasesItsCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		character.restore(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION + 10)));
	}
	
	@Test
	void destroyingAnItemDecreasesItsConditionToMinCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void recoveringAnItemIncreasesItsConditionToMaxCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		character.recover(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
	}
	
	@Test
	void characterGainingAnItemsOwnsTheItem() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(character.owns(sword), is(equalTo(false)));
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
	}
	
	@Test
	void characterLosingAnItemsDoesntOwnTheItem() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		character.lose(sword);
		assertThat(character.owns(sword), is(equalTo(false)));
	}
	
	@Test
	void characterLosingAnItemMakesItDisappearFromItsInventory() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
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
	
	@Test
	void characterEquippingASwordHasTheSwordEquipped() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(false)));
		assertThat(sword.isEquipped(), is(equalTo(false)));
		character.gain(sword);
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(false)));
		assertThat(sword.isEquipped(), is(equalTo(false)));
		character.equip(sword);
		assertThat(character.hasEquipped(sword), is(equalTo(true)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(true)));
		assertThat(sword.isEquipped(), is(equalTo(true)));
	}
	
	@Test
	void characterTryingToEquipAnItemThatCantBeEquippedThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThrows(IllegalArgumentException.class, () -> {
			character.equip(sword);
		});
	}
	
	@Test
	void characterUnequippingAnItemMakesItUnequipped() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
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
	void characterTryingToUnequipAnUnequippableItemThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.getEquippedItems().contains(sword), is(equalTo(false)));
		assertThat(sword.isEquipped(), is(equalTo(false)));
		assertThat(sword.isEquippable(), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			character.unequip(sword);
		});
	}
	
	@Test
	void characterCanGiveItemThatIsOwnedAndUnequipped() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		character.equip(sword);
		character.unequip(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.hasEquipped(sword), is(equalTo(false)));
		assertThat(character.canGive(sword), is(equalTo(true)));
	}
	
	@Test
	void characterCantGiveItemThatIsNotOwned() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.canGive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantGiveItemThatIsEquipped() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		character.equip(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.hasEquipped(sword), is(equalTo(true)));
		assertThat(character.canGive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantReceiveItemThatCharacterAlreadyOwns() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.getInventory().isFull(), is(equalTo(false)));
		assertThat(character.canReceive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantReceiveItemWhenInventoryIsFull() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		while (character.getInventory().hasAvailableSpace()) {
			character.getInventory().add(new Sword());
		}
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.getInventory().isFull(), is(equalTo(true)));
		assertThat(character.canReceive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterGivingAnItemToAnotherCharacterMakesTheItemBecomeOwnedByTheOtherCharacter() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		assertThat(player1.owns(sword), is(equalTo(true)));
		assertThat(player2.owns(sword), is(equalTo(false)));
		assertThat(sword.canBeGiven(player1, player2), is(equalTo(true)));
		player1.give(sword, player2);
		assertThat(player1.owns(sword), is(equalTo(false)));
		assertThat(player2.owns(sword), is(equalTo(true)));
	}
	
	@Test
	void characterTryingToGiveAnItemThatCantBeGivenToAnotherCharacterThrowsIAE() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player2.gain(sword);
		assertThat(player1.owns(sword), is(equalTo(false)));
		assertThat(player2.owns(sword), is(equalTo(true)));
		assertThat(sword.canBeGiven(player1, player2), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			player1.give(sword, player2);
		});
	}
	
	@Test
	void characterThatCanReceiveAndCanAffordItemCanBuyAnItem() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player2.gain(sword);
		player1.gainMoney(1000);
		assertThat(player1.canReceive(sword), is(equalTo(true)));
		assertThat(player1.canAfford(sword), is(equalTo(true)));
		assertThat(player1.canBuy(sword), is(equalTo(true)));
	}
	
	@Test
	void characterThatCanReceiveButCantAffordItemCantBuyAnItem() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player2.gain(sword);
		player1.gainMoney(50);
		assertThat(player1.canReceive(sword), is(equalTo(true)));
		assertThat(player1.canAfford(sword), is(equalTo(false)));
		assertThat(player1.canBuy(sword), is(equalTo(false)));
	}
	
	@Test
	void characterThatCanGiveAnItemCanSellTheItem() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		assertThat(player1.canGive(sword), is(equalTo(true)));
		assertThat(player1.canSell(sword), is(equalTo(true)));
	}
	
	@Test
	void characterThatCantGiveAnItemCantSellTheItem() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.equip(sword);
		assertThat(player1.canGive(sword), is(equalTo(false)));
		assertThat(player1.canSell(sword), is(equalTo(false)));
	}
	
	@Test
	void characterBuyingAnItemMakesBuyersAmountOfMoneyDecrease() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(true)));
		player2.buy(sword, player1);
		assertThat(player2.getMoney(), is(equalTo(1000 - sword.getValue())));
	}
	
	@Test
	void characterBuyingAnItemMakesSellersAmountOfMoneyIncrease() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(true)));
		player2.buy(sword, player1);
		assertThat(player1.getMoney(), is(equalTo(5000 + sword.getValue())));
	}
	
	@Test
	void characterSellingAnItemMakesSellersAmountOfMoneyIncrease() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(true)));
		player1.sell(sword, player2);
		assertThat(player1.getMoney(), is(equalTo(5000 + sword.getValue())));
	}
	
	@Test
	void characterSellingAnItemMakesBuyersAmountOfMoneyDecrease() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(true)));
		player1.sell(sword, player2);
		assertThat(player2.getMoney(), is(equalTo(1000 - sword.getValue())));
	}
	
	@Test
	void characterWhoHasBoughtAnItemOwnsTheItemAfterTransaction() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(player1.owns(sword), is(equalTo(true)));
		assertThat(player2.owns(sword), is(equalTo(false)));
		player2.buy(sword, player1);
		assertThat(player1.owns(sword), is(equalTo(false)));
		assertThat(player2.owns(sword), is(equalTo(true)));
	}
	
	@Test
	void characterWhoHasSoldAnItemDoesntTheItemAfterTransaction() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(player2.owns(sword), is(equalTo(false)));
		assertThat(player1.owns(sword), is(equalTo(true)));
		player1.sell(sword, player2);
		assertThat(player2.owns(sword), is(equalTo(true)));
		assertThat(player1.owns(sword), is(equalTo(false)));
	}
	
	@Test
	void characterTryingToBuyAnItemThatCantBeBoughtThrowsIAE() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.equip(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			player2.buy(sword, player1);
		});
	}
	
	@Test
	void characterTryingToSellAnItemThatCantBeBoughtThrowsIAE() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.equip(sword);
		player1.gainMoney(5000);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			player1.sell(sword, player2);
		});
	}
	
	@Test
	void characterTryingToUseAnItemThatCantBeUsedThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item wand = new Wand();
		character.gain(wand);
		assertThrows(IllegalArgumentException.class, () -> {
			character.use(wand);
		});
	}
	
	@Test
	void characterTryingToEnhanceAnItemThatCantBeEnhancedThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThrows(IllegalArgumentException.class, () -> {
			character.enhance(sword);
		});
	}
	
	@Test
	void characterTryingToDestroyAnItemThatIsntDestroyableThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		assertThat(sword.isDestroyable(), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			character.destroy(sword);
		});
	}
	
	@Test
	void characterTryingToRecoverAnItemThatIsntRecoverableThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(sword.isRecoverable(), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			character.recover(sword);
		});
	}
	
	@Test
	void characterThatEnhancesAnEnhancableItemMakesTheItemEnhanced() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword(Size.SMALL);
		assertThat(sword.isEnhancable(), is(equalTo(true)));
		assertThat(sword.isEnhanced(), is(equalTo(false)));
		character.enhance(sword);
		assertThat(sword.isEnhanced(), is(equalTo(true)));
	}
}