package project1.muchadoaboutto_do;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joshuagoldberg on 7/6/16.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListViewHolder> {

    LinkedList<ToDoList> mToDoLists;

    public ListRecyclerViewAdapter (LinkedList<ToDoList> toDoLists){
        mToDoLists = toDoLists;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.list_to_do, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(parentView);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        final MuchAdo muchAdo = MuchAdo.getInstance();
        final ToDoList toDoList = mToDoLists.get(position);
        holder.getColorBox().setBackgroundColor(Color.parseColor(toDoList.getColor()));
        holder.getListTitle().setText(toDoList.getTitle());
        holder.getListDescription().setText(toDoList.getDescription());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete List");
                builder.setMessage("Would you like to delete the To-Do list " + toDoList.getTitle()
                        + "?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        muchAdo.removeToDo(position);
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
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return MuchAdo.getToDoLists().size();
    }
}
