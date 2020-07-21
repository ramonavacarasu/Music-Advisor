package advisor.Controller;

import advisor.Config;
import advisor.SpotifyData;
import advisor.View.PrintData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GetNew extends GetRequest {

    public static void getNew() {
        List<SpotifyData> data = new ArrayList<>();
        String path = Config.RESOURCE + "/v1/browse/new-releases";
        HttpRequest request = getRequest(path);

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject categories = jo.getAsJsonObject("albums");
            //System.out.println(categories);


            //direct way

            /*List<String> totalArtists = new ArrayList<>();

            for (JsonElement j : categories.getAsJsonArray("items")) {

                //System.out.println(j.getAsJsonObject().get("name").getAsString()); // album name

                ArrayList<String> artistNames = new ArrayList<>();

                for (JsonElement artistName : j.getAsJsonObject().getAsJsonArray("artists")) {

                    artistNames.add(artistName.getAsJsonObject().get("name").getAsString());
                }

                totalArtists.add(artistNames.toString());

                //System.out.println(j.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString() + "\n");
            }
            System.out.println(totalArtists.toString());*/


            for (JsonElement j : categories.getAsJsonArray("items")) {
                SpotifyData elem = new SpotifyData();

                elem.setAlbum(j.getAsJsonObject().get("name").toString()); // album name


                //ArrayList variant
                ArrayList<String> artistNames = new ArrayList<>();

                for (JsonElement name : j.getAsJsonObject().getAsJsonArray("artists")) {
                    //System.out.println(Collections.singletonList(j.getAsJsonObject().getAsJsonArray("artists")));
                    //System.out.println();
                    artistNames.add(name.getAsJsonObject().get("name").getAsString());
                }

                elem.setArtists(artistNames.toString());


                //StringBuilder variant
                /*StringBuilder artists = new StringBuilder("[");
                for (JsonElement name : item.getAsJsonObject().getAsJsonArray("artists")) {
                    System.out.println(Collections.singletonList(item.getAsJsonObject().getAsJsonArray("artists")));
                    System.out.println();

                    if (!artists.toString().endsWith("[")) {
                        artists.append(", ");
                    }
                    artists.append(name.getAsJsonObject().get("name"));
                }

                elem.setArtists(artists.append("]").toString());*/


                elem.setLink(j.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString());
                data.add(elem);
            }

        } catch (InterruptedException | IOException e) { System.out.println("Error response"); }

        PrintData.printData(data);
    }
}
