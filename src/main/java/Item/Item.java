package Item;

import java.util.*;

public abstract class Item {
	
	private int weight;
	private int value;
	private List<String> jobCertifications;
	private List<String> raceCertifications;
	
	public Item(int weight, int value, String[] jobCertifications, String[] raceCertifications) {
		this.weight = weight;
		this.value = value;
		this.jobCertifications = Arrays.asList(jobCertifications);
		this.raceCertifications = Arrays.asList(raceCertifications);
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getValue() {
		return value;
	}
	
	public List<String> getJobCertifications() {
		return Collections.unmodifiableList(jobCertifications);
	}
	
	public List<String> getRaceCertifications() {
		return Collections.unmodifiableList(raceCertifications);
	}
}
