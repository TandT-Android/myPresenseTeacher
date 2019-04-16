package boys.indecent.mypresense.teacher;

import com.google.android.gms.common.util.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomClass implements Serializable {
    private ArrayList<String> StudentList = new ArrayList<String>();
    private int total_students;

    public CustomClass(ArrayList<String> StudentList, int total_students) {
        this.StudentList = StudentList;
        this.total_students = total_students;
    }

    public CustomClass() {
    }

    public ArrayList<String> getStudentList() {
        return StudentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        StudentList = studentList;
    }

    public int getTotal_students() {
        return total_students;
    }

    public void setTotal_students(int total_students) {
        this.total_students = total_students;
    }
}
