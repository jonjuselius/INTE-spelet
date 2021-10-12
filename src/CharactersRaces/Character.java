package CharactersRaces;

public abstract class Character {

	private String name;
	private int age;
	private boolean alive;

	private int strength;
	private int intelligence;
	private int wisdom;
	private int charisma;

	// hitpoints attackpoints?

	public Character(String name, int age, boolean alive) {
		this.name = name;
		this.age = age;
		alive = true;
		setStrength(10);
		setIntelligence(10);
		setWisdom(10);
		setCharisma(10);
		
	}

	public int getStrength() {
		return strength;
	}

	protected void setStrength(int strength) {
		this.strength = strength;
	}

	public int getintelligence() {
		return intelligence;
	}

	protected void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	protected void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	protected void setCharisma(int charisma) {
		this.charisma = charisma;
	}
}