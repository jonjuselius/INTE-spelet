package Item;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Exceptions.*;
import GameCharacters.*;
import GameCharacters.Character;
import Jobs.*;
import Map.*;
import Races.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ItemTest {
	private Item[] items = {new Sword(), new Wand(), new Potion(), new Shield(), new Ring()};
	private Item[] swordSizes = {new Sword(Size.SMALL), new Sword(Size.MEDIUM), new Sword(Size.LARGE)};
	private Item sword = items[0];
	private Item wand = items[1];
	private Item potion = items[2];
	private Item shield = items[3];
	private Item ring = items[4];
	private Item smallSword = swordSizes[0];
	private Item mediumSword = swordSizes[1];
	private Item largeSword = swordSizes[2];
	private List<Race> races = new ArrayList<>(Race.getAllRaces());
	private List<Job> jobs = new ArrayList<>(Job.getAllJobs());
	private Race human = races.get(0);
	private Race ogre = races.get(1);
	private Race elf = races.get(2);
	private Job knight = jobs.get(0);
	private Job magician = jobs.get(1);
	private Job healer = jobs.get(2);
	private GameMapPosition defaultMapPosition = (new GameMapGenerator(4, 4)).generate(1).getMapTiles()[2][2];
	private Character character = new Player("Default character", human, knight, true, defaultMapPosition);
	private Character otherCharacter = new Player("Other character", human, knight, true, defaultMapPosition);
	private Character[] defaultCharacters = {
			new Player("Player 1", races.get(0), jobs.get(0), true, defaultMapPosition),
			new Player("Player 2", races.get(0), jobs.get(0), true, defaultMapPosition)
	};
	private Character[] characters = {
			new Player("Player", human, knight, true, defaultMapPosition),
			new Player("Player", ogre, knight, true, defaultMapPosition),
			new Player("Player", elf, knight, true, defaultMapPosition),
			new Player("Player", human, magician, true, defaultMapPosition),
			new Player("Player", ogre, magician, true, defaultMapPosition),
			new Player("Player", elf, magician, true, defaultMapPosition),
			new Player("Player", human, healer, true, defaultMapPosition),
			new Player("Player", ogre, healer, true, defaultMapPosition),
			new Player("Player", elf, healer, true, defaultMapPosition),
	};
	private List<Character> players = Arrays.asList(characters);
	private Character knightHuman = characters[0];
	private Character knightOgre = characters[1];
	private Character knightElf = characters[2];
	private Character magicianHuman = characters[3];
	private Character magicianOgre = characters[4];
	private Character magicianElf = characters[5];
	private Character healerHuman = characters[6];
	private Character healerOgre = characters[7];
	private Character healerElf = characters[8];
	
	@Test
	void BT1_knightsCanUseSwords() {
		assertThat(knightHuman.canUse(sword), is(true));
	}
	
	@Test
	void BT2_otherJobsThanKnightsCantUseSwords() {
		assertThat(magicianHuman.canUse(sword), is(false));
	}
	
	@Test
	void BT3_magiciansAndHealersCanUseWands() {
		assertThat(healerHuman.canUse(wand), is(true));
	}
	
	@Test
	void BT4_otherJobsThanMagiciansOrHealersCantUseWands() {
		assertThat(knightOgre.canUse(wand), is(false));
	}
	
	@Test
	void BT5_everyJobCanUsePotions() {
		assertThat(healerElf.canUse(potion), is(true));
	}
	
	@Test
	void BT6_knightsCanUseShields() {
		assertThat(knightElf.canUse(shield), is(true));
	}
	
	@Test
	void BT7_otherJobsThanKnightsCantUseShields() {
		assertThat(healerOgre.canUse(shield), is(false));
	}
	
	@Test
	void BT8_everyJobCanUseRings() {
		assertThat(magicianOgre.canUse(ring), is(true));
	}
	
	@Test
	void settingMapPositionToAnItemWorks() {
		sword.setMapPosition(defaultMapPosition);
		assertThat(sword.getMapPosition(), is(equalTo(defaultMapPosition)));
	}
	
	@Test
	void smallItemIsEnhancable() {
		assertThat(smallSword.isSmall(), is(true));
		assertThat(smallSword.isEnhancable(), is(true));
	}
	
	@Test
	void mediumItemIsNotEnhancable() {
		assertThat(mediumSword.isMedium(), is(true));
		assertThat(mediumSword.isEnhancable(), is(false));
	}
	
	@Test
	void largeItemIsNotEnhancable() {
		assertThat(largeSword.isLarge(), is(true));
		assertThat(largeSword.isEnhancable(), is(false));
	}
	
	@Test
	void itemThatIsOwnedByOneCharacterCanBeGivenToAnotherCharacter() {
		Character player1 = defaultCharacters[0];
		Character player2 = defaultCharacters[1];
		player1.gain(sword);
		assertThat(sword.canBeGiven(player1, player2), is(true));
	}
	
	@Test
	void itemThatIsOwnedByOneCharacterCanBeSoldToAnotherCharacter() {
		Character player1 = defaultCharacters[0];
		Character player2 = defaultCharacters[1];
		player1.gain(sword);
		player2.gainMoney(1000);
		assertThat(sword.canBeSold(player1, player2), is(true));
	}
	
	@Test
	void weaponThatIsOwnedIsEquippable() {
		character.gain(sword);
		assertThat(sword.isWeapon(), is(true));
		assertThat(sword.isOwned(), is(true));
		assertThat(sword.isEquippable(), is(true));
	}
	
	@Test
	void weaponThatIsNotOwnedIsNotEquippable() {
		character.gain(sword);
		character.lose(sword);
		assertThat(sword.isWeapon(), is(true));
		assertThat(sword.isOwned(), is(false));
		assertThat(sword.isEquippable(), is(false));
	}
	
	@Test
	void armorThatIsOwnedIsEquippable() {
		character.gain(shield);
		assertThat(shield.isArmor(), is(true));
		assertThat(shield.isOwned(), is(true));
		assertThat(shield.isEquippable(), is(true));
	}
	
	@Test
	void jewelleryThatIsOwnedIsEquippable() {
		character.gain(ring);
		assertThat(ring.isJewewllery(), is(true));
		assertThat(ring.isOwned(), is(true));
		assertThat(ring.isEquippable(), is(true));
	}
	
	@Test
	void jewelleryThatIsNotOwnedIsEquippable() {
		assertThat(ring.isOwned(), is(false));
		assertThat(ring.isEquippable(), is(false));
	}
	
	@Test
	void foodThatIsOwnedIsNotEquippable() {
		character.gain(potion);
		assertThat(potion.isFood(), is(true));
		assertThat(potion.isEquippable(), is(false));
	}
	
	@Test
	void foodThatIsNotOwnedIsNotEquippable() {
		assertThat(potion.isFood(), is(true));
		assertThat(potion.isEquippable(), is(false));
	}
	
	@Test
	void settingAnEnhancableItemAsEnhancedMakesItEnhanced() {
		assertThat(smallSword.isSmall(), is(true));
		assertThat(smallSword.isEnhancable(), is(true));
		smallSword.setEnhanced(true);
		assertThat(smallSword.isEnhanced(), is(true));
	}
	
	@Test
	void settingAnUnenhancableItemAsEnhancedDoesntMakeItEnhanced() {
		assertThat(mediumSword.isMedium(), is(equalTo(true)));
		assertThat(mediumSword.isEnhancable(), is(false));
		mediumSword.setEnhanced(true);
		assertThat(mediumSword.isEnhanced(), is(false));
	}
	
	@Test
	void settingAnEquippableItemAsEquippedMakesItEquipped() {
		character.gain(sword);
		assertThat(sword.isEquippable(), is(true));
		sword.setEquipped(true);
		assertThat(sword.isEquipped(), is(true));
	}
	
	@Test
	void settingAnUnequippableItemAsEquippedMakesItNotEquipped() {
		assertThat(sword.isEquippable(), is(false));
		sword.setEquipped(true);
		assertThat(sword.isEquipped(), is(false));
	}
	
	@Test
	void itemThatBecomesDamagedWithANegativeAmountThrowsException() {
		assertThrows(DamageException.class, () -> {
			sword.becomeDamaged(-10);
		});
	}
	
	@Test
	void itemThatBecomesRestoredWithANegativeAmountThrowsException() {
		assertThrows(RestoreException.class, () -> {
			sword.becomeRestored(-10);
		});
	}
}