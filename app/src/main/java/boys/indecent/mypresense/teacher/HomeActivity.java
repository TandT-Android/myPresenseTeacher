package boys.indecent.mypresense.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b1=findViewById(R.id.viewAttendence);
        b2=findViewById(R.id.markAttendance);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentsLists();
            }
        });

    }

    public void openStudentsLists()
    {

        Intent intent = new Intent(this,StudentsLists.class);
         startActivity(intent);


    }


}
