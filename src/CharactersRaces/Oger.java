package CharactersRaces;

public class Oger extends Character {

	private int lifePoints = 400;

	public Oger(String name, int age, boolean alive) {
		super(name, age, alive);
		this.lifePoints = 100;
		setStrength(30);
	}

	public int getLifePoints() {
		return lifePoints;
	}

}
