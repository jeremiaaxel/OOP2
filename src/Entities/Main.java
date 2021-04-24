package Entities;//package Player;
import java.io.*;
import java.util.Scanner;

public class Main {
    private static String mapPath = "../data/map.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Map map = new Map();
        try {
            String map_text = map.parse(mapPath);
            map = new Map(12, 14, map_text);
            map.displayMap();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("a");
        } finally {
            scanner.close();
        }


        System.out.print("Insert player name : ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName,map);

        System.out.println("Welcome " + playerName);

        String command = scanner.nextLine();
        while (!command.equals("exit")) {
            System.out.println(command);
            command = scanner.nextLine();
        }

    }
}
