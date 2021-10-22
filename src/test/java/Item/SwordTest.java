package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import Map.Map;
import Map.MapGenerator;
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

class SwordTest {
	/**
	 * Tests for Sword class
	 */
	public static final Sword DEFAULT_SWORD = new Sword();
	public static final String NAME = "Mr Default";
	public static final Race[] RACES = {new Human(), new Ogre(), new Elf()};
	public static final Job[] JOBS = {new Knight(), new Magician(), new Healer()};
	public static final boolean ALIVE = true;
	private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
	public static final Map MAP = MAP_GENERATOR.generate(1);
	public static final List<Character> DEFAULT_PLAYERS = new ArrayList<>();
	
	@BeforeAll
	static void beforeAll() {
		for (Job job : JOBS) {
			for (Race race : RACES) {
				DEFAULT_PLAYERS.add(new Player(NAME, race, job, ALIVE, MAP));
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
		assertThat(new Sword().getWeight(), is(equalTo(Sword.WEIGHT)));
	}
	
	@Test
	void SW2_newSwordHasDefaultValue() {
		assertThat(new Sword().getValue(), is(equalTo(Sword.VALUE)));
	}
	
	@Test
	void SW3_newSwordHasDefaultSize() {
		assertThat(new Sword().getSize(), is(equalTo(Sword.DEFAULT_SIZE)));
	}
	
	@Test
	void SW4_newSwordHasDefaultType() {
		assertThat(new Sword().getType(), is(equalTo(Sword.TYPE)));
	}
	
	@Test
	void SW5_newSwordHasDefaultCondition() {
		assertThat(new Sword().getCondition(), is(equalTo(Sword.DEFAULT_CONDITION)));
	}
	
	@Test
	void newSwordWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		int condition = (Sword.DEFAULT_CONDITION + Sword.DEFAULT_CONDITION) / 2;
		Item sword = new Sword(condition);
		assertThat(sword.getCondition(), is(equalTo(condition)));
	}
	
	@Test
	void newSwordWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newSwordWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Item.MAX_CONDITION + 1));
	}
	
	@Test
	void newSwordWithSizeSpecifiedAsSmallInConstructorCreatesASmallSword() {
		assertThat(new Sword(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newSwordWithSizeSpecifiedAsMediumInConstructorCreatesAMediumSword() {
		assertThat(new Sword(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newSwordWithSizeSpecifiedAsLargeInConstructorCreatesALargeSword() {
		assertThat(new Sword(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void swordCanOnlyBeUsedByKnight() {
		List<String> jobs = Arrays.asList("Knight");
		List<String> jobCertifications = DEFAULT_SWORD.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
}