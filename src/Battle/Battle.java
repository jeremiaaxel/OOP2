import java.util.Scanner;

public class Battle {

    private Engimon engEnemy;
    private Engimon engPlayer;

    public Battle(Engimon engEnemy, Engimon engPlayer){
        engEnemy = engEnemy;
        engPlayer = engPlayer;
    }

    public void doBattle(Map map, Player player, WildEngimon listWildEgimon){
        if(this.getWinner() == this.engPlayer){
            updateDeadEngimonTile(map, engEnemy.getCurrentPosition());
            this.playerWins(player, listWildEgimon);
        }
        else if (this.getWinner() == this.engEnemy){
            updateDeadEngimonTile(map, engPlayer.getCurrentPosition());
            this.enemyWins(map, player, listWildEgimon);
        }
    }

    public void displatBattleInfo (Player player, WildEngimon listWildEngimon){
        int dimana = listWildEgimon.posisiWildEngimonDiList(this.engEnemy);
        System.out.println("Battle between : ");
        System.out.println("Yout engimon : ");
        engPlayer.displayEngimonInfo();
        System.out.println("Power : " + engPlayer.getPower(engEnemy));
        System.out.println("Enemy : ");
        engEnemy.displayEngimonInfo();
        System.out.println("Power : " + engEnemy.getPower(engPlayer));
        Engimon yangmenang = getWinner();
        System.out.println("\r\n Winner : " + yangmenang.getEngimonName());
    }

    public void playerWins (Player player, WildEngimon engEnemy){
        player.addEngimon(this.engEnemy);

        engEnemy.deleteNthWildEngimon(engEnemy.posisiWildEngimonDiList(this.engEnemy));

        player.getActiveEngimon();
        //player.getActiveEngimon().addExp(1000);

        //add skill
    }

    public void enemyWins (Map map, Player player, WildEgimon engEnemy){
        player.removeEngimon(player.getActiveEngimon());

        if(player.showOwnedEngimons()){
            player.showOwnedEngimons();
            System.out.println("Pilih ID engimon baru yang akan dipakai!");
            Scanner inputID = new Scanner(System.in);
            int idEngimonBerikut = inputID.nextInt();
            player.setActiveEngimon(map, idEngimonBerikut);
            
        }else{
            System.out.println("You're dead, man!");
        }
    }

    private void kepemilikanEngimon (Engimon engimon, Player player){
        player.addEngimon(engimon);
    }

    private void killWildEngimon (wildEngimon listWildEgimon, Engimon kalah){
        listWildEgimon.deleteNthWildEngimon(listWildEgimon.posisiWildEngimonDiList(kalah)+1);
    }

    private Engimon getWinner(){
        if (engEnemy.getPower(engPlayer) > engPlayer.getPower(engEnemy)){
            return engEnemy;
        }
        else{
            return engPlayer;
        }
    }

    //private Engimon getLoser(){
    //}

    private void updateDeadEngimonTile(Map map, Tile posisi){
        map.setTileOcc(posisi.getOrdinat(), posisi.getabsis(), ' ');
    }
}

