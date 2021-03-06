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
        Tanken med detta test var att mocka metoden generate() i klassen GameMapGenerator, och p?? s?? s??tt kunna
        generera en karta med hj??lp av en riktig Random. Problemet blev att generering av kartan inneb??r inte bara
        generate-metoden, utan ??ven flera privata hj??lpmetoder d??r parametern fr??n generate(), som ??r en fake-Random,
        passas vidare till dessa. Eftersom dessa ??r privata, blir de sv??ra att mocka fr??n testklassen, men nedan kod
        ??r ett misslyckat f??rs??k att p??b??rja mockandet. Det andra problemet med att testa det som detta testfall g??r,
        ??r att, med anv??ndning av en riktig Random kan vi aldrig veta om den genererade kartan kommer att inneh??lla
        olika terr??nger, eftersom detta alltid slumpas fram. Hade vi haft ett GUI, hade detta sj??lvklart varit enkelt
        att se, men nu kan vi inte f??ruts??ga vilken terr??ng en viss position kommer att ha, utan bara titta p??
        sannolikhet. Kartan kan lika g??rna vara t??ckt i gr??s, om det ??r det som slumpats fram. Jag f??rs??kte ??ven skriva
        parametriska tester f??r detta, med olika Randoms, men kom inte l??ngre p?? grund av detta, och f??r att jag la
        mycket fokus p?? att ist??llet testa att spelare kan r??ra sig p?? kartan p?? olika s??tt.
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
        //Detta g??r givetvis inte, eftersom vi aldrig kan veta var gr??s kommer att hamna och inte hamna
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