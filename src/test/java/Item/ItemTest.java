package Item;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ItemTest {
	
	public static final Item DEFAULT_SWORD = new Sword();
	public static final Item DEFAULT_WAND = new Wand();
	public static final Item DEFAULT_EGG = new Egg();
	
	@Test
	void newItemHasDefaultItemWeight() {
		assertThat(DEFAULT_SWORD.getWeight(), is(equalTo(Sword.WEIGHT)));
		assertThat(DEFAULT_WAND.getWeight(), is(equalTo(Wand.WEIGHT))); // Överflödigt?
		assertThat(DEFAULT_EGG.getWeight(), is(equalTo(Egg.WEIGHT))); // Överflödigt?
	}
	
	@Test
	void newItemHasDefaultItemValue() {
		assertThat(DEFAULT_SWORD.getValue(), is(equalTo(Sword.VALUE)));
		assertThat(DEFAULT_WAND.getValue(), is(equalTo(Wand.VALUE))); // Överflödigt?
		assertThat(DEFAULT_EGG.getValue(), is(equalTo(Egg.VALUE))); // Överflödigt?
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
	
}