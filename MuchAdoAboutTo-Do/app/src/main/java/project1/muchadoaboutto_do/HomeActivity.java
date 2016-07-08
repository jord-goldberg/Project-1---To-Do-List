package project1.muchadoaboutto_do;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Much A-Do About To-Do");
        //       setSupportActionBar(toolbar);

//Creates an instance of my singleton

        final MuchAdo muchAdo = MuchAdo.getInstance();

//Instantiates the recyclerview and sets up a vertical linear layout manager

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.home_recycler);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//Instantiates an adapter and sets it to the recyclerview

        final ListRecyclerViewAdapter listRecyclerViewAdapter =
                new ListRecyclerViewAdapter(muchAdo.getToDoLists());
        recyclerView.setAdapter(listRecyclerViewAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listRecyclerViewAdapter.notifyDataSetChanged();
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                final View dialogView = LayoutInflater.from(HomeActivity.this).
                        inflate(R.layout.new_list_dialog, null);
                builder.setView(dialogView);
                final ToDoList newList = new ToDoList();
                final EditText title = (EditText) dialogView.findViewById(R.id.list_title_entry);
                final EditText description = (EditText) dialogView.
                        findViewById(R.id.list_description_entry);
                builder.setPositiveButton("Create List", null)
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
                            title.setError("Every List needs a title! Enter one");
                        } else if (title.getText().length() > 36) {
                            title.setError("Title too long. Put info into description");
                        } else if(title.getText().toString().contains("\n")){
                            title.setError("Keep the title to a single line!");
                        }
                        else {
                            newList.setTitle(title.getText().toString());
                            newList.setDescription(description.getText().toString());
                            muchAdo.addToDo(newList);
                            listRecyclerViewAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        if(muchAdo.getToDoLists().size() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Welcome to Much A-Do About To-Do!");
            builder.setMessage("I see you have no To-Do lists yet. \nI'll help you get started."+
                    "\n \n - Use the pink button to create new To-Do lists"+
                    "\n - Click on your existing lists to access them\n - Long click on any list "+
                    "or item to delete it\n - Use your back button to navigate back");
            builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}