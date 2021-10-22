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
import Map.*;
import Map.MapGenerator;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class EggTest {
	/**
	 * Tests for Egg class
	 */
	public static final Egg DEFAULT_EGG = new Egg();
	public static final String NAME = "Mr Default";
	public static final Race[] RACES = {new Human(), new Ogre(), new Elf()};
	public static final Job[] JOBS = {new Knight(), new Magician(), new Healer()};
	public static final boolean ALIVE = true;
	private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
	public static final Map MAP = MAP_GENERATOR.generate(1);
	public static final List<Character> DEFAULT_PLAYERS = new ArrayList<>();
	
	@BeforeAll
	static void beforeAll() {
		for (Job job : JOBS) {
			for (Race race : RACES) {
				DEFAULT_PLAYERS.add(new Player(NAME, race, job, ALIVE, MAP));
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
	void EG1_newEggHasDefaultWeight() {
		assertThat(new Egg().getWeight(), is(equalTo(Egg.WEIGHT)));
	}
	
	@Test
	void EG2_newEggHasDefaultValue() {
		assertThat(new Egg().getValue(), is(equalTo(Egg.VALUE)));
	}
	
	@Test
	void EG3_newEggHasDefaultSize() {
		assertThat(new Egg().getSize(), is(equalTo(Egg.DEFAULT_SIZE)));
	}
	
	@Test
	void EG4_newEggHasDefaultType() {
		assertThat(new Egg().getType(), is(equalTo(Egg.TYPE)));
	}
	
	@Test
	void EG5_newEggHasDefaultCondition() {
		assertThat(new Egg().getCondition(), is(equalTo(Egg.DEFAULT_CONDITION)));
	}
	
	@Test
	void eatingFoodMakesItConsumed() {
		Food egg = new Egg();
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
	}
	
	@Test
	void eatingConsumedFoodThrowsISE() {
		Food egg = new Egg();
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
		assertThrows(IllegalStateException.class, egg::consume);
	}
}