package Map;

import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private MapTile[][] mapTiles;
    //private ArrayList<ArrayList<MapTile>> tiles = new ArrayList<>();

    public Map(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.width = width;
        if (height <= 0) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.height = height;
        this.mapTiles = new MapTile[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

//    public ArrayList<ArrayList<MapTile>> getTiles() {
//        ArrayList<ArrayList<MapTile>> copyOfTiles = tiles;
//        return copyOfTiles;
//    }

    public MapTile[][] getMapTiles() {
        MapTile[][] copyOfTiles = mapTiles;
        return copyOfTiles;
    }

    public void put(MapTile tile, int xPos, int yPos) {
        if (xPos > width || yPos > height || xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Coordinates must match the size");
        }
        mapTiles[xPos][yPos] = tile;
        //tiles.get(xPos).add(yPos, tile);

    }

}
