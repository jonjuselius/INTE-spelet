package Jobs;

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

	protected void setHealing(int healingSkill) {
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
}
