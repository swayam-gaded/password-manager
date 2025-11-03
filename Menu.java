import java.io.File;
import java.util.*;

public class Menu {
    // Master Password 
    public static boolean check(Scanner sc) throws Exception {
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

    // Credential class with toString() cuz it every sub class has it and instead of gibberish we define it properly
    static class Credential {
        String username;
        String password;
        Credential(String username,String password) {
            this.username = username;
            this.password = password;
        }
        public String toString() {
            return "Username: "+username+"\nPassword: "+password;
        }
    }

    //Add method
    public static void add(HashMap<String,Credential> pwds,Scanner sc) {
        System.out.println("Enter the account: ");
        String acc = sc.nextLine();
        System.out.println("Enter the username: ");
        String usr = sc.nextLine();
        System.out.println("Enter the password: ");
        String pass = sc.nextLine();
        Credential curr = new Credential(usr,pass);
        pwds.put(acc,curr);
        System.out.println("Account details successfully added");
        System.out.println();
        return;
    } 

    //Search method
    public static void search(HashMap<String,Credential> pwds, Scanner sc) {
        System.out.println("Enter the account required: ");
        String rq = sc.nextLine();
        if(!pwds.containsKey(rq)) {
            System.out.println("No details found with the account given!");
            return;
        }
        System.out.println("The details are as follows: \n"+pwds.get(rq));
        System.out.println();
        return;
    }

    //Delete method
    public static void delete(HashMap<String,Credential> pwds,Scanner sc) {
        System.out.println("Enter the account whose details are to be erased: ");
        String dl = sc.nextLine();
        if(!pwds.containsKey(dl)) {
            System.out.println("No details found with the account given!");
            return;
        }
        System.out.println("The details are: \n"+pwds.get(dl));
        System.out.println("Are you sure you want to delete the above details ?(Y/N)");
        String verify = sc.nextLine();
        if(verify.equals("Y")) {
            pwds.remove(dl);
            System.out.println("The details have been deleted.");
        }
        else if(verify.equals("N")) { 
            return;
        }
    }

    //Update method
    public static void update(HashMap<String,Credential> pwds,Scanner sc) {
        System.out.println("Enter the account whose details are to be updated: ");
        String ud = sc.nextLine();
        System.out.println("Enter the new username: ");
        String nusr = sc.nextLine();
        System.out.println("Enter the new password: ");
        String npass = sc.nextLine();
        Credential ncreds = new Credential(nusr, npass);
        pwds.put(ud,ncreds);
        System.out.println("The details have been updated!");
        return;
    }

    //View method 
    public static void view(HashMap<String,Credential> pwds) {
        System.out.println("The accounts whose details are saved here are as follows: ");
        for(String el : pwds.keySet()) {
            System.out.println("--> "+el);
        }
        return;
    }

    // main function 
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        boolean access = check(sc);
        while(!access) {
            System.out.println("Try again!");
            access = check(sc);
        }
        HashMap<String,Credential> pwds = new HashMap<>();
        boolean run = true;
        while(run) {
            System.out.println("-------Main Menu-------");
            System.out.println("\n1. Add Password\n2. Search for password\n3. Delete password\n4. Update password\n5. View the all accounts present\n6. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    add(pwds,sc);
                    break;
                case 2:
                    search(pwds,sc);
                    break;
                case 3:
                    delete(pwds,sc);
                    break;
                case 4:
                    update(pwds,sc);
                    break;
                case 5: 
                    view(pwds);
                    break;
                case 6:
                    run=false;
                    System.out.println("------Sayonara------");
                    break;
                default:
                    System.out.println("Invalid Choice!!");
                    break;
            }
        }
        sc.close();
    }
}
