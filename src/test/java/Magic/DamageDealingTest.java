package Magic;

import GameCharacters.*;
import Races.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageDealingTest {

    Human human = new Human();

    @Test
    void elementalDamageConstructor(){
        DamageDealingSpell dd = new DamageDealingSpell("Fireball", 50, Element.FIRE);

        assertEquals("Fireball", dd.getName());
        assertEquals(50, dd.getManaCost());
        assertEquals(Element.FIRE, dd.getElement());
    }

    @Test
    void physicalDamageSpellConstructor(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);

        assertEquals("Stonefist", dd.getName());
        assertEquals(25, dd.getManaCost());
        assertEquals(Element.PHYSICAL, dd.getElement());
    }

    @Test
    void defaultInitialDamage() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        assertEquals(10, dd.getInitialDamage());
    }

    @Test
    void defaultDamageOverTime() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        assertEquals(0, dd.getDamageOverTime());
    }

    @Test
    void defaultDuration(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        assertEquals(0, dd.getDuration());
    }

    //TODO när player kan skapas
    @Test
    void powerProgressionChangesInitialDamage() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        Player p = new Player("Player1", human, true);
        dd.powerProgression(p);

        assertEquals(230, dd.getInitialDamage());
    }

    @Test
    void castThrowsExceptionWhenManaTooLow() {
        DamageDealingSpell dd = new DamageDealingSpell("Implode", 250, Element.FIRE);
        Player p = new Player("Player1", human, true);
        Adversary a = new Adversary("Bandit", human, true, 5);

        assertThrows(IllegalStateException.class, ()-> dd.cast(p,a));
    }

    @Test
    void castDecreasesCorrectMana() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        Player p = new Player("Player1", human, true);
        Adversary a = new Adversary("Bandit", human, true, 5);
        dd.cast(p,a);
        assertEquals(175, p.getRemainingMana());
    }

    @Test
    void castDealsCorrectDamage(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        Player p = new Player("Player1", human, true);
        Adversary a = new Adversary("Bandit", human, true, 1);
        dd.cast(p,a);

        assertEquals(70, a.getRemainingHealth());
    }
}