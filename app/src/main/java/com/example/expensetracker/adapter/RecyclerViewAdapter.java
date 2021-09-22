package com.example.expensetracker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensetracker.MainActivity;
import com.example.expensetracker.R;
import com.example.expensetracker.data.MyDbHandler;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.updateDeleteActivity;
import com.example.expensetracker.utils.SpacingItemDecorator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Expense> expenseList;
    MyDbHandler db = new MyDbHandler(context);
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
        String check_for_in_and_out = expense.getCheck_for_in_out();
        holder.expenseDetail.setText(expense.getDetail());
        holder.expenseAmount.setText("₹ "+expense.getAmount());
//        holder.checkForUpdate.setText(expense.getCheck_for_update());
//        holder.checkForInOut.setText(check_for_in_and_out);
        holder.expenseHr.setText(expense.getHour()+":");
        holder.expenseMin.setText(expense.getMin());
        holder.expenseDate.setText(expense.getDate()+"-");
        holder.expenseMonth.setText(expense.getMonth()+"-");
        holder.expenseYear.setText(expense.getYear());
        int x = expense.getId();



//        holder.expenseId.setText(String.valueOf(x));


//        holder.expenseId.setText(String.valueOf(currentTime));


        if(check_for_in_and_out.equals("In"))
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#99ffb3"));
        }
        else
        {
            Log.d("dbFirst", "data: " + x + check_for_in_and_out +x);
            holder.itemView.setBackgroundColor(Color.parseColor("#ffb3b3"));
        }



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
        public TextView expenseHr;
        public TextView expenseMin;
        public TextView expenseDate;
        public TextView expenseMonth;
        public TextView expenseYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            expenseDetail = itemView.findViewById(R.id.textView);
            expenseAmount = itemView.findViewById(R.id.textView2);
            expenseHr = itemView.findViewById(R.id.text_hr);
            expenseMin = itemView.findViewById(R.id.text_min);
            expenseDate = itemView.findViewById(R.id.text_date);
            expenseMonth = itemView.findViewById(R.id.text_month);
            expenseYear = itemView.findViewById(R.id.text_year);
        }

        @Override
        public void onClick(View view) {
            int position = this.getBindingAdapterPosition();
            Expense expense = expenseList.get(position);
            Log.d("dbFirst", "an item is clicked");
            int id_to_update_row = expense.getId();
            itemView.setBackgroundColor(Color.parseColor("#808080"));
            Intent intent = new Intent(context, updateDeleteActivity.class);
            intent.putExtra("Rindex",Integer.toString(id_to_update_row));
            context.startActivity(intent);
        }

    }
}
