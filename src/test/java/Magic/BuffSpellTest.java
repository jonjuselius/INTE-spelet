package Magic;

import GameCharacters.Player;
import Jobs.Magician;
import Map.GameMap;
import Races.Human;
import Map.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuffSpellTest {

    private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    public static final GameMap MAP = MAP_GENERATOR.generate(1);
    private static final GameMapPosition MAP_POSITION = MAP.getMapTiles()[2][2];
    Human human = new Human();
    Magician magician = new Magician();

    @Test
    void buffSpellConstructor(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);

        assertEquals("StrengthBuff", bs.getName());
        assertEquals(10, bs.getManaCost());
        assertEquals(Element.PHYSICAL, bs.getElement());
        assertEquals(5, bs.getSpellStrength());
    }

    @Test
    void physicalBuffSpellIncreasesAndDecreasesStrength(){
        List<Integer> strengthStats = new ArrayList<>();
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, MAP_POSITION){
            @Override
            public void setStrength(int strength){
                super.setStrength(strength);
                strengthStats.add(strength);
            }
        };
        bs.cast(p,p,1);

        assertEquals(3, strengthStats.size());
        assertEquals(20, strengthStats.get(0));
        assertEquals(40, strengthStats.get(1));
        assertEquals(20, strengthStats.get(2));
    }

    @Test
    void buffSpellLastsCorrectDuration(){
        List<Integer> strengthStats = new ArrayList<>();
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, MAP_POSITION){
            @Override
            public void setStrength(int strength){
                super.setStrength(strength);
                strengthStats.add((int) System.nanoTime());
            }
        };
        bs.cast(p,p,10);
        long duration = (strengthStats.get(2) - strengthStats.get(0)) / 1000000;
        System.out.println(strengthStats);
        System.out.println(duration);

        assertEquals(3, strengthStats.size());
        assertTrue(duration > 8 && duration < 30);

    }
}