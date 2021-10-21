package Item;

import static org.junit.jupiter.api.Assertions.*;

import Map.Map;
import Map.MapGenerator;
import org.junit.jupiter.api.Test;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Healer;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;

class ShieldTest {
	/**
	 * Tests for Shield class
	 */
	public static final Shield DEFAULT_SHIELD = new Shield();
	public static final String NAME = "Mr Default";
	public static final Race HUMAN = new Human();
	public static final Race OGRE = new Ogre();
	public static final Race ELF = new Elf();
	public static final Job KNIGHT = new Knight();
	public static final Job MAGICIAN = new Magician();
	public static final Job HEALER = new Healer();
	private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
	public static final Map MAP = MAP_GENERATOR.generate(1);
	public static final boolean ALIVE = true;
	public static final Character[] DEFAULT_PLAYERS = {
			new Player(NAME, HUMAN, KNIGHT, ALIVE, MAP),
			new Player(NAME, OGRE, KNIGHT, ALIVE, MAP),
			new Player(NAME, ELF, KNIGHT, ALIVE, MAP),
			new Player(NAME, HUMAN, MAGICIAN, ALIVE, MAP),
			new Player(NAME, OGRE, MAGICIAN, ALIVE, MAP),
			new Player(NAME, ELF, MAGICIAN, ALIVE, MAP),
			new Player(NAME, HUMAN, HEALER, ALIVE, MAP),
			new Player(NAME, OGRE, HEALER, ALIVE, MAP),
			new Player(NAME, ELF, HEALER, ALIVE, MAP),
	};
	
	@Test
	void R28_canUse() {
		assertTrue(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[0]));
	}
	
	@Test
	void R29_canUse() {
		assertTrue(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[1]));
	}
	
	@Test
	void R30_canUse() {
		assertTrue(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[2]));
	}
	
	@Test
	void R31_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[3]));
	}
	
	@Test
	void R32_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[4]));
	}
	
	@Test
	void R33_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[5]));
	}
	
	@Test
	void R34_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[6]));
	}
	
	@Test
	void R35_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[7]));
	}
	
	@Test
	void R36_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS[8]));
	}
	
}