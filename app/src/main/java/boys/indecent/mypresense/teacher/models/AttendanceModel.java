package boys.indecent.mypresense.teacher.models;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;
import java.util.Date;

public class AttendanceModel implements Serializable {

    FieldValue DATE;
    boolean PRESENCE;

    public AttendanceModel() {
    }

    public FieldValue getDATE() {
        return DATE;
    }

    public void setDATE(FieldValue DATE) {
        this.DATE = DATE;
    }

    public boolean isPRESENCE() {
        return PRESENCE;
    }

    public void setPRESENCE(boolean PRESENCE) {
        this.PRESENCE = PRESENCE;
    }

    public AttendanceModel(FieldValue DATE, boolean PRESENCE) {
        this.DATE = DATE;
        this.PRESENCE = PRESENCE;
    }
}
