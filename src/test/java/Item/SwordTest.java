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
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Jobs.Healer;
import Races.Race;
import Races.Human;
import Races.Ogre;
import Races.Elf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class SwordTest {
	/**
	 * Tests for Sword class
	 */
	public static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
	public static final GameMap MAP = MAP_GENERATOR.generate(1);
	public static final GameMapPosition MAP_POSITION = MAP.generateRealRandomPos(new Random(), new Random());
	public static final Sword DEFAULT_SWORD = new Sword(MAP_POSITION);
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
	void R1_canUse() {
		assertTrue(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(0)));
	}
	
	@Test
	void R2_canUse() {
		assertTrue(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(1)));
	}
	
	@Test
	void R3_canUse() {
		assertTrue(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(2)));
	}
	
	@Test
	void R4_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(3)));
	}
	
	@Test
	void R5_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(4)));
	}
	
	@Test
	void R6_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(5)));
	}
	
	@Test
	void R7_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(6)));
	}
	
	@Test
	void R8_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(7)));
	}
	
	@Test
	void R9_canUse() {
		assertFalse(DEFAULT_SWORD.canBeUsedBy(DEFAULT_PLAYERS.get(8)));
	}
	
	@Test
	void SW1_newSwordHasDefaultWeight() {
		assertThat(new Sword(MAP_POSITION).getWeight(), is(equalTo(Sword.WEIGHT)));
	}
	
	@Test
	void SW2_newSwordHasDefaultValue() {
		assertThat(new Sword(MAP_POSITION).getValue(), is(equalTo(Sword.VALUE)));
	}
	
	@Test
	void SW3_newSwordHasDefaultSize() {
		assertThat(new Sword(MAP_POSITION).getSize(), is(equalTo(Sword.DEFAULT_SIZE)));
	}
	
	@Test
	void SW4_newSwordHasDefaultType() {
		assertThat(new Sword(MAP_POSITION).getType(), is(equalTo(Sword.TYPE)));
	}
	
	@Test
	void SW5_newSwordHasDefaultCondition() {
		assertThat(new Sword(MAP_POSITION).getCondition(), is(equalTo(Sword.DEFAULT_CONDITION)));
	}
	
	@Test
	void SW6_swordCanOnlyBeUsedByKnight() {
		List<String> jobs = Arrays.asList("Knight");
		List<String> jobCertifications = DEFAULT_SWORD.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void SW7_swordCanBeUsedByAllRaces() {
		List<String> races = Arrays.asList("Human", "Ogre", "Elf");
		List<String> raceCertifications = DEFAULT_SWORD.getRaceCertifications();
		String[] racesArr = races.toArray(new String[races.size()]);
		String[] raceCertificationsArr = raceCertifications.toArray(new String[raceCertifications.size()]);
		assertArrayEquals(racesArr, raceCertificationsArr);
	}
	
	@Test
	void SW8_newSwordWithSizeSpecifiedAsSmallInConstructorCreatesASmallSword() {
		assertThat(new Sword(Size.SMALL, MAP_POSITION).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void SW9_newSwordWithSizeSpecifiedAsMediumInConstructorCreatesAMediumSword() {
		assertThat(new Sword(Size.MEDIUM, MAP_POSITION).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void SW10_newSwordWithSizeSpecifiedAsLargeInConstructorCreatesALargeSword() {
		assertThat(new Sword(Size.LARGE, MAP_POSITION).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void SW11_newSwordWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Sword(50, MAP_POSITION).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void SW12_newSwordWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Item.MIN_CONDITION - 1, MAP_POSITION));
	}
	
	@Test
	void SW13_newSwordWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Item.MAX_CONDITION + 1, MAP_POSITION));
	}
	
}