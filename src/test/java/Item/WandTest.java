package Item;

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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WandTest {
	
	public static final Wand DEFAULT_WAND = new Wand();
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
	void R10_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[0]));
	}
	
	@Test
	void R11_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[1]));
	}
	
	@Test
	void R12_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[2]));
	}
	
	@Test
	void R13_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[3]));
	}
	
	@Test
	void R14_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[4]));
	}
	
	@Test
	void R15_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[5]));
	}
	
	@Test
	void R16_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[6]));
	}
	
	@Test
	void R17_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[7]));
	}
	
	@Test
	void R18_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS[8]));
	}
	
}