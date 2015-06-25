package ec.torres.spotifystreamer;

import android.net.Uri;

/**
 * Created by whoami on 6/25/15.
 */
public class RowItem {
    private Uri thumbnail;
    private String name;
    private String id;

    public RowItem(Uri thumbnail, String name, String id) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.id = id;
    }

    public Uri getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Uri thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
