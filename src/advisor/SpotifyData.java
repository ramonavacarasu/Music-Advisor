package advisor;

public class SpotifyData {

    String album = null;
    String artists = null;
    String category = null;
    String link = null;

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtists(String authors) {
        this.artists = authors;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {

        StringBuilder info = new StringBuilder();

        if (album != null) {
            info.append(album).append("\n"); }
        if (artists != null) {
            info.append(artists).append("\n");
        }
        if (link != null) {
            info.append(link).append("\n");
        }
        if (category != null) {
            info.append(category).append("\n");
        }

        return info.toString().replaceAll("\"", "");
    }
}