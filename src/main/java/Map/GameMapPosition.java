package Map;

public class GameMapPosition {
    private int xPos;
    private int yPos;
    private Terrain terrain;
    private GameMapPosition[] neighbors = new GameMapPosition[4];

    public GameMapPosition(int xPos, int yPos) {
        if (xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Coordinates must be > 0");
        }
        this.xPos = xPos;
        this.yPos = yPos;
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

    public void setNeighbors(GameMapPosition west, GameMapPosition east, GameMapPosition north, GameMapPosition south) {
        neighbors[0] = west;
        neighbors[1] = east;
        neighbors[2] = north;
        neighbors[3] = south;
    }

    public GameMapPosition getWestNeighbor() {
        return neighbors[0];
    }

    public GameMapPosition getEastNeighbor() {
        return neighbors[1];
    }

    public GameMapPosition getNorthNeighbor() {
        return neighbors[2];
    }

    public GameMapPosition getSouthNeighbor() {
        return neighbors[3];
    }

    public GameMapPosition[] getNeighbors() {
        GameMapPosition[] copyOfNeighbors = neighbors;
        return copyOfNeighbors;
    }
}
