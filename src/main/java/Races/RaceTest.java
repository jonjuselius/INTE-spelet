package Races;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class RaceTest {

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
		assertEquals(100, human.getMaxHealth());
		assertEquals(false, human.getIfCanFly());
		assertEquals(true, human.getIfCanSwim());
		assertEquals(true, human.getIfCanWalkThroughTerraign());

	}

	@Test
	void ogreConstructor() {
		Ogre oger = new Ogre();
		assertEquals(400, oger.getMaxHealth());
		assertEquals(false, oger.getIfCanFly());
		assertEquals(false, oger.getIfCanSwim());
		assertEquals(true, oger.getIfCanWalkThroughTerraign());

	}

	@Test
	void increasingLifePointsOverMaxForOgre() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true, 400);
		assertThrows(IllegalStateException.class, () -> {
			c1.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingLifePointsOverMaxForHuman() {

		Player c2 = new Player("Emma", new Human(), new Knight(), true, 100);
		assertThrows(IllegalStateException.class, () -> {
			c2.increaseHealth(100);
			;
		});
	}

	@Test
	void increasingLifePointsOverMaxForElf() {

		Player c3 = new Player("Oliver", new Elf(), new Knight(), true, 300);
		assertThrows(IllegalStateException.class, () -> {
			c3.increaseHealth(100);
			;
		});
	}
}