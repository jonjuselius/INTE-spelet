package GameCharacters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import Inventory.Inventory;
import Item.*;
import Jobs.Healer;
import Jobs.Knight;
import Jobs.Magician;
import org.junit.jupiter.api.Test;
import Races.*;
import Map.*;

class CharacterTest {
	private Map testMap = createTestMap();
	private Human human = new Human();
	private Magician magician = new Magician();
	private MapPosition defaultPosition = new MapPosition(0, 0, testMap);

	private Map createTestMap() {
		Map map = new Map(2, 2);
		MapPosition firstPos = new MapPosition(0, 0, map);
		firstPos.setTerrain(Terrain.GRASS);
		MapPosition secondPos = new MapPosition(0, 1, map);
		secondPos.setTerrain(Terrain.WATER);
		MapPosition thirdPos = new MapPosition(1, 0, map);
		thirdPos.setTerrain(Terrain.GRASS);
		MapPosition fourthPos = new MapPosition(1, 1, map);
		fourthPos.setTerrain(Terrain.LAVA);
		map.put(firstPos, firstPos.getXPos(), firstPos.getXPos());
		map.put(secondPos, secondPos.getXPos(), secondPos.getYPos());
		map.put(thirdPos, thirdPos.getXPos(), thirdPos.getYPos());
		map.put(fourthPos, fourthPos.getXPos(), fourthPos.getYPos());
		return testMap;
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

	}

	@Test
	void positionChangesWhenPlayerMovesSouth() {

	}

	@Test
	void positionChangesWhenPlayerMovesWest() {

	}

	@Test
	void positionChangesWhenPlayerMovesEast() {

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
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		MapPosition currentPos = human_player.getPosition();
		MapPosition grassPosToVisit = testMap.getMapTiles()[1][0];

		assertEquals(grassPosToVisit, human_player.moveEast());
	}

	@Test
	void humanCanMoveInWater() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
		MapPosition currentPos = human_player.getPosition();
		MapPosition waterPosToVisit = testMap.getMapTiles()[0][1];

		assertEquals(waterPosToVisit, human_player.moveNorth());
	}

	@Test
	void humanDiesInLava() {
		Player human_player = new Player("Human", human, magician, true, defaultPosition);
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
	
	@Test
	void newCharacterHasNoMoney() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		assertThat(character.getMoney(), is(equalTo(0)));
	}
	
	@Test
	void characterCanGainMoney() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(1000);
		assertThat(character.getMoney(), is(equalTo(1000)));
	}
	
	@Test
	void characterCanLoseMoney() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(1000);
		character.loseMoney(500);
		assertThat(character.getMoney(), is(equalTo(500)));
	}
	
	@Test
	void characterCanLoseMoreMoneyThanIsOwned() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(500);
		character.loseMoney(1000);
		assertThat(character.getMoney(), is(equalTo(-500)));
	}
	
	@Test
	void characterWithMoneyUnder5000CantAffordARing() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(4000);
		assertFalse(character.canAfford(new Ring()));
	}
	
	@Test
	void characterWithMoneyOver5000CantAffordARing() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		character.gainMoney(6000);
		assertTrue(character.canAfford(new Ring()));
	}
	
	@Test
	void characterCanEquipOwnedWeapon() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertTrue(character.canEquip(sword));
	}
	
	@Test
	void characterCantEquipUnownedWeapon() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertFalse(character.canEquip(sword));
	}
	
	@Test
	void characterCantEquipFood() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item egg = new Egg();
		character.gain(egg);
		assertFalse(character.canEquip(egg));
	}
	
	@Test
	void characterCantEatJewewllery() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item ring = new Ring();
		character.gain(ring);
		assertFalse(character.canEat(ring));
	}
	
	@Test
	void characterCanEatFood() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item egg = new Egg();
		character.gain(egg);
		assertTrue(character.canEat(egg));
	}
	
	@Test
	void usingWeaponMakesItDamaged() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		character.use(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	@Test
	void eatingFoodMakesItDestroyed() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item egg = new Egg();
		character.gain(egg);
		character.eat(egg);
		assertThat(egg.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void tryingToEatNonFoodThrowsException() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThrows(IllegalArgumentException.class, () -> {
			character.eat(sword);
		});
	}
	
	@Test
	void damagingAnItemDecreasesItsCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.damage(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	@Test
	void restoringAnItemIncreasesItsCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		character.restore(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION + 10)));
	}
	
	@Test
	void destroyingAnItemDecreasesItsConditionToMinCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void recoveringAnItemIncreasesItsConditionToMaxCondition() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		character.recover(sword);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
	}
	
	@Test
	void characterGainingAnItemsOwnsTheItem() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(character.owns(sword), is(equalTo(false)));
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
	}
	
	@Test
	void characterLosingAnItemsDoesntOwnTheItem() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		character.lose(sword);
		assertThat(character.owns(sword), is(equalTo(false)));
	}
	
	@Test
	void characterLosingAnItemMakesItDisappearFromItsInventory() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThrows(IllegalArgumentException.class, () -> {
			character.equip(sword);
		});
	}
	
	@Test
	void characterUnequippingAnItemMakesItUnequipped() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(character.owns(sword), is(equalTo(false)));
		assertThat(character.canGive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantGiveItemThatIsEquipped() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		character.equip(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.hasEquipped(sword), is(equalTo(true)));
		assertThat(character.canGive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantReceiveItemThatCharacterAlreadyOwns() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThat(character.owns(sword), is(equalTo(true)));
		assertThat(character.getInventory().isFull(), is(equalTo(false)));
		assertThat(character.canReceive(sword), is(equalTo(false)));
	}
	
	@Test
	void characterCantReceiveItemWhenInventoryIsFull() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player2.gain(sword);
		player1.gainMoney(1000);
		assertThat(player1.canReceive(sword), is(equalTo(true)));
		assertThat(player1.canAfford(sword), is(equalTo(true)));
		assertThat(player1.canBuy(sword), is(equalTo(true)));
	}
	
	@Test
	void characterThatCanReceiveButCantAffordItemCantBuyAnItem() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player2.gain(sword);
		player1.gainMoney(50);
		assertThat(player1.canReceive(sword), is(equalTo(true)));
		assertThat(player1.canAfford(sword), is(equalTo(false)));
		assertThat(player1.canBuy(sword), is(equalTo(false)));
	}
	
	@Test
	void characterThatCanGiveAnItemCanSellTheItem() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		assertThat(player1.canGive(sword), is(equalTo(true)));
		assertThat(player1.canSell(sword), is(equalTo(true)));
	}
	
	@Test
	void characterThatCantGiveAnItemCantSellTheItem() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		player1.gain(sword);
		player1.equip(sword);
		assertThat(player1.canGive(sword), is(equalTo(false)));
		assertThat(player1.canSell(sword), is(equalTo(false)));
	}
	
	@Test
	void characterBuyingAnItemMakesBuyersAmountOfMoneyDecrease() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
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
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item wand = new Wand();
		character.gain(wand);
		assertThrows(IllegalArgumentException.class, () -> {
			character.use(wand);
		});
	}
	
	@Test
	void characterTryingToEnhanceAnItemThatCantBeEnhancedThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.gain(sword);
		assertThrows(IllegalArgumentException.class, () -> {
			character.enhance(sword);
		});
	}
	
	@Test
	void characterTryingToDestroyAnItemThatIsntDestroyableThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		character.destroy(sword);
		assertThat(sword.isDestroyable(), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			character.destroy(sword);
		});
	}
	
	@Test
	void characterTryingToRecoverAnItemThatIsntRecoverableThrowsIAE() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword();
		assertThat(sword.isRecoverable(), is(equalTo(false)));
		assertThrows(IllegalArgumentException.class, () -> {
			character.recover(sword);
		});
	}
	
	@Test
	void characterThatEnhancesAnEnhancableItemMakesTheItemEnhanced() {
		Character character = new Player("Default character", new Human(), new Knight(), true, (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2]);
		Item sword = new Sword(Size.SMALL);
		assertThat(sword.isEnhancable(), is(equalTo(true)));
		assertThat(sword.isEnhanced(), is(equalTo(false)));
		character.enhance(sword);
		assertThat(sword.isEnhanced(), is(equalTo(true)));
	}
}