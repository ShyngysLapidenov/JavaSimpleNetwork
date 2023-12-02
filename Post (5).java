
import java.io.Serializable;
        import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Post implements Serializable, Comparable<Like> {

    protected ArrayList<Comment> CommentList;
    protected ArrayList<Like> like;
    protected PriorityQueue<Integer> queue;

    public Post() {
        this.CommentList=new ArrayList<Comment>();
        this.like = new ArrayList<Like>();
        this.queue = new PriorityQueue<Integer>(Collections.reverseOrder());
    }

    public Post(ArrayList<Comment> list, ArrayList<Like> like, PriorityQueue<Integer> queue) {
        this.CommentList=list;
        this.like = like;
        this.queue = queue;
    }

    public void addComment(Comment m){
        CommentList.add(m);
    }

    public String toString() {
        String toReturn="";
        if(CommentList.isEmpty()) {
            toReturn+="no Comment in the post";
        }else
            for(int i=0; i<CommentList.size(); i++)
                toReturn+="On "+CommentList.get(i).getDate()+" From: "+CommentList.get(i).getSender().getSurname()+" "+CommentList.get(i).getSender().getName()+"\n\t"+CommentList.get(i).getContent()+"\n";
        return toReturn;
    }

    public void deleteComment(Comment mess) {
        for(int i=0; i<CommentList.size(); i++) {
            if(CommentList.get(i)==mess) {
                CommentList.remove(i);
            }
        }
    }

    public void addQueue(PriorityQueue<Integer> queue) {
        this.queue.add(like.size());
    }

    public PriorityQueue<Integer> getQueue() {
        return queue;
    }

    public ArrayList<Comment> getCommentList() {
        return CommentList;
    }
    public ArrayList<Like> getLike() {return like;}
    public int getLikeSize() {return like.size();}

    public void setMessageList(ArrayList<Comment> messageList) {
        this.CommentList = messageList;
    }

    @Override
    public int compareTo(final Like o) {
        return 0;
    }
}