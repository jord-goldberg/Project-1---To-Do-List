package project1.muchadoaboutto_do;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//Receives the position of the list that was clicked so that the right toDoItems are opened

        Intent intent = getIntent();
        final int position = intent.getIntExtra("toDoList", 0);

        final MuchAdo muchAdo = MuchAdo.getInstance();
        final ToDoList toDoList = muchAdo.getToDoLists().get(position);
        TextView listName = (TextView) findViewById(R.id.list_name);
        listName.setText(muchAdo.getToDoLists().get(position).getTitle());
        listName.setTextColor(Color.parseColor(muchAdo.getToDoLists().get(position).getColor()));

//Instantiates the recyclerview and sets up a vertical linear layout manager

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_recycler);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//Instantiates an adapter to adapt the appropriate toDoItems and sets it to the recyclerview

        final ItemRecyclerViewAdapter itemRecyclerViewAdapter =
                new ItemRecyclerViewAdapter(toDoList.getToDoItemList());
        recyclerView.setAdapter(itemRecyclerViewAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_list);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                final View dialogView = LayoutInflater.from(ListActivity.this).
                        inflate(R.layout.new_item_dialog, null);
                builder.setView(dialogView);
                final ToDoItem newItem = new ToDoItem();
                final EditText title = (EditText) dialogView.findViewById(R.id.item_title_entry);
                final EditText description = (EditText) dialogView.
                        findViewById(R.id.item_description_entry);
                builder.setPositiveButton("Create To-Do", null)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                final AlertDialog dialog = builder.create();
                dialog.show();

                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (title.getText().length() == 0) {
                            title.setError("Every Item needs a name! Enter one");
                        } else if (title.getText().length() > 36) {
                            title.setError("Item name too long. Put info into description");
                        } else if (title.getText().toString().contains("\n")) {
                            title.setError("Keep the item name to a single line!");
                        } else {
                            newItem.setTitle(title.getText().toString());
                            newItem.setDescription(description.getText().toString());
                            toDoList.addToDoItem(newItem);
                            itemRecyclerViewAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }

                });
            }

        });

    }
}
