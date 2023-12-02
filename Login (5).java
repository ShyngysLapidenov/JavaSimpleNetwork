import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Login {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Welcome");
//        System.out.println("/n/nLOGIN");
//        System.out.println("Username :");
//        String name = sc.next();
//
//
//    }

    public static void main(String[] args) {

        String email_in, password_in;
        int choice,choice2;
        Scanner sc = new Scanner(System.in);
        Login_network social = new Login_network();
        System.out.println("///////////////////////Initial//////////////////////////");
//        User_profile newProfile = new User_profile(name, surname, password, age, gender, phone_number, email, true);
        social.registeredUsers.put("arman", new User_profile("arman","arystanbek","1", "16","male","1", "arman", true));
        social.registeredUsers.put("enlik", new User_profile("enlik","polat","1","22","female","1","enlik", true));
        social.registeredUsers.put("humar", new User_profile("humar","nazar","1","16","male","1","humar", true));
        social.registeredUsers.put("aigul", new User_profile("aigul","ahmet","1","18","female","1","aigul", true));
        social.registeredUsers.put("amir", new User_profile("amir","kassym","1","19","male","1","amir", true));
        social.registeredUsers.put("gulnaz", new User_profile("gulnaz","nurlan","1","20","female","1","gulnaz", true));
        social.registeredUsers.put("damir", new User_profile("damir","khasan","1","21","male","1","damir", true));
        social.registeredUsers.put("meyram", new User_profile("meyram","okap","1","19","male","1","meyram", true));
        social.registeredUsers.put("aisha", new User_profile("aisha","nurtas","1","23","female","1","aisha", true));
        social.registeredUsers.put("hadisha", new User_profile("hadisha","aman","1","17","female","1","hadisha", true));

//        User_invitation i = this.invitationList.get(0);
        social.registeredUsers.get("arman").getFriendsList().add(social.registeredUsers.get("enlik"));
        social.registeredUsers.get("enlik").getFriendsList().add(social.registeredUsers.get("arman"));

        social.registeredUsers.get("arman").getFriendsList().add(social.registeredUsers.get("humar"));
        social.registeredUsers.get("humar").getFriendsList().add(social.registeredUsers.get("arman"));

        social.registeredUsers.get("arman").getFriendsList().add(social.registeredUsers.get("aigul"));
        social.registeredUsers.get("aigul").getFriendsList().add(social.registeredUsers.get("arman"));

        social.registeredUsers.get("enlik").getFriendsList().add(social.registeredUsers.get("aigul"));
        social.registeredUsers.get("aigul").getFriendsList().add(social.registeredUsers.get("enlik"));

        social.registeredUsers.get("arman").getFriendsList().add(social.registeredUsers.get("gulnaz"));
        social.registeredUsers.get("gulnaz").getFriendsList().add(social.registeredUsers.get("arman"));

        social.registeredUsers.get("enlik").getFriendsList().add(social.registeredUsers.get("gulnaz"));
        social.registeredUsers.get("gulnaz").getFriendsList().add(social.registeredUsers.get("enlik"));

        social.registeredUsers.get("humar").getFriendsList().add(social.registeredUsers.get("meyram"));
        social.registeredUsers.get("meyram").getFriendsList().add(social.registeredUsers.get("humar"));

        social.registeredUsers.get("gulnaz").getFriendsList().add(social.registeredUsers.get("meyram"));
        social.registeredUsers.get("meyram").getFriendsList().add(social.registeredUsers.get("gulnaz"));

        social.registeredUsers.get("hadisha").getFriendsList().add(social.registeredUsers.get("meyram"));
        social.registeredUsers.get("meyram").getFriendsList().add(social.registeredUsers.get("hadisha"));

        social.registeredUsers.get("enlik").getFriendsList().add(social.registeredUsers.get("amir"));
        social.registeredUsers.get("amir").getFriendsList().add(social.registeredUsers.get("enlik"));

        social.registeredUsers.get("amir").getFriendsList().add(social.registeredUsers.get("gulnaz"));
        social.registeredUsers.get("gulnaz").getFriendsList().add(social.registeredUsers.get("amir"));

        social.registeredUsers.get("amir").getFriendsList().add(social.registeredUsers.get("humar"));
        social.registeredUsers.get("humar").getFriendsList().add(social.registeredUsers.get("amir"));

        social.registeredUsers.get("humar").getFriendsList().add(social.registeredUsers.get("aisha"));
        social.registeredUsers.get("aisha").getFriendsList().add(social.registeredUsers.get("humar"));

        social.registeredUsers.get("aisha").getFriendsList().add(social.registeredUsers.get("gulnaz"));
        social.registeredUsers.get("gulnaz").getFriendsList().add(social.registeredUsers.get("aisha"));

        social.registeredUsers.get("aisha").getFriendsList().add(social.registeredUsers.get("enlik"));
        social.registeredUsers.get("enlik").getFriendsList().add(social.registeredUsers.get("aisha"));

        social.registeredUsers.get("enlik").getFriendsList().add(social.registeredUsers.get("damir"));
        social.registeredUsers.get("damir").getFriendsList().add(social.registeredUsers.get("enlik"));


        System.out.println("////////////////////////////////////////////////////////");
        try {
            social=social.deserialiser();
        } catch (IOException | ClassNotFoundException e1) {
            System.err.println("Loading error.");
        }
        do{
            System.out.println("What do you want to do?");
            System.out.println("1 Create account");
            System.out.println("2 Open session");
            System.out.println("3 activate my session");
            System.out.println("4 Deactivate my session");
            //System.out.println("5 see the list of session");
            //System.out.println("6 Exit");
            System.out.println("5 Exit");
            try{
                choice = Integer.parseInt(sc.nextLine());
            }catch(Exception e){
                choice=-1;
            }
            switch(choice) {
                case(1):
                    social.createAccount();
                    break;
                case(2):
                    System.out.println("Please enter your email and your password:");
                    System.out.println("Email? ");
                    email_in = sc.nextLine();
                    System.out.println("Password?");
                    password_in = sc.nextLine();
                    social.openSession(email_in,password_in);
                    if(social.getCurrentUser()!=null){
                        do{
                            System.out.println("1 See the list of your friends");
                            System.out.println("2 Send an invitation");
                            System.out.println("3 Cancel an invitation sended");
                            System.out.println("4 View your invitation list");
                            System.out.println("5 Making posts on your profile");
                            System.out.println("6 Comment your friend's post");
                            System.out.println("7 See your post");
                            System.out.println("8 See a friend's post");
                            System.out.println("9 Blocking friends");
                            System.out.println("10 Unblocking friends");
                            System.out.println("11 k post with max like");
                            System.out.println("12 Max intersection");
                            System.out.println("13 Log out");
                            try{
                                choice2 = Integer.parseInt(sc.nextLine());
                            }catch(Exception e){
                                choice2=-1;
                            }
                            switch(choice2) {
                                case 1:
                                    social.getCurrentUser().displayFriends();
                                    break;
                                case 2:
                                    System.out.println("Enter the email for this invitation");
                                    User_profile p=social.getRegisteredUsers().get(sc.nextLine());
                                    if(p!=null){
                                        social.getCurrentUser().sendInvitation(p);
                                    }else
                                        System.out.println("Doesn't exist");
                                    break;
                                case 3:
                                    social.getCurrentUser().cancelInvitation();
                                    break;
                                case 4:
                                    social.getCurrentUser().acceptInvitation();
                                    break;
                                case 5:
                                    social.getCurrentUser().writeComment();
                                    break;
                                case 6:
                                    social.getCurrentUser().writeCommentToFriend();
                                    break;
                                case 7:
                                    System.out.println(social.getCurrentUser().getPersonalPost());
                                    break;
                                case 8:
                                    social.getCurrentUser().displayFriendPost();
                                    break;
                                case 9:
                                    //Login_network block = new Login_network();
                                    //block.getCurrentUser();
                                    //block.closeSession();
                                    social.getCurrentUser().blockFriends();
                                    break;
                                case 10:
                                    social.getCurrentUser().unblockFriends();
                                    break;
                                case 11:
                                    social.getCurrentUser().highestLikePost();
                                    break;
                                case 12:
                                    social.getCurrentUser().intersection();
                                    break;
                                default:
                                    break;
                            }
                        }while(choice2!=13);
                    }
                    break;
                case(3):
                    System.out.println("Please enter your email and your password:");
                    System.out.println("Email? ");
                    email_in = sc.nextLine();
                    System.out.println("Password?");
                    password_in = sc.nextLine();
                    social.activateAccount(email_in, password_in);
                    break;
                case(4):
                    System.out.println("Please enter your email and your password:");
                    System.out.println("Email? ");
                    email_in = sc.nextLine();
                    System.out.println("Password?");
                    password_in = sc.nextLine();
                    social.deactivateAccount(email_in, password_in);
                    break;
                case(5):
                    //social.DisplayRegisteredUsers();
                    break;
                default:
                    break;
            }
        }
        //while(choice!=6);
        while(choice!=5);
        try {
            social.serialiser();
        } catch (IOException e) {
            System.out.println("Error when saving");
        }
    }
}
