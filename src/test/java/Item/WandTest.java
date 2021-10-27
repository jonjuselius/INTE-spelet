package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import Map.GameMap;
import Map.GameMapGenerator;
import Map.GameMapPosition;
import org.junit.jupiter.api.Test;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Job;
import Races.Race;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WandTest {
	private Item wand = new Wand();
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
	void R10_canUse() {
		assertThat(knightHuman.canUse(wand), is(false));
	}
	
	@Test
	void R11_canUse() {
		assertThat(knightOgre.canUse(wand), is(false));
	}
	
	@Test
	void R12_canUse() {
		assertThat(knightElf.canUse(wand), is(false));
	}
	
	@Test
	void R13_canUse() {
		assertThat(magicianHuman.canUse(wand), is(true));
	}
	
	@Test
	void R14_canUse() {
		assertThat(magicianOgre.canUse(wand), is(true));
	}
	
	@Test
	void R15_canUse() {
		assertThat(magicianElf.canUse(wand), is(true));
	}
	
	@Test
	void R16_canUse() {
		assertThat(healerHuman.canUse(wand), is(true));
	}
	
	@Test
	void R17_canUse() {
		assertThat(healerOgre.canUse(wand), is(true));
	}
	
	@Test
	void R18_canUse() {
		assertThat(healerElf.canUse(wand), is(true));
	}
	
	@Test
	void newWandHasDefaultWeight() {
		assertThat(new Wand().getWeight(), is(equalTo(Wand.WEIGHT)));
	}
	
	@Test
	void newWandHasDefaultValue() {
		assertThat(new Wand().getValue(), is(equalTo(Wand.VALUE)));
	}
	
	@Test
	void newWandHasDefaultSize() {
		assertThat(new Wand().getSize(), is(equalTo(Wand.DEFAULT_SIZE)));
	}
	
	@Test
	void newWandHasDefaultType() {
		assertThat(new Wand().getType(), is(equalTo(Wand.TYPE)));
	}
	
	@Test
	void newWandHasDefaultCondition() {
		assertThat(new Wand().getCondition(), is(equalTo(Wand.DEFAULT_CONDITION)));
	}
	
	@Test
	void ewWandWithSizeSpecifiedAsSmallInConstructorCreatesASmallWand() {
		assertThat(new Wand(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newWandWithSizeSpecifiedAsMediumInConstructorCreatesAMediumWand() {
		assertThat(new Wand(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newWandWithSizeSpecifiedAsLargeInConstructorCreatesALargeWand() {
		assertThat(new Wand(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void newWandWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Wand(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void newWandWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Wand(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newWandWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Wand(Item.MAX_CONDITION + 1));
	}
}