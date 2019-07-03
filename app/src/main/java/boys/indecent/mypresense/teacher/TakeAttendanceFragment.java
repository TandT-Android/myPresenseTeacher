package boys.indecent.mypresense.teacher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TakeAttendanceFragment extends Fragment {

    Button b1,b2;
    Spinner s1,s2,s3;
    String str1,str2,str3;

    public TakeAttendanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_take_attendance, container, false);
        b1=view.findViewById(R.id.ViewAttendence);
        b2=view.findViewById(R.id.MarkAttendence);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StudentsLists.class));
            }
        });

        s1=view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(getActivity(),R.array.SEMESTER,android.R.layout.simple_spinner_item) ;
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter((adapter1));


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(),str1,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        s2=view.findViewById(R.id.spinner2);
        ArrayAdapter <CharSequence> adapter2 =ArrayAdapter.createFromResource(getActivity(),R.array.SECTIONS,android.R.layout.simple_spinner_item) ;
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter((adapter2));
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(),str2,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        s3=view.findViewById(R.id.spinner3);
        ArrayAdapter <CharSequence> adapter3 =ArrayAdapter.createFromResource(getActivity(),R.array.SUBJECTS,android.R.layout.simple_spinner_item) ;
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter((adapter3));
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str3 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(),str3,Toast.LENGTH_SHORT).show();


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

        return view;
    }






    public void openStudentsLists()
    {

        Intent intent = new Intent(getActivity(),StudentsLists.class);
        intent.putExtra("SEMESTER",str1);
        intent.putExtra("SECTION",str2);
        intent.putExtra("SUBJECT",str3);

        startActivity(intent);
    }



    public void viewAttendance()
    {
        Intent intent = new Intent(getActivity(),ViewAttendance.class);
        startActivity(intent);


    }
}
