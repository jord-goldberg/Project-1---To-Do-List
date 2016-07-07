package project1.muchadoaboutto_do;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by joshuagoldberg on 7/7/16.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    LinkedList<ToDoItem> mToDoItems;

    public ItemRecyclerViewAdapter (LinkedList<ToDoItem> toDoItems) {
        mToDoItems = toDoItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.item_to_do, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(parentView);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final ToDoItem toDoItem = mToDoItems.get(position);
        holder.getCheckBox().setChecked(toDoItem.isBoxChecked());
        holder.getItemTitle().setText(toDoItem.getTitle());
        holder.getItemDescription().setText(toDoItem.getDescription());

//Sets onLongClickListener to allow deletion of items

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete Item");
                builder.setMessage("Would you like to delete the To-Do item \"" +
                        toDoItem.getTitle() + "\"?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mToDoItems.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

        holder.getCheckBox().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getCheckBox().isChecked()){
                    mToDoItems.get(position).setCheckBox(true);
                    Toast.makeText(view.getContext(), "You did it! Go you!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mToDoItems.get(position).setCheckBox(false);
                    Toast.makeText(view.getContext(), "Reminder: Long-click to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDoItems.size();
    }
}
