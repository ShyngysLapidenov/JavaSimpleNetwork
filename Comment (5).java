
import java.io.Serializable;
        import java.util.Date;

public class Comment implements Serializable {

    protected User_profile sender;
    protected Date date;
    protected String content;

    public Comment(User_profile sender, Date date, String content) {
        this.sender=sender;
        this.date=date;
        this.content=content;
    }

    public void sendComment(User_profile receiver) {	}

    public User_profile getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date=date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content=content;
    }

}
