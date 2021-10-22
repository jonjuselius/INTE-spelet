package Item;

public class Shield extends Item {
	/**
	 * Shield is an instantiable item categorized under the item type "armor".
	 * The purpose of a shield is to protect the character from taking damage.
	 * Only knights can use shields, but it doesn't matter which race the
	 * character is. This means that humans, ogres and elves can use shields,
	 * as long as their job is knight. A new shield has a perfect condition,
	 * the mean weight of a shield is 100 units, and the mean value of a shield
	 * is 1000 units. This means that a shield is usually heavy and quite expensive.
	 * Shields come in different sizes: small, medium and large, but unless otherwise
	 * specificed, medium is the default size for a shield.
	 */
	public static final int WEIGHT = 100;
	public static final int VALUE = 1000;
	public static final Type TYPE = Type.ARMOR;
	public static final Size DEFAULT_SIZE = Size.MEDIUM;
	public static final int DEFAULT_CONDITION = Item.MAX_CONDITION;
	public static final String[] JOB_CERTIFICATIONS = {"Knight"};
	public static final String[] RACE_CERTIFICATIONS = {"Human", "Ogre", "Elf"};
	
	public Shield() {
		this(DEFAULT_CONDITION);
	}
	
	public Shield(int condition) {
		this(condition, DEFAULT_SIZE);
	}
	
	public Shield(Size size) {
		this(DEFAULT_CONDITION, size);
	}
	
	public Shield(int condition, Size size) {
		super(WEIGHT, VALUE, JOB_CERTIFICATIONS, RACE_CERTIFICATIONS, size, TYPE, condition);
	}
}
