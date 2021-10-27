package Magic;

import GameCharacters.Adversary;
import GameCharacters.Player;
import Jobs.Magician;
import Map.*;
import Races.Human;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DebuffSpellTest {

    private static final GameMapGenerator MAP_GENERATOR = new GameMapGenerator(4, 4);
    private static final GameMap MAP = MAP_GENERATOR.generate(1);
    private static final GameMapPosition MAP_POSITION = MAP.generateRandomPos(new Random(), new Random());
    private static Player player;
    private static Adversary target;
    private static Human human;
    private static Magician magician;

    @BeforeAll
    public static void setup(){
        human = new Human();
        magician = new Magician();

        player = new Player("Player1", human, magician, true, MAP_POSITION);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION);
    }

    @Test
    public void debuffSpellConstructor(){
        DebuffSpell ds = new DebuffSpell("StrengthDebuff", 10, Element.PHYSICAL, 5);

        assertEquals("StrengthDebuff", ds.getName());
        assertEquals(10, ds.getManaCost());
        assertEquals(Element.PHYSICAL, ds.getElement());
        assertEquals(5, ds.getSpellStrength());
    }

    @Test
    public void exceptionThrownWhenNotEnoughManaForCast(){
        DebuffSpell ds = new DebuffSpell("StrengthDebuff", 305, Element.PHYSICAL, 5);

        assertThrows(IllegalStateException.class, ()-> ds.cast(player, target, 1));
    }

    @Test
    void castDepletesCorrectAmountOfMana(){
        DebuffSpell ds = new DebuffSpell("StrengthDebuff", 10, Element.PHYSICAL, 5);
        ds.cast(player, target, 1);
        assertEquals(290, player.getRemainingMana());
    }

    @Test
    void debuffSpellLastsCorrectDuration(){
        List<Integer> strengthStats = new ArrayList<>();
        DebuffSpell ds = new DebuffSpell("StrengthDebuff", 10, Element.PHYSICAL, 5);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION){
            @Override
            public void setStrength(int strength){
                super.setStrength(strength);
                strengthStats.add((int) System.nanoTime());
            }
        };
        ds.cast(player,target,10);
        long duration = (strengthStats.get(2) - strengthStats.get(0)) / 1000000;

        assertEquals(3, strengthStats.size());
        assertTrue(duration > 8 && duration < 30);

    }

    @Test
    public void physicalDebuffDecreasesAndIncreasesStrength(){
        List<Integer> strengthStats = new ArrayList<>();
        DebuffSpell ds = new DebuffSpell("StrengthDebuff", 10, Element.PHYSICAL, 5);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION){
            @Override
            public void setStrength(int strength){
                super.setStrength(strength);
                strengthStats.add(strength);
            }
        };
        ds.cast(player, target, 1);

        assertEquals(3, strengthStats.size());
        assertEquals(20, strengthStats.get(0));
        assertEquals(17, strengthStats.get(1));
        assertEquals(20, strengthStats.get(2));
    }

    @Test
    public void fireDebuffDecreasesAndIncreasesIntelligence(){
        List<Integer> intelligenceStats = new ArrayList<>();
        DebuffSpell ds = new DebuffSpell("IntelligenceDebuff", 10, Element.FIRE, 5);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION){
            @Override
            public void setIntelligence(int intelligence){
                super.setIntelligence(intelligence);
                intelligenceStats.add(intelligence);
            }
        };
        ds.cast(player, target, 1);

        assertEquals(3, intelligenceStats.size());
        assertEquals(20, intelligenceStats.get(0));
        assertEquals(17, intelligenceStats.get(1));
        assertEquals(20, intelligenceStats.get(2));
    }

    @Test
    public void lightningDebuffDecreasesAndIncreasesMagicSkill(){
        List<Integer> magicSkillStats = new ArrayList<>();
        DebuffSpell ds = new DebuffSpell("MagicDebuff", 10, Element.LIGHTNING, 5);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION){
            @Override
            public void setMagicSkill(int magicSkill){
                super.setMagicSkill(magicSkill);
                magicSkillStats.add(magicSkill);
            }
        };
        ds.cast(player, target, 1);

        assertEquals(3, magicSkillStats.size());
        assertEquals(10, magicSkillStats.get(0));
        assertEquals(9, magicSkillStats.get(1));
        assertEquals(10, magicSkillStats.get(2));
    }

    @Test
    public void waterDebuffDecreasesAndIncreasesHealingSkill(){
        List<Integer> healingSkillStats = new ArrayList<>();
        DebuffSpell ds = new DebuffSpell("HealingDebuff", 10, Element.WATER, 5);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION){
            @Override
            public void setHealingSkill(int healingSkill){
                super.setHealingSkill(healingSkill);
                healingSkillStats.add(healingSkill);
            }
        };
        ds.cast(player, target, 1);

        assertEquals(3, healingSkillStats.size());
        assertEquals(7, healingSkillStats.get(0));
        assertEquals(6, healingSkillStats.get(1));
        assertEquals(7, healingSkillStats.get(2));
    }

    @Test
    public void windDebuffDecreasesAndIncreasesMaxMana(){
        List<Integer> maxManaSkillStats = new ArrayList<>();
        DebuffSpell ds = new DebuffSpell("ManaDebuff", 10, Element.WIND, 5);
        target = new Adversary("Bandit", human, magician,true, 1, MAP_POSITION){
            @Override
            public void setMaxMana(int maxMana){
                super.setMaxMana(maxMana);
                maxManaSkillStats.add(maxMana);
            }
        };
        ds.cast(player, target, 1);

        assertEquals(3, maxManaSkillStats.size());
        assertEquals(300, maxManaSkillStats.get(0));
        assertEquals(255, maxManaSkillStats.get(1));
        assertEquals(300, maxManaSkillStats.get(2));
    }


}