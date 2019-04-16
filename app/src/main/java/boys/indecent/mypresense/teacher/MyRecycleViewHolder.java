package boys.indecent.mypresense.teacher;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


    public class MyRecycleViewHolder extends RecyclerView.ViewHolder {


        public TextView mRollno;
        public CheckBox mcheck;




        public MyRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            mRollno=itemView.findViewById(R.id.rollno);
            mcheck=itemView.findViewById(R.id.check);
        }
    }


