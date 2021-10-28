package Jobs;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import Map.GameMap;
import Map.GameMapGenerator;
import org.junit.jupiter.api.Test;

class JobTest {
	private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
	public static final GameMap MAP = MAP_GENERATOR.generate(1);

	@Test
	void knightConstructor() {

		Knight h = new Knight();
		assertEquals(7, h.getHealing());
		assertEquals(3, h.getMagic());
		assertEquals(10, h.getSwordSkill());
		assertEquals(400, h.getMaxMana());

	}

	@Test
	void healerConstructor() {
		Healer h = new Healer();
		assertEquals(10, h.getHealing());
		assertEquals(3, h.getMagic());
		assertEquals(7, h.getSwordSkill());
		assertEquals(350, h.getMaxMana());

	}

	@Test
	void magicianConstructor() {
		Magician h = new Magician();
		assertEquals(7, h.getHealing());
		assertEquals(10, h.getMagic());
		assertEquals(3, h.getSwordSkill());
		assertEquals(300, h.getMaxMana());

	}

}