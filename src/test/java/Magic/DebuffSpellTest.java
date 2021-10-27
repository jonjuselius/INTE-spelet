package Magic;

import GameCharacters.Adversary;
import GameCharacters.Player;
import Jobs.Magician;
import Map.*;
import Races.Human;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DebuffSpellTest {

    private static Player player;
    private static Adversary target;

    @BeforeAll
    public static void setup(){
        Human human = new Human();
        Magician magician = new Magician();
        final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
        final GameMap MAP = MAP_GENERATOR.generate(1);
        final GameMapPosition MAP_POSITION = MAP.generateRandomPos(new Random(), new Random());
        player = new Player("Player1", human, magician, true, MAP_POSITION);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION);
    }

    @Test
    public void debuffSpellConstructor(){
        DebuffSpell ds = new DebuffSpell("StrengthDebuff", 10, Element.PHYSICAL, 5);

        assertEquals("StrengthDebuff", ds.getName());
        assertEquals(10, ds.getManaCost());
        assertEquals(Element.PHYSICAL, ds.getElement());
        assertEquals(5, ds.getSpellStrength());
    }

    @Test
    public void exceptionThrownWhenNotEnoughManaForCast(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 305, Element.PHYSICAL, 5);

        assertThrows(IllegalStateException.class, ()-> bs.cast(player,1));
    }

    @Test
    void castDepletesCorrectAmountOfMana(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        bs.cast(player,1);
        assertEquals(290, player.getRemainingMana());
    }



}