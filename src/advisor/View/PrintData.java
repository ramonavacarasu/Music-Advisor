package advisor.View;

import advisor.Config;
import advisor.SpotifyData;

import java.util.List;

public class PrintData {

    static List<SpotifyData> data;
    static int totalPages;
    static int currentPage = 1;

    public static void printData(List<SpotifyData> data) {

        PrintData.data = data;

        totalPages = (int) Math.floor(data.size() / (double) Config.ENTRIES);

        for (int i = 0; i < Config.ENTRIES; i++) {
            System.out.print(data.get(i));
        }

        System.out.println("---PAGE " + currentPage + " OF " + totalPages + "---");
    }

    public static void printPrevPage() {

        if (currentPage - 1 < 1) {

            System.out.println("No more pages.");

        } else {

            currentPage--;

            for (int i = Config.ENTRIES * (currentPage - 1); i < Config.ENTRIES * currentPage; i++) {
                System.out.print(data.get(i));
            }

            System.out.println("---PAGE " + currentPage + " OF " + totalPages + "---");
        }

    }

    public static void printNextPage() {

        if (currentPage + 1 > totalPages) {

            System.out.println("No more pages.");

        } else {

            currentPage++;

            for (int i = Config.ENTRIES * (currentPage - 1); i < Config.ENTRIES * currentPage; i++) {
                System.out.print(data.get(i));
            }

            System.out.println("---PAGE " + currentPage + " OF " + totalPages + "---");
        }

    }
}
