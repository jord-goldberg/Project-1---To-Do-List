package project1.muchadoaboutto_do;

/**
 * Created by joshuagoldberg on 7/6/16.
 */
public class ToDoItem {

    private String mTitle;
    private String mDescription;
    private boolean mCheckBox;

    public ToDoItem() {
        mCheckBox = false;
    }

    public void setCheckBox(boolean isChecked) {
        mCheckBox = isChecked;
    }

    public boolean isBoxChecked() {
        return mCheckBox;
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
