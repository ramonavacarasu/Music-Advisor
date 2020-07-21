package advisor.Controller;

import advisor.Config;
import advisor.SpotifyData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GetRequest {

    static HttpRequest getRequest(String path) {

        return HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Config.ACCESS_TOKEN)
                .uri(URI.create(path))
                .GET()
                .build();

    }

    static List<SpotifyData> addPlaylists(HttpResponse<String> response) {

        JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject playlists = jo.getAsJsonObject("playlists");
        List<SpotifyData> data = new ArrayList<>();

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            SpotifyData elem = new SpotifyData();

            elem.setAlbum(item.getAsJsonObject().get("name").toString());
            elem.setLink(item.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString());

            data.add(elem);

        }

        return data;
    }

}