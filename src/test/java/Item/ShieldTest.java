package Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import Map.GameMap;
import Map.GameMapGenerator;
import Map.GameMapPosition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Healer;
import Jobs.Job;
import Jobs.Knight;
import Jobs.Magician;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ShieldTest {
	private Item shield = new Shield();
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
	void R28_canUse() {
		assertThat(knightHuman.canUse(shield), is(true));
	}
	
	@Test
	void R29_canUse() {
		assertThat(knightOgre.canUse(shield), is(true));
	}
	
	@Test
	void R30_canUse() {
		assertThat(knightElf.canUse(shield), is(true));
	}
	
	@Test
	void R31_canUse() {
		assertThat(magicianHuman.canUse(shield), is(false));
	}
	
	@Test
	void R32_canUse() {
		assertThat(magicianOgre.canUse(shield), is(false));
	}
	
	@Test
	void R33_canUse() {
		assertThat(magicianElf.canUse(shield), is(false));
	}
	
	@Test
	void R34_canUse() {
		assertThat(healerHuman.canUse(shield), is(false));
	}
	
	@Test
	void R35_canUse() {
		assertThat(healerOgre.canUse(shield), is(false));
	}
	
	@Test
	void R36_canUse() {
		assertThat(healerElf.canUse(shield), is(false));
	}
	
	@Test
	void newShieldHasDefaultWeight() {
		assertThat(new Shield().getWeight(), is(equalTo(Shield.WEIGHT)));
	}
	
	@Test
	void newShieldHasDefaultValue() {
		assertThat(new Shield().getValue(), is(equalTo(Shield.VALUE)));
	}
	
	@Test
	void newShieldHasDefaultSize() {
		assertThat(new Shield().getSize(), is(equalTo(Shield.DEFAULT_SIZE)));
	}
	
	@Test
	void newShieldHasDefaultType() {
		assertThat(new Shield().getType(), is(equalTo(Shield.TYPE)));
	}
	
	@Test
	void newShieldHasDefaultCondition() {
		assertThat(new Shield().getCondition(), is(equalTo(Shield.DEFAULT_CONDITION)));
	}
	
	@Test
	void newShieldWithSizeSpecifiedAsSmallInConstructorCreatesASmallShield() {
		assertThat(new Shield(Size.SMALL).getSize(), is(equalTo(Size.SMALL)));
	}
	
	@Test
	void newShieldWithSizeSpecifiedAsMediumInConstructorCreatesAMediumShield() {
		assertThat(new Shield(Size.MEDIUM).getSize(), is(equalTo(Size.MEDIUM)));
	}
	
	@Test
	void newShieldWithSizeSpecifiedAsLargeInConstructorCreatesALargeShield() {
		assertThat(new Shield(Size.LARGE).getSize(), is(equalTo(Size.LARGE)));
	}
	
	@Test
	void newShieldWithConditionBetweenMinAndMaxSetsConditionInConstructor() {
		assertThat(new Shield(50).getCondition(), is(equalTo(50)));
	}
	
	@Test
	void newShieldWithConditionUnderMinimumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Shield(Item.MIN_CONDITION - 1));
	}
	
	@Test
	void newShieldWithConditionOverMaximumThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Shield(Item.MAX_CONDITION + 1));
	}
}