package Map;

public class MapPosition {
    private Map map;
    private int xPos;
    private int yPos;
    private Terrain terrain;
    private MapPosition[] neighbors = new MapPosition[4];

    public MapPosition(int xPos, int yPos, Map map) {
        if (xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Coordinates must be > 0");
        }
        this.xPos = xPos;
        this.yPos = yPos;
        this.map = map;
        //The standard terrain is grass
        this.terrain = Terrain.GRASS;
    }


    public MapPosition(int xPos, int yPos, Terrain terrain, Map map) {
        if (xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Coordinates must be > 0");
        }
        this.xPos = xPos;
        this.yPos = yPos;
        this.terrain = terrain;
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public int getYPos() {
        return yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setNeighbors(MapPosition west, MapPosition east, MapPosition north, MapPosition south) {
        neighbors[0] = west;
        neighbors[1] = east;
        neighbors[2] = north;
        neighbors[3] = south;
    }

    public MapPosition[] getNeighbors() {
        MapPosition[] copyOfNeighbors = neighbors;
        return copyOfNeighbors;
    }
}
