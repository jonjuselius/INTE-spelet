package Item;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {
	
	public static final Item DEFAULT_SWORD = new Sword();
	public static final Item DEFAULT_WAND = new Wand();
	public static final Item DEFAULT_EGG = new Egg();
	public static final Item DEFAULT_SHIELD = new Shield();
	public static final Item DEFAULT_RING = new Ring();
	
	@Test
	void newItemHasDefaultItemWeight() {
		assertThat(DEFAULT_SWORD.getWeight(), is(equalTo(Sword.WEIGHT)));
		assertThat(DEFAULT_WAND.getWeight(), is(equalTo(Wand.WEIGHT)));
		assertThat(DEFAULT_EGG.getWeight(), is(equalTo(Egg.WEIGHT)));
		assertThat(DEFAULT_SHIELD.getWeight(), is(equalTo(Shield.WEIGHT)));
		assertThat(DEFAULT_RING.getWeight(), is(equalTo(Ring.WEIGHT)));
	}
	
	@Test
	void newItemHasDefaultItemValue() {
		assertThat(DEFAULT_SWORD.getValue(), is(equalTo(Sword.VALUE)));
		assertThat(DEFAULT_WAND.getValue(), is(equalTo(Wand.VALUE)));
		assertThat(DEFAULT_EGG.getValue(), is(equalTo(Egg.VALUE)));
		assertThat(DEFAULT_SHIELD.getValue(), is(equalTo(Shield.VALUE)));
		assertThat(DEFAULT_RING.getValue(), is(equalTo(Ring.VALUE)));
	}
	
	@Test
	void itemWithNoJobRestrictionsCanBeUsedByAllJobs() {
		List<String> jobs = Arrays.asList("Knight", "Magician", "Healer");
		List<String> jobCertifications = DEFAULT_EGG.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void swordCanOnlyBeUsedByKnight() {
		List<String> jobs = Arrays.asList("Knight");
		List<String> jobCertifications = DEFAULT_SWORD.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void wandCanOnlyBeUsedByMagicianAndHealer() {
		List<String> jobs = Arrays.asList("Magician", "Healer");
		List<String> jobCertifications = DEFAULT_WAND.getJobCertifications();
		String[] jobsArr = jobs.toArray(new String[jobs.size()]);
		String[] jobCertificationsArr = jobCertifications.toArray(new String[jobCertifications.size()]);
		assertArrayEquals(jobsArr, jobCertificationsArr);
	}
	
	@Test
	void itemWithNoRaceRestrictionsCanBeUsedByAllRaces() {
		List<String> races = Arrays.asList("Human", "Ogre", "Elf");
		List<String> raceCertifications = DEFAULT_EGG.getRaceCertifications();
		String[] racesArr = races.toArray(new String[races.size()]);
		String[] raceCertificationsArr = raceCertifications.toArray(new String[raceCertifications.size()]);
		assertArrayEquals(racesArr, raceCertificationsArr);
	}
	
	@Test
	void newSwordHasDefaultSize() {
		assertThat(DEFAULT_SWORD.getSize(), is(equalTo(Sword.DEFAULT_SIZE)));
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
		assertThat(DEFAULT_SWORD.getCondition(), is(equalTo(Sword.DEFAULT_CONDITION)));
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
		assertThat(DEFAULT_SWORD.getType(), is(equalTo(Sword.TYPE)));
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
		assertThrows(IllegalStateException.class, () -> egg.consume());
	}
	
}