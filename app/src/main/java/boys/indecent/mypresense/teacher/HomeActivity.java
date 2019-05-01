package boys.indecent.mypresense.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class HomeActivity extends AppCompatActivity{

    Button b1,b2;
    Spinner s1,s2,s3;
    String str1,str2,str3;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b1=findViewById(R.id.ViewAttendence);
        b2=findViewById(R.id.MarkAttendence);

        s1=findViewById(R.id.spinner1);
        ArrayAdapter <CharSequence> adapter1 =ArrayAdapter.createFromResource(this,R.array.SEMESTER,android.R.layout.simple_spinner_item) ;
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter((adapter1));


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 str1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(HomeActivity.this,str1,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        s2=findViewById(R.id.spinner2);
        ArrayAdapter <CharSequence> adapter2 =ArrayAdapter.createFromResource(this,R.array.SECTIONS,android.R.layout.simple_spinner_item) ;
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter((adapter2));
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(HomeActivity.this,str2,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        s3=findViewById(R.id.spinner3);
        ArrayAdapter <CharSequence> adapter3 =ArrayAdapter.createFromResource(this,R.array.SUBJECTS,android.R.layout.simple_spinner_item) ;
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter((adapter3));
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 str3 = parent.getItemAtPosition(position).toString();
                Toast.makeText(HomeActivity.this,str3,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentsLists();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAttendance();
            }
        });

    }





    public void openStudentsLists()
    {

        Intent intent = new Intent(this,StudentsLists.class);
        intent.putExtra("SEMESTER",str1);
        intent.putExtra("SECTION",str2);
        intent.putExtra("SUBJECT",str3);

        startActivity(intent);



    }



    public void viewAttendance()
    {
        Intent intent = new Intent(this,ViewAttendance.class);
        startActivity(intent);


    }

}
