package boys.indecent.mypresense.teacher.models;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;
import java.sql.Time;

public class AttendanceModel implements Serializable {

    Object date;
    boolean presence;

    public AttendanceModel() {
    }

    public Object getDate() {
        return date;
    }

    public void setDate(FieldValue date) {
        this.date = date;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public AttendanceModel(Object date, boolean presence) {
        this.date = Timestamp.now();
        this.presence = presence;
    }
}
