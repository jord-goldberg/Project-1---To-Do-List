package project1.muchadoaboutto_do;

import java.util.LinkedList;

/**
 * Created by joshuagoldberg on 7/6/16.
 */
public class MuchAdo {

    private static MuchAdo mMuchAdo = null;
    private static LinkedList<ToDoList> mToDoLists = new LinkedList<>();

    private MuchAdo(){
        mToDoLists = new LinkedList<ToDoList>();
    }

    public static MuchAdo getInstance(){
        if(mMuchAdo == null){
            mMuchAdo = new MuchAdo();
        }
        return mMuchAdo;
    }

    public LinkedList<ToDoList> getToDoLists() {
        return mToDoLists;
    }

    public void setToDoLists(LinkedList<ToDoList> toDoList) {
        mToDoLists = toDoList;
    }

    public void addToDo(ToDoList toDoList){
        mToDoLists.add(toDoList);
    }

    public void removeToDo(int position){
        mToDoLists.remove(position);
    }
}
