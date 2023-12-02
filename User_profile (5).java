import HZ.Profile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class User_profile {
    protected String name, surname, password, age, gender, phone_number, email;
    protected ArrayList<User_invitation> invitationList, invitationSendedList;
    protected ArrayList<User_profile> friendsList;
    protected ArrayList<User_profile> removedfriendsList;
    protected boolean activation;
    protected Post personalPost;
    protected ArrayList<Post> posts;

    public User_profile() {}

    public User_profile(String name, String surname, String password,
                   String age, String gender, String phone_number, String email,
                   boolean activation) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
        this.activation = activation;
        this.personalPost = new Post();
        this.invitationList = new ArrayList<User_invitation>();
        this.invitationSendedList = new ArrayList<User_invitation>();
        this.friendsList = new ArrayList<User_profile>();
        this.removedfriendsList = new ArrayList<User_profile>();
    }

    public String toString() {
        return "name: " + this.name + "\nsurname: " + this.surname
                + "\nage: " + this.age.toString() + "\ngender: "
                + this.gender.toString() + "\nphone number: "
                + this.phone_number.toString() + "\nemail: " + this.email + "\n";
    }



    public void displayFriends() {
        for (int i = 0; i < this.friendsList.size(); i++)
            System.out.println(i + ") " + this.friendsList.get(i).getSurname()
                    + " " + this.friendsList.get(i).getName());
    }


    public void displayInvitation() {
        if (!this.getInvitationList().isEmpty())
            System.out.println(this.getInvitationList().toString());
        else
            System.out.println("There are no new invitations yet.");
    }

    public void sendInvitation(User_profile p) {
        if(!this.friendsList.contains(p)){
            this.invitationSendedList.add(new User_invitation(this, p));
            p.getInvitationList().add(new User_invitation(this, p));
            System.out.println("An invitation was sended");
        }else
            System.out.println("Already your friend");
    }

    public void acceptInvitation() {
        Scanner sc = new Scanner(System.in);
        while (!this.getInvitationList().isEmpty()) {
            User_invitation i = this.invitationList.get(0);
            System.out.println(i.getSender().getSurname() + " "
                    + i.getSender().getName()
                    + " wants to add you on Facebook.");
            System.out.println("a  accept    r  refuse");
            char answer = sc.nextLine().charAt(0);
            if (answer == 'a') {
                if(!this.friendsList.contains(i.sender)){
                    this.getFriendsList().add(i.getSender());
                    i.getSender().getFriendsList().add(i.getReceiver());
                    this.getInvitationList().remove(i);
                    i.getSender().getInvitationSendedList().remove(i);
                    System.out.println(i.sender + "was added to your friend list");
                }else
                    System.out.println("already one of your friends");
            } else if (answer == 'r') {
                this.getInvitationList().remove(i);
                i.getSender().getInvitationSendedList().remove(i);
            } else {
                System.out.println("Character unvalid. Try again.");
            }
        }
    }
    public void blockFriends() {
        Scanner sc = new Scanner(System.in);
        int choice;
        for (int i = 0; i < this.friendsList.size(); i++)
            System.out.println(i + ") " + this.friendsList.get(i).getSurname()
                    + " " + this.friendsList.get(i).getName());
        choice = Integer.parseInt(sc.nextLine());
        if (this.friendsList.get(choice) != null) {
            this.getRemovedFriendsList().add(this.friendsList.get(choice));
//            this.removedfriendsList.add(this.friendsList.get(choice));
            this.friendsList.remove(choice);
        }
        System.out.println(choice + ") " + this.friendsList.get(choice).getSurname()
                + " " + this.friendsList.get(choice).getName()+" is blocked");

    }


    public void unblockFriends() {
        Scanner sc = new Scanner(System.in);
        int choice;
        for (int i = 0; i < this.removedfriendsList.size(); i++)
            System.out.println(i + ") " + this.removedfriendsList.get(i).getSurname()
                    + " " + this.removedfriendsList.get(i).getName());
        choice = Integer.parseInt(sc.nextLine());
        if (this.removedfriendsList.get(choice) != null) {
            this.friendsList.add(this.removedfriendsList.get(choice));
            this.removedfriendsList.remove(choice);
        }
        System.out.println(choice + ") " + this.friendsList.get(choice).getSurname()
                + " " + this.friendsList.get(choice).getName()+" is unblocked");

    }


    public void cancelInvitation() {
        Scanner sc = new Scanner(System.in);
        int choice;
        if (!this.getInvitationSendedList().isEmpty()) {
            int k = 0;
            for (User_invitation i : this.invitationSendedList)
                System.out.println(k + ") " + i);
            System.out.println("Choose an invitation: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }
            if (choice > -1 && choice < this.invitationSendedList.size()) {
                User_invitation i = this.invitationSendedList.get(choice);
                i.getReceiver().getInvitationList().remove(i);
                this.invitationSendedList.remove(i);
                System.out.println("Invitation removed.");
            } else
                System.out.println("Invalid input.");
        } else
            System.out.println("There are no invitation.");
    }

    public void serialiser(String path) throws IOException {
        String nomFic = path + this.email + ".bin";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                nomFic));
        try {
            oos.writeObject(this);
        } catch (IOException ioe) {
            System.err.println("FATAL ERROR -- " + ioe.toString());
        }
        oos.close();
    }

    public Profile deserialiser(String email) throws IOException {
        String nomFic = email+".bin";
        Profile tmp = null;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                nomFic));
        try {
            tmp = (Profile) ois.readObject();
        } catch (IOException ioe) {
            System.err.println("FATAL ERROR -- " + ioe.toString());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ERROR 'Unknown class' -- " + cnfe.toString());
        }
        ois.close();
        return tmp;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public ArrayList<User_profile> getFriendsList() {
        return this.friendsList;
    }

    public void setFriendsList(ArrayList<User_profile> friendsList) {
        this.friendsList = friendsList;
    }

    public ArrayList<User_profile> getRemovedFriendsList() {
        return this.removedfriendsList;
    }

    public void setRemovedFriendsList(ArrayList<User_profile> removedfriendsList) {
        this.removedfriendsList = removedfriendsList;
    }

    public String getAge_() {
        return age;
    }

    public void setAge_(String age) {
        this.age = age;
    }

    public ArrayList<User_invitation> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(ArrayList<User_invitation> invitationList) {
        this.invitationList = invitationList;
    }

    public ArrayList<User_invitation> getInvitationSendedList() {
        return invitationSendedList;
    }

    public Post getPersonalPost() {
        return personalPost;
    }

    public void setPersonalPost(Post personalPost) {
        this.personalPost = personalPost;
    }


    public void writeComment() {
        Scanner sc = new Scanner(System.in);
        Date d = new Date();
        System.out.println("Your Comment : ");
        Comment m = new Comment(this, d, sc.nextLine());
        this.getPersonalPost().getCommentList().add(m);
        posts.add(this.getPersonalPost());
    }

    public void writeCommentToFriend() {
        Scanner sc = new Scanner(System.in);
        int choice;
        if (!this.friendsList.isEmpty()) {
            displayFriends();
            System.out.println("Choose a friend: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }
            if (choice > -1 && choice < this.friendsList.size()) {
                Date d = new Date();
                System.out.println("Your Comment: ");
                Comment m = new Comment(this, d, sc.nextLine());
                this.friendsList.get(choice).getPersonalPost().getCommentList()
                        .add(m);
                System.out.println("Do you wanna like "+ this.friendsList.get(choice).getSurname()+" "+friendsList.get(choice).getName()+ "'s post?");
                System.out.println("a  yes    r  no");
                char answer = sc.nextLine().charAt(0);
                if (answer == 'a') {
                    Like l = new Like(this);
                    this.friendsList.get(choice).getPersonalPost().getLike().add(l);
                    }

            } else
                System.out.println("Invalid input.");
        } else
            System.out.println("You have no friend.");
    }

    public void displayFriendPost() {
        Scanner sc = new Scanner(System.in);
        int choice;
        if (!this.friendsList.isEmpty()) {
            displayFriends();
            System.out.println("Choose a friend: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }
            if (choice > -1 && choice < this.friendsList.size()) {
                this.friendsList.get(choice).displayPost();
            } else
                System.out.println("Invalid input.");
        } else
            System.out.println("You have no friend.");
    }

    public void displayPost() {
//        List<Post> sortedPosts = personalPost.stream()
//                .sorted((p1, p2) -> p2.getLikes() - p1.getLikes())
//                .collect(Collectors.toList());
//        System.out.println(this.getPersonalPost().getLike().size()+"- likes");
//        System.out.println(this.getPersonalPost().toString());
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                return p2.getLikeSize() - p1.getLikeSize();
            }
        });

        // Display the sorted posts
        for (Post post : posts) {
            System.out.println(post.getCommentList());
            System.out.println("Likes: " + post.getLikeSize());
            System.out.println("-------------------");
        }
    }
    public void highestLikePost() {

        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < choice; i++){
            System.out.println(this.getPersonalPost().getQueue().poll()+"- likes");
        System.out.println(this.getPersonalPost().getLike().size()+"- likes");
        System.out.println(this.getPersonalPost().toString());}
    }

    public void intersection(){
        HashMap<String, List<User_profile>> map=new HashMap<>();
        if (!this.friendsList.isEmpty()) {



            int maxIntersectSize = 0;
            int b= 0;
            List<User_profile> usersWithMaxIntersect = new ArrayList<>();
            for(int i = 0; i < this.friendsList.size(); i++) {
                User_profile user1 = this.friendsList.get(i);
                List<User_profile> intersect = this.friendsList.stream()
                        .filter(user1.friendsList::contains).collect(Collectors.toList());
                int intersectSize = intersect.size();
                if (intersectSize > maxIntersectSize) {
                    maxIntersectSize = intersectSize;
                    usersWithMaxIntersect.clear();
                    usersWithMaxIntersect.add(user1);
                    b = i;
                } else if (intersectSize == maxIntersectSize) {
                    usersWithMaxIntersect.add(user1);
                     b = i;
                }
            }
            System.out.println(usersWithMaxIntersect.get(0));
            List<User_profile> intersect = this.friendsList.stream().
                    filter(this.friendsList.get(b).friendsList::contains).collect(Collectors.toList());
            for (int i = 0; i < intersect.size(); i++){
            System.out.println(intersect.get(i));}
        } else
            System.out.println("You have no friend.");


    }


}
