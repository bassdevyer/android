package ec.torres.spotifystreamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by whoami on 6/25/15.
 */
public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

    private Context context;
    private int layoutResourceId;
    private List<RowItem> rowItems;

    public CustomListViewAdapter(Context context, int layoutResourceId, List<RowItem> rowItems){
        super(context, layoutResourceId, rowItems);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RowItem rowItem = getItem(position);

//        View row = super.getView(position, convertView, parent);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView name = (TextView) convertView.findViewById(R.id.name);

//        RowItem currentRowItem = rowItems.get(position);

        if(rowItem.getThumbnail() != null) {
            Picasso.with(context).load(rowItem.getThumbnail()).into(icon);
        }
        else{
            Picasso.with(context).load(R.mipmap.ic_launcher).into(icon);
        }
        name.setText(rowItem.getName());

        return convertView;

    }
}
