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
	void eggIsFood() {
		Item egg = new Egg();
		assertThat(egg.isFood(), is(equalTo(true)));
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
		assertThat(sword.isEnhancable(), is(equalTo(true)));
	}
	
	@Test
	void mediumItemIsNotEnhancable() {
		Item sword = new Sword(Size.MEDIUM);
		assertThat(sword.isEnhancable(), is(equalTo(false)));
	}
	
	@Test
	void largeItemIsNotEnhancable() {
		Item sword = new Sword(Size.LARGE);
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
}