package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Job;
import Map.GameMap;
import Map.GameMapPosition;
import Map.GameMapGenerator;
import Races.Race;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PotionTest {
	private Item sword = new Sword();
	private Item wand = new Wand();
	private Item potion = new Potion();
	private Item shield = new Shield();
	private Item ring = new Ring();
	private GameMapGenerator mapGenerator = new GameMapGenerator(4, 4);
	private GameMap defaultMap = mapGenerator.generate(1);
	private GameMapPosition defaultMapPosition = defaultMap.getMapTiles()[2][2];
	private List<Race> races = new ArrayList<>(Race.getAllRaces());
	private List<Job> jobs = new ArrayList<>(Job.getAllJobs());
	private Race human = races.get(0);
	private Race ogre = races.get(1);
	private Race elf = races.get(2);
	private Job knight = jobs.get(0);
	private Job magician = jobs.get(1);
	private Job healer = jobs.get(2);
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
	void R19_canUse() {
		assertThat(knightHuman.canUse(potion), is(true));
	}
	
	@Test
	void R20_canUse() {
		assertThat(knightOgre.canUse(potion), is(true));
	}
	
	@Test
	void R21_canUse() {
		assertThat(knightElf.canUse(potion), is(true));
	}
	
	@Test
	void R22_canUse() {
		assertThat(magicianHuman.canUse(potion), is(true));
	}
	
	@Test
	void R23_canUse() {
		assertThat(magicianOgre.canUse(potion), is(true));
	}
	
	@Test
	void R24_canUse() {
		assertThat(magicianElf.canUse(potion), is(true));
	}
	
	@Test
	void R25_canUse() {
		assertThat(healerHuman.canUse(potion), is(true));
	}
	
	@Test
	void R26_canUse() {
		assertThat(healerOgre.canUse(potion), is(true));
	}
	
	@Test
	void R27_canUse() {
		assertThat(healerElf.canUse(potion), is(true));
	}
	
	@Test
	void newPotionHasDefaultWeight() {
		assertThat(new Potion().getWeight(), is(equalTo(Potion.WEIGHT)));
	}
	
	@Test
	void newPotionHasDefaultValue() {
		assertThat(new Potion().getValue(), is(equalTo(Potion.VALUE)));
	}
	
	@Test
	void newPotionHasDefaultSize() {
		assertThat(new Potion().getSize(), is(equalTo(Potion.DEFAULT_SIZE)));
	}
	
	@Test
	void newPotionHasDefaultType() {
		assertThat(new Potion().getType(), is(equalTo(Potion.TYPE)));
	}
	
	@Test
	void newPotionHasDefaultCondition() {
		assertThat(new Potion().getCondition(), is(equalTo(Potion.DEFAULT_CONDITION)));
	}
	
	@Test
	void newPotionWithSizeSpecifiedAsSmallInConstructorCreatesASmallEgg() {
		assertThat(new Potion(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newPotionWithSizeSpecifiedAsMediumInConstructorCreatesAMediumEgg() {
		assertThat(new Potion(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newPotionWithSizeSpecifiedAsLargeInConstructorCreatesALargeEgg() {
		assertThat(new Potion(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void newPotionWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Potion(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void newPotionWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Potion(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newPotionWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Potion(Item.MAX_CONDITION + 1));
	}
}