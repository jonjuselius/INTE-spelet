package Magic;

import GameCharacters.*;
import Jobs.Magician;
import Map.*;
import Races.*;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DamageDealingTest {

    public static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    public static final GameMap MAP = MAP_GENERATOR.generate(1);
    public static final GameMapPosition MAP_POSITION = MAP.generateRealRandomPos(new Random(), new Random());
    private Human human = new Human();
    private Magician magician = new Magician();

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
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, MAP_POSITION);
        dd.powerProgression(p);

        assertEquals(205, dd.getInitialDamage());
    }

    @Test
    void castThrowsExceptionWhenManaTooLow() {
        DamageDealingSpell dd = new DamageDealingSpell("Implode", 305, Element.FIRE, 100);
        Player p = new Player("Player1", human, magician, true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician, true, 5, MAP_POSITION);

        assertThrows(IllegalStateException.class, ()-> dd.cast(p,a));
    }

    @Test
    void castDecreasesCorrectMana() {
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician, true, 5, MAP_POSITION);
        dd.cast(p,a);
        assertEquals(275, p.getRemainingMana());
    }

    @Test
    void castDealsCorrectDamage(){
        DamageDealingSpell dd = new DamageDealingSpell("Stonefist", 25, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true, MAP_POSITION);
        Adversary a = new Adversary("Bandit", human, magician, true, 1, MAP_POSITION);
        dd.cast(p,a);

        assertEquals(95, a.getRemainingHealth());
    }
}