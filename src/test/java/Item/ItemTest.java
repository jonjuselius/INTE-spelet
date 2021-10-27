package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import GameCharacters.*;
import GameCharacters.Character;
import Jobs.*;
import Map.*;
import Races.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class ItemTest {
	private Item[] items = {new Sword(), new Wand(), new Potion(), new Shield(), new Ring()};
	private Item[] swordSizes = {new Sword(Size.SMALL), new Sword(Size.MEDIUM), new Sword(Size.LARGE)};
	private Item sword = items[0];
	private Item wand = items[1];
	private Item egg = items[2];
	private Item shield = items[3];
	private Item ring = items[4];
	private Item smallSword = swordSizes[0];
	private Item mediumSword = swordSizes[1];
	private Item largeSword = swordSizes[2];
	private GameMapPosition defaultMapPosition = (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2];
	private Character character = new Player("Default character", new Human(), new Knight(), true, defaultMapPosition);
	private List<Race> races = new ArrayList<>(Race.getAllRaces());
	private List<Job> jobs = new ArrayList<>(Job.getAllJobs());
	private Character[] defaultPlayers = {
			new Player("Player 1", races.get(0), jobs.get(0), true, defaultMapPosition),
			new Player("Player 2", races.get(0), jobs.get(0), true, defaultMapPosition)
	};
	
	@Test
	void newItemIsNotOwned() {
		assertThat(sword.isOwned(), is(equalTo(false)));
	}
	
	@Test
	void newItemIsNotEquipped() {
		assertThat(sword.isEquipped(), is(equalTo(false)));
	}
	
	@Test
	void newItemIsNotEnhanced() {
		assertThat(sword.isEnhanced(), is(equalTo(false)));
	}
	
	@Test
	void settingMapPositionToAnItemWorks() {
		sword.setMapPosition(defaultMapPosition);
		assertThat(sword.getMapPosition(), is(equalTo(defaultMapPosition)));
	}
	
	@Test
	void characterGainingAnItemMakesTheItemOwned() {
		character.gain(sword);
		assertThat(sword.isOwned(), is(equalTo(true)));
	}
	
	@Test
	void destroyedSwordHasMinimumCondition() {
		sword.becomeDestroyed();
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void recoveredSwordHasMaximumCondition() {
		sword.becomeDestroyed();
		sword.becomeRecovered();
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
	}
	
	@Test
	void damagedSwordHasDecreasedCondition() {
		sword.becomeDamaged(10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	@Test
	void restoredSwordHasIncreasedCondition() {
		sword.becomeDestroyed();
		sword.becomeRestored(10);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION + 10)));
	}
	
	@Test
	void smallItemIsEnhancable() {
		assertThat(smallSword.isSmall(), is(equalTo(true)));
		assertThat(smallSword.isEnhancable(), is(equalTo(true)));
	}
	
	@Test
	void mediumItemIsNotEnhancable() {
		assertThat(mediumSword.isMedium(), is(equalTo(true)));
		assertThat(mediumSword.isEnhancable(), is(equalTo(false)));
	}
	
	@Test
	void largeItemIsNotEnhancable() {
		assertThat(largeSword.isLarge(), is(equalTo(true)));
		assertThat(largeSword.isEnhancable(), is(equalTo(false)));
	}
	
	@Test
	void destroyedItemIsNotDestroyable() {
		sword.becomeDestroyed();
		assertThat(sword.isDestroyable(), is(equalTo(false)));
	}
	
	@Test
	void undestroyedItemIsDestroyable() {
		assertThat(sword.isDestroyable(), is(equalTo(true)));
	}
	
	@Test
	void restoringAnItemWithPerfectConditionDoesntIncreaseItsCondition() {
		sword.becomeRestored(10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
		assertThat(sword.isPerfect(), is(equalTo(true)));
	}
	
	@Test
	void itemWithMaxConditionIsNotRecoverable() {
		assertThat(sword.isRecoverable(), is(equalTo(false)));
	}
	
	@Test
	void itemWithConditionLessThanMaxConditionIsRecoverable() {
		sword.becomeDamaged(10);
		assertThat(sword.isRecoverable(), is(equalTo(true)));
	}
	
	@Test
	void itemThatIsOwnedByOneCharacterCanBeGivenToAnotherCharacter() {
		Character player1 = defaultPlayers[0];
		Character player2 = defaultPlayers[1];
		player1.gain(sword);
		assertThat(sword.canBeGiven(player1, player2), is(equalTo(true)));
	}
	
	@Test
	void itemThatIsOwnedByOneCharacterCanBeSoldToAnotherCharacter() {
		Character player1 = defaultPlayers[0];
		Character player2 = defaultPlayers[1];
		player1.gain(sword);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(true)));
	}
	
	@Test
	void foodCanBeEatenByACharacter() {
		assertThat(egg.isFood(), is(equalTo(true)));
		assertThat(egg.canBeEatenBy(character), is(equalTo(true)));
	}
	
	@Test
	void weaponThatIsOwnedIsEquippable() {
		character.gain(sword);
		assertThat(sword.isWeapon(), is(equalTo(true)));
		assertThat(sword.isOwned(), is(equalTo(true)));
		assertThat(sword.isEquippable(), is(equalTo(true)));
	}
	
	@Test
	void weaponThatIsNotOwnedIsNotEquippable() {
		character.gain(sword);
		character.lose(sword);
		assertThat(sword.isWeapon(), is(equalTo(true)));
		assertThat(sword.isOwned(), is(equalTo(false)));
		assertThat(sword.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void armorThatIsOwnedIsEquippable() {
		character.gain(shield);
		assertThat(shield.isArmor(), is(equalTo(true)));
		assertThat(shield.isOwned(), is(equalTo(true)));
		assertThat(shield.isEquippable(), is(equalTo(true)));
	}
	
	@Test
	void jewelleryThatIsOwnedIsEquippable() {
		character.gain(ring);
		assertThat(ring.isJewewllery(), is(equalTo(true)));
		assertThat(ring.isOwned(), is(equalTo(true)));
		assertThat(ring.isEquippable(), is(equalTo(true)));
	}
	
	@Test
	void jewelleryThatIsNotOwnedIsEquippable() {
		assertThat(ring.isOwned(), is(equalTo(false)));
		assertThat(ring.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void foodThatIsOwnedIsNotEquippable() {
		character.gain(egg);
		assertThat(egg.isFood(), is(equalTo(true)));
		assertThat(egg.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void foodThatIsNotOwnedIsNotEquippable() {
		assertThat(egg.isFood(), is(equalTo(true)));
		assertThat(egg.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void settingAnEnhancableItemAsEnhancedMakesItEnhanced() {
		assertThat(smallSword.isSmall(), is(equalTo(true)));
		assertThat(smallSword.isEnhancable(), is(equalTo(true)));
		smallSword.setEnhanced(true);
		assertThat(smallSword.isEnhanced(), is(equalTo(true)));
	}
	
	@Test
	void settingAnUnenhancableItemAsEnhancedDoesntMakeItEnhanced() {
		assertThat(mediumSword.isMedium(), is(equalTo(true)));
		assertThat(mediumSword.isEnhancable(), is(equalTo(false)));
		mediumSword.setEnhanced(true);
		assertThat(mediumSword.isEnhanced(), is(equalTo(false)));
	}
	
	@Test
	void settingAnEquippableItemAsEquippedMakesItEquipped() {
		character.gain(sword);
		assertThat(sword.isEquippable(), is(equalTo(true)));
		sword.setEquipped(true);
		assertThat(sword.isEquipped(), is(equalTo(true)));
	}
	
	@Test
	void settingAnUnequippableItemAsEquippedMakesItNotEquipped() {
		assertThat(sword.isEquippable(), is(equalTo(false)));
		sword.setEquipped(true);
		assertThat(sword.isEquipped(), is(equalTo(false)));
	}
	
	@Test
	void damagingADestroyedItemDoesntDecreaseItsCondition() {
		sword.becomeDestroyed();
		assertThat(sword.isDestroyed(), is(equalTo(true)));
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
		sword.becomeDamaged(10);
		assertThat(sword.isDestroyed(), is(equalTo(true)));
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
}