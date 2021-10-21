package Magic;

import GameCharacters.Player;
import Jobs.Magician;
import Races.Human;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuffSpellTest {

    Human human = new Human();
    Magician magician = new Magician();

    @Test
    void buffSpellConstructor(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);

        assertEquals("StrengthBuff", bs.getName());
        assertEquals(10, bs.getManaCost());
        assertEquals(Element.PHYSICAL, bs.getElement());
        assertEquals(5, bs.getSpellStrength());
    }

    @Test
    void physicalBuffSpellIncreasesStrength(){
        BuffSpell bs = new BuffSpell("StrengthBuff", 10, Element.PHYSICAL, 5);
        Player p = new Player("Player1", human, magician, true);
        bs.in;

        assertEquals(30, p.getStrength());
    }
}