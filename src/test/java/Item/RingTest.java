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

class RingTest {
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
	void R38_canUse() {
		assertThat(knightHuman.canUse(ring), is(true));
	}
	
	@Test
	void R39_canUse() {
		assertThat(knightOgre.canUse(ring), is(true));
	}
	
	@Test
	void R40_canUse() {
		assertThat(knightElf.canUse(ring), is(true));
	}
	
	@Test
	void R41_canUse() {
		assertThat(magicianHuman.canUse(ring), is(true));
	}
	
	@Test
	void R42_canUse() {
		assertThat(magicianOgre.canUse(ring), is(true));
	}
	
	@Test
	void R43_canUse() {
		assertThat(magicianElf.canUse(ring), is(true));
	}
	
	@Test
	void R44_canUse() {
		assertThat(healerHuman.canUse(ring), is(true));
	}
	
	@Test
	void R45_canUse() {
		assertThat(healerOgre.canUse(ring), is(true));
	}
	
	@Test
	void R46_canUse() {
		assertThat(healerElf.canUse(ring), is(true));
	}
	
	@Test
	void newRingHasDefaultWeight() {
		assertThat(new Ring().getWeight(), is(equalTo(Ring.WEIGHT)));
	}
	
	@Test
	void newRingHasDefaultValue() {
		assertThat(new Ring().getValue(), is(equalTo(Ring.VALUE)));
	}
	
	@Test
	void newRingHasDefaultSize() {
		assertThat(new Ring().getSize(), is(equalTo(Ring.DEFAULT_SIZE)));
	}
	
	@Test
	void newRingHasDefaultType() {
		assertThat(new Ring().getType(), is(equalTo(Ring.TYPE)));
	}
	
	@Test
	void newRingHasDefaultCondition() {
		assertThat(new Ring().getCondition(), is(equalTo(Ring.DEFAULT_CONDITION)));
	}
	
	@Test
	void newRingWithSizeSpecifiedAsSmallInConstructorCreatesASmallRing() {
		assertThat(new Ring(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newRingWithSizeSpecifiedAsMediumInConstructorCreatesAMediumRing() {
		assertThat(new Ring(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newRingWithSizeSpecifiedAsLargeInConstructorCreatesALargeRing() {
		assertThat(new Ring(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void newRingWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Ring(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void newRingWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Ring(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newRingWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Ring(Item.MAX_CONDITION + 1));
	}
}