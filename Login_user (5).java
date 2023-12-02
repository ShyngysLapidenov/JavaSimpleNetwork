public class Login_user {
    String name;
    String lastname;
    String age;
    String password;
    int year;
    Login_user friends;
    Login_user block;
    Login_user friendrequest;
    Login_user suggestfriend;
    public Login_user()
    {
        name = "";
        friends = null;
        block = null;
        friendrequest=null;
        suggestfriend=null;
    }
    public Login_user(String name)
    {
        this.name = name;
        friends = null;
        block = null;
        friendrequest=null;
        suggestfriend=null;
    }
    public Login_user(String name, String lastname, String age, int year, String password)
    {
        this.name = name;
        this.lastname = lastname;
        this.age=age;
        this.year  = year;
        this.password = password;
        friends = null;
        block = null;
        friendrequest=null;
        suggestfriend=null;
    }
}
