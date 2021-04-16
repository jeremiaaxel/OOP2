import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String filepath = scanner.nextLine();
        
        try (FileInputStream fis = new FileInputStream(filepath)) {
            scanner = new Scanner(fis);

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        } finally {
            scanner.close();
        }
    }
}
