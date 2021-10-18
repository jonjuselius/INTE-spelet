package GameCharacters;


public class Human extends Race {

    public Human() {
        setIfCanFly(false);
        setIfCanSwim(true);
        setIfCanWalkThroughTerrain(true);

    }

}