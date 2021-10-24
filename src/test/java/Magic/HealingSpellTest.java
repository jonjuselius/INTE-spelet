package Magic;

import GameCharacters.Adversary;
import GameCharacters.Player;
import Jobs.Magician;
import Races.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealingSpellTest {
    private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
    public static final Map MAP = MAP_GENERATOR.generate(1);
    private static final MapPosition MAP_POSITION = MAP.getMapTiles()[2][2];
    Human human = new Human();
    Magician magician = new Magician();
    Healer healer = new Healer();

    @Test
    void elementalHealingConstructor(){
        HealingSpell hs = new HealingSpell("Cauterize", 30, Element.FIRE,6);

        assertEquals("Cauterize", hs.getName());
        assertEquals(30, hs.getManaCost());
        assertEquals(Element.FIRE, hs.getElement());
        assertEquals(6, hs.getBaseHeal());
    }

    @Test
    void physicalHealingConstructor(){
        HealingSpell hs = new HealingSpell("Band-aid", 5);

        assertEquals("Band-aid", hs.getName());
        assertEquals(5, hs.getManaCost());
        assertEquals(Element.PHYSICAL, hs.getElement());
    }

    @Test
    void defaultInitialHeal() {
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);

        assertEquals(1, hs.getInitialHeal());
    }

    @Test
    void defaultHealOverTime() {
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);

        assertEquals(0, hs.getHealOverTime());
    }


    @Test
    void defaultDuration() {
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);

        assertEquals(0, hs.getDuration());
    }

    @Test
    void powerProgressionSetsCorrectInitialHeal(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1, MAP_POSITION);
        Player p = new Player("Player1", human, magician, true);
        hs.powerProgression(p);

        assertEquals(35, hs.getInitialHeal());
    }

    @Test
    void powerProgressionSetsCorrectInitialHealForHealer(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, healer, true, MAP_POSITION);
        hs.powerProgression(p);

        assertEquals(82, hs.getInitialHeal());
    }


    @Test
    void exceptionThrownWhenNotEnoughManaForCast(){
        HealingSpell hs = new HealingSpell("Morphine-pill", 205);
        Player p = new Player("Player1", human, magician,true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician,true, 1, MAP.generateRandomPos(1, 1));

        assertThrows(IllegalStateException.class, ()-> hs.cast(p,a));
    }

    @Test
    void castDepletesCorrectAmountOfMana(){
        HealingSpell hs = new HealingSpell("Band-aid", 5);
        Player p = new Player("Player1", human, magician,true);
        Adversary a = new Adversary("Bandit", human, magician,true, 1);
        hs.cast(p,a);

        assertEquals(195, p.getRemainingMana());
    }

    @Test
    void castIncreasesHealthCorrectAmount(){
        HealingSpell hs = new HealingSpell("Band-aid", 5);
        Player p = new Player("Player1", human, magician,true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician,true, 1, MAP.generateRandomPos(1, 1));
        p.setRemainingHealth(0);
        hs.cast(p,a);

        assertEquals(130, p.getRemainingHealth());
    }

    @Test
    void castIncreasesHealthToMaxIfHealAmountTooHigh(){
        HealingSpell hs = new HealingSpell("Band-aid", 5);
        Player p = new Player("Player1", human, magician,true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician,true, 1, MAP.generateRandomPos(1, 1));
        p.setRemainingHealth(190);
        hs.cast(p,a);

        assertEquals(300, p.getRemainingHealth());
    }

}