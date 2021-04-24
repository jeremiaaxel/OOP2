package Entities;

import java.util.Scanner;
import java.lang.*;
import java.util.Vector;

public class Breeding extends Exception{
    protected boolean successBreed;
    protected Skill[] skillGabungan;
    protected Skill[] sortedSkill;
    protected int numberOfSkill;

    public Breeding(Engimon e1, Engimon e2, Tile childPosition/*, Player player*/){
        this.successBreed = false;
        this.numberOfSkill = 0;
        makeChild(e1, e2, childPosition/*, player*/);
    }

    public void makeChild(Engimon e1, Engimon e2, Tile childPosition/*, Player player*/){
        try{
            if (isBreedPossible(e1, e2)){
                Parent parent = new Parent(e1.getEngimonName(), e1.getEngimonSpesies(), e2.getEngimonName(), e2.getEngimonSpesies());

                if ((maxAdvantage(e1, e2) == 1) || (maxAdvantage(e1, e2) == 2)){
                    this.successBreed = true;

                    Scanner keyboard = new Scanner(System.in);
                    System.out.println("Breeding berhasil!!!");
                    System.out.print("Silahkan beri nama untuk engimon baru mu : ");
                    String nama = keyboard.nextLine();

                    if (e1.getEngimonSpesies() == "Aggron"){
                        Engimon eChild = new Aggron(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e1.getEngimonSpesies() == "Ampharos"){
                        Engimon eChild = new Ampharos(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e1.getEngimonSpesies() == "Araquanid"){
                        Engimon eChild = new Araquanid(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e1.getEngimonSpesies() == "Blaziken"){
                        Engimon eChild = new Blaziken(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e1.getEngimonSpesies() == "Eiscue"){
                        Engimon eChild = new Eiscue(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e1.getEngimonSpesies() == "Loceam"){
                        Engimon eChild = new Loceam(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e1.getEngimonSpesies() == "Megalapras"){
                        Engimon eChild = new Megalapras(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e1.getEngimonSpesies() == "Meganium"){
                        Engimon eChild = new Meganium(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }

                    //this.skillGabungan[0] = eChild.getNthEngimonSkill(1);
                    this.numberOfSkill++;
                    //player.addEngimon(eChild);
                    //addSkillWithPrior(e1, e2, eChild);
                }
                else if (maxAdvantage(e1, e2) == 3){
                    this.successBreed = true;

                    Scanner keyboard = new Scanner(System.in);
                    System.out.println("Breeding berhasil!!!");
                    System.out.print("Silahkan beri nama untuk engimon baru mu : ");
                    String nama = keyboard.nextLine();

                    if (e2.getEngimonSpesies() == "Aggron"){
                        Engimon eChild = new Aggron(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e2.getEngimonSpesies() == "Ampharos"){
                        Engimon eChild = new Ampharos(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e2.getEngimonSpesies() == "Araquanid"){
                        Engimon eChild = new Araquanid(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e2.getEngimonSpesies() == "Blaziken"){
                        Engimon eChild = new Blaziken(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e2.getEngimonSpesies() == "Eiscue"){
                        Engimon eChild = new Eiscue(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e2.getEngimonSpesies() == "Loceam"){
                        Engimon eChild = new Loceam(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e2.getEngimonSpesies() == "Megalapras"){
                        Engimon eChild = new Megalapras(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    else if (e2.getEngimonSpesies() == "Meganium"){
                        Engimon eChild = new Meganium(nama, parent, childPosition);
                        this.numberOfSkill++;
                        addSkillWithPrior(e1, e2, eChild);
                        eChild.displayEngimonInfo();
                    }
                    
                    //this.skillGabungan[0] = eChild.getNthEngimonSkill(1);
                    
                    //player.addEngimon(eChild);
                    //addSkillWithPrior(e1, e2);
                }
                else if (maxAdvantage(e1, e2) == 4){
                    try{
                        this.successBreed = true;

                        Scanner keyboard = new Scanner(System.in);
                        System.out.println("Breeding berhasil!!!");
                        System.out.print("Silahkan beri nama untuk engimon baru mu : ");
                        String nama = keyboard.nextLine();
                        //Engimon eChild;

                        if (((e1.getNthEngimonElement(1) == "Fire") && (e2.getNthEngimonElement(1) == "Electric")) || ((e2.getNthEngimonElement(1) == "Fire") && (e1.getNthEngimonElement(1) == "Electric"))){
                            Engimon eChild = new Loceam(nama, parent, childPosition);
                            this.numberOfSkill++;
                            addSkillWithPrior(e1, e2, eChild);
                            //player.addEngimon(eChild);
                            //addSkillWithPrior(e1, e2);
                            eChild.displayEngimonInfo();
                        }
                        else if (((e1.getNthEngimonElement(1) == "Water") && (e2.getNthEngimonElement(1) == "Ice")) || ((e2.getNthEngimonElement(1) == "Water") && (e1.getNthEngimonElement(1) == "Ice"))){
                            Engimon eChild = new Megalapras(nama, parent, childPosition);
                            this.numberOfSkill++;
                            addSkillWithPrior(e1, e2, eChild);
                            //player.addEngimon(eChild);
                            //addSkillWithPrior(e1, e2);
                            eChild.displayEngimonInfo();
                        }
                        else if (((e1.getNthEngimonElement(1) == "Water") && (e2.getNthEngimonElement(1) == "Ground")) || ((e2.getNthEngimonElement(1) == "Water") && (e1.getNthEngimonElement(1) == "Ground"))){
                            Engimon eChild = new Meganium(nama, parent, childPosition);
                            this.numberOfSkill++;
                            addSkillWithPrior(e1, e2, eChild);
                            //player.addEngimon(eChild);
                            //addSkillWithPrior(e1, e2);
                            eChild.displayEngimonInfo();
                        }
                        else{
                            throw new Exception("Gagal Breeding");
                        }
                    } catch (Exception err1){
                        System.out.println(err1.getMessage());
                    }
                }
            }
            else{
                throw new Exception("Tidak bisa breeding!!! Level tidak mencukupi!");
            }
        } catch (Exception err2){
            System.out.println(err2.getMessage());
        }
    }

    

    public boolean isBreedPossible(Engimon e1, Engimon e2){
        if ((e1.getEngimonLevel() >= 4) && (e2.getEngimonLevel() >= 4)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean getSuccessBreed(){
        return this.successBreed;
    }

    public int maxAdvantage(Engimon e1, Engimon e2) throws Exception{
        int jenis = 0;

        if (e1.getAdvantage(e2) > e2.getAdvantage(e1)){
            jenis = 2; // Elemen berbeda, dengan Advantage e1 > e2
        }
        else if (e1.getAdvantage(e2) < e2.getAdvantage(e1)){
            jenis = 3; // Elemen berbeda, dengan Advantage e1 < e2
        }
        else if (e1.getAdvantage(e2) == e2.getAdvantage(e1)){
            if (e1.getNthEngimonElement(1) == e2.getNthEngimonElement(1)){
                jenis = 1; // Elemen sama, pastinya Advantage e1 = e2
            }
            else{
                jenis = 4; // Elemen berbeda, dengan Advantage e1 = e2
            }
        }
        return jenis;
    }

    public void addSkillWithPrior(Engimon e1, Engimon e2, Engimon eChild){
        int A = e1.getNumberOfSkill();
        int B = e2.getNumberOfSkill();

        for (int i=0; i < A; i++){
            this.skillGabungan[i] = e1.getNthEngimonSkill(i+1);
        }
        for (int i=0; i < B; i++){
            this.skillGabungan[i+A] = e2.getNthEngimonSkill(i+1);
        }

        int tempNumberOfSkill = A+B;
        this.skillGabungan = new Skill[A+B];
        this.sortedSkill = new Skill[A+B];
        sortSkill(tempNumberOfSkill);

        tempNumberOfSkill = hitungBanyakSkill(this.skillGabungan);

        if (tempNumberOfSkill > 3){
            for (int i=0; i < 3; i++){
                try{
                    eChild.addSkill(this.skillGabungan[i]);
                    this.numberOfSkill++;
                } catch (Exception err){
                    System.out.println(err.getMessage());
                }
            }
        }
        else{
            for (int i=0; i < tempNumberOfSkill; i++){
                try{
                    eChild.addSkill(this.skillGabungan[i]);
                    this.numberOfSkill++;
                } catch (Exception err){
                    System.out.println(err.getMessage());
                }
            }
        }

        //delSkillAfterMerge
        boolean sama = false;
        for (int i=1; i < eChild.getNumberOfSkill(); i++){
            if (eChild.getNthEngimonSkill(1).getName() == eChild.getNthEngimonSkill(i+1).getName()){
                sama = true;
                break;
            }
        }
        
        if (sama){
            try{
                eChild.delSkill(0);
            } catch (Exception err){
                System.out.println(err.getMessage());
            }
            this.numberOfSkill--;
            if (tempNumberOfSkill > 3){
                try{
                    eChild.addSkill(this.skillGabungan[3]);
                } catch (Exception err){
                    System.out.println(err.getMessage());
                }
            }
        }

        
    }

    public void sortSkill(int temp){
        int tempSkill = temp;
        //Skill[] sortedSkill = new Skill[temp];

        for (int i=0; i < temp; i++){
            int max = 0;
            for (int j=0; j < temp; j++){
                if (this.skillGabungan[max].getMasteryLevel() < this.skillGabungan[j].getMasteryLevel()){
                    max = j;
                }
            }

            this.sortedSkill[i] = this.skillGabungan[max];
            for (int k=max; k < temp-1; k++){
                this.skillGabungan[k] = this.skillGabungan[k+1];
            }
            temp--;
            max = 0;
        }

        temp = tempSkill;

        for (int i=0; i < temp; i++){
            this.skillGabungan[i] = this.sortedSkill[i];
        }

        //delete skill yang double
        for (int i=0; i < temp; i++){
            for (int j=i+1; j < temp; j++){
                if (this.skillGabungan[i].getName() == this.skillGabungan[j].getName()){
                    if (this.skillGabungan[i].getMasteryLevel() == this.skillGabungan[j].getMasteryLevel()){
                        if (this.skillGabungan[i].getMasteryLevel() < 3){
                            this.skillGabungan[i].setMasteryLevel(this.skillGabungan[i].getMasteryLevel()+1);
                            for (int k=j; k < temp-1; k++){
                                this.skillGabungan[k] = this.skillGabungan[k+1];
                            }
                            temp--;
                        }
                    }
                    else{
                        int lvl = 0;
                        if (this.skillGabungan[i].getMasteryLevel() > this.skillGabungan[j].getMasteryLevel()){
                            lvl = this.skillGabungan[i].getMasteryLevel();
                        }
                        else{
                            lvl = this.skillGabungan[j].getMasteryLevel();
                        }
                        
                        this.skillGabungan[i].setMasteryLevel(lvl);
                        for (int k=j; k < temp-1; k++){
                            this.skillGabungan[k] = this.skillGabungan[k+1];
                        }
                        temp--;
                    }
                    break;
                }
            }
        }
    }

    public int hitungBanyakSkill(Skill[] s){
        int banyak = 0;
        for (int i=0; i < s.length; i++){
            banyak++;
        }
        return banyak;
    }

    public static void main(String[] args) throws Exception{
        Parent parent1 = new Parent("A1", "Aggron", "A2", "Aggron");
        Parent parent2 = new Parent("B1", "Ampharos", "B2", "Ampharos");
        Parent parent3 = new Parent("C1", "Araquanid", "C2", "Araquanid");
        Parent parent4 = new Parent("D1", "Blaziken", "D2", "Blaziken");
        Parent parent5 = new Parent("E1", "Eiscue", "E2", "Eiscue");
        Parent parent6 = new Parent("F1", "Loceam", "F2", "Loceam");
        Parent parent7 = new Parent("G1", "Megalapras", "G2", "Megalapras");
        Parent parent8 = new Parent("H1", "Meganium", "H2", "Meganium");
        Tile position = new Tile();
        Engimon e1 = new Aggron("Satu", parent1, position);
        Engimon e2 = new Ampharos("Dua", parent2, position);
        Engimon e3 = new Araquanid("Tiga", parent3, position);
        Engimon e4 = new Blaziken("Empat", parent4, position);
        Engimon e5 = new Eiscue("Lima", parent5, position);
        Engimon e6 = new Loceam("Enam", parent6, position);
        Engimon e7 = new Megalapras("Tujuh", parent7, position);
        Engimon e8 = new Meganium("Delapan", parent8, position);
        Engimon e9 = new Aggron("Sembilan", parent1, position);
        
        e1.setLevel(5);
        e2.setLevel(5);
        e3.setLevel(5);
        e4.setLevel(5);
        e5.setLevel(5);
        e6.setLevel(5);
        e7.setLevel(5);
        e8.setLevel(5);
        e9.setLevel(5);
        Breeding b1 = new Breeding(e4, e2, position);
        Breeding b2 = new Breeding(e1, e2, position);
        Breeding b3 = new Breeding(e1, e9, position);
        Breeding b4 = new Breeding(e6, e1, position);
    }
}
