public class Tile {
    private int absis;
    private int ordinat;

    public Boolean isSame(Tile other){
        return absis == other.absis && ordinat == other.ordinat;
    }
}
