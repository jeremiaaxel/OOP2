public class Parent {
    private String parent1name;
    private String parent1species;
    private String parent2name;
    private String parent2species;

    public Parent(){
        parent1name = "-";
        parent1species = "-";
        parent2name = "-";
        parent2species = "-";
    }
    public Parent(String name1, String spc1, String name2, String spc2){
        parent1name = name1;
        parent1species = spc1;
        parent2name = name2;
        parent2species = spc2;
    }
    void displayInfo(){
        System.out.print(parent1name +" (Spesies " + parent1species + "'), ");
        System.out.println(parent1name +" (Spesies " + parent1species + "'), ");
    }
}
