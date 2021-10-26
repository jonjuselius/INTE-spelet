package Magic;

import GameCharacters.Adversary;
import GameCharacters.Player;
import Jobs.*;
import Map.*;
import Races.*;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HealingSpellTest {
    public static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    public static final GameMap MAP = MAP_GENERATOR.generate(1);
    public static final GameMapPosition MAP_POSITION = MAP.generateRandomPos(new Random(), new Random());
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
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);

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
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, magician, true, MAP_POSITION);
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
        HealingSpell hs = new HealingSpell("Morphine-pill", 305, Element.PHYSICAL, 10);
        Player p = new Player("Player1", human, magician,true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION);

        assertThrows(IllegalStateException.class, ()-> hs.cast(p,a));
    }

    @Test
    void castDepletesCorrectAmountOfMana(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, magician,true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION);
        hs.cast(p,a);

        assertEquals(295, p.getRemainingMana());
    }

    @Test
    void castIncreasesHealthCorrectAmount(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, magician,true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION);
        p.setRemainingHealth(0);
        hs.cast(p,a);

        assertEquals(35, p.getRemainingHealth());
    }

    @Test
    void castIncreasesHealthToMaxIfHealAmountTooHigh(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, magician,true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION);
        p.setRemainingHealth(290);
        hs.cast(p,a);

        assertEquals(300, p.getRemainingHealth());
    }

}