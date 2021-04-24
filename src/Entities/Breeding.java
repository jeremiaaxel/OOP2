package Entities;

import java.util.Scanner;

public class Breeding {
    protected boolean successBreed;
    protected Skill[] skillGabungan;
    protected int numberOfSkill;

    public void main(String[] args){
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

        Breeding b1 = new Breeding(e1, e2, position);

    }

    public Breeding(Engimon e1, Engimon e2, Tile childPosition/*, Player player*/){
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
                    Engimon eChild;

                    if (e1.getEngimonSpesies() == "Aggron"){
                        eChild = new Aggron(nama, parent, childPosition);
                    }
                    else if (e1.getEngimonSpesies() == "Ampharos"){
                        eChild = new Ampharos(nama, parent, childPosition);
                    }
                    else if (e1.getEngimonSpesies() == "Araquanid"){
                        eChild = new Araquanid(nama, parent, childPosition);
                    }
                    else if (e1.getEngimonSpesies() == "Blaziken"){
                        eChild = new Blaziken(nama, parent, childPosition);
                    }
                    else if (e1.getEngimonSpesies() == "Eiscue"){
                        eChild = new Eiscue(nama, parent, childPosition);
                    }
                    else if (e1.getEngimonSpesies() == "Loceam"){
                        eChild = new Loceam(nama, parent, childPosition);
                    }
                    else if (e1.getEngimonSpesies() == "Megalapras"){
                        eChild = new Megalapras(nama, parent, childPosition);
                    }
                    else if (e1.getEngimonSpesies() == "Meganium"){
                        eChild = new Meganium(nama, parent, childPosition);
                    }

                    //this.skillGabungan[0] = eChild.getNthEngimonSkill(0);
                    //player.addEngimon(eChild);
                    //addSkillWithPrior(e1, e2);
                }
                else if (maxAdvantage(e1, e2) == 3){
                    this.successBreed = true;

                    Scanner keyboard = new Scanner(System.in);
                    System.out.println("Breeding berhasil!!!");
                    System.out.print("Silahkan beri nama untuk engimon baru mu : ");
                    String nama = keyboard.nextLine();
                    Engimon eChild;

                    if (e2.getEngimonSpesies() == "Aggron"){
                        eChild = new Aggron(nama, parent, childPosition);
                    }
                    else if (e2.getEngimonSpesies() == "Ampharos"){
                        eChild = new Ampharos(nama, parent, childPosition);
                    }
                    else if (e2.getEngimonSpesies() == "Araquanid"){
                        eChild = new Araquanid(nama, parent, childPosition);
                    }
                    else if (e2.getEngimonSpesies() == "Blaziken"){
                        eChild = new Blaziken(nama, parent, childPosition);
                    }
                    else if (e2.getEngimonSpesies() == "Eiscue"){
                        eChild = new Eiscue(nama, parent, childPosition);
                    }
                    else if (e2.getEngimonSpesies() == "Loceam"){
                        eChild = new Loceam(nama, parent, childPosition);
                    }
                    else if (e2.getEngimonSpesies() == "Megalapras"){
                        eChild = new Megalapras(nama, parent, childPosition);
                    }
                    else if (e2.getEngimonSpesies() == "Meganium"){
                        eChild = new Meganium(nama, parent, childPosition);
                    }
                    
                    //this.skillGabungan[0] = eChild.getNthEngimonSkill(0);
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
                        Engimon eChild;

                        if (((e1.getNthEngimonElement(0) == "Fire") && (e2.getNthEngimonElement(0) == "Electric")) || ((e2.getNthEngimonElement(0) == "Fire") && (e1.getNthEngimonElement(0) == "Electric"))){
                            eChild = new Loceam(nama, parent, childPosition);
                            this.skillGabungan[0] = eChild.getNthEngimonSkill(0);
                            //player.addEngimon(eChild);
                            //addSkillWithPrior(e1, e2);
                        }
                        else if (((e1.getNthEngimonElement(0) == "Water") && (e2.getNthEngimonElement(0) == "Ice")) || ((e2.getNthEngimonElement(0) == "Water") && (e1.getNthEngimonElement(0) == "Ice"))){
                            eChild = new Megalapras(nama, parent, childPosition);
                            this.skillGabungan[0] = eChild.getNthEngimonSkill(0);
                            //player.addEngimon(eChild);
                            //addSkillWithPrior(e1, e2);
                        }
                        else if (((e1.getNthEngimonElement(0) == "Water") && (e2.getNthEngimonElement(0) == "Ground")) || ((e2.getNthEngimonElement(0) == "Water") && (e1.getNthEngimonElement(0) == "Ground"))){
                            eChild = new Meganium(nama, parent, childPosition);
                            this.skillGabungan[0] = eChild.getNthEngimonSkill(0);
                            //player.addEngimon(eChild);
                            //addSkillWithPrior(e1, e2);
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
            if (e1.getNthEngimonElement(0) == e2.getNthEngimonElement(0)){
                jenis = 1; // Elemen sama, pastinya Advantage e1 = e2
            }
            else{
                jenis = 4; // Elemen berbeda, dengan Advantage e1 = e2
            }
        }
        return jenis;
    }

    public void addSkillWithPrior(Engimon e1, Engimon e2){
        int A = e1.getNumberOfSkill();
        int B = e2.getNumberOfSkill();

        
    }

    public void sortSkill(){
        int tempSkill = this.numberOfSkill;
    }
}
