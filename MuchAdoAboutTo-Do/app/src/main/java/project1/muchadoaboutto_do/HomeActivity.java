package project1.muchadoaboutto_do;

import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
 //       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
 //       setSupportActionBar(toolbar);

//Creates an instance of my singleton

        final MuchAdo muchAdo = MuchAdo.getInstance();

//Instantiates the recyclerview and sets up a vertical linear layout manager

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.home_recycler);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//Instantiates an adapter and sets it to the recyclerview

        final ListRecyclerViewAdapter listRecyclerViewAdapter = new ListRecyclerViewAdapter(muchAdo.getToDoLists());
        recyclerView.setAdapter(listRecyclerViewAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                final View dialogView = LayoutInflater.from(HomeActivity.this).
                        inflate(R.layout.new_list_dialog, null);
                builder.setView(dialogView)
                        .setPositiveButton("Create List", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ToDoList newList = new ToDoList();
                                EditText title = (EditText) dialogView.
                                        findViewById(R.id.list_title_entry);
                                EditText description = (EditText) dialogView.
                                        findViewById(R.id.list_description_entry);
                                newList.setTitle(title.getText().toString());
                                newList.setDescription(description.getText().toString());
                                muchAdo.addToDo(newList);
                                listRecyclerViewAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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