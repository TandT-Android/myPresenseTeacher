package boys.indecent.mypresense.teacher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewHolder> {

    Context c;
    private ArrayList<String> userArrayList;

    MyRecycleViewAdapter(Context c, ArrayList<String> userArrayList) {
        this.c=c;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View view = layoutInflater.inflate(R.layout.single_row, viewGroup,false);


        return new MyRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewHolder myRecycleViewHolder, int i) {


        myRecycleViewHolder.mRollno.setText(userArrayList.get(i));



    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

}
