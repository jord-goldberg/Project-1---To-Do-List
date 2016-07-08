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

import java.util.ArrayList;

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
        final TextView listName = (TextView) findViewById(R.id.list_name);
        listName.setText(muchAdo.getToDoLists().get(position).getTitle());
        listName.setTextColor(Color.parseColor(toDoList.getColor()));

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
                        ToDoItem newItem = new ToDoItem();
                        EditText title = (EditText) dialogView.findViewById(R.id.item_title_entry);
                        EditText description = (EditText) dialogView.
                                findViewById(R.id.item_description_entry);
                        if (title.getText().toString().trim().length() == 0) {
                            title.setError("Every Item needs a name! Enter one");
                        } else if (title.getText().toString().trim().length() > 36 &&
                                !title.getText().toString().contains("\n")) {
                            title.setError("Item name too long. Put info into description");
                        } else if (title.getText().toString().contains("\n")) {
                            String[] items = title.getText().toString().split("\n");
                            for (int i = 0; i < items.length; i++){
                                toDoList.addToDoItem(new ToDoItem());
                                toDoList.getToDoItem(toDoList.getToDoItemList().size()-1)
                                        .setTitle(items[i].trim());
                                toDoList.getToDoItem(toDoList.getToDoItemList().size()-1)
                                        .setDescription(description.getText().toString().trim());
                                itemRecyclerViewAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }

                        } else {
                            newItem.setTitle(title.getText().toString().trim());
                            newItem.setDescription(description.getText().toString().trim());
                            toDoList.addToDoItem(newItem);
                            itemRecyclerViewAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

//Allows the colorbox and title TextView to change colors, but the colorbox only changes after
// a new list is made in the home activity, which notifies the adapter that the dataset was updated

        final String[] colorChoices = new String[4];
        colorChoices[0] = "Red";
        colorChoices[1] = "Orange";
        colorChoices[2] = "Blue";
        colorChoices[3] = "Green";
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("Pick a color");
                builder.setItems(colorChoices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                toDoList.setColor("#be0000");
                                listName.setTextColor(Color.parseColor(toDoList.getColor()));
                                break;
                            case 1:
                                toDoList.setColor("#ff6600");
                                listName.setTextColor(Color.parseColor(toDoList.getColor()));
                                break;
                            case 2:
                                toDoList.setColor("#007fff");
                                listName.setTextColor(Color.parseColor(toDoList.getColor()));
                                break;
                            default:
                                toDoList.setColor("#37ae5f");
                                listName.setTextColor(Color.parseColor(toDoList.getColor()));
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
        listName.setOnClickListener(onClickListener);
    }
}
