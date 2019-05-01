package boys.indecent.mypresense.teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import boys.indecent.mypresense.teacher.models.AttendanceModel;

import static android.content.ContentValues.TAG;

public class StudentsLists extends Activity {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    Button update;
    Map <String,Boolean> selected = new HashMap <String,Boolean>();
    String str1,str2,str3;
    int totalPresence=0,totalAbsent=0,total=0;
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_lists);
        Intent intent=getIntent();
        db = FirebaseFirestore.getInstance();
        str1=intent.getStringExtra("SEMESTER");
        str2=intent.getStringExtra("SECTION");
        str3=intent.getStringExtra("SUBJECT");
       // Log.e("BUNDLE", str1+"\t"+str2+"\t"+str3);
      //  Toast.makeText(StudentsLists.this,str1,Toast.LENGTH_SHORT).show();

        update=findViewById(R.id.mUpdateBtn);

        setUpRecyclerView();
        loadDataFromFirebase();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateRollNo();
            }
        });
    }

    public void  UpdateRollNo() {
        Log.e("MAP", selected.toString());

        //Date current

        WriteBatch batch = db.batch();

        AttendanceModel attendanceModelPresent = new AttendanceModel(FieldValue.serverTimestamp(), true);
        AttendanceModel attendanceModelAbsent = new AttendanceModel(FieldValue.serverTimestamp(), false);

        for (final Map.Entry<String,Boolean> i: selected.entrySet()){

            final DocumentReference ref = db.collection("SEM").document(str1).collection("SEC")
                    .document(str2).collection("ROLL").document(i.getKey());

            if (i.getValue()) {
                batch.update(ref, str3, FieldValue.arrayUnion(attendanceModelPresent));
                totalPresence++;

            }
            else {
                batch.update(ref, str3, FieldValue.arrayUnion(attendanceModelAbsent));
                totalAbsent++;
            }
        }
        Toast.makeText(StudentsLists.this,String.valueOf(totalPresence), Toast.LENGTH_SHORT).show();

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if( task.isSuccessful()){
                    Toast.makeText(StudentsLists.this,"Data uploaded successfully !!", Toast.LENGTH_SHORT).show();

                    total=totalAbsent+totalPresence;



                    Intent intent = new Intent(StudentsLists.this,Report.class);
                    intent.putExtra("PRESENCE",totalPresence);
                    intent.putExtra("ABSENT",totalAbsent);
                    intent.putExtra("TOTAL",total);

                    Toast.makeText(StudentsLists.this,String.valueOf(totalPresence), Toast.LENGTH_SHORT).show();
                    startActivity(intent);


                } else {
                    Log.e("UPLOAD","ERROR",task.getException());
                    Toast.makeText(StudentsLists.this,"Try Again!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadDataFromFirebase() {
        DocumentReference reference = db.collection("SEM").document(str1).collection("SEC")
                .document(str2);
        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.e("TASK ", "OK");
                            DocumentSnapshot snapshot= task.getResult();
                            if(snapshot!=null){
                                setUpAdapter(snapshot);
                            } else {
                                Log.e(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    }
                });

    }

    private void setUpAdapter(DocumentSnapshot snapshot) {
        ArrayList<String> studentList = new ArrayList<>((Collection<? extends String>) snapshot.get("StudentList"));

        for (String x : studentList){
            selected.put(x,false);
        }

        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(StudentsLists.this,studentList,selected);

        mRecyclerView.setAdapter(adapter);
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.mRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
