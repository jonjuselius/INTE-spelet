package Item;

import GameCharacters.*;
import GameCharacters.Character;
import Jobs.*;
import Races.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {
	
	public static final Sword DEFAULT_SWORD = new Sword();
	public static final String DEFAULT_NAME = "Mr Default";
	public static final Race HUMAN = new Human();
	public static final Race OGRE = new Ogre();
	public static final Race ELF = new Elf();
	public static final Job KNIGHT = new Knight();
	public static final Job MAGICIAN = new Magician();
	public static final Job HEALER = new Healer();
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