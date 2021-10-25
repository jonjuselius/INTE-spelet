package Races;

public abstract class Job {

	private int magicSkill;
	private int healingSkill;
	private int swordSkill;

	public Job() {
		setMagic(0);
		setHealing(0);
		setSwordSkill(0);
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

	protected void setHealing(int healingSkill) {
		this.healingSkill = healingSkill;
	}

	public int getSwordSkill() {
		return swordSkill;
	}

	protected void setSwordSkill(int swordSkill) {
		this.swordSkill = swordSkill;
	}
	
	

}
