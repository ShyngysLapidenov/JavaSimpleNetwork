import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Login_network  implements Serializable{
    public HashMap<String, User_profile> registeredUsers;
    public User_profile currentUser;

    public Login_network() {
        this.registeredUsers = new HashMap<String, User_profile>();
        this.currentUser = null;
    }

    public Login_network(HashMap<String, User_profile> list) {
        this.registeredUsers = list;
    }


    public void createAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("To login, enter the following informations:");
        System.out.println("name? " );
        String name = sc.nextLine();
        System.out.println("surname? ");
        String surname = sc.nextLine();
        System.out.println("password? ");
        String password = sc.nextLine();
        System.out.println("age? ");
        String age = sc.nextLine();
        System.out.println("gender? ");
        String gender = sc.nextLine();
        System.out.println("phone number? ");
        String phone_number = sc.nextLine();
        System.out.println("email? ");
        String email = sc.nextLine();
        User_profile newProfile = new User_profile(name, surname, password, age, gender, phone_number, email, true);


        if (registeredUsers.get(email) == null) {
            registeredUsers.put(email, newProfile);
            System.out.println("Account created");
        } else
            System.out.println("Already registered");
    }

    public void openSession(String email_in, String password_in) {
        User_profile tmp = registeredUsers.get(email_in);
        if (tmp == null)
            System.out.println("Incorrect email.");
        else if (!tmp.getPassword().equals(password_in))
            System.out.println("Incorrect password.");
        else if (tmp.getActivation() == false)
            System.out.println("not activated");
        else {
            System.out.println("User identified.");
            System.out.println("Welcome, " + tmp.getSurname() + "!");
            currentUser = tmp;
        }
    }

    public void closeSession() {
        currentUser = null;
    }

    public void activateAccount(String email_in, String password_in) {
        User_profile tmp = registeredUsers.get(email_in);
        if (tmp == null)
            System.out.println("Incorrect email.");
        else if (!tmp.getPassword().equals(password_in))
            System.out.println("Incorrect password.");
        else if (tmp.getActivation() == true)
            System.out.println("Already activated.");
        else {
            tmp.setActivation(true);
            System.out.println("Your account has been activated.");
        }
    }

    public void deactivateAccount(String email_in, String password_in) {
        User_profile tmp = registeredUsers.get(email_in);
        if (tmp == null)
            System.out.println("Incorrect email.");
        else if (!tmp.getPassword().equals(password_in))
            System.out.println("Incorrect password.");
        else if (tmp.getActivation() == false)
            System.out.println("The account is already inactivated.");
        else {
            tmp.setActivation(false);
            System.out.println("Your account has been deactivated.");
        }
    }

    public User_profile search(String name, String surname) {
        LinkedList<User_profile> tmp = new LinkedList<>();
        tmp.addAll(registeredUsers.values());
        User_profile found = null;
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).getName() == name
                    && tmp.get(i).getSurname() == surname) {
                found = tmp.get(i);
            }
        }
        return found;
    }

    public User_profile getCurrentUser() {
        return currentUser;
    }

    public HashMap<String, User_profile> getRegisteredUsers() {
        return registeredUsers;
    }

    public void serialiser() throws IOException {
        String nomFic = "social_network_.bin";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFic));
        try {
            oos.writeObject(this);
        } catch (IOException ioe) {
            System.err.println("FATAL ERROR -- "+ ioe.toString());
        }
        oos.close();
    }

    public Login_network deserialiser() throws IOException, ClassNotFoundException {
        String nomFic = "social_network_.bin";
        Login_network tmp = null;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFic));

            tmp = (Login_network) ois.readObject();

        ois.close();
        return tmp;
    }
}
