package Magic;

import GameCharacters.*;
import Jobs.Magician;
import Map.*;
import Races.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class DamageDealingTest {

    private static Human human;
    private static Magician magician;
    private static GameMapPosition mockMapPosition;

    @BeforeAll
    static void setup(){
        human = new Human();
        magician = new Magician();
        mockMapPosition = mock(GameMapPosition.class);

    }

    @Test
    void damageDealingSpellConstructor(){
        DamageDealingSpell dd = new DamageDealingSpell("Fireball", 50, Element.FIRE, 10);

        assertEquals("Fireball", dd.getName());
        assertEquals(50, dd.getManaCost());
        assertEquals(Element.FIRE, dd.getElement());
        assertEquals(10, dd.getBaseDamage());
    }

    @Test
    void defaultInitialDamage() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        assertEquals(5, dd.getInitialDamage());
    }

    @Test
    void defaultDamageOverTime() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        assertEquals(0, dd.getDamageOverTime());
    }

    @Test
    void damageOverTime(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        dd.setDuration(6);
        dd.setDamageOverTime(6);

        assertEquals(1,dd.getDamageOverTime());
    }

    @Test
    void defaultDuration(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        assertEquals(0, dd.getDuration());
    }

    @Test
    void powerProgressionChangesInitialDamage() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, mockMapPosition);
        dd.powerProgression(p);

        assertEquals(205, dd.getInitialDamage());
    }

    @Test
    void castThrowsExceptionWhenManaTooLow() {
        DamageDealingSpell dd = new DamageDealingSpell("Implode", 305, Element.FIRE, 100);
        Player p = new Player("Player1", human, magician, true, mockMapPosition);
        Adversary a = new Adversary("Bandit", human, magician, true, 5, mockMapPosition);

        assertThrows(IllegalStateException.class, ()-> dd.cast(p,a));
    }

    @Test
    void castDecreasesCorrectMana() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, mockMapPosition);
        Adversary a = new Adversary("Bandit", human, magician, true, 5, mockMapPosition);
        dd.cast(p,a);
        assertEquals(275, p.getRemainingMana());
    }

    @Test
    void castDealsCorrectDamage(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, mockMapPosition);
        Adversary a = new Adversary("Bandit", human, magician, true, 1, mockMapPosition);
        dd.cast(p,a);

        assertEquals(95, a.getRemainingHealth());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple()
                .forClass(DamageDealingSpell.class).withIgnoredFields("initialDamage", "damageOverTime", "duration")
                .verify();
    }
}