package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


//Responsible for displaying from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnLongClickListener{
        void onItemLongItemClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;


    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
//        ItemsAdapter itemsAdapter = new ItemsAdapter(items, longClickListener);
        this.items = items;
        this.longClickListener = longClickListener;
    }

//    @NonNull
    @Override
    //Creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it inside a view Holder and return it
        return new ViewHolder(todoView);
    }

    //Taking data at a particular position and putting it in side the view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at position
        String item = items.get(position);
        //Bind the item into the specified view holder
        holder.bind(item);
    }

    //Number of items in the data
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             tvItem = itemView.findViewById(android.R.id.text1);
         }

         //Update the view inside the view holder with this data
        public void bind(String item) {
             tvItem.setText(item);
             tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View view) {
                     longClickListener.onItemLongItemClicked(getAdapterPosition());
                     return true;
                 }
             });
        }

    }
}
