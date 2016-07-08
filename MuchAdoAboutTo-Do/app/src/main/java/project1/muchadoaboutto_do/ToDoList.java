package project1.muchadoaboutto_do;

import java.util.LinkedList;

/**
 * Created by joshuagoldberg on 7/6/16.
 */
public class ToDoList {

    private String mTitle = "";
    private String mDescription = "";
    private String mColor = "";
    private LinkedList<ToDoItem> mToDoItems;

    public ToDoList(){
        mToDoItems = new LinkedList<ToDoItem>();
        mColor = "#be0000";
    }

    public String getColor(){
        return mColor;
    }

    public void setColor(String colorHex){
        mColor = colorHex;
    }

    public void addToDoItem(ToDoItem toDoItem){
        mToDoItems.add(toDoItem);
    }

    public ToDoItem getToDoItem(int position) {
        return mToDoItems.get(position);
    }

    public LinkedList<ToDoItem> getToDoItemList() {
        return mToDoItems;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
