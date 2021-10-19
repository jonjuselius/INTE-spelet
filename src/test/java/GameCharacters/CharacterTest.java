package GameCharacters;

import Jobs.Magician;
import org.junit.jupiter.api.Test;
import Races.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    Human human = new Human();
    Magician magician = new Magician();

    @Test
    void takeDamageReducesCorrectHealth() {
        Player p = new Player("Player1", human, magician, true);
        p.takeDamage(100);

        assertEquals(200, p.getRemainingHealth());
    }

    @Test
    void takeDamageKills(){
        Player p = new Player("Player1", human, magician, true);
        p.takeDamage(300);

        assertFalse(p.isAlive());
    }

    @Test
    void useManaReducesCorrectAmount() {
        Player p = new Player("Player1", human, magician, true);
        p.useMana(100);

        assertEquals(100, p.getRemainingMana());
    }

    @Test
    void getHealedIncreasesHealth() {
        Player p = new Player("Player1", human, magician, true);
//        p.setRemainingHealth(100);
        p.getHealed(100);

        assertEquals(200, p.getRemainingHealth());
    }

    @Test
    void getHealedMoreThanMaxSetsHealthToMax(){
        Player p = new Player("Player1", human, magician, true);
//        p.setRemainingHealth(250);
        p.getHealed(100);

        assertEquals(300, p.getRemainingHealth());
    }
}