package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Healer;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Map.GameMap;
import Map.GameMapPosition;
import Map.GameMapGenerator;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class EggTest {
	private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
	public static final GameMap MAP = MAP_GENERATOR.generate(1);
	public static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[2][2];
	public static final Egg DEFAULT_EGG = new Egg();
	public static final String NAME = "Mr Default";
	public static final Race[] RACES = {new Human(), new Ogre(), new Elf()};
	public static final Job[] JOBS = {new Knight(), new Magician(), new Healer()};
	public static final boolean ALIVE = true;
	public static final List<Character> DEFAULT_PLAYERS = new ArrayList<>();

	@BeforeAll
	static void beforeAll() {
		for (Job job : JOBS) {
			for (Race race : RACES) {
				DEFAULT_PLAYERS.add(new Player(NAME, race, job, ALIVE, MAP_POSITION));
			}
		}
	}
	
	@Test
	void R19_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(0)));
	}
	
	@Test
	void R20_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(1)));
	}
	
	@Test
	void R21_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(2)));
	}
	
	@Test
	void R22_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(3)));
	}
	
	@Test
	void R23_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(4)));
	}
	
	@Test
	void R24_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(5)));
	}
	
	@Test
	void R25_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(6)));
	}
	
	@Test
	void R26_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(7)));
	}
	
	@Test
	void R27_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(8)));
	}
	
	@Test
	void newEggHasDefaultWeight() {
		assertThat(new Egg().getWeight(), is(equalTo(Egg.WEIGHT)));
	}
	
	@Test
	void newEggHasDefaultValue() {
		assertThat(new Egg().getValue(), is(equalTo(Egg.VALUE)));
	}
	
	@Test
	void newEggHasDefaultSize() {
		assertThat(new Egg().getSize(), is(equalTo(Egg.DEFAULT_SIZE)));
	}
	
	@Test
	void newEggHasDefaultType() {
		assertThat(new Egg().getType(), is(equalTo(Egg.TYPE)));
	}
	
	@Test
	void newEggHasDefaultCondition() {
		assertThat(new Egg().getCondition(), is(equalTo(Egg.DEFAULT_CONDITION)));
	}
	
	@Test
	void newEggWithSizeSpecifiedAsSmallInConstructorCreatesASmallEgg() {
		assertThat(new Egg(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newEggWithSizeSpecifiedAsMediumInConstructorCreatesAMediumEgg() {
		assertThat(new Egg(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newEggWithSizeSpecifiedAsLargeInConstructorCreatesALargeEgg() {
		assertThat(new Egg(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void newEggWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Egg(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void newEggWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Egg(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newEggWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Egg(Item.MAX_CONDITION + 1));
	}
}