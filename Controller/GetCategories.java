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

public class GetCategories extends GetRequest {

    public static void getCategories() {

        String path = Config.RESOURCE + "/v1/browse/categories";
        List<SpotifyData> data = new ArrayList<>();

        HttpRequest request = getRequest(path);

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject categories = jo.getAsJsonObject("categories");

            for (JsonElement item : categories.getAsJsonArray("items")) {
                SpotifyData elem = new SpotifyData();
                elem.setCategory(item.getAsJsonObject().get("name").toString());
                data.add(elem);
                //data.add(item.getAsJsonObject().get("name").getAsString());

            }

        } catch (InterruptedException | IOException e) { System.out.println("Error response"); }

        PrintData.printData(data);
        /*for (String d : data) {
            System.out.println(d);
        }*/
    }
}