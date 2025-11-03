import java.io.File;
import java.util.*;

public class menu {
    public static boolean check() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Master Password: ");
        String input = sc.nextLine();
        Scanner fileScanner = new Scanner(new File("master.txt"));
        String saved = fileScanner.nextLine();
        fileScanner.close();
        if(input.equals(saved)) {
            System.out.println("Access granted");
            return true;
        }
        else {
            System.out.println("Access denied");
            return false;
        }
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        boolean access = check();
        while(!access) {
            System.out.println("Try again!");
            access = check();
        }
        System.out.println("-------Main Menu-------");
        System.out.println("\n1. Add Password\n2. Search for password\n3. Delete password\n4. Update password\n5. View the all accounts present\n6. Exit");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        
    }
}
