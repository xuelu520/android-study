package makyu.view.Model;

/**
 * Created by -(^_^)- on 2016/4/18.
 */
public class Tiezi {
    private int tid;
    private String subject;

    public int getId() {
        return tid;
    }
    public void setId(int tid) {
        this.tid = tid;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString(){
        return this.tid + ":" + this.subject;
    }
}
