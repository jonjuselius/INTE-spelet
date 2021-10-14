package Map;

public class MapTile {
    private int xPos;
    private int yPos;
    private Terrain terrain;
    private MapTile[] neighbors = new MapTile[4];

    public enum Terrain {
        GRASS, WATER, LAVA
    }

    public MapTile(int xPos, int yPos, Terrain terrain) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.terrain = terrain;
    }

    public void setNeighbors(MapTile left, MapTile right, MapTile up, MapTile down) {
        neighbors[0] = left;
        neighbors[1] = right;
        neighbors[2] = up;
        neighbors[3] = down;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public Terrain getTerrain() {
        return terrain;
    }
}
