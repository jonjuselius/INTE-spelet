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
		assertThat(potion.canBeUsedBy(knightHuman), is(true));
	}
	
	@Test
	void R20_canUse() {
		assertThat(potion.canBeUsedBy(knightOgre), is(true));
	}
	
	@Test
	void R21_canUse() {
		assertThat(potion.canBeUsedBy(knightElf), is(true));
	}
	
	@Test
	void R22_canUse() {
		assertThat(potion.canBeUsedBy(magicianHuman), is(true));
	}
	
	@Test
	void R23_canUse() {
		assertThat(potion.canBeUsedBy(magicianOgre), is(true));
	}
	
	@Test
	void R24_canUse() {
		assertThat(potion.canBeUsedBy(magicianElf), is(true));
	}
	
	@Test
	void R25_canUse() {
		assertThat(potion.canBeUsedBy(healerHuman), is(true));
	}
	
	@Test
	void R26_canUse() {
		assertThat(potion.canBeUsedBy(healerOgre), is(true));
	}
	
	@Test
	void R27_canUse() {
		assertThat(potion.canBeUsedBy(healerElf), is(true));
	}
	
	@Test
	void newEggHasDefaultWeight() {
		assertThat(new Potion().getWeight(), is(equalTo(Potion.WEIGHT)));
	}
	
	@Test
	void newEggHasDefaultValue() {
		assertThat(new Potion().getValue(), is(equalTo(Potion.VALUE)));
	}
	
	@Test
	void newEggHasDefaultSize() {
		assertThat(new Potion().getSize(), is(equalTo(Potion.DEFAULT_SIZE)));
	}
	
	@Test
	void newEggHasDefaultType() {
		assertThat(new Potion().getType(), is(equalTo(Potion.TYPE)));
	}
	
	@Test
	void newEggHasDefaultCondition() {
		assertThat(new Potion().getCondition(), is(equalTo(Potion.DEFAULT_CONDITION)));
	}
	
	@Test
	void newEggWithSizeSpecifiedAsSmallInConstructorCreatesASmallEgg() {
		assertThat(new Potion(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newEggWithSizeSpecifiedAsMediumInConstructorCreatesAMediumEgg() {
		assertThat(new Potion(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newEggWithSizeSpecifiedAsLargeInConstructorCreatesALargeEgg() {
		assertThat(new Potion(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void newEggWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Potion(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void newEggWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Potion(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newEggWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Potion(Item.MAX_CONDITION + 1));
	}
}