package Map;

import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private ArrayList<ArrayList<MapTile> > tiles = new ArrayList<ArrayList<MapTile>>();
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

    public void setTile(MapTile tile) {
        int xPos = tile.getXPos();
        //int yPos = tile.getYPos();
        tiles.get(xPos).add(tile);

    }

}
