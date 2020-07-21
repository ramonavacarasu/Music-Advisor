package advisor;

import advisor.Controller.GetCategories;
import advisor.Controller.GetFeatures;
import advisor.Controller.GetNew;
import advisor.Controller.GetPlaylist;
import advisor.View.PrintData;

import java.util.Scanner;


public class MusicAdvisor {

    boolean adviceOn = false;

    void advise() throws Exception {
        adviceOn = true;
        chooseCommand();
    }

    void chooseCommand() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNext("auth")) {
            if (scanner.nextLine().equals("exit")) {
                System.out.println("---GOODBYE!---");
                adviceOn = false;
                break;
            }
            System.out.println("Please, provide access for application.");
        }

        while (adviceOn) {
            String input = scanner.nextLine().trim();
            String command = input.contains(" ") ? input.substring(0, input.indexOf(" ")) : input;

            switch(command) {
                case "new":
                    GetNew.getNew();
                    break;
                case "featured":
                    GetFeatures.getFeatures();
                    break;
                case "categories":
                    GetCategories.getCategories();
                    break;
                case "playlists":
                    String categoryName = input.substring(input.indexOf(" ") + 1);
                    GetPlaylist.getPlaylist(categoryName);
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    adviceOn = false;
                    break;
                case "auth":
                    Authorization auth = new Authorization();
                    auth.createHttpServer();
                    auth.authRequest();
                    break;
                case "prev":
                    PrintData.printPrevPage();
                    break;
                case "next":
                    PrintData.printNextPage();
                    break;
                default:
                    System.out.println("Incorrect command. Try again.");
            }
        }
    }

}