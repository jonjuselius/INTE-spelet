package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import Map.GameMap;
import Map.GameMapGenerator;
import Map.GameMapPosition;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ShieldTest {
	private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
	public static final GameMap MAP = MAP_GENERATOR.generate(1);
	public static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[2][2];
	public static final Shield DEFAULT_SHIELD = new Shield();
	public static final String NAME = "Mr Default";
	public static final Race[] RACES = {new Human(), new Ogre(), new Elf()};
	public static final Job[] JOBS = {new Knight(), new Magician(), new Healer()};
	public static final boolean ALIVE = true;
	public static final List<Character> DEFAULT_PLAYERS = new ArrayList<>();
	
	@BeforeAll
	static void beforeAll() {
		for (Job job : JOBS) {
			for (Race race : RACES) {
				DEFAULT_PLAYERS.add(new Player(NAME, race, job, ALIVE, MAP_POSITION));
			}
		}
	}
	
	@Test
	void R28_canUse() {
		assertTrue(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(0)));
	}
	
	@Test
	void R29_canUse() {
		assertTrue(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(1)));
	}
	
	@Test
	void R30_canUse() {
		assertTrue(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(2)));
	}
	
	@Test
	void R31_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(3)));
	}
	
	@Test
	void R32_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(4)));
	}
	
	@Test
	void R33_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(5)));
	}
	
	@Test
	void R34_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(6)));
	}
	
	@Test
	void R35_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(7)));
	}
	
	@Test
	void R36_canUse() {
		assertFalse(DEFAULT_SHIELD.canBeUsedBy(DEFAULT_PLAYERS.get(8)));
	}
	
	@Test
	void SH1_newShieldHasDefaultWeight() {
		assertThat(new Shield().getWeight(), is(equalTo(Shield.WEIGHT)));
	}
	
	@Test
	void SH2_newShieldHasDefaultValue() {
		assertThat(new Shield().getValue(), is(equalTo(Shield.VALUE)));
	}
	
	@Test
	void SH3_newShieldHasDefaultSize() {
		assertThat(new Shield().getSize(), is(equalTo(Shield.DEFAULT_SIZE)));
	}
	
	@Test
	void SH4_newShieldHasDefaultType() {
		assertThat(new Shield().getType(), is(equalTo(Shield.TYPE)));
	}
	
	@Test
	void SH5_newShieldHasDefaultCondition() {
		assertThat(new Shield().getCondition(), is(equalTo(Shield.DEFAULT_CONDITION)));
	}
	
	@Test
	void SH8_newShieldWithSizeSpecifiedAsSmallInConstructorCreatesASmallShield() {
		assertThat(new Shield(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void SH9_newShieldWithSizeSpecifiedAsMediumInConstructorCreatesAMediumShield() {
		assertThat(new Shield(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void SH10_newShieldWithSizeSpecifiedAsLargeInConstructorCreatesALargeShield() {
		assertThat(new Shield(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void SH11_newShieldWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Shield(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void SH12_newShieldWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Shield(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void SH13_newShieldWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Shield(Item.MAX_CONDITION + 1));
	}
}