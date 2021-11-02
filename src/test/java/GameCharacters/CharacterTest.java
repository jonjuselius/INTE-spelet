package GameCharacters;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import Exceptions.*;
import Inventory.Inventory;
import Inventory.Wallet;
import Magic.DebuffSpell;
import Magic.Element;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
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
	private Character otherCharacter = new Player("Other character", human, knight, true, defaultPosition);
	private Character[] players = { new Player("Player 1", human, knight, true, defaultPosition),
			new Player("Player 2", human, knight, true, defaultPosition) };
	private Item[] defaultItems = { new Sword(), new Wand(), new Potion(), new Shield(), new Ring() };
	private Item[] swordSizes = { new Sword(Size.SMALL), new Sword(Size.MEDIUM), new Sword(Size.LARGE) };
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
			return (exception.getClass().equals(IllegalArgumentException.class)
					|| exception instanceof IllegalArgumentException) && exception.getMessage().equals(errorMessage);
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("Didn't throw expected exception");
		}

		public static Matcher<IllegalArgumentException> isCantWalkOutsideMapException() {
			return new ExceptionMatcher("Can't move out of the map");
		}

		public static Matcher<IllegalArgumentException> isUseException() {
			return new ExceptionMatcher((new UseException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isGiveException() {
			return new ExceptionMatcher((new GiveException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isSellException() {
			return new ExceptionMatcher((new SellException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isBuyException() {
			return new ExceptionMatcher((new BuyException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isEnhanceException() {
			return new ExceptionMatcher((new EnhanceException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isDamageException() {
			return new ExceptionMatcher((new DamageException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isRestoreException() {
			return new ExceptionMatcher((new RestoreException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isGainException() {
			return new ExceptionMatcher((new GainException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isLoseException() {
			return new ExceptionMatcher((new LoseException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isEquipException() {
			return new ExceptionMatcher((new EquipException()).getMessage());
		}

		public static Matcher<IllegalArgumentException> isUnequipException() {
			return new ExceptionMatcher((new UnequipException()).getMessage());
		}
	}

	@Test
	void constructorSetsGameMapPosition() {
		Player player = new Player("Player", human, magician, true, defaultPosition);
		assertThat(player.getPosition(), is(defaultPosition));
	}

	//Lena

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

	@Test
	public void addSpellsToSpellCollection(){
		Player p = new Player("Player1", human, magician, true, defaultPosition);
		DebuffSpell ds = new DebuffSpell("StrengthDebuff", 10, Element.PHYSICAL, 5);
		p.addSpellToSpellCollection(ds);

		assertThat(p.getSpellCollection().getSpellCollection(), IsMapContaining.hasKey("StrengthDebuff"));

	}

/////// Tdd Jasmyn////////////
	
	@Test
	void nameTest() {
		Player c3 = new Player("Oliver", new Elf(), new Knight(), true, defaultPosition);

		assertEquals("Oliver", c3.getName());

	}
	
	@Test
	void getMagicSkillTest() {
		Player c3 = new Player("Oliver", new Elf(), new Magician(), true, defaultPosition);

		assertEquals(10, c3.getMagicSkill());

	}
	
	@Test
	void getMaxManaTest() {
		Player c3 = new Player("Oliver", new Elf(), new Knight(), true, defaultPosition);

		assertEquals(400, c3.getMaxMana());

	}
	
	@Test
	void setLevelTest() {
		Character c3 = new Player("Oliver", new Elf(), new Knight(), true, defaultPosition);
		c3.setLevel(3);
		assertEquals(3, c3.getLevel());

	}

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
	// Humans initial intelligence = 20
	void increaseIntelligenceFromWinningASpellByThree() {
		Player p = new Player("Player1", human, magician, true, defaultPosition);

		p.increaseIntelligenceFromWinningASpell();

		assertEquals(23, p.getIntelligence());
	}

	// Can't increase more than 15 over initial intelligence
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

	// Humans initial strength = 20
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
	void dealDamageDependingOnYourSwordSkillAndStrengthCharacterDiesAtZeroLife() {
		Player p = new Player("jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);
		p.dealDamageDependingOnYourSwordSkillAndStrength(160, p1);

		assertFalse(p1.isAlive());

	}

	@Test
	void dealDamageDependingOnYourSwordSkillAndStrengthCharacterisAlreadyDead() {

		Player p = new Player("jasmyn", new Ogre(), new Knight(), true, defaultPosition);
		Player p1 = new Player("Michael", new Elf(), new Healer(), true, defaultPosition);

		p1.takeDamage(200);
		p.dealDamageDependingOnYourSwordSkillAndStrength(10, p1);

		assertFalse(p1.isAlive());

	}

	@Test
	void humanLevelsTriesToLevelUpToLevelTwoButCant() {
		Player x = new Player("Player1", new Human(), new Knight(), true, defaultPosition);

		x.levelsUp();

		assertEquals(1, x.getLevel());
	}

	@Test
	void humanLevelsUpTwoTimesFromLevelOneToLevelThreeFomIntelligence() {
		Player c1 = new Player("Player1", new Human(), new Knight(), true, defaultPosition);

		c1.increaseIntelligenceFromWinningASpell();
		c1.levelsUp();
		c1.levelsUp();

		assertEquals(3, c1.getLevel());
	}

	@Test

	void humanLevelsUpTwoTimesFromLevelOneToLevelThreeFomStrength() {
		Player c1 = new Player("Player1", new Human(), new Knight(), true, defaultPosition);
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
		Player c1 = new Player("elfOne", new Elf(), new Magician(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.levelsUp();
		assertTrue(c1.getIfCanSwim());
	}

	@Test
	void elfCantSwimAtLevelThreeMagicskillNotMoreThanInitial() {
		Player c1 = new Player("elfOne", new Elf(), new Magician(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		c1.loseMagicSkillFromLoss(10);
		c1.levelsUp();
		assertFalse(c1.getIfCanSwim());
	}

	@Test
	void elfCantSwimAtLevelUnderThreeMagicskillMoreThanInitial() {
		Player c1 = new Player("elfOne", new Elf(), new Magician(), true, defaultPosition);
		c1.increaseStrengthFromWinningASpell();//
		c1.levelsUp();
		assertFalse(c1.getIfCanSwim());
	}

	@Test
	void elfCantSwimAtLevelUnderThreeMagicskillNotMoreThanInitial() {
		Player c1 = new Player("elfOne", new Elf(), new Magician(), true, defaultPosition);
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

	@Test
	void fightWithWeakerCharacterMyHealth() {
		Player h = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		Player e = new Player("Human", new Elf(), new Magician(), true, defaultPosition);
		h.fight(e);
		assertThat(h.getRemainingHealth(), equalTo(300));
	}
	
	@Test
	void fightWithWeakerCharacterTheirHealth() {
		Player h = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		Player e = new Player("Human", new Elf(), new Magician(), true, defaultPosition);
		h.fight(e);
		assertThat(e.getRemainingHealth(), equalTo(180));
	}

	@Test
	void fightWithStrongerCharacterMyHealth() {
		Player h = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		Player e = new Player("Human", new Elf(), new Magician(), true, defaultPosition);
		e.fight(h);
		assertThat(e.getRemainingHealth(), equalTo(190));
	}
	
	@Test
	void fightWithStrongerCharacterTheirHealth() {
		Player h = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		Player e = new Player("Human", new Elf(), new Magician(), true, defaultPosition);
		e.fight(h);
		assertThat(h.getRemainingHealth(), equalTo(300));
	}
	
	@Test
	void stealFromWeakerCharacterCheckMyWalletAfter() {
		Player h = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		Player e = new Player("Elf", new Elf(), new Magician(), true, defaultPosition);
		h.steal(e);
		assertThat(h.getMoney(), equalTo(10));
	}
	
	@Test
	void stealFromWeakerCharacterCheckTheirWalletAfter() {
		Player h = new Player("Human", new Human(), new Magician(), true, defaultPosition);
		Player e = new Player("Elf", new Elf(), new Magician(), true, defaultPosition);
		h.steal(e);
		assertThat(e.getMoney(), equalTo(0));
	}

	/** Use **/
	@Test
	void usingWeaponMakesItDamaged() {
		character.gain(sword);
		character.use(sword);
		assertThat(character.canUse(sword), is(true));
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}

	@Test
	void usingFoodMakesItDestroyed() {
		character.gain(potion);
		character.use(potion);
		assertThat(character.canUse(potion), is(true));
		assertThat(potion.getCondition(), is(equalTo(Item.MIN_CONDITION)));
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
		} catch (EnhanceException e) {
			assertThat(e, ExceptionMatcher.isEnhanceException());
		}
	}

	@Test
	void damagingAnItemDecreasesItsCondition() {
		character.damage(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}

	@Test
	void restoringAnItemIncreasesItsCondition() {
		character.damage(sword, 100);
		character.restore(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION + 10)));
	}

	@Test
	void damagingAnItemNeverMakesTheConditionOfTheItemGoBelowMinimumCondition() {
		character.damage(sword, 110);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}

	@Test
	void restoringAnItemNeverMakesTheConditionOfTheItemGoOverMaximumCondition() {
		character.restore(sword, 10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
	}

	@Test
	void damagingAnItemWithANegativeAmountThrowsException() {
		try {
			character.damage(sword, -10);
		} catch (DamageException e) {
			assertThat(e, ExceptionMatcher.isDamageException());
		}
	}

	@Test
	void restoringAnItemWithANegativeAmountThrowsException() {
		try {
			character.restore(sword, -10);
		} catch (RestoreException e) {
			assertThat(e, ExceptionMatcher.isRestoreException());
		}
	}

	@Test
	void characterGainingAnItemsOwnsTheItem() {
		assertThat(character.owns(sword), is(false));
		character.gain(sword);
		assertThat(character.owns(sword), is(true));
	}

	@Test
	void characterLosingAnItemsDoesntOwnTheItem() {
		character.gain(sword);
		assertThat(character.owns(sword), is(true));
		character.lose(sword);
		assertThat(character.owns(sword), is(false));
	}

	@Test
	void characterLosingAnItemMakesItDisappearFromItsInventory() {
		assertThat(character.getItems().contains(sword), is(false));
		assertThat(character.getInventory().contains(sword), is(false));
		character.gain(sword);
		assertThat(character.getItems().contains(sword), is(true));
		assertThat(character.getInventory().contains(sword), is(true));
		character.lose(sword);
		assertThat(character.getItems().contains(sword), is(false));
		assertThat(character.getInventory().contains(sword), is(false));
	}

	@Test
	void characterLosingAnEquippedItemMakesItUnequipped() {
		character.gain(sword);
		assertThat(character.owns(sword), is(true));
		assertThat(character.hasEquipped(sword), is(false));
		assertThat(character.getEquippedItems().contains(sword), is(false));
		assertThat(sword.isEquipped(), is(false));
		character.equip(sword);
		assertThat(character.owns(sword), is(true));
		assertThat(character.hasEquipped(sword), is(true));
		assertThat(character.getEquippedItems().contains(sword), is(true));
		assertThat(sword.isEquipped(), is(true));
		character.lose(sword);
		assertThat(character.owns(sword), is(false));
		assertThat(character.hasEquipped(sword), is(false));
		assertThat(character.getEquippedItems().contains(sword), is(false));
		assertThat(sword.isEquipped(), is(false));
	}

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
		assertThat(character.hasEquipped(sword), is(true));
		assertThat(character.getEquippedItems().contains(sword), is(true));
		assertThat(sword.isEquipped(), is(true));
		character.unequip(sword);
		assertThat(character.hasEquipped(sword), is(false));
		assertThat(character.getEquippedItems().contains(sword), is(false));
		assertThat(sword.isEquipped(), is(false));
	}

	@Test
	void characterTryingToUnequipAnItemThatIsNotEquippedThrowsIAE() {
		assertThat(character.owns(sword), is(false));
		assertThat(character.hasEquipped(sword), is(false));
		try {
			character.unequip(sword);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isUnequipException());
		}
	}

	@Test
	void characterCanEquipThatIsOwned() {
		character.gain(sword);
		assertThat(character.owns(sword), is(true));
		assertThat(character.canEquip(sword), is(true));
	}

	@Test
	void characterCantEquipThatIsNotOwned() {
		assertThat(character.owns(sword), is(false));
		assertThat(character.canEquip(sword), is(false));
	}

	@Test
	void foodCantBeEquipped() {
		assertThat(character.owns(potion), is(false));
		assertThat(character.canEquip(potion), is(false));
		character.gain(potion);
		assertThat(character.owns(potion), is(true));
		assertThat(character.canEquip(potion), is(false));
	}

	@Test
	void characterCanGiveItemThatIsOwnedAndUnequipped() {
		character.gain(sword);
		character.equip(sword);
		character.unequip(sword);
		assertThat(character.owns(sword), is(true));
		assertThat(character.hasEquipped(sword), is(false));
		assertThat(character.canGive(sword), is(true));
	}

	@Test
	void characterCantGiveItemThatIsNotOwned() {
		assertThat(character.owns(sword), is(false));
		assertThat(character.canGive(sword), is(false));
	}

	@Test
	void characterCantGiveItemThatIsEquipped() {
		character.gain(sword);
		character.equip(sword);
		assertThat(character.owns(sword), is(true));
		assertThat(character.hasEquipped(sword), is(true));
		assertThat(character.canGive(sword), is(false));
	}

	@Test
	void characterCantReceiveItemThatCharacterAlreadyOwns() {
		character.gain(sword);
		assertThat(character.owns(sword), is(true));
		assertThat(character.getInventory().isFull(), is(false));
		assertThat(character.canReceive(sword), is(false));
	}

	@Test
	void characterCantReceiveItemWhenInventoryIsFull() {
		while (character.getInventory().hasAvailableSpace()) {
			character.getInventory().add(new Sword());
		}
		assertThat(character.owns(sword), is(false));
		assertThat(character.getInventory().isFull(), is(true));
		assertThat(character.canReceive(sword), is(false));
	}

	@Test
	void characterGivingAnItemToAnotherCharacterMakesTheItemBecomeOwnedByTheOtherCharacter() {
		Character giver = players[0];
		Character receiver = players[1];
		giver.gain(sword);
		assertThat(giver.owns(sword), is(true));
		assertThat(receiver.owns(sword), is(false));
		assertThat(sword.canBeGiven(giver, receiver), is(true));
		giver.give(sword, receiver);
		assertThat(giver.owns(sword), is(false));
		assertThat(receiver.owns(sword), is(true));
	}

	@Test
	void characterTryingToGiveAnItemThatCantBeGivenToAnotherCharacterThrowsIAE() {
		Character giver = players[0];
		Character receiver = players[1];
		receiver.gain(sword);
		assertThat(giver.owns(sword), is(false));
		assertThat(receiver.owns(sword), is(true));
		assertThat(sword.canBeGiven(giver, receiver), is(false));
		try {
			giver.give(sword, receiver);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isGiveException());
		}
	}

	@Test
	void characterThatCanReceiveAndCanAffordItemCanBuyAnItem() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		buyer.gainMoney(1000);
		assertThat(buyer.canReceive(sword), is(true));
		assertThat(buyer.canAfford(sword), is(true));
		assertThat(buyer.canBuy(sword), is(true));
	}

	@Test
	void characterThatCanReceiveButCantAffordItemCantBuyAnItem() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		buyer.gainMoney(50);
		assertThat(buyer.canReceive(sword), is(true));
		assertThat(buyer.canAfford(sword), is(false));
		assertThat(buyer.canBuy(sword), is(false));
	}

	@Test
	void characterThatCanGiveAnItemCanSellTheItem() {
		Character seller = players[0];
		seller.gain(sword);
		assertThat(seller.canGive(sword), is(true));
		assertThat(seller.canSell(sword), is(true));
	}

	@Test
	void characterThatCantGiveAnItemCantSellTheItem() {
		Character seller = players[0];
		seller.gain(sword);
		seller.equip(sword);
		assertThat(seller.canGive(sword), is(false));
		assertThat(seller.canSell(sword), is(false));
	}

	@Test
	void characterBuyingAnItemMakesBuyersAmountOfMoneyDecrease() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		buyer.gainMoney(1000);
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
		buyer.gainMoney(1000);
		assertThat(sword.canBeSold(seller, buyer), is(true));
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
		buyer.buy(sword, seller);
		assertThat(buyer.owns(sword), is(true));
	}

	@Test
	void buyingAnItemMakesItAppearInBuyersInventory() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(potion);
		buyer.gainMoney(1000);
		buyer.buy(potion, seller);
		assertThat(buyer.getInventory().contains(potion), is(true));
	}

	@Test
	void characterWhoHasSoldAnItemDoesntTheItemAfterTransaction() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.gainMoney(5000);
		buyer.gainMoney(1000);
		seller.sell(sword, buyer);
		assertThat(seller.owns(sword), is(false));
	}

	@Test
	void characterTryingToBuyAnItemThatCantBeBoughtThrowsIAE() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.equip(sword);
		buyer.gainMoney(1000);
		try {
			buyer.buy(sword, seller);
		} catch (BuyException e) {
			assertThat(e, ExceptionMatcher.isBuyException());
		}
	}

	@Test
	void characterTryingToSellAnItemThatCantBeBoughtThrowsIAE() {
		Character seller = players[0];
		Character buyer = players[1];
		seller.gain(sword);
		seller.equip(sword);
		buyer.gainMoney(1000);
		try {
			seller.sell(sword, buyer);
		} catch (IllegalArgumentException e) {
			assertThat(e, ExceptionMatcher.isSellException());
		}
	}

	@Test
	void characterGainingAndLosingMoneyUpdatesContentInWallet() {
		Wallet wallet = character.getWallet();
		character.gainMoney(1000);
		character.loseMoney(2000);
		assertThat(wallet.getAmount(), is(equalTo(-1000)));
	}

	@Test
	void characterGainingNegativeAmountOfMoneyThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			character.gainMoney(-1000);
		});
	}

	@Test
	void characterLosingNegativeAmountOfMoneyThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			character.loseMoney(-1000);
		});
	}

	@Test
	void characterCantAffordAnItemIfHasNotEnoughMoney() {
		assertThat(character.canAfford(ring), is(false));
	}

	@Test
	void characterCanAffordARingIfHasEnoughMoney() {
		character.gainMoney(5000);
		assertThat(character.canAfford(ring), is(true));
	}

	@Test
	void TF1_itemsCanBeGainedLostEquippedUnequippedWhenStateIsCorrect() {
		character.gain(sword);
		character.give(sword, otherCharacter);
		otherCharacter.give(sword, character);
		character.equip(sword);
		character.unequip(sword);
		character.lose(sword);
		character.gain(sword);
		character.equip(sword);
		character.lose(sword);
		assertTrue(!sword.isOwned() && !sword.isEquipped());
	}

	@Test
	void TF2_unownedItemsCantBeLost() {
		try {
			character.lose(sword);
		} catch (LoseException e) {
			assertThat(e, ExceptionMatcher.isLoseException());
		}
	}

	@Test
	void TF3_unownedItemsCantBeEquipped() {
		try {
			character.equip(sword);
		} catch (EquipException e) {
			assertThat(e, ExceptionMatcher.isEquipException());
		}
	}

	@Test
	void TF4_unownedItemsCantBeUnequipped() {
		try {
			character.unequip(sword);
		} catch (UnequipException e) {
			assertThat(e, ExceptionMatcher.isUnequipException());
		}
	}

	@Test
	void TF5_unownedItemsCantBeGivenAway() {
		assertTrue(!sword.isOwned() && !sword.isEquipped());
		try {
			character.give(sword, otherCharacter);
		} catch (GiveException e) {
			assertThat(e, ExceptionMatcher.isGiveException());
		}
	}

	@Test
	void TF6_ownedItemsCantBeGained() {
		character.gain(sword);
		try {
			character.gain(sword);
		} catch (GainException e) {
			assertThat(e, ExceptionMatcher.isGainException());
		}
	}

	@Test
	void TF7_unequippedItemsCantBeUnequippedAgain() {
		character.gain(sword);
		try {
			character.unequip(sword);
		} catch (UnequipException e) {
			assertThat(e, ExceptionMatcher.isUnequipException());
		}
	}

	@Test
	void TF8_equippedItemsCantBeGained() {
		character.gain(sword);
		character.equip(sword);
		try {
			character.gain(sword);
		} catch (GainException e) {
			assertThat(e, ExceptionMatcher.isGainException());
		}
	}

	@Test
	void TF9_equippedItemsCantBeEquippedAgain() {
		assertTrue(!sword.isOwned() && !sword.isEquipped());
		character.gain(sword);
		assertTrue(sword.isOwned() && !sword.isEquipped());
		character.equip(sword);
		assertTrue(sword.isOwned() && sword.isEquipped());
		try {
			character.equip(sword);
		} catch (EquipException e) {
			assertThat(e, ExceptionMatcher.isEquipException());
		}
	}

	@Test
	void TF10_equippedItemsCantBeGivenAway() {
		assertTrue(!sword.isOwned() && !sword.isEquipped());
		character.gain(sword);
		assertTrue(sword.isOwned() && !sword.isEquipped());
		character.equip(sword);
		assertTrue(sword.isOwned() && sword.isEquipped());
		try {
			character.give(sword, otherCharacter);
		} catch (GiveException e) {
			assertThat(e, ExceptionMatcher.isGiveException());
		}
	}// Jon

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
			assertThat(e, ExceptionMatcher.isCantWalkOutsideMapException());
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
	// Emma

}
