package CharactersRaces;

public class Human extends Character {

	private int lifePoints = 100;

	public Human(String name, int age, boolean alive) {
		super(name, age, alive);
		this.lifePoints = 100;
		setCharisma(20);
	}

	public int getLifePoints() {
		return lifePoints;
	}

}
