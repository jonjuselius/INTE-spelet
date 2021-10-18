package Item;

import java.util.*;

public abstract class Item {
	
	private int weight;
	private int value;
	private List<String> jobCertifications;
	private List<String> raceCertifications;
	private Size size;
	
	public Item(int weight, int value, String[] jobCertifications, String[] raceCertifications, Size size) {
		this.weight = weight;
		this.value = value;
		this.jobCertifications = Arrays.asList(jobCertifications);
		this.raceCertifications = Arrays.asList(raceCertifications);
		this.size = size;
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
	
	public Size getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName().toString();
	}
}
