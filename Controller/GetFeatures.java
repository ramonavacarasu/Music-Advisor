package advisor.Controller;

import advisor.Config;
import advisor.SpotifyData;
import advisor.View.PrintData;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetFeatures extends GetRequest {

    public static void getFeatures() {
        String path = Config.RESOURCE + "/v1/browse/featured-playlists";
        HttpRequest request = getRequest(path);
        List<SpotifyData> data = null;

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            data = addPlaylists(response);
        } catch (InterruptedException | IOException e) { System.out.println("Error response"); }

        assert data != null;

        PrintData.printData(data);
    }


}