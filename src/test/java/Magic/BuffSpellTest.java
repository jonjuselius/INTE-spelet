package Magic;

import GameCharacters.Player;
import Jobs.Magician;
import Map.GameMap;
import Races.Human;
import Map.*;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuffSpellTest {

    public static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    public static final GameMap MAP = MAP_GENERATOR.generate(1);
    public static final GameMapPosition MAP_POSITION = MAP.generateRandomPos(new Random(), new Random());
    private static Human human;
    private static Magician magician;
    private static Player p;

    @BeforeAll
    static void setup(){
        human = new Human();
        magician = new Magician();
        p = mock(Player.class);
        //p.setIntelligence(20);

        //when(p.getStrength()).thenReturn(p.getRace().getStrength());
        when(p.getIntelligence()).thenReturn(20);
        when(p.getMagicSkill()).thenReturn(10);
        when(p.getRace()).thenReturn(human);
        when(p.getJob()).thenReturn(magician);
    }


    @Test
    public void buffSpellConstructor(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);

        assertEquals("StrengthBuff", bs.getName());
        assertEquals(10, bs.getManaCost());
        assertEquals(Element.PHYSICAL, bs.getElement());
        assertEquals(5, bs.getSpellStrength());
    }

    @Test
    public void physicalBuffSpellIncreasesAndDecreasesStrength(){
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
    public void buffSpellLastsCorrectDuration(){
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

        assertEquals(3, strengthStats.size());
        assertTrue(duration > 8 && duration < 30);

    }

    @Test
    public void fireBuffSpellIncreasesAndDecreasesIntelligence(){
        BuffSpell bs = new BuffSpell("IntelligenceBuff", 10, Element.FIRE, 5);
        bs.cast(p,p,1);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(int.class);
        verify(p,times(2)).setIntelligence(argumentCaptor.capture());
        List<Integer> intelligenceStats = argumentCaptor.getAllValues();

        assertEquals(40, intelligenceStats.get(0));
        assertEquals(20, intelligenceStats.get(1));

    }
}