package boys.indecent.mypresense.teacher.models;

import java.io.Serializable;
import java.util.ArrayList;

public class AttendanceService implements Serializable {

    ArrayList<AttendanceModel> BD = new ArrayList<>();

    public AttendanceService(ArrayList<AttendanceModel> BD) {
        this.BD = BD;
    }

    public ArrayList<AttendanceModel> getBD() {
        return BD;
    }

    public void setBD(ArrayList<AttendanceModel> BD) {
        this.BD = BD;
    }

    public AttendanceService() {
    }
}
