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
import Map.*;
import Map.GameMapGenerator;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class EggTest {
	/**
	 * Tests for Egg class
	 */
	private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
	public static final GameMap MAP = MAP_GENERATOR.generate(1);
	public static final GameMapPosition MAP_POSITION = MAP.generateRealRandomPos(new Random(), new Random());
	public static final Egg DEFAULT_EGG = new Egg(MAP_POSITION);
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
	void R19_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(0)));
	}
	
	@Test
	void R20_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(1)));
	}
	
	@Test
	void R21_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(2)));
	}
	
	@Test
	void R22_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(3)));
	}
	
	@Test
	void R23_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(4)));
	}
	
	@Test
	void R24_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(5)));
	}
	
	@Test
	void R25_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(6)));
	}
	
	@Test
	void R26_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(7)));
	}
	
	@Test
	void R27_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS.get(8)));
	}
	
	@Test
	void EG1_newEggHasDefaultWeight() {
		assertThat(new Egg(MAP_POSITION).getWeight(), is(equalTo(Egg.WEIGHT)));
	}
	
	@Test
	void EG2_newEggHasDefaultValue() {
		assertThat(new Egg(MAP_POSITION).getValue(), is(equalTo(Egg.VALUE)));
	}
	
	@Test
	void EG3_newEggHasDefaultSize() {
		assertThat(new Egg(MAP_POSITION).getSize(), is(equalTo(Egg.DEFAULT_SIZE)));
	}
	
	@Test
	void EG4_newEggHasDefaultType() {
		assertThat(new Egg(MAP_POSITION).getType(), is(equalTo(Egg.TYPE)));
	}
	
	@Test
	void EG5_newEggHasDefaultCondition() {
		assertThat(new Egg(MAP_POSITION).getCondition(), is(equalTo(Egg.DEFAULT_CONDITION)));
	}
	
	@Test
	void EG6_eggCanBeUsedByAllJobs() {
		List<String> jobs = Arrays.asList("Knight", "Magician", "Healer");
		List<String> jobCertifications = DEFAULT_EGG.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void EG7_eggCanBeUsedByAllRaces() {
		List<String> races = Arrays.asList("Human", "Ogre", "Elf");
		List<String> raceCertifications = DEFAULT_EGG.getRaceCertifications();
		String[] racesArr = races.toArray(new String[races.size()]);
		String[] raceCertificationsArr = raceCertifications.toArray(new String[raceCertifications.size()]);
		assertArrayEquals(racesArr, raceCertificationsArr);
	}
	
	@Test
	void EG8_newEggWithSizeSpecifiedAsSmallInConstructorCreatesASmallEgg() {
		assertThat(new Egg(Size.SMALL, MAP_POSITION).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void EG9_newEggWithSizeSpecifiedAsMediumInConstructorCreatesAMediumEgg() {
		assertThat(new Egg(Size.MEDIUM, MAP_POSITION).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void EG10_newEggWithSizeSpecifiedAsLargeInConstructorCreatesALargeEgg() {
		assertThat(new Egg(Size.LARGE, MAP_POSITION).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void EG11_newEggWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Egg(50, MAP_POSITION).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void EG12_newEggWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Egg(Item.MIN_CONDITION - 1, MAP_POSITION));
	}
	
	@Test
	void EG13_newEggWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Egg(Item.MAX_CONDITION + 1, MAP_POSITION));
	}
	
	@Test
	void eatingFoodMakesItConsumed() {
		Food egg = new Egg(MAP_POSITION);
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
	}
	
	@Test
	void eatingConsumedFoodThrowsISE() {
		Food egg = new Egg(MAP_POSITION);
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
		assertThrows(IllegalStateException.class, egg::consume);
	}
}