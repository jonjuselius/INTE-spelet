package Jobs;

public class Healer extends Job {

	public Healer() {

		setHealing(10);
		setSwordSkill(5);
		setMaxMana(350);

	}

	public int getHealing() {
		return healingSkill;
	}

//increase healing depending on level

}