package com.example.expensetracker.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensetracker.R;
import com.example.expensetracker.model.Expense;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Expense> expenseList;

    public RecyclerViewAdapter(Context context, List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }


    // Where to get the single card as Viewholder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }


    // What will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // khali card aayega ek(blank single row aayega)
        Expense expense = expenseList.get(position);

        holder.expenseDetail.setText(expense.getDetail());
        holder.expenseAmount.setText(expense.getAmount());
        holder.checkForUpdate.setText(expense.getCheck_for_update());
        holder.checkForInOut.setText(expense.getCheck_for_in_out());

    }


    // How may items are there
    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        public TextView expenseId;
        public TextView expenseDetail;
        public TextView expenseAmount;
        public TextView checkForUpdate;
        public TextView checkForInOut;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            expenseDetail = itemView.findViewById(R.id.textView);
            expenseAmount = itemView.findViewById(R.id.textView2);
            checkForUpdate = itemView.findViewById(R.id.textView3);
            checkForInOut = itemView.findViewById(R.id.textView4);


        }

        @Override
        public void onClick(View view) {
            Log.d("dbFirst", "an item is clicked");
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
