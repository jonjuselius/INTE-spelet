package Race;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class RaceTest {

	@Test
	void elfConstructor() {

		Elf elf = new Elf();
		assertEquals(300, elf.getHealth());
		assertEquals(true, elf.getIfCanFly());
		assertEquals(false, elf.getIfCanSwim());
		assertEquals(true, elf.getIfCanWalkThroughTerraign());

	}

	@Test
	void humanConstructor() {
		Human human = new Human();
		assertEquals(100, human.getHealth());
		assertEquals(false, human.getIfCanFly());
		assertEquals(true, human.getIfCanSwim());
		assertEquals(true, human.getIfCanWalkThroughTerraign());

	}

	@Test
	void ogreConstructor() {
		Ogre oger = new Ogre();
		assertEquals(400, oger.getHealth());
		assertEquals(false, oger.getIfCanFly());
		assertEquals(false, oger.getIfCanSwim());
		assertEquals(true, oger.getIfCanWalkThroughTerraign());

	}
}