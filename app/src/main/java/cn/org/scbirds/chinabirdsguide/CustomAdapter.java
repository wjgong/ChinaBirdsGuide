package cn.org.scbirds.chinabirdsguide;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by gonggwen on 2017/5/10.
 */

public class CustomAdapter extends CursorAdapter {
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // FInd fields to populate in inflated template
        TextView tvId = (TextView) view.findViewById(R.id.birdId);
        TextView tvName = (TextView) view.findViewById(R.id.birdName);
        // Extract properties from cursor
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(Bird.KEY_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(Bird.KEY_NAME_CN));
        // Populate fields with extracted properties
        tvId.setText(String.valueOf(id));
        tvName.setText(name);
    }
}
