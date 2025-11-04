import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Menu {

    //Reset method 
    public static void reset(Scanner sc) throws Exception {
        System.out.println("Enter the new password: ");
        String input = sc.nextLine();
        FileWriter fw = new FileWriter("master.txt",false);
        fw.write(input);
        fw.close();
        System.out.println("Master password reset successfully!");
    }

    // Master Password 
    public static boolean check(Scanner sc) throws Exception {
        File master = new File("master.txt");
        if(!master.exists()) {
            System.out.println("No master password found.Set up a new password: ");
            String newpass = sc.nextLine();
            java.io.FileWriter fw = new java.io.FileWriter(master);
            fw.write(newpass);
            fw.close();
            System.out.println("Master password created succesfully!!");
            return true;
        }
        System.out.println("Enter the Master Password: ");
        String input = sc.nextLine();
        Scanner fileScanner = new Scanner(master);
        String saved = fileScanner.nextLine();
        fileScanner.close();
        if(input.equals(saved)) {
            System.out.println("Access granted");
            return true;
        }
        else {
            System.out.println("Access denied");
            System.out.println("Forgetten master password? (Y/N): ");
            String in = sc.nextLine();
            if(in.equals("Y")) {
                reset(sc);
            }
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

    //Saving details into file
    public static void save(HashMap<String,Credential> pwds) throws Exception {
        FileWriter fw = new FileWriter("data.txt");
        for(String acc : pwds.keySet()) {
            Credential c = pwds.get(acc);
            fw.write(acc+","+c.username+","+c.password+"\n");
        }
        fw.close();
    }

    //Get details from file
    public static void load(HashMap<String,Credential> pwds) throws Exception {
        File f = new File("data.txt");
        if(!f.exists()) {
            System.out.println("No details have been saved!!");
            return;
        }
        Scanner filScanner = new Scanner(f);
        while(filScanner.hasNextLine()) {
            String curr = filScanner.nextLine();
            String[] sets = curr.split(",");
            if(sets.length == 3) {
                String acc = sets[0];
                String usr = sets[1];
                String pass = sets[2];
                pwds.put(acc, new Credential(usr, pass));
            }
        }
        filScanner.close();
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
        if(pwds.containsKey(ud)) {
            System.out.println("Enter the new username: ");
            String nusr = sc.nextLine();
            System.out.println("Enter the new password: ");
            String npass = sc.nextLine();
            Credential ncreds = new Credential(nusr, npass);
            pwds.put(ud,ncreds);
            System.out.println("The details have been updated!");
        }
        else {
            System.out.println("No details with the account given!");
        }
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
        load(pwds);
        boolean run = true;
        while(run) {
            System.out.println("-------Main Menu-------");
            System.out.println("\n1. Add Password\n2. Search for password\n3. Delete password\n4. Update password\n5. View the all accounts present\n6. Exit\n7.Reset password");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    add(pwds,sc);
                    save(pwds);
                    break;
                case 2:
                    search(pwds,sc);
                    break;
                case 3:
                    delete(pwds,sc);
                    save(pwds);
                    break;
                case 4:
                    update(pwds,sc);
                    save(pwds);
                    break;
                case 5: 
                    view(pwds);
                    break;
                case 6:
                    run=false;
                    System.out.println("------Sayonara------");
                    break;
                case 7:
                    reset(sc);
                    break;
                default:
                    System.out.println("Invalid Choice!!");
                    break;
            }
        }
        sc.close();
    }
}
