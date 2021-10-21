package Map;

public class Map {
    private int width;
    private int height;
    private MapPosition[][] mapTiles;

    public Map(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.width = width;
        if (height <= 0) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.height = height;
        this.mapTiles = new MapPosition[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MapPosition[][] getMapTiles() {
        MapPosition[][] copyOfTiles = mapTiles;
        return copyOfTiles;
    }

    public void put(MapPosition tile, int xPos, int yPos) {
        if (xPos > width || yPos > height || xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Coordinates must match the size");
        }
        mapTiles[xPos][yPos] = tile;
    }

    public MapPosition generateRandomPos(int xfakeRandom, int yFakeRandom) {
        /*Generates xPos and yPos inside of the interval for the Map's width and height.
        To make this method testable, an int called fakeRandom is used,
        instead of using e.g. random.nextInt(0, width - 1) for xPos. The same for yPos.
         */
        int xPos = xfakeRandom;
        int yPos = yFakeRandom;
        return new MapPosition(xPos, yPos);
    }

}
