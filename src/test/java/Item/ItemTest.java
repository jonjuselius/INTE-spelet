package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class ItemTest {
	/**
	 * Tests for Item class
	 */
	public static final Item[] DEFAULT_ITEMS = {new Sword(), new Wand(), new Egg(), new Shield(), new Ring()};
	
	@Test
	void newItemHasDefaultItemWeight() {
		assertThat(DEFAULT_ITEMS[0].getWeight(), is(equalTo(Sword.WEIGHT)));
		assertThat(DEFAULT_ITEMS[1].getWeight(), is(equalTo(Wand.WEIGHT)));
		assertThat(DEFAULT_ITEMS[2].getWeight(), is(equalTo(Egg.WEIGHT)));
		assertThat(DEFAULT_ITEMS[3].getWeight(), is(equalTo(Shield.WEIGHT)));
		assertThat(DEFAULT_ITEMS[4].getWeight(), is(equalTo(Ring.WEIGHT)));
	}
	
	@Test
	void newItemHasDefaultItemValue() {
		assertThat(DEFAULT_ITEMS[0].getValue(), is(equalTo(Sword.VALUE)));
		assertThat(DEFAULT_ITEMS[1].getValue(), is(equalTo(Wand.VALUE)));
		assertThat(DEFAULT_ITEMS[2].getValue(), is(equalTo(Egg.VALUE)));
		assertThat(DEFAULT_ITEMS[3].getValue(), is(equalTo(Shield.VALUE)));
		assertThat(DEFAULT_ITEMS[4].getValue(), is(equalTo(Ring.VALUE)));
	}
	
	@Test
	void itemWithNoJobRestrictionsCanBeUsedByAllJobs() {
		List<String> jobs = Arrays.asList("Knight", "Magician", "Healer");
		List<String> jobCertifications = DEFAULT_ITEMS[2].getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void swordCanOnlyBeUsedByKnight() {
		List<String> jobs = Arrays.asList("Knight");
		List<String> jobCertifications = DEFAULT_ITEMS[0].getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void wandCanOnlyBeUsedByMagicianAndHealer() {
		List<String> jobs = Arrays.asList("Magician", "Healer");
		List<String> jobCertifications = DEFAULT_ITEMS[1].getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void itemWithNoRaceRestrictionsCanBeUsedByAllRaces() {
		List<String> races = Arrays.asList("Human", "Ogre", "Elf");
		List<String> raceCertifications = DEFAULT_ITEMS[2].getRaceCertifications();
		String[] racesArr = races.toArray(new String[races.size()]);
		String[] raceCertificationsArr = raceCertifications.toArray(new String[raceCertifications.size()]);
		assertArrayEquals(racesArr, raceCertificationsArr);
	}
	
	@Test
	void newSwordHasDefaultSize() {
		assertThat(DEFAULT_ITEMS[0].getSize(), is(equalTo(Sword.DEFAULT_SIZE)));
	}
	
	@Test
	void specifyingSizeInConstructorCreatesEquipmentOfCorrespondingSize() {
		for (Size size : Size.values()) {
			assertThat(new Sword(size).getSize(), is(equalTo(size)));
			assertThat(new Wand(size).getSize(), is(equalTo(size)));
			assertThat(new Egg(size).getSize(), is(equalTo(size)));
			assertThat(new Shield(size).getSize(), is(equalTo(size)));
			assertThat(new Ring(size).getSize(), is(equalTo(size)));
		}
	}
	
	@Test
	void newSwordHasDefaultCondition() {
		assertThat(DEFAULT_ITEMS[0].getCondition(), is(equalTo(Sword.DEFAULT_CONDITION)));
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
	void newSwordIsAWeapon() {
		assertThat(DEFAULT_ITEMS[0].getType(), is(equalTo(Sword.TYPE)));
	}
	
	@Test
	void eatingFoodMakesItConsumed() {
		Food egg = new Egg();
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
	}
	
	@Test
	void eatingConsumedFoodThrowsISE() {
		Food egg = new Egg();
		assertThat(egg.isConsumed(), is(equalTo(false)));
		egg.consume();
		assertThat(egg.isConsumed(), is(equalTo(true)));
		assertThrows(IllegalStateException.class, egg::consume);
	}
}