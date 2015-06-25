package ec.torres.spotifystreamer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class TopFragment extends Fragment {

    private ArrayAdapter<String> mTopAdapter;

    private static final String LOG_TAG = TopFragment.class.getName();

    public TopFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top, container, false);

        String[] topArray = {
                "Today - Sunny - 88/63",
                "Tomorrow - Foggy - 70/46",
                "Weds - Cloudy - 72/63",
                "Thurs - Rainy - 64/51",
                "Fri - Foggy - 70/46",
                "Sat - Sunny - 76/68",
                "Sun - Asteroids - 10/5",
                "Today - Sunny - 88/63",
                "Tomorrow - Foggy - 70/46",
                "Weds - Cloudy - 72/63",
        };
        List<String> topList = new ArrayList<>(Arrays.asList(topArray ));

        mTopAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item,
                R.id.name,
                topList);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_top);

        listView.setAdapter(mTopAdapter);

        // TODO listView.setOnItemClickListener

        return rootView;
    }
}
