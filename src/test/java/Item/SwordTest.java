package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import Map.GameMap;
import Map.GameMapGenerator;
import Map.GameMapPosition;
import org.junit.jupiter.api.Test;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Job;
import Races.Race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SwordTest {
	private Item sword = new Sword();
	private Item wand = new Wand();
	private Item potion = new Potion();
	private Item shield = new Shield();
	private Item ring = new Ring();
	private GameMapGenerator mapGenerator = new GameMapGenerator(4, 4);
	private GameMap defaultMap = mapGenerator.generate(1);
	private GameMapPosition defaultMapPosition = defaultMap.getMapTiles()[2][2];
	private List<Race> races = new ArrayList<>(Race.getAllRaces());
	private List<Job> jobs = new ArrayList<>(Job.getAllJobs());
	private Race human = races.get(0);
	private Race ogre = races.get(1);
	private Race elf = races.get(2);
	private Job knight = jobs.get(0);
	private Job magician = jobs.get(1);
	private Job healer = jobs.get(2);
	private Character[] characters = {
			new Player("Player", human, knight, true, defaultMapPosition),
			new Player("Player", ogre, knight, true, defaultMapPosition),
			new Player("Player", elf, knight, true, defaultMapPosition),
			new Player("Player", human, magician, true, defaultMapPosition),
			new Player("Player", ogre, magician, true, defaultMapPosition),
			new Player("Player", elf, magician, true, defaultMapPosition),
			new Player("Player", human, healer, true, defaultMapPosition),
			new Player("Player", ogre, healer, true, defaultMapPosition),
			new Player("Player", elf, healer, true, defaultMapPosition),
	};
	private List<Character> players = Arrays.asList(characters);
	private Character knightHuman = characters[0];
	private Character knightOgre = characters[1];
	private Character knightElf = characters[2];
	private Character magicianHuman = characters[3];
	private Character magicianOgre = characters[4];
	private Character magicianElf = characters[5];
	private Character healerHuman = characters[6];
	private Character healerOgre = characters[7];
	private Character healerElf = characters[8];
	
	@Test
	void R1_canUse() {
		assertThat(sword.canBeUsedBy(knightHuman), is(true));
	}
	
	@Test
	void R2_canUse() {
		assertThat(sword.canBeUsedBy(knightOgre), is(true));
	}
	
	@Test
	void R3_canUse() {
		assertThat(sword.canBeUsedBy(knightElf), is(true));
	}
	
	@Test
	void R4_canUse() {
		assertThat(sword.canBeUsedBy(magicianHuman), is(false));
	}
	
	@Test
	void R5_canUse() {
		assertThat(sword.canBeUsedBy(magicianOgre), is(false));
	}
	
	@Test
	void R6_canUse() {
		assertThat(sword.canBeUsedBy(magicianElf), is(false));
	}
	
	@Test
	void R7_canUse() {
		assertThat(sword.canBeUsedBy(healerHuman), is(false));
	}
	
	@Test
	void R8_canUse() {
		assertThat(sword.canBeUsedBy(healerOgre), is(false));
	}
	
	@Test
	void R9_canUse() {
		assertThat(sword.canBeUsedBy(healerElf), is(false));
	}
	
	@Test
	void newSwordHasDefaultWeight() {
		assertThat(new Sword().getWeight(), is(equalTo(Sword.WEIGHT)));
	}
	
	@Test
	void newSwordHasDefaultValue() {
		assertThat(new Sword().getValue(), is(equalTo(Sword.VALUE)));
	}
	
	@Test
	void newSwordHasDefaultSize() {
		assertThat(new Sword().getSize(), is(equalTo(Sword.DEFAULT_SIZE)));
	}
	
	@Test
	void newSwordHasDefaultType() {
		assertThat(new Sword().getType(), is(equalTo(Sword.TYPE)));
	}
	
	@Test
	void newSwordHasDefaultCondition() {
		assertThat(new Sword().getCondition(), is(equalTo(Sword.DEFAULT_CONDITION)));
	}
	
	@Test
	void newSwordWithSizeSpecifiedAsSmallInConstructorCreatesASmallSword() {
		assertThat(new Sword(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newSwordWithSizeSpecifiedAsMediumInConstructorCreatesAMediumSword() {
		assertThat(new Sword(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newSwordWithSizeSpecifiedAsLargeInConstructorCreatesALargeSword() {
		assertThat(new Sword(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void newSwordWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Sword(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void newSwordWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newSwordWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Sword(Item.MAX_CONDITION + 1));
	}
	
}