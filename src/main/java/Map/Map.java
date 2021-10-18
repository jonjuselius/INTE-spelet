package Map;

import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private ArrayList<ArrayList<MapTile> > tiles = new ArrayList<>();
    private int size;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.size = width * height;
    }

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    protected ArrayList<ArrayList<MapTile>> getTiles() {
        ArrayList<ArrayList<MapTile>> copyOfTiles = tiles;
        return copyOfTiles;
    }

    public void put(MapTile tile, int xPos, int yPos) {
        tiles.get(xPos).add(yPos, tile);

    }

}
