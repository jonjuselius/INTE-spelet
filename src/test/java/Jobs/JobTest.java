package Jobs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameCharacters.Player;
import Races.Ogre;

class JobTest {

	@Test
	void getHealedByHealerOnceShouldBeThreehundredTenForLevelOne() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Healer(), true);
		c1.getHealedByHealer();

		assertEquals(310, c1.getRemainingHealth());

	}
	
	@Test
	void getHealedByHealerTwiceShouldBeThreehundredTenForLevelOne() {

		Player c1 = new Player("Jasmyn", new Ogre(), new Healer(), true);
		c1.getHealedByHealer();

		assertEquals(310, c1.getRemainingHealth());

	}
	
//	@Test
//	void getHealedOverMaxLifePointsForElfThrowsException() {
//
//		Player c1 = new Player("Jasmyn", new Ogre(), new Knight(), true);
//		assertThrows(IllegalStateException.class, () -> {
//			
//
//		});
//	}
}
