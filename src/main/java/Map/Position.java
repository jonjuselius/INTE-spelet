package Map;

public class Position {

    private int xPos;
    private int yPos;

    public Position(int xPos, int yPos) {
        if (xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Coordinates must be > 0");
        }
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
