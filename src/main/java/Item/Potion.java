package Item;

import Jobs.*;
import Races.*;
import Races.Elf;
import Races.Human;
import Races.Ogre;

public class Potion extends Item {
	public static final int WEIGHT = 2;
	public static final int VALUE = 10;
	public static final Type TYPE = Type.FOOD;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final Job[] JOB_CERTIFICATIONS = {new Knight(), new Magician(), new Healer()};
	public static final Race[] RACE_CERTIFICATIONS = {new Human(), new Ogre(), new Elf()};
	
	public Potion() {
		this(DEFAULT_CONDITION, DEFAULT_SIZE);
	}
	
	public Potion(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Potion(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Potion(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
}
