package boys.indecent.mypresense.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StudentsLists extends Activity {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<CustomClass> userArrayList;
    MyRecycleViewAdapter adapter;
    CustomClass cl;
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_lists);


        //Intent intent = new Intent(this,SignInActivity.class);
        // startActivity(intent);

        userArrayList = new ArrayList<CustomClass>();
        setUpRecyclerView();
        // setUpFireBase();
        //addTestDataToFirebase();
        loadDataToFirebase();



    }

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
        DocumentReference reference = FirebaseFirestore.getInstance().collection("YOA").document("2016").collection("SECTIONS")
                .document("CS4");
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot snapshot = task.getResult();
                    if(snapshot!=null){
                        Log.e("DOC",snapshot.toString() );
                       cl = task.getResult().toObject(CustomClass.class);
                          //userArrayList.add(c);
                          //userArrayList.notifyAll();
                        Log.e("Size", cl.getStudentList().get(0)+"");
                       adapter = new MyRecycleViewAdapter(StudentsLists.this,cl.getStudentList());
                        mRecyclerView.setAdapter(adapter);


                    }
                }
            }
        });





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
