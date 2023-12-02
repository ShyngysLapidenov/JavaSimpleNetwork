public class User_invitation {
    public User_profile sender;
    public User_profile receiver;

    public User_invitation(User_profile sender, User_profile receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public User_profile getSender() {
        return sender;
    }

    public User_profile getReceiver() {
        return receiver;
    }

    public String toString() {
        return this.getSender().getSurname() + " " + this.getSender().getName()
                + " has sent an invitation to "
                + this.getReceiver().getSurname() + " "
                + this.getReceiver().getName();
    }
}
