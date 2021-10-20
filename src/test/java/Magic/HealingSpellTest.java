package Magic;

import GameCharacters.*;
import Jobs.*;
import Races.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealingSpellTest {

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
        Player p = new Player("Player1", human, magician, true);
        hs.powerProgression(p);

        assertEquals(35, hs.getInitialHeal());
    }

    @Test
    void powerProgressionSetsCorrectInitialHealForHealer(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, healer, true);
        hs.powerProgression(p);

        assertEquals(82, hs.getInitialHeal());
    }


    @Test
    void exceptionThrownWhenNotEnoughManaForCast(){
        HealingSpell hs = new HealingSpell("Morphine-pill", 305, Element.PHYSICAL, 41);
        Player p = new Player("Player1", human, magician,true);
        Adversary a = new Adversary("Bandit", human, magician,true, 1);

        assertThrows(IllegalStateException.class, ()-> hs.cast(p,a));
    }

    @Test
    void castDepletesCorrectAmountOfMana(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, magician,true);
        Adversary a = new Adversary("Bandit", human, magician,true, 1);
        hs.cast(p,a);

        assertEquals(295, p.getRemainingMana());
    }

    @Test
    void castIncreasesHealthCorrectAmount(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, magician,true);
        Adversary a = new Adversary("Bandit", human, magician,true, 1);
        p.setRemainingHealth(0);
        hs.cast(p,a);

        assertEquals(35, p.getRemainingHealth());
    }

    @Test
    void castIncreasesHealthToMaxIfHealAmountTooHigh(){
        HealingSpell hs = new HealingSpell("Band-aid", 5, Element.PHYSICAL, 1);
        Player p = new Player("Player1", human, magician,true);
        Adversary a = new Adversary("Bandit", human, magician,true, 1);
        p.setRemainingHealth(190);
        hs.cast(p,a);

        assertEquals(300, p.getRemainingHealth());
    }

}