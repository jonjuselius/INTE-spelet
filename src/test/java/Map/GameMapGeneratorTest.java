package Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.*;

class GameMapGeneratorTest {

    private static int FAKE_RANDOM = 1;
    private static final int DEFAULT_MAPWIDTH = 2;
    private static final int DEFAULT_MAPHEIGHT = 4;
    private static GameMapGenerator mapGenerator;
    private static GameMap map;
    private static GameMapPosition mapPosition;
//    private static GameMap mockMap;

    @BeforeAll
    static void setup() {
        mapGenerator = new GameMapGenerator(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        map = mapGenerator.generate(FAKE_RANDOM);
        mapPosition = map.getMapTiles()[1][1];

//        mockMap = mock(GameMap.class);
//        when(mockMap.getWidth()).thenReturn(4);
//        when(mockMap.getHeight()).thenReturn(4);

    }

    @Test
    void constructorSetsMapWidth() {
        GameMap map = new GameMap(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        GameMapGenerator mg = new GameMapGenerator(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        assertEquals(map.getWidth(), mg.generate(FAKE_RANDOM).getWidth());
    }

    @Test
    void constructorSetsMapHeight() {
        GameMap map = new GameMap(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        GameMapGenerator mg = new GameMapGenerator(DEFAULT_MAPWIDTH, DEFAULT_MAPHEIGHT);
        assertEquals(map.getHeight(), mg.generate(FAKE_RANDOM).getHeight());
    }

    @Test
    void firstTileOfFakeRandomizedMapIsSetToGrass() {
        assertThat(map.getMapTiles()[0][0].getTerrain(), is(Terrain.GRASS));
    }

    @Test
    void fakeRandomizedMapContainsOnlyGrass() {
        GameMapGenerator mg = new GameMapGenerator(2, 2);
        GameMap map = mg.generate(1);
        assertThat(map.getMapTiles()[0][0].getTerrain(), is(Terrain.GRASS));
        assertThat(map.getMapTiles()[1][0].getTerrain(), is(Terrain.GRASS));
        assertThat(map.getMapTiles()[0][1].getTerrain(), is(Terrain.GRASS));
        assertThat(map.getMapTiles()[1][1].getTerrain(), is(Terrain.GRASS));
    }

    @Test
    void probabilityIncreasesForGrassIfTileHasNoNeighbors() {
        GameMapGenerator mg = new GameMapGenerator(1, 1);
        GameMap map = mg.generate(FAKE_RANDOM);
        HashMap<Terrain, Integer> probabilityOfTerrains = mg.getProbabilityOfTerrains();
        assertThat(probabilityOfTerrains.get(Terrain.GRASS), greaterThan(probabilityOfTerrains.get(Terrain.LAVA)));
        assertThat(probabilityOfTerrains.get(Terrain.GRASS), greaterThan(probabilityOfTerrains.get(Terrain.WATER)));
    }

    @Test
    void terrainOfNeighBorsIncreasesProbabilityForCertainTerrainOnTile() {
        GameMapGenerator mg = new GameMapGenerator(2,1);
        GameMap map = mg.generate(1);
        HashMap<Terrain, Integer> probabilityOfTerrains = mg.getProbabilityOfTerrains();
        //Since the first tile was grass, the probability of grass should be the highest
        assertThat(probabilityOfTerrains.get(Terrain.GRASS), greaterThan(probabilityOfTerrains.get(Terrain.LAVA)));
        assertThat(probabilityOfTerrains.get(Terrain.GRASS), greaterThan(probabilityOfTerrains.get(Terrain.WATER)));
    }

 /*   @Test
    void realRandomizedMapDoesNotContainOnlyGrass() {
        *//*
        Tanken med detta test var att mocka metoden generate() i klassen GameMapGenerator, och på så sätt kunna
        generera en karta med hjälp av en riktig Random. Problemet blev att generering av kartan innebär inte bara
        generate-metoden, utan även flera privata hjälpmetoder där parametern från generate(), som är en fake-Random,
        passas vidare till dessa. Eftersom dessa är privata, blir de svåra att mocka från testklassen, men nedan kod
        är ett misslyckat försök att påbörja mockandet. Det andra problemet med att testa det som detta testfall gör,
        är att, med användning av en riktig Random kan vi aldrig veta om den genererade kartan kommer att innehålla
        olika terränger, eftersom detta alltid slumpas fram. Hade vi haft ett GUI, hade detta självklart varit enkelt
        att se, men nu kan vi inte förutsäga vilken terräng en viss position kommer att ha, utan bara titta på
        sannolikhet. Kartan kan lika gärna vara täckt i gräs, om det är det som slumpats fram. Jag försökte även skriva
        parametriska tester för detta, med olika Randoms, men kom inte längre på grund av detta, och för att jag la
        mycket fokus på att istället testa att spelare kan röra sig på kartan på olika sätt.
         *//*

        Random random = new Random();
        GameMapGenerator mapGenerator = new GameMapGenerator(2, 2);
        GameMap map = mapGenerator.generate(random.nextInt(1, 100));
        GameMapGenerator generator = new GameMapGenerator(4, 4){
            @Override
            public GameMap generate(Random random){
                for (int x = 0; x < mockMap.getWidth(); x++) {
                    for (int y = 0; y < mockMap.getHeight(); y++) {
                        GameMapPosition tile = new GameMapPosition(x, y);
                        mockMap.putTileOnMap(tile, x, y);

                        findNeighbors(tile, mockMap);

                        //Check the terrain of the south and west neighbor & increase the probability of this tile getting the same terrain
                        Terrain terrain = getTerrainOfNeighbors(tile);
                        tile.setTerrain(terrainGenerator(terrain, random));
                    }
                }
                return mockMap;
            }
        };
        //Detta går givetvis inte, eftersom vi aldrig kan veta var gräs kommer att hamna och inte hamna
        assertThat(map.getMapTiles()[0][1].getTerrain(), not(Terrain.GRASS));
    }*/

    @Test
    void correctCoordinatesSetForWestNeighbor() {
        GameMapPosition westNeighbor = mapPosition.getNeighbors()[0];
        assertEquals(westNeighbor.getXPos(), map.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getXPos());
        assertEquals(westNeighbor.getYPos(), map.getMapTiles()[westNeighbor.getXPos()][westNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesSetForEastNeighBor() {
        GameMapPosition mapPosition = map.getMapTiles()[0][0];
        GameMapPosition eastNeighbor = mapPosition.getNeighbors()[1];
        assertEquals(eastNeighbor.getXPos(), map.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getXPos());
        assertEquals(eastNeighbor.getYPos(), map.getMapTiles()[eastNeighbor.getXPos()][eastNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesSetForNorthNeighbor() {
        GameMapPosition northNeighbor = mapPosition.getNeighbors()[2];
        assertEquals(northNeighbor.getXPos(), map.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getXPos());
        assertEquals(northNeighbor.getYPos(), map.getMapTiles()[northNeighbor.getXPos()][northNeighbor.getYPos()].getYPos());
    }

    @Test
    void correctCoordinatesSetForSouthNeighbor() {
        GameMapPosition southNeighbor = mapPosition.getNeighbors()[3];
        assertEquals(southNeighbor.getXPos(), map.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getXPos());
        assertEquals(southNeighbor.getYPos(), map.getMapTiles()[southNeighbor.getXPos()][southNeighbor.getYPos()].getYPos());
    }
}