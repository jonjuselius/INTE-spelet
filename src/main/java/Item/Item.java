package Item;

import java.util.*;
import GameCharacters.Character;
import Jobs.Job;
import Races.Race;

public abstract class Item {
	
	public static final int MAX_CONDITION = 100;
	public static final int MIN_CONDITION = 0;
	private int weight;
	private int value;
	private List<String> jobCertifications;
	private List<String> raceCertifications;
	private Size size;
	private Type type;
	private int condition;
	
	public Item(int weight, int value, String[] jobCertifications, String[] raceCertifications, Size size, Type type, int condition) {
		if (condition < MIN_CONDITION || condition > MAX_CONDITION) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
		this.value = value;
		this.jobCertifications = Arrays.asList(jobCertifications);
		this.raceCertifications = Arrays.asList(raceCertifications);
		this.size = size;
		this.type = type;
		this.condition = condition;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getCondition() {
		return condition;
	}
	
	public Type getType() {
		return type;
	}
	
	public List<String> getJobCertifications() {
		return Collections.unmodifiableList(jobCertifications);
	}
	
	public List<String> getRaceCertifications() {
		return Collections.unmodifiableList(raceCertifications);
	}
	
	public Size getSize() {
		return size;
	}
	
	public boolean canBeUsedBy(Character character) {
		Race race = character.getRace();
		Job job = character.getJob();
		
		String raceStr = race.getClass().getSimpleName().toString();
		String jobStr = job.getClass().getSimpleName().toString();
		
		//System.out.println(raceStr);
		//System.out.println(jobStr);
		
		if (getRaceCertifications().contains(raceStr) && getJobCertifications().contains(jobStr)) {
			return true;
		}
		
		return false;
	}
}
