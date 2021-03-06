package Races;

//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import Map.GameMap;
import Map.GameMapGenerator;
import org.junit.jupiter.api.Test;

class RaceTest {
	private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
	public static final GameMap MAP = MAP_GENERATOR.generate(1);
	// human health 100, elf health 200, oger health 300, max �r + 100
	@Test
	void elfConstructor() {

		Elf elf = new Elf();
		assertEquals(200, elf.getMaxHealth());
		assertEquals(true, elf.getIfCanFly());
		assertEquals(false, elf.getIfCanSwim());
		assertEquals(true, elf.getIfCanWalkThroughTerraign());

	}

	@Test
	void humanConstructor() {
		Human human = new Human();
		assertEquals(300, human.getMaxHealth());
		assertEquals(false, human.getIfCanFly());
		assertEquals(true, human.getIfCanSwim());
		assertEquals(true, human.getIfCanWalkThroughTerraign());

	}

	@Test
	void ogreConstructor() {
		Ogre oger = new Ogre();
		assertEquals(300, oger.getMaxHealth());
		assertEquals(false, oger.getIfCanFly());
		assertEquals(false, oger.getIfCanSwim());
		assertEquals(true, oger.getIfCanWalkThroughTerraign());

	}}