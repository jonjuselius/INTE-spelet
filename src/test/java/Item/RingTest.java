package Item;

import static org.junit.jupiter.api.Assertions.*;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Healer;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Map.Map;
import Map.MapGenerator;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class RingTest {
	/**
	 * Tests for Ring class
	 */
	public static final Ring DEFAULT_RING = new Ring();
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
	void R37_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(0)));
	}
	
	@Test
	void R38_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(1)));
	}
	
	@Test
	void R39_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(2)));
	}
	
	@Test
	void R40_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(3)));
	}
	
	@Test
	void R41_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(4)));
	}
	
	@Test
	void R42_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(5)));
	}
	
	@Test
	void R43_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(6)));
	}
	
	@Test
	void R44_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(7)));
	}
	
	@Test
	void R45_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(8)));
	}
	
}