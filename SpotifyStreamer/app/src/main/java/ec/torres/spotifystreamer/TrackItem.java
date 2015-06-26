package ec.torres.spotifystreamer;

import android.net.Uri;

/**
 * Created by whoami on 6/25/15.
 */
public class TrackItem {

    private String trackName;
    private String albumName;
    private Uri albumArtThumbnailLarge;
    private Uri albumArtThumbnailSmall;
    private Uri previewUrl;

    public TrackItem(String trackName, String albumName, Uri albumArtThumbnailLarge, Uri albumArtThumbnailSmall, Uri previewUrl) {
        this.trackName = trackName;
        this.albumName = albumName;
        this.albumArtThumbnailLarge = albumArtThumbnailLarge;
        this.albumArtThumbnailSmall = albumArtThumbnailSmall;
        this.previewUrl = previewUrl;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Uri getAlbumArtThumbnailLarge() {
        return albumArtThumbnailLarge;
    }

    public void setAlbumArtThumbnailLarge(Uri albumArtThumbnailLarge) {
        this.albumArtThumbnailLarge = albumArtThumbnailLarge;
    }

    public Uri getAlbumArtThumbnailSmall() {
        return albumArtThumbnailSmall;
    }

    public void setAlbumArtThumbnailSmall(Uri albumArtThumbnailSmall) {
        this.albumArtThumbnailSmall = albumArtThumbnailSmall;
    }

    public Uri getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(Uri previewUrl) {
        this.previewUrl = previewUrl;
    }
}
