
import java.io.Serializable;
import java.util.Date;

public class Like implements Serializable {

    protected User_profile sender;

    public Like(User_profile sender) {
        this.sender=sender;
    }

    public void sendLike(User_profile receiver) {	}

    public User_profile getLike() {
        return sender;
    }


}
