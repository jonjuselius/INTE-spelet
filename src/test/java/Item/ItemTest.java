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

class ItemTest {
	
	MapPosition defaultMapPosition = (new MapGenerator(4, 4)).generate(1).getMapTiles()[2][2];
	Character character = new Player("Default character", new Human(), new Knight(), true, defaultMapPosition);
	
	@Test
	void newItemIsNotOwned() {
		Item sword = new Sword();
		assertThat(sword.isOwned(), is(equalTo(false)));
	}
	
	@Test
	void newItemIsNotEquipped() {
		Item sword = new Sword();
		assertThat(sword.isEquipped(), is(equalTo(false)));
	}
	
	@Test
	void newItemIsNotEnhanced() {
		Item sword = new Sword();
		assertThat(sword.isEnhanced(), is(equalTo(false)));
	}
	
	@Test
	void settingMapPositionToAnItemWorks() {
		Item sword = new Sword();
		sword.setMapPosition(defaultMapPosition);
		assertThat(sword.getMapPosition(), is(equalTo(defaultMapPosition)));
	}
	
	@Test
	void characterGainingAnItemMakesTheItemOwned() {
		Item sword = new Sword();
		character.gain(sword);
		assertThat(sword.isOwned(), is(equalTo(true)));
	}
	
	@Test
	void destroyedSwordHasMinimumCondition() {
		Item sword = new Sword();
		sword.becomeDestroyed();
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
	
	@Test
	void recoveredSwordHasMaximumCondition() {
		Item sword = new Sword();
		sword.becomeDestroyed();
		sword.becomeRecovered();
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
	}
	
	@Test
	void damagedSwordHasDecreasedCondition() {
		Item sword = new Sword();
		sword.becomeDamaged(10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION - 10)));
	}
	
	@Test
	void restoredSwordHasIncreasedCondition() {
		Item sword = new Sword();
		sword.becomeDestroyed();
		sword.becomeRestored(10);
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION + 10)));
	}
	
	@Test
	void smallItemIsEnhancable() {
		Item sword = new Sword(Size.SMALL);
		assertThat(sword.isSmall(), is(equalTo(true)));
		assertThat(sword.isEnhancable(), is(equalTo(true)));
	}
	
	@Test
	void mediumItemIsNotEnhancable() {
		Item sword = new Sword(Size.MEDIUM);
		assertThat(sword.isMedium(), is(equalTo(true)));
		assertThat(sword.isEnhancable(), is(equalTo(false)));
	}
	
	@Test
	void largeItemIsNotEnhancable() {
		Item sword = new Sword(Size.LARGE);
		assertThat(sword.isLarge(), is(equalTo(true)));
		assertThat(sword.isEnhancable(), is(equalTo(false)));
	}
	
	@Test
	void destroyedItemIsNotDestroyable() {
		Item sword = new Sword();
		sword.becomeDestroyed();
		assertThat(sword.isDestroyable(), is(equalTo(false)));
	}
	
	@Test
	void undestroyedItemIsDestroyable() {
		Item sword = new Sword();
		assertThat(sword.isDestroyable(), is(equalTo(true)));
	}
	
	@Test
	void restoringAnItemWithPerfectConditionDoesntIncreaseItsCondition() {
		Item sword = new Sword();
		sword.becomeRestored(10);
		assertThat(sword.getCondition(), is(equalTo(Item.MAX_CONDITION)));
		assertThat(sword.isPerfect(), is(equalTo(true)));
	}
	
	@Test
	void itemWithMaxConditionIsNotRecoverable() {
		Item sword = new Sword();
		assertThat(sword.isRecoverable(), is(equalTo(false)));
	}
	
	@Test
	void itemWithConditionLessThanMaxConditionIsRecoverable() {
		Item sword = new Sword();
		sword.becomeDamaged(10);
		assertThat(sword.isRecoverable(), is(equalTo(true)));
	}
	
	@Test
	void itemThatIsOwnedByOneCharacterCanBeGivenToAnotherCharacter() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, defaultMapPosition);
		Item sword = new Sword();
		player1.gain(sword);
		assertThat(sword.canBeGiven(player1, player2), is(equalTo(true)));
	}
	
	@Test
	void itemThatIsOwnedByOneCharacterCanBeSoldToAnotherCharacter() {
		Character player1 = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Character player2 = new Player("Player 2", new Human(), new Knight(), true, defaultMapPosition);
		Item sword = new Sword();
		player1.gain(sword);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(equalTo(true)));
	}
	
	@Test
	void foodCanBeEatenByACharacter() {
		Character character = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Item egg = new Egg();
		assertThat(egg.isFood(), is(equalTo(true)));
		assertThat(egg.canBeEatenBy(character), is(equalTo(true)));
	}
	
	@Test
	void weaponThatIsOwnedIsEquippable() {
		Character character = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Item sword = new Sword();
		character.gain(sword);
		assertThat(sword.isWeapon(), is(equalTo(true)));
		assertThat(sword.isOwned(), is(equalTo(true)));
		assertThat(sword.isEquippable(), is(equalTo(true)));
	}
	
	@Test
	void weaponThatIsNotOwnedIsNotEquippable() {
		Character character = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Item sword = new Sword();
		character.gain(sword);
		character.lose(sword);
		assertThat(sword.isWeapon(), is(equalTo(true)));
		assertThat(sword.isOwned(), is(equalTo(false)));
		assertThat(sword.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void armorThatIsOwnedIsEquippable() {
		Character character = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Item shield = new Shield();
		character.gain(shield);
		assertThat(shield.isArmor(), is(equalTo(true)));
		assertThat(shield.isOwned(), is(equalTo(true)));
		assertThat(shield.isEquippable(), is(equalTo(true)));
	}
	
	@Test
	void jewelleryThatIsOwnedIsEquippable() {
		Character character = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Item ring = new Ring();
		character.gain(ring);
		assertThat(ring.isJewewllery(), is(equalTo(true)));
		assertThat(ring.isOwned(), is(equalTo(true)));
		assertThat(ring.isEquippable(), is(equalTo(true)));
	}
	
	@Test
	void jewelleryThatIsNotOwnedIsEquippable() {
		Item ring = new Ring();
		assertThat(ring.isOwned(), is(equalTo(false)));
		assertThat(ring.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void foodThatIsOwnedIsNotEquippable() {
		Character character = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Item egg = new Egg();
		character.gain(egg);
		assertThat(egg.isFood(), is(equalTo(true)));
		assertThat(egg.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void foodThatIsNotOwnedIsNotEquippable() {
		Item egg = new Egg();
		assertThat(egg.isFood(), is(equalTo(true)));
		assertThat(egg.isEquippable(), is(equalTo(false)));
	}
	
	@Test
	void settingAnEnhancableItemAsEnhancedMakesItEnhanced() {
		Item sword = new Sword(Size.SMALL);
		assertThat(sword.isSmall(), is(equalTo(true)));
		assertThat(sword.isEnhancable(), is(equalTo(true)));
		sword.setEnhanced(true);
		assertThat(sword.isEnhanced(), is(equalTo(true)));
	}
	
	@Test
	void settingAnUnenhancableItemAsEnhancedDoesntMakeItEnhanced() {
		Item sword = new Sword(Size.MEDIUM);
		assertThat(sword.isMedium(), is(equalTo(true)));
		assertThat(sword.isEnhancable(), is(equalTo(false)));
		sword.setEnhanced(true);
		assertThat(sword.isEnhanced(), is(equalTo(false)));
	}
	
	@Test
	void settingAnEquippableItemAsEquippedMakesItEquipped() {
		Character character = new Player("Player 1", new Human(), new Knight(), true, defaultMapPosition);
		Item sword = new Sword();
		character.gain(sword);
		assertThat(sword.isEquippable(), is(equalTo(true)));
		sword.setEquipped(true);
		assertThat(sword.isEquipped(), is(equalTo(true)));
	}
	
	@Test
	void settingAnUnequippableItemAsEquippedMakesItNotEquipped() {
		Item sword = new Sword();
		assertThat(sword.isEquippable(), is(equalTo(false)));
		sword.setEquipped(true);
		assertThat(sword.isEquipped(), is(equalTo(false)));
	}
	
	@Test
	void damagingADestroyedItemDoesntDecreaseItsCondition() {
		Item sword = new Sword();
		sword.becomeDestroyed();
		assertThat(sword.isDestroyed(), is(equalTo(true)));
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
		sword.becomeDamaged(10);
		assertThat(sword.isDestroyed(), is(equalTo(true)));
		assertThat(sword.getCondition(), is(equalTo(Item.MIN_CONDITION)));
	}
}