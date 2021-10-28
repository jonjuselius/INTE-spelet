package Item;

import Jobs.Healer;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;

public class Ring extends Item {
	public static final int WEIGHT = 5;
	public static final int VALUE = 5000;
	public static final Type TYPE = Type.JEWELLERY;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final Job[] JOB_CERTIFICATIONS = {new Knight(), new Magician(), new Healer()};
	public static final Race[] RACE_CERTIFICATIONS = {new Human(), new Ogre(), new Elf()};
	
	public Ring() {
		this(DEFAULT_CONDITION);
	}
	
	public Ring(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Ring(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Ring(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
}
