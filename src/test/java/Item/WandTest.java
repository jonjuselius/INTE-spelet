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

class WandTest {
	/**
	 * Tests for Wand class
	 */
	private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
	public static final GameMap MAP = MAP_GENERATOR.generate(1);
	public static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[2][2];
	public static final Wand DEFAULT_WAND = new Wand(MAP_POSITION);
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
	void R10_canUse() {
		assertFalse(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(0)));
	}
	
	@Test
	void R11_canUse() {
		assertFalse(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(1)));
	}
	
	@Test
	void R12_canUse() {
		assertFalse(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(2)));
	}
	
	@Test
	void R13_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(3)));
	}
	
	@Test
	void R14_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(4)));
	}
	
	@Test
	void R15_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(5)));
	}
	
	@Test
	void R16_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(6)));
	}
	
	@Test
	void R17_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(7)));
	}
	
	@Test
	void R18_canUse() {
		assertTrue(DEFAULT_WAND.canBeUsedBy(DEFAULT_PLAYERS.get(8)));
	}
	
	@Test
	void WA1_newWandHasDefaultWeight() {
		assertThat(new Wand(MAP_POSITION).getWeight(), is(equalTo(Wand.WEIGHT)));
	}
	
	@Test
	void WA2_newWandHasDefaultValue() {
		assertThat(new Wand(MAP_POSITION).getValue(), is(equalTo(Wand.VALUE)));
	}
	
	@Test
	void WA3_newWandHasDefaultSize() {
		assertThat(new Wand(MAP_POSITION).getSize(), is(equalTo(Wand.DEFAULT_SIZE)));
	}
	
	@Test
	void WA4_newWandHasDefaultType() {
		assertThat(new Wand(MAP_POSITION).getType(), is(equalTo(Wand.TYPE)));
	}
	
	@Test
	void WA5_newWandHasDefaultCondition() {
		assertThat(new Wand(MAP_POSITION).getCondition(), is(equalTo(Wand.DEFAULT_CONDITION)));
	}
	
	@Test
	void WA6_wandCanOnlyBeUsedByMagicianAndHealer() {
		List<String> jobs = Arrays.asList("Magician", "Healer");
		List<String> jobCertifications = DEFAULT_WAND.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void WA7_wandCanBeUsedByAllRaces() {
		List<String> races = Arrays.asList("Human", "Ogre", "Elf");
		List<String> raceCertifications = DEFAULT_WAND.getRaceCertifications();
		String[] racesArr = races.toArray(new String[races.size()]);
		String[] raceCertificationsArr = raceCertifications.toArray(new String[raceCertifications.size()]);
		assertArrayEquals(racesArr, raceCertificationsArr);
	}
	
	@Test
	void WA8_newWandWithSizeSpecifiedAsSmallInConstructorCreatesASmallWand() {
		assertThat(new Wand(Size.SMALL, MAP_POSITION).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void WA9_newWandWithSizeSpecifiedAsMediumInConstructorCreatesAMediumWand() {
		assertThat(new Wand(Size.MEDIUM, MAP_POSITION).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void WA10_newWandWithSizeSpecifiedAsLargeInConstructorCreatesALargeWand() {
		assertThat(new Wand(Size.LARGE, MAP_POSITION).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void WA11_newWandWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Wand(50, MAP_POSITION).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void WA12_newWandWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Wand(Item.MIN_CONDITION - 1, MAP_POSITION));
	}
	
	@Test
	void WA13_newWandWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Wand(Item.MAX_CONDITION + 1, MAP_POSITION));
	}
}