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
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import boys.indecent.mypresense.teacher.models.AttendanceModel;
import boys.indecent.mypresense.teacher.models.AttendanceService;

import static android.content.ContentValues.TAG;
import static com.google.firebase.firestore.FieldValue.arrayUnion;

public class StudentsLists extends Activity {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    Button update;
    MyRecycleViewAdapter adapter;
    CustomClass customClassObject;
    Map <String,Boolean> selected = new HashMap <String,Boolean>();
    String str1,str2,str3;
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_lists);
        Intent intent=getIntent();
        db = FirebaseFirestore.getInstance();
        str1=intent.getStringExtra("SEMESTER");
        str2=intent.getStringExtra("SECTION");
        str3=intent.getStringExtra("SUBJECT");
       // Log.e("BUNDLE", str1+"\t"+str2+"\t"+str3);
        Toast.makeText(StudentsLists.this,str1,Toast.LENGTH_SHORT).show();

        update=findViewById(R.id.mUpdateBtn);


        //Intent intent = new Intent(this,SignInActivity.class);
        // startActivity(intent);

        setUpRecyclerView();
        // setUpFireBase();
        //addTestDataToFirebase();
        loadDataToFirebase();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateRollNo();
            }
        });







    }

    public void  UpdateRollNo()
    {

        Log.e("MAP", selected.toString());

        //Date current


        for (final Map.Entry<String,Boolean> i: selected.entrySet()){

//            final AttendanceService service = new AttendanceService();

            final DocumentReference ref = db.collection("SEM").document(str1).collection("SEC")
                    .document(str2).collection("ROLL").document(i.getKey()).collection("SUBJECT").document(str3);

           // ref.update(str3,ref.update("PRESENCE",FieldValue.arrayUnion(i.getValue())));


           ref.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.e("TASK ", "OK");
                                DocumentSnapshot snapshot= task.getResult();
                                if(snapshot!=null){
                            /*for (QueryDocumentSnapshot document : task.getResult()) {

                                StudentsModel studentsModel=document.toObject(StudentsModel.class);
                                userArrayList.add(studentsModel);
                            }*/
                                    // Log.e(TAG, document.getId() + " => " + document.getData());
                                    AttendanceService  service = snapshot.toObject(AttendanceService.class);

                                    AttendanceModel attendanceModel = new AttendanceModel(FieldValue.serverTimestamp(), i.getValue());
                                    Log.e("Attendance", attendanceModel.getDATE() + " "+attendanceModel.isPRESENCE());
                                    service.getBD().add(attendanceModel);

                                    ref.set(service);




                                } else {
                                    Log.e(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        }});

           // ref.update("PRESENCE",arrayUnion(i.getValue()));






       }



    }

//
//    public void selectedRollNo(View v)
//    {
//
//        boolean checked = ((CheckBox) v).isChecked();
//
//
//    }


    private void loadDataToFirebase() {


//        if (userArrayList.size()>0)
//            userArrayList.clear();

        // db.collection("YOA")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for (QueryDocumentSnapshot querySnapshot:task.getResult()){
//
//                            user User = new user(querySnapshot.getString("Roll No"));
//                            userArrayList.add(User);
//
//                        }
//                        adapter = new MyRecycleViewAdapter(MainActivity.this,userArrayList);
//                        mRecyclerView.setAdapter(adapter);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this,"Fail to mark",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
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
                            /*for (QueryDocumentSnapshot document : task.getResult()) {
                               // Log.e(TAG, document.getId() + " => " + document.getData());
                                StudentsModel studentsModel=document.toObject(StudentsModel.class);
                                userArrayList.add(studentsModel);
                            }*/
                            customClassObject= task.getResult().toObject(CustomClass.class);

                            adapter=new MyRecycleViewAdapter(StudentsLists.this, customClassObject != null ? customClassObject.getStudentList() : null,selected );
                            mRecyclerView.setAdapter(adapter);

                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                }});

    }

 /*   private void addTestDataToFirebase() {
        Random random = new Random();
        for (int i=0;i<6;i++) {
            Map<String, String> dataMap = new HashMap<>();

            dataMap.put("rollno", "try rollno" + random.nextInt());


            db.collection("YOA")
                    .add(dataMap)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(MainActivity.this, "Roll No. Fetched", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }*/

    // private void setUpFireBase() {

    //      db = FirebaseFirestore.getInstance();

    //  }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.mRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
