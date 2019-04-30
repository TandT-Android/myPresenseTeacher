package boys.indecent.mypresense.teacher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewHolder> {

    Context c;
    private ArrayList<String> userArrayList;
    private Map<String, Boolean> selected;

    /*MyRecycleViewAdapter(Context c, ArrayList<String> userArrayList, Map<String, Boolean> selected) {
        this.c=c;
        this.userArrayList = userArrayList;
        this.selected = selected;
    }*/

    MyRecycleViewAdapter(Context c, ArrayList<String> userArrayList, Map<String, Boolean> selected) {
        this.c=c;
        this.userArrayList = userArrayList;
        this.selected = selected;
    }

    @NonNull
    @Override
    public MyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View view = layoutInflater.inflate(R.layout.single_row, viewGroup,false);


        return new MyRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewHolder myRecycleViewHolder, @SuppressLint("RecyclerView") final int i) {

        myRecycleViewHolder.mRollno.setText(userArrayList.get(i));
        final CheckBox ch = myRecycleViewHolder.mcheck;

        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selected.put(userArrayList.get(i), true);
                }else {
                    selected.put(userArrayList.get(i), false);
                }
            }
        });

//        if(ch.isChecked()){
//            selected.put(userArrayList.get(i), true);
//        }else{
//            selected.put(userArrayList.get(i), false);
//        }
    }




    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

}
