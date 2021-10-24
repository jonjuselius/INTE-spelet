package Magic;

import GameCharacters.*;
import Jobs.Magician;
import Map.*;
import Races.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageDealingTest {

    private Human human = new Human();
    private Magician magician = new Magician();
    private MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
    private Map MAP = MAP_GENERATOR.generate(1);
    private MapPosition MAP_POSITION = MAP.getMapTiles()[2][2];

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
    void defaultDuration(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        assertEquals(0, dd.getDuration());
    }

    //TODO när player kan skapas
    @Test
    void powerProgressionChangesInitialDamage() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        Player p = new Player("Player1", human, magician, true);
        dd.powerProgression(p);

        assertEquals(205, dd.getInitialDamage());
    }

    @Test
    void castThrowsExceptionWhenManaTooLow() {
        DamageDealingSpell dd = new DamageDealingSpell("Implode", 250, Element.FIRE);
        Player p = new Player("Player1", human, magician, true);
        Adversary a = new Adversary("Bandit", human, magician, true, 5);

        assertThrows(IllegalStateException.class, ()-> dd.cast(p,a));
    }

    @Test
    void castDecreasesCorrectMana() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        Player p = new Player("Player1", human, magician, true);
        Adversary a = new Adversary("Bandit", human, magician, true, 5);
        dd.cast(p,a);
        assertEquals(275, p.getRemainingMana());
    }

    @Test
    void castDealsCorrectDamage(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25);
        Player p = new Player("Player1", human, magician, true);
        Adversary a = new Adversary("Bandit", human, magician, true, 1);
        dd.cast(p,a);

        assertEquals(95, a.getRemainingHealth());
    }
}