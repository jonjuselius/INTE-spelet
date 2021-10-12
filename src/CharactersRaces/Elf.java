package CharactersRaces;

public class Elf extends Character {

	private int lifePoints = 300;

	public Elf(String name, int age, boolean alive) {
		super(name, age, alive);
		this.lifePoints = 100;
		setWisdom(20);
		setIntelligence(30);
	}

	public int getLifePoints() {
		return lifePoints;
	}

}
