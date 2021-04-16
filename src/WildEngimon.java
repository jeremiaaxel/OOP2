import java.util.List;
import java.util.Random;

public class WildEngimon{
    private List<Engimon> wildEngimonList;
    private int capacity;

    public WildEngimon(){
        capacity = 12;
    }

    public WildEngimon(int capacity){
        this.capacity = capacity;
    }
    public int getNumberOfWildEngimon(){

        return wildEngimonList.size();
    }

    public void addWildEngimon(Engimon other) throws Exception{
        if (getNumberOfWildEngimon() < capacity){
            wildEngimonList.add(other);
        }
    }
    public void deleteNthWildEngimon(int n) throws Exception{
        if (n < 0 || n >= getNumberOfWildEngimon()){
            throw new IndexOutOfBoundsException();
        }
        this.getNthEngimon(n).getCurrentPosition().setOccupier(' ');
        wildEngimonList.remove(n);
    }
    public void randomMove(Map map){
        boolean success = false;
        Random rand = new Random(1);
        int engimon_id = rand.nextInt(100) % this.getNumberOfWildEngimon(), i = 0, directionId;
        Tile[] surrTile = new Tile[4];
        map.getSurroundingTile(this.getNthEngimon(engimon_id).getCurrentPosition(),surrTile);
        while (i < 4 && !success){
            directionId = rand.nextInt(100) % 4;
            switch (directionId){
                case 0:
                    success = this.getNthEngimon(engimon_id).move(map, 'w', true);
                    break;
                case 1:
                    success = this.getNthEngimon(engimon_id).move(map, 'd', true);
                    break;
                case 2:
                    success = this.getNthEngimon(engimon_id).move(map, 's', true);
                    break;
                case 3:
                    success = this.getNthEngimon(engimon_id).move(map, 'a', true);
                    break;
                default:
                    System.out.println("random move default");
                    break;
            }
            i++;
        }
    }
    public Engimon getNthEngimon(int n){
        if (n < 0 || n >= getNumberOfWildEngimon()){
            throw new IndexOutOfBoundsException();
        }
        return wildEngimonList.get(n);
    }
    public void updateAllLevel(){
        for (Engimon eng : wildEngimonList){
            eng.levelUp();
        }
    }

    public int getIdOfEngimon(Engimon eng){
        return wildEngimonList.indexOf(eng);
    }

    public Engimon engimonOnTheTile(Tile posisyen){
        for (Engimon e : wildEngimonList){
            if (e.getCurrentPosition().isSame(posisyen)){
                return e;
            }
        }
        return null;
    }
}
