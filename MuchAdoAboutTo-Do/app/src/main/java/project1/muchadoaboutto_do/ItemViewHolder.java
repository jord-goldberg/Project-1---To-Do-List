package project1.muchadoaboutto_do;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by joshuagoldberg on 7/7/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mItemTitle;
    private TextView mItemDescription;
    private CheckBox mCheckBox;

    public ItemViewHolder(View itemView) {
        super(itemView);

        mItemTitle = (TextView) itemView.findViewById(R.id.item_title);
        mItemDescription = (TextView) itemView.findViewById(R.id.item_description);
        mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        itemView.setOnClickListener(onClickListener);
    }

    public TextView getItemTitle() {
        return mItemTitle;
    }

    public TextView getItemDescription() {
        return mItemDescription;
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }
}
