
package Entities;

import java.io.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

public class PlayerTest {
    private static Player p;
    private static Parent parent;
    private static Map map = new Map();

    public void println(Object obj) {
        System.out.println(obj);
    }

    public void addMoreEngimons() {
        p.addEngimon(new Ampharos("Ampharos", parent, p.getPlayerPosition(),map));
        p.addEngimon(new Eiscue("Eiscue", parent, p.getPlayerPosition(),map));
        p.addEngimon(new Araquanid("Ara ara~", parent, p.getPlayerPosition(),map));
    }

    public void addMoreSkill() {
        p.addSkillItem(new TripleAxel());
        p.addSkillItem(new BubbleBeam());
        p.addSkillItem(new FusionFlare());
    }

    @BeforeAll
    public static void init() {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        String filepath = "data/map.txt";
        try {
            String map_text = map.parse(filepath);
            map = new Map(16, 20, map_text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        p = new Player("ULLLAA", map);

        p.setPlayerPosition(map, map.getTile(5, 6));
        parent = new Parent("Ula", "Uli", "Ulu", "Ulo");

        Engimon eng1 = new Aggron("Aggron", parent, p.getPlayerPosition(),map);
        p.addEngimon(eng1);

    }

    @Test
    void testSetAndGetPlayerPosition() {
        p.setPlayerPosition(p.map.getTile(5, 5));
        println(p.getPlayerPosition().getAbsis() + ", " + p.getPlayerPosition().getOrdinat());
        assert p.getPlayerPosition().getAbsis() == 5;
        assert p.getPlayerPosition().getOrdinat() == 5;
    }

    @Test
    void testMove() {
        p.setPlayerPosition(p.map.getTile(5, 5));
        p.move(p.map, 'w');
        assert p.getPlayerPosition().getAbsis() == 5 && p.getPlayerPosition().getOrdinat() == 4;
        p.move(p.map, 'a');
        assert p.getPlayerPosition().getAbsis() == 4 && p.getPlayerPosition().getOrdinat() == 4;
        p.move(p.map, 's');
        assert p.getPlayerPosition().getAbsis() == 4 && p.getPlayerPosition().getOrdinat() == 5;
        p.move(p.map, 'd');
        assert p.getPlayerPosition().getAbsis() == 5 && p.getPlayerPosition().getOrdinat() == 5;
    }

    @Test
    void testAddEngimon() {
        addMoreEngimons();
        println(p.getOwnedEngimonSize());
        assert p.getOwnedEngimonSize() == 4;
    }

    @Test
    void testRemoveEngimon() {
        addMoreEngimons();
        println(p.getOwnedEngimonSize());
        assert p.getOwnedEngimonSize() == 4;
        p.removeEngimon(1);
        assert p.getOwnedEngimonSize() == 3;
        p.removeEngimon(1);
        assert p.getOwnedEngimonSize() == 2;
        p.removeEngimon(1);
        assert p.getOwnedEngimonSize() == 1;
    }

    @Test
    void testGetActiveEngimon() {
        Engimon eng1 = new Blaziken("Blaz", parent, p.getPlayerPosition(),map);
        p.addEngimon(eng1);
        p.switchActiveEngimon(1);
        assert p.getActiveEngimon().equals(eng1);
        println(p.getActiveEngimon().getEngimonName());
    }

    @Test
    void testGetEngimon() {
        Engimon eng1 = new Blaziken("Blaz", parent, p.getPlayerPosition(),map);
        p.addEngimon(eng1);
        assert p.getEngimon(1).equals(eng1);
        println(p.getActiveEngimon().getEngimonName());
    }

    @Test
    void testSetActiveEngimonPosition() {
        Engimon eng1 = new Blaziken("Blaz", parent, p.getPlayerPosition(),map);
        p.addEngimon(eng1);
        p.switchActiveEngimon(1);
        Tile tile = new Tile();
        tile.setAbsis(10);
        tile.setOrdinat(13);
        p.setActiveEngimonPosition(tile);
        assert p.getActiveEngimon().getCurrentPosition().getAbsis() == 10;
        assert p.getActiveEngimon().getCurrentPosition().getOrdinat() == 13;
        println(p.getActiveEngimon().getCurrentPosition().getAbsis() + ", " + p.getActiveEngimon().getCurrentPosition().getOrdinat());
    }

    @Test
    void testInteract() {
        p.interact();
    }

    @Test
    void testShowOwnedEngimons() {
        addMoreEngimons();
        p.showOwnedEngimons();
    }

    @Test
    void testShowEngimonDetails() {
        addMoreEngimons();
        for (int i = 0; i < p.getOwnedEngimonSize(); i++) {
            p.showEngimonDetails(i);
        }
    }

    @Test
    void testBreed() {
    }

    @Test
    void testFreeTheEngimon() {
        addMoreEngimons();
        assert p.getOwnedEngimonSize() == 4;
        p.freeTheEngimon(0);
        assert p.getOwnedEngimonSize() == 3;
    }

    @Test
    void testChangeEngimonName() {
        addMoreEngimons();
        assert p.getOwnedEngimonSize() == 4;
        p.changeEngimonName(0, "Miaw");
        assert p.getEngimon(0).getEngimonName().equals("Miaw");
        p.interact();
    }

    @Test
    void testIsThereAnyEnemyAround() {
    }

    @Test
    void testAddSkillItem() {
        assert p.getOwnedSkillItemSize() == 0;
        addMoreSkill();
        assert p.getOwnedSkillItemSize() == 3;
        addMoreSkill();
        assert p.getOwnedSkillItemSize() == 6;
    }

    @Test
    void testUseSkillItem() {
        addMoreEngimons();
        addMoreSkill();
        p.switchActiveEngimon(0);
        assert p.getOwnedSkillItemSize() == 3;
        p.getActiveEngimon().displayEngimonInfo();
        println("");
        try {
            p.showSkillItem();
            println("");
            p.useSkillItem(p.getSkillItem(0));
            assert p.getOwnedSkillItemSize() == 2;
            p.getActiveEngimon().displayEngimonInfo();
            println("");
            p.showSkillItem();
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    @Test
    void testShowSkillItem() {
        try {
            addMoreSkill();
            p.showSkillItem();
            addMoreSkill();
            addMoreSkill();
            p.showSkillItem();
        } catch (Exception e) {
            println(e.getLocalizedMessage());
        }
    }

    @Test
    void testSerializeable() {
        try {
            FileOutputStream fileOut = new FileOutputStream("data/test.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.close();
            fileOut.close();
            println("Done");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    void testDeSerializeable() {
        try {
            FileInputStream fileIn = new FileInputStream("data/test.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Player play = (Player)in.readObject();
            in.close();
            fileIn.close();
            println(play.getPlayerName());
            println(play.getPlayerPosition().getAbsis() + "," + play.getPlayerPosition().getOrdinat());
            play.getEngimon(0).getEngimonParent().displayInfo();
            println("Done");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
}

