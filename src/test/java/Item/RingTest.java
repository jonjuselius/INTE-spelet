package Item;

import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Job;
import Jobs.Knight;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingTest {
	
	public static final Ring DEFAULT_RING = new Ring();
	public static final String DEFAULT_NAME = "Mr Default";
	public static final Race HUMAN = new Human();
	public static final Race OGRE = new Ogre();
	public static final Race ELF = new Elf();
	public static final Job KNIGHT = new Knight();
	public static final Job MAGICIAN = new Knight();
	public static final Job HEALER = new Knight();
	public static final boolean DEFAULT_LIFE_STATUS = true;
	public static final Character[] DEFAULT_PLAYERS = {
			new Player(DEFAULT_NAME, HUMAN, KNIGHT, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, OGRE, KNIGHT, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, ELF, KNIGHT, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, HUMAN, MAGICIAN, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, OGRE, MAGICIAN, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, ELF, MAGICIAN, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, HUMAN, HEALER, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, OGRE, HEALER, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, ELF, HEALER, DEFAULT_LIFE_STATUS),
	};
	
	@Test
	void R37_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[0]));
	}
	
	@Test
	void R38_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[1]));
	}
	
	@Test
	void R39_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[2]));
	}
	
	@Test
	void R40_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[3]));
	}
	
	@Test
	void R41_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[4]));
	}
	
	@Test
	void R42_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[5]));
	}
	
	@Test
	void R43_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[6]));
	}
	
	@Test
	void R44_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[7]));
	}
	
	@Test
	void R45_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS[8]));
	}
	
}