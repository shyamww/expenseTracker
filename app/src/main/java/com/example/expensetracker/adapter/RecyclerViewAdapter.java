package com.example.expensetracker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        holder.expenseDetail.setText(expense.getDetail());
        holder.expenseAmount.setText(expense.getAmount());
        holder.checkForUpdate.setText(expense.getCheck_for_update());
        holder.checkForInOut.setText(expense.getCheck_for_in_out());
        int x = expense.getId();
        holder.expenseId.setText(Integer.toString(x));

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
        public TextView expenseId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            expenseDetail = itemView.findViewById(R.id.textView);
            expenseAmount = itemView.findViewById(R.id.textView2);
            checkForUpdate = itemView.findViewById(R.id.textView3);
            checkForInOut = itemView.findViewById(R.id.textView4);
            expenseId = itemView.findViewById(R.id.textView5);


        }

        @Override
        public void onClick(View view) {
            int position = this.getBindingAdapterPosition();
            Expense expense = expenseList.get(position);
            Log.d("dbFirst", "an item is clicked");
            int id_to_update_row = expense.getId();
            Toast.makeText(context, "clicked " + position, Toast.LENGTH_SHORT).show();

//            String row_data = expenses.get(i);
//                String a = row_data.contains(" ") ? row_data.split(" ")[0] : row_data;
                int id_of_this_row_in_db =expense.getId();
//                db.make_check_column_yes_for_delete(id_of_this_row_in_db);
////                Toast.makeText(MainActivity.this, row_data, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, updateDeleteActivity.class);
            intent.putExtra("Rindex",Integer.toString(position));
//            try {
//                //  Block of code to try
////                db.make_check_column_yes_for_delete(id_to_update_row);
//                db.make_check_column_yes_for_delete(id_to_update_row);
//                Log.d("dbFirst", "fetched data ");
//
//            }
//            catch(Exception e) {
//                //  Block of code to handle errors
//                Log.d("dbFirst", "e: "+ e);
//            }

            context.startActivity(intent);
//            ((Activity) context).startActivityForResult(intent,((Activity) context).RESULT_OK);
        }




//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//            if (requestCode == 1001) {
//                Toast.makeText(context, "Hello back", Toast.LENGTH_SHORT).show();
//            }
//        }

    }
}
