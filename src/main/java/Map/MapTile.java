package Map;

public class MapTile {
    private Terrain terrain;
    private Position position;
    private Position[] neighbors = new Position[4];

    public enum Terrain {
        GRASS, WATER, LAVA
    }

    public MapTile(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setNeighbors(Position left, Position right, Position up, Position down) {
        neighbors[0] = left;
        neighbors[1] = right;
        neighbors[2] = up;
        neighbors[3] = down;
    }

    public Position[] getNeighbors() {
        Position[] copyOfNeighbors = neighbors;
        return copyOfNeighbors;
    }
}
