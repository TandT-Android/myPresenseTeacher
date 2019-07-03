package boys.indecent.mypresense.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class Report extends AppCompatActivity {

     int  totalPresent,totalAbsent,totalStudent;
     //TextView t1,t2,t3;
     TextView totalStudents,totalPresents,totalAbsents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent intent=getIntent();
        totalPresent =intent.getIntExtra("PRESENCE",0);
        totalAbsent=intent.getIntExtra("ABSENT",0);
        totalStudent =intent.getIntExtra("TOTAL",0);

       // Toast.makeText(Report.this,totalAbsent, Toast.LENGTH_SHORT).show();


        totalStudents=findViewById(R.id.totalstudents);
        totalPresents=findViewById(R.id.totalpresents);
        totalAbsents=findViewById(R.id.totalabsents);

        totalStudents.setText(String.valueOf(totalStudent));
        totalPresents.setText(String.valueOf(totalPresent));
        totalAbsents.setText(String.valueOf(totalAbsent));


    }
}
