package ec.torres.spotifystreamer;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

//    private ArrayAdapter<String> mArtistAdapter;

    private CustomListViewAdapter mArtistAdapter;

    private static final String LOG_TAG = SearchFragment.class.getName();

    public SearchFragment() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // Add this line in order for this fragment to handle menu events
//        setHasOptionsMenu(true);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);


        mArtistAdapter = new CustomListViewAdapter(getActivity(), R.layout.list_item, new ArrayList<RowItem>());

        ListView listView = (ListView) rootView.findViewById(R.id.listview_artist);

        listView.setAdapter(mArtistAdapter);

        // TODO listView.setOnItemClickListener
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        RowItem rowItem = mArtistAdapter.getItem(position);
                        Intent intent = new Intent(getActivity(), ec.torres.spotifystreamer.TopActivity.class);
                        intent.putExtra(Intent.EXTRA_TEXT, rowItem.getId());
                        startActivity(intent);
                    }
                }
        );


        final SearchView searchView = (SearchView) rootView.findViewById(R.id.search_field);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


                                              // for implementing typing delay in searchview
                                              Timer timer = new Timer();
                                              final long DELAY = 1000; // in ms

                                              /**
                                               * Called when the user submits the query.
                                               * This could be due to a key press on the keyboard or due to pressing a submit button.
                                               * The listener can override the standard behavior by returning true to indicate that it has handled the submit request.
                                               * Otherwise return false to let the SearchView handle the submission by launching any associated intent.
                                               *
                                               * @param query the query text that is to be submitted
                                               * @return true if the query has been handled by the listener, false to let the SearchView perform the default action.
                                               */
                                              @Override

                                              public boolean onQueryTextSubmit(String query) {
                                                  searchView.clearFocus();
                                                  updateArtistList(query.toString());
                                                  return true;
                                              }

                                              /**
                                               * Called when the query text is changed by the user.
                                               *
                                               * @param newText the new content of the query text field.
                                               * @return false if the SearchView should perform the default action of showing any suggestions if available, true if the action was handled by the listener.
                                               */
                                              @Override
                                              public boolean onQueryTextChange(final String newText) {
                                                  // TODO timed change
                                                  // If text is changed, timer is cancelled
                                                  timer.cancel();
                                                  //instance of a new Timer
                                                  timer = new Timer();
                                                  // reset timer
                                                  timer.schedule(new TimerTask() {
                                                      @Override
                                                      public void run() {
                                                          updateArtistList(newText.toString());
                                                      }
                                                  }, DELAY);
                                                  return false;

                                              }
                                          }

        );

        /**
         * @see http://developer.android.com/reference/android/widget/TextView.html#addTextChangedListener%28android.text.TextWatcher%29
         *//*
        searchText.addTextChangedListener(
                *//**
         * @see http://developer.android.com/reference/android/text/TextWatcher.html
         *//*
                new TextWatcher() {
                    *//**
         * This method is called to notify you that, within s,
         * the count characters beginning at start are about to be replaced by new text with length after.
         * @param s
         * @param start
         * @param count
         * @param after
         *//*
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    *//**
         * This method is called to notify you that, within s,
         * the count characters beginning at start have just replaced old text that had length before.
         * @param s
         * @param start
         * @param before
         * @param count
         *//*
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        CharSequence searchCriteria =  searchText.getText();
                        if(searchCriteria == null || searchCriteria.toString().trim().length() == 0){
                            mArtistAdapter.clear();
                            return;
                        }
                        updateArtistList(searchCriteria.toString());
                    }

                    *//**
         * This method is called to notify you that,
         * somewhere within s, the text has been changed.
         * @param s
         *//*
                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );*/

        return rootView;
    }

    private void updateArtistList(String searchCriteria) {

        if (searchCriteria != null && !searchCriteria.isEmpty()) {
            FetchContentTask fetchContentTask = new FetchContentTask();
            fetchContentTask.execute(searchCriteria);
        }

    }

    public class FetchContentTask extends AsyncTask<String, Void, List<RowItem>> {


        @Override
        protected List<RowItem> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            /**
             * @see https://docs.google.com/document/d/1v4Kv5lSd8-4cs0BW6F24ccA3c1-KDQZG3EV49CUHQys/pub?embedded=true#h.vvxvgd8zz1vj
             */
            SpotifyApi api = new SpotifyApi();
            SpotifyService spotify = api.getService();
            ArtistsPager results = spotify.searchArtists(params[0]);
            Log.i(LOG_TAG, results.toString());
            List<RowItem> rowItems = new ArrayList<>();
            for (Artist artist : results.artists.items) {
                String id = artist.id;
                String name = artist.name;
                Uri thumbnail = null;
                if (artist.images.size() > 0) {
                    thumbnail = Uri.parse(artist.images.get(0).url).buildUpon().build();
                }
                RowItem rowItem = new RowItem(thumbnail, name, id);
                rowItems.add(rowItem);
            }
            return rowItems;
        }

        // Invoked on the UI thread after the background computation finishes
        @Override
        protected void onPostExecute(List<RowItem> result) {
            if (result != null && result.size() > 0) {
                mArtistAdapter.clear();
                mArtistAdapter.addAll(result);
            } else {
                Toast.makeText(getActivity(), getString(R.string.no_artist_found_message), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
