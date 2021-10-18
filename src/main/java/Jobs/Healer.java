package Jobs;

public class Healer extends Job {

	public Healer() {
		
		
		setHealing(10);
		setMagic(3);
		setSwordSkill(7);
		setMaxMana(350);

	}

	public int getHealing() {
		return healingSkill;
	}

//increase healing depending on level

}