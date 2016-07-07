package project1.muchadoaboutto_do;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by joshuagoldberg on 7/6/16.
 */
public class ListViewHolder extends RecyclerView.ViewHolder {

    private ImageView mColorBox;
    private TextView mListTitle;
    private TextView mListDescription;


    public ListViewHolder(View itemView) {
        super(itemView);

        mColorBox = (ImageView) itemView.findViewById(R.id.colorbox);
        mListTitle = (TextView) itemView.findViewById(R.id.list_title);
        mListDescription = (TextView) itemView.findViewById(R.id.list_description);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        itemView.setOnClickListener(onClickListener);
    }

    public ImageView getColorBox() {
        return mColorBox;
    }

    public TextView getListTitle() {
        return mListTitle;
    }

    public TextView getListDescription() {
        return mListDescription;
    }
}
