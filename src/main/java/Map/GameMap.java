package Map;

public class GameMap {
    private int width;
    private int height;
    private GameMapPosition[][] mapTiles;

    public GameMap(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.width = width;
        if (height <= 0) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.height = height;
        this.mapTiles = new GameMapPosition[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameMapPosition[][] getMapTiles() {
        GameMapPosition[][] copyOfTiles = mapTiles;
        return copyOfTiles;
    }

    public void putTileOnMap(GameMapPosition tile, int xPos, int yPos) {
        if (xPos > width || yPos > height || xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Coordinates must match the size");
        }
        mapTiles[xPos][yPos] = tile;
    }

    public GameMapPosition generateRandomPos(int xFakeRandom, int yFakeRandom) {
        /*Generates xPos and yPos inside of the interval for the Map's width and height.
        To make this method testable, an int called fakeRandom is used,
        instead of using e.g. random.nextInt(0, width - 1) for xPos. The same for yPos.
         */

        return new GameMapPosition(xFakeRandom, yFakeRandom);
    }

}
