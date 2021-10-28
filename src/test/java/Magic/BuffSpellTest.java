package Magic;

import GameCharacters.Player;
import Jobs.Magician;
import Races.Human;
import Map.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuffSpellTest {

    private static final int HUMAN_INTELLIGENCE = 20;
    private static final int MAGICIAN_MAGICSKILL = 10;
    private static final int MAGICIAN_MANA = 300;
    private static final int MAGICIAN_HEALINGSKILL = 7;
    private static Human human;
    private static Magician magician;
    private static Player mockPlayer;
    private static GameMapPosition mockMapPosition;

    @BeforeAll
    static void setup(){
        human = new Human();
        magician = new Magician();
        mockPlayer = mock(Player.class);

        when(mockPlayer.getIntelligence()).thenReturn(HUMAN_INTELLIGENCE);
        when(mockPlayer.getMagicSkill()).thenReturn(MAGICIAN_MAGICSKILL);
        when(mockPlayer.getRemainingMana()).thenReturn(MAGICIAN_MANA);
        when(mockPlayer.getMaxMana()).thenReturn(MAGICIAN_MANA);
        when(mockPlayer.getHealingSkill()).thenReturn(MAGICIAN_HEALINGSKILL);
        when(mockPlayer.getRace()).thenReturn(human);
        when(mockPlayer.getJob()).thenReturn(magician);

        mockMapPosition = mock(GameMapPosition.class);

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
    public void exceptionThrownWhenNotEnoughManaForCast(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 305, Element.PHYSICAL, 5);

        assertThrows(IllegalStateException.class, ()-> bs.cast(mockPlayer,1));
    }

    @Test
    void castDepletesCorrectAmountOfMana(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, mockMapPosition);
        bs.cast(p,1);
        assertEquals(290, p.getRemainingMana());
    }

    @Test
    public void physicalBuffSpellIncreasesAndDecreasesStrength(){
        List<Integer> strengthStats = new ArrayList<>();
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, mockMapPosition){
            @Override
            public void setStrength(int strength){
                super.setStrength(strength);
                strengthStats.add(strength);
            }
        };
        bs.cast(p,1);

        assertEquals(3, strengthStats.size());
        assertEquals(20, strengthStats.get(0));
        assertEquals(23, strengthStats.get(1));
        assertEquals(20, strengthStats.get(2));
    }

    @Test
    public void buffSpellLastsCorrectDuration(){
        List<Integer> strengthStats = new ArrayList<>();
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, mockMapPosition){
            @Override
            public void setStrength(int strength){
                super.setStrength(strength);
                strengthStats.add((int) System.nanoTime());
            }
        };
        bs.cast(p,10);
        long duration = (strengthStats.get(2) - strengthStats.get(0)) / 1000000;

        assertEquals(3, strengthStats.size());
        assertTrue(duration > 8 && duration < 30);

    }

    @Test
    void castCalcuatesDurationFromSpellcaster(){
        List<Integer> strengthStats = new ArrayList<>();
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, mockMapPosition){
            @Override
            public void setStrength(int strength){
                super.setStrength(strength);
                strengthStats.add((int) System.nanoTime());
            }
        };
        bs.cast(p, p);
        long duration = (strengthStats.get(0) - strengthStats.get(2)) / 1000000;

        assertEquals(3, strengthStats.size());
        assertTrue(duration > 1000 && duration < 3000);

    }
    //Unsuccessful attempt at checking duration with a mock
   /* @Test
    public void buffSpellLastsCorrectDurationMock(){
        BuffSpell bs = new BuffSpell("IntelligenceBuff", 10, Element.FIRE, 5);
        bs.cast(mockPlayer,10);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(long.class);
        verify(mockPlayer,times(2)).setIntelligence((int) System.nanoTime().argumentCaptor.capture());

        List<Integer> intelligenceStats = argumentCaptor.getAllValues();
        long startTime = intelligenceStats.get(1) ;


    }*/

    @Test
    public void fireBuffSpellIncreasesAndDecreasesIntelligence(){
        BuffSpell bs = new BuffSpell("IntelligenceBuff", 10, Element.FIRE, 5);
        bs.cast(mockPlayer,1);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(int.class);
        verify(mockPlayer,times(2)).setIntelligence(argumentCaptor.capture());
        List<Integer> intelligenceStats = argumentCaptor.getAllValues();

        assertEquals(23, intelligenceStats.get(0));
        assertEquals(20, intelligenceStats.get(1));
    }

    @Test
    public void lightningBuffSpellIncreasesAndDecreasesMagicSkill(){
        BuffSpell bs = new BuffSpell("MagicBuff", 10, Element.LIGHTNING, 5);
        bs.cast(mockPlayer,1);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(int.class);
        verify(mockPlayer,times(2)).setMagicSkill(argumentCaptor.capture());
        List<Integer> magicStats = argumentCaptor.getAllValues();

        assertEquals(12, magicStats.get(0));
        assertEquals(10, magicStats.get(1));
    }

    @Test
    public void waterBuffSpellIncreasesAndDecreasesHealingSkill(){
        BuffSpell bs = new BuffSpell("HealingBuff", 10, Element.WATER, 5);
        bs.cast(mockPlayer,1);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(int.class);
        verify(mockPlayer,times(2)).setHealingSkill(argumentCaptor.capture());
        List<Integer> healingStats = argumentCaptor.getAllValues();

        assertEquals(8, healingStats.get(0));
        assertEquals(7, healingStats.get(1));
    }

    @Test
    public void windBuffSpellIncreasesAndDecreasesMaxMana(){
        BuffSpell bs = new BuffSpell("ManaBuff", 10, Element.WIND, 5);
        bs.cast(mockPlayer,1);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(int.class);
        verify(mockPlayer,times(2)).setMaxMana(argumentCaptor.capture());
        List<Integer> manaStats = argumentCaptor.getAllValues();

        assertEquals(345, manaStats.get(0));
        assertEquals(300, manaStats.get(1));
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple()
                .forClass(BuffSpell.class).withIgnoredFields()
                .verify();
    }


}