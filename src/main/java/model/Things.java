package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Things {
    private final int id;
    private final int user_id;
    private final String thing;
    private final String timeLimit;

    private int processed;

    public Things (int id, int user_id, String thing, String timeLimit, int processed) {
        this.id = id;
        this.user_id = user_id;
        this.thing = thing;
        this.timeLimit = timeLimit;
        this.processed = processed;
    }

    public int getId() {return id;}
    public String getThing() {return thing;}
    public int getUser_id() {return user_id;}
    public String getTimeLimit() {return timeLimit;}
    public int getProcessed() {return processed;}

}
