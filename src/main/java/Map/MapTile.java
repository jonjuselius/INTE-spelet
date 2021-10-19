package Map;

public class MapTile {
    private Terrain terrain;
    private Position position;
    private Position[] neighbors = new Position[4];

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

    public void setNeighbors(Position west, Position east, Position north, Position south) {
        neighbors[0] = west;
        neighbors[1] = east;
        neighbors[2] = north;
        neighbors[3] = south;
    }

    public Position[] getNeighbors() {
        Position[] copyOfNeighbors = neighbors;
        return copyOfNeighbors;
    }
}
