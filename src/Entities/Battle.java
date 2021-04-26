package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Scanner;

public class Battle {
    private Engimon engPlayer;
    private Engimon engEnemy;
    private Map map;
    private Player player;
    private boolean battlestate;

    public Battle(Engimon engPlayer, Engimon engEnemy, Map map, Player player, boolean battlestate) {
        this.engEnemy = engEnemy;
        this.engPlayer = engPlayer;
        this.map = map;
        this.player = player;
        this.battlestate = battlestate;
    }

    public void doBattle(Map map, Player player, WildEngimon listWildEngimon) throws Exception {
        if (this.getWinner() == this.engPlayer) {
            updateDeadEngimonTile(map, engEnemy.getCurrentPosition());
            this.playerWins(player, listWildEngimon);
        } else if (this.getWinner() == this.engEnemy) {
            updateDeadEngimonTile(map, engPlayer.getCurrentPosition());
            this.enemyWins(map, player, listWildEngimon);
        }
    }

    public void displatBattleInfo(Player player, WildEngimon listWildEngimon) throws Exception {
        int dimana = listWildEngimon.getIdOfEngimon(this.engEnemy);
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

    public void playerWins(Player player, WildEngimon engEnemy) throws Exception {
        player.addEngimon(this.engEnemy);
        engEnemy.deleteNthWildEngimon(engEnemy.getIdOfEngimon(this.engEnemy));
        player.getActiveEngimon();
        player.getActiveEngimon().addExp(1000);

        //add skill
    }

    public void enemyWins(Map map, Player player, WildEngimon engEnemy) {
        player.removeEngimon(player.getActiveEngimonId());

        if (player.getOwnedEngimonSize() > 0) {
            player.showOwnedEngimons();
            System.out.println("Pilih ID engimon baru yang akan dipakai!");
            Scanner inputID = new Scanner(System.in);
            int idEngimonBerikut = inputID.nextInt();
            player.switchActiveEngimon(idEngimonBerikut);

        } else {
            System.out.println("You're dead, man!");
        }
    }

    public boolean getbattlestate(){
        return this.battlestate;
    }

    public void setBattle(boolean b){
        this.battlestate = b;
    }

    /*wprivate void kepemilikanEngimon(Engimon engimon, Player player) {
        player.addEngimon(engimon);
    }

    private void killWildEngimon(WildEngimon listWildEgimon, Engimon kalah) {
        listWildEgimon.deleteNthWildEngimon(listWildEgimon.posisiWildEngimonDiList(kalah) + 1);
    }*/

    private Engimon getWinner(){
        try {
            if (engEnemy.getPower(engPlayer) > engPlayer.getPower(engEnemy)) {
                return engEnemy;
            } else {
                return engPlayer;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return engEnemy;
        }
    }

    //private Engimon getLoser() {
    //}

    private void updateDeadEngimonTile(Map map, Tile posisi) {
        map.setTileOcc(posisi.getOrdinat(), posisi.getAbsis(), ' ');
    }

    public void draw_Battle(Graphics2D g, int w, int h){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(102,51,0));
        g2.fillRect((map.getNumberOfColumn()*map.getTilesize())-75,0,w,h);

        g.setColor(new Color(255,217,179));
        g.setFont(new Font("MicrosoftYaHei",Font.BOLD,20));
        g.drawString("Battle",map.getNumberOfColumn()*map.getTilesize()+30,30);

        g.setColor(new Color(255,217,179));
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));

        g.drawString("Battle between  :",map.getNumberOfColumn()*map.getTilesize(),50);
        g.drawString("Your Engimon : ",map.getNumberOfColumn()*map.getTilesize()-10,70);
        g.drawString("* Name: " + this.player.getActiveEngimon().getEngimonName(),map.getNumberOfColumn()*map.getTilesize()-20,80);
        g.drawString("* Parent 1: " + this.player.getActiveEngimon().getEngimonParent().getparent1name() + " (Species : " + this.player.getActiveEngimon().getEngimonParent().getparent1species(),map.getNumberOfColumn()*map.getTilesize()-20,90);
        g.drawString("* Parent 2: " + this.player.getActiveEngimon().getEngimonParent().getparent2name() + " (Species : " + this.player.getActiveEngimon().getEngimonParent().getparent2species(),map.getNumberOfColumn()*map.getTilesize()-20,100);
        if (this.player.getActiveEngimon().getNumberOfElement() == 1){
            g.drawString("* Element: " + this.player.getActiveEngimon().getNthEngimonElement(1),map.getNumberOfColumn()*map.getTilesize()-20,110);
        }
        else{
            g.drawString("* Element: " + this.player.getActiveEngimon().getNthEngimonElement(1) + ", " + this.player.getActiveEngimon().getNthEngimonElement(2) ,map.getNumberOfColumn()*map.getTilesize()-20,110);
        }
        g.drawString("* Level: " + this.player.getActiveEngimon().getEngimonLevel(),map.getNumberOfColumn()*map.getTilesize()-20,120);
        g.drawString("* Experience: " + this.player.getActiveEngimon().getEngimonExp(),map.getNumberOfColumn()*map.getTilesize()-20,130);
        g.drawString("* Cumulative Exp: " + this.player.getActiveEngimon().getCumulativeExp(),map.getNumberOfColumn()*map.getTilesize()-20,140);
        g.drawString("* Life: " + this.player.getActiveEngimon().getEngimonLife(),map.getNumberOfColumn()*map.getTilesize()-20,150);
        try {
            g.drawString("* Power: " + this.player.getActiveEngimon().getPower(engEnemy),map.getNumberOfColumn()*map.getTilesize()-20,160);
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.drawString("* Skill: ", map.getNumberOfColumn()*map.getTilesize()-20,170);
        Skill[] c = this.player.getActiveEngimon().engimonSkill;
        int i;
        for(i=0; i <this.player.getActiveEngimon().getNumberOfSkill();i++){
            int l = i+1;
            g.drawString(l + ". "+ c[i].getName(),map.getNumberOfColumn()*map.getTilesize(),180+(10*i));
        }
        int jarak = 200+(10*i);
        g.drawString("Wild Engimon : ",map.getNumberOfColumn()*map.getTilesize()-10, jarak+10 );
        g.drawString("* Name: " + this.engEnemy.getEngimonName(),map.getNumberOfColumn()*map.getTilesize()-20,jarak+20);
        g.drawString("* Parent 1: " + this.engEnemy.getEngimonParent().getparent1name() + " (Species : " + this.engEnemy.getEngimonParent().getparent1species(),map.getNumberOfColumn()*map.getTilesize()-20,jarak+30);
        g.drawString("* Parent 2: " + this.engEnemy.getEngimonParent().getparent2name() + " (Species : " + this.engEnemy.getEngimonParent().getparent2species(),map.getNumberOfColumn()*map.getTilesize()-20,jarak+40);
        if (this.engEnemy.getNumberOfElement() == 1){
            g.drawString("* Element: " + engEnemy.getNthEngimonElement(1),map.getNumberOfColumn()*map.getTilesize()-20,jarak + 50);
        }
        else{
            g.drawString("* Element: " + engEnemy.getNthEngimonElement(1) + ", " + this.engEnemy.getNthEngimonElement(2) ,map.getNumberOfColumn()*map.getTilesize()-20,jarak + 50);
        }
        g.drawString("* Level: " + this.engEnemy.getEngimonLevel(),map.getNumberOfColumn()*map.getTilesize()-20,jarak+60);
        g.drawString("* Experience: " + this.engEnemy.getEngimonExp(),map.getNumberOfColumn()*map.getTilesize()-20,jarak+70);
        g.drawString("* Cumulative Exp: " + this.engEnemy.getCumulativeExp(),map.getNumberOfColumn()*map.getTilesize()-20,jarak+80);
        g.drawString("* Life: " + this.engEnemy.getEngimonLife(),map.getNumberOfColumn()*map.getTilesize()-20,jarak+90);
        try {
            g.drawString("* Power: " + this.engEnemy.getPower(this.player.getActiveEngimon()),map.getNumberOfColumn()*map.getTilesize()-20,jarak+100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.drawString("* Skill: ", map.getNumberOfColumn()*map.getTilesize()-20,jarak+110);
        Skill[] d = this.engEnemy.engimonSkill;
        int j;
        for(j=0; j <this.engEnemy.getNumberOfSkill();j++){
            int k = j+1;
            g.drawString(k +". "+d[j].getName(),map.getNumberOfColumn()*map.getTilesize(),jarak+120+(10*j));
        }
        int jarak2 = jarak+160+(10*j);
        g.setColor(new Color(255,217,179));
        g.setFont(new Font("MicrosoftYaHei",Font.BOLD,15));
        g.drawString("Winner : " + this.getWinner().getEngimonName(),map.getNumberOfColumn()*map.getTilesize(),jarak2);
    }
}