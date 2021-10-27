package Item;

import Jobs.Healer;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;

public class Sword extends Item {
	public static final int WEIGHT = 10;
	public static final int VALUE = 200;
	public static final Type TYPE = Type.WEAPON;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final Job[] JOB_CERTIFICATIONS = {new Knight()};
	public static final Race[] RACE_CERTIFICATIONS = {new Human(), new Ogre(), new Elf()};
	
	public Sword() {
		this(DEFAULT_CONDITION, DEFAULT_SIZE);
	}
	
	public Sword(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Sword(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Sword(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
	
}
