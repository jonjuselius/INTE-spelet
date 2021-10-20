package Item;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Jobs.Healer;
import Races.Race;
import Races.Human;
import Races.Ogre;
import Races.Elf;

class SwordTest {
	/**
	 * Test class for Sword class
	 */
	public static final Sword DEFAULT_SWORD = new Sword();
	public static final String NAME = "Mr Default";
	public static final Race HUMAN = new Human();
	public static final Race OGRE = new Ogre();
	public static final Race ELF = new Elf();
	public static final Job KNIGHT = new Knight();
	public static final Job MAGICIAN = new Magician();
	public static final Job HEALER = new Healer();
	public static final boolean ALIVE = true;
	public static final Character[] DEFAULT_PLAYERS = {
			new Player(NAME, HUMAN, KNIGHT, ALIVE),
			new Player(NAME, OGRE, KNIGHT, ALIVE),
			new Player(NAME, ELF, KNIGHT, ALIVE),
			new Player(NAME, HUMAN, MAGICIAN, ALIVE),
			new Player(NAME, OGRE, MAGICIAN, ALIVE),
			new Player(NAME, ELF, MAGICIAN, ALIVE),
			new Player(NAME, HUMAN, HEALER, ALIVE),
			new Player(NAME, OGRE, HEALER, ALIVE),
			new Player(NAME, ELF, HEALER, ALIVE),
	};
	
	@Test
	void R1_canUse() {
		assertTrue(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[0]));
	}
	
	@Test
	void R2_canUse() {
		assertTrue(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[1]));
	}
	
	@Test
	void R3_canUse() {
		assertTrue(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[2]));
	}
	
	@Test
	void R4_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[3]));
	}
	
	@Test
	void R5_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[4]));
	}
	
	@Test
	void R6_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[5]));
	}
	
	@Test
	void R7_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[6]));
	}
	
	@Test
	void R8_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[7]));
	}
	
	@Test
	void R9_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS[8]));
	}
}