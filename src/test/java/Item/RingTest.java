package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Healer;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Map.Map;
import Map.MapPosition;
import Map.MapGenerator;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RingTest {
	/**
	 * Tests for Ring class
	 */
	private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
	public static final Map MAP = MAP_GENERATOR.generate(1);
	public static final MapPosition MAP_POSITION = MAP.getMapTiles()[2][2];
	public static final Ring DEFAULT_RING = new Ring();
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
	void R37_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(0)));
	}
	
	@Test
	void R38_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(1)));
	}
	
	@Test
	void R39_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(2)));
	}
	
	@Test
	void R40_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(3)));
	}
	
	@Test
	void R41_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(4)));
	}
	
	@Test
	void R42_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(5)));
	}
	
	@Test
	void R43_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(6)));
	}
	
	@Test
	void R44_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(7)));
	}
	
	@Test
	void R45_canUse() {
		assertTrue(DEFAULT_RING.canBeUsedBy(DEFAULT_PLAYERS.get(8)));
	}
	
	@Test
	void RI1_newRingHasDefaultWeight() {
		assertThat(new Ring().getWeight(), is(equalTo(Ring.WEIGHT)));
	}
	
	@Test
	void RI2_newRingHasDefaultValue() {
		assertThat(new Ring().getValue(), is(equalTo(Ring.VALUE)));
	}
	
	@Test
	void RI3_newRingHasDefaultSize() {
		assertThat(new Ring().getSize(), is(equalTo(Ring.DEFAULT_SIZE)));
	}
	
	@Test
	void RI4_newRingHasDefaultType() {
		assertThat(new Ring().getType(), is(equalTo(Ring.TYPE)));
	}
	
	@Test
	void RI5_newRingHasDefaultCondition() {
		assertThat(new Ring().getCondition(), is(equalTo(Ring.DEFAULT_CONDITION)));
	}
	
	@Test
	void RI6_ringCanBeUsedByAllJobs() {
		List<String> jobs = Arrays.asList("Knight", "Magician", "Healer");
		List<String> jobCertifications = DEFAULT_RING.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void RI7_ringCanBeUsedByAllRaces() {
		List<String> races = Arrays.asList("Human", "Ogre", "Elf");
		List<String> raceCertifications = DEFAULT_RING.getRaceCertifications();
		String[] racesArr = races.toArray(new String[races.size()]);
		String[] raceCertificationsArr = raceCertifications.toArray(new String[raceCertifications.size()]);
		assertArrayEquals(racesArr, raceCertificationsArr);
	}
	
	@Test
	void RI8_newRingWithSizeSpecifiedAsSmallInConstructorCreatesASmallRing() {
		assertThat(new Ring(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void RI9_newRingWithSizeSpecifiedAsMediumInConstructorCreatesAMediumRing() {
		assertThat(new Ring(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void RI10_newRingWithSizeSpecifiedAsLargeInConstructorCreatesALargeRing() {
		assertThat(new Ring(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void RI11_newRingWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Ring(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void RI12_newRingWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Ring(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void RI13_newRingWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Ring(Item.MAX_CONDITION + 1));
	}
}