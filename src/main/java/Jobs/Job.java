package Jobs;

import Races.Human;
import Races.Ogre;
import Races.Race;

import java.util.*;

public abstract class Job {

	protected int magicSkill;
	protected int healingSkill;
	protected int swordSkill;
	protected int maxMana;

	public Job() {

	}

	public int getMagic() {
		return magicSkill;
	}

	protected void setMagic(int magicSkill) {
		this.magicSkill = magicSkill;
	}

	public int getHealing() {
		return healingSkill;
	}

	public void setHealing(int healingSkill) {
		this.healingSkill = healingSkill;
	}

	public int getSwordSkill() {
		return swordSkill;
	}

	protected void setSwordSkill(int swordSkill) {
		this.swordSkill = swordSkill;
	}

	public int getMaxMana() {
		return maxMana;
	}

	protected void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	
	public static List<Job> getAllJobs() {
		List<Job> allJobs = new ArrayList<>();
		allJobs.add(new Knight());
		allJobs.add(new Healer());
		allJobs.add(new Magician());
		return allJobs;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Job job = (Job) o;
		return magicSkill == job.magicSkill && healingSkill == job.healingSkill && swordSkill == job.swordSkill && maxMana == job.maxMana;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(magicSkill, healingSkill, swordSkill, maxMana);
	}
}
