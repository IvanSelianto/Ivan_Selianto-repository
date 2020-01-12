import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RuTracker {
    private String searchRequest = "Irishman";


    public void getHtmlCode1() throws IOException {
        String inputLine;
        URL ruTracker = new URL("https://rutracker.org/forum/tracker.php?nm=" + searchRequest);
        BufferedReader in = new BufferedReader(new InputStreamReader(ruTracker.openConnection().getInputStream(), "cp1251"));
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void getHtmlCode2() throws IOException {


        String link = "https://rutracker.org/forum/tracker.php?nm=" + searchRequest;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(link).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(2500);
            connection.setReadTimeout(2500);
            connection.connect();

            StringBuilder sb = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line)
                            .append("\n");


                }
                FileWriter fileWriter = new FileWriter("output.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(sb.toString());


                System.out.println(sb);


            } else {
                System.out.println("fail" + connection.getResponseCode() + " " + connection.getResponseMessage());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }

    }

    public void getHtmlCode3() throws IOException {
        Document doc = Jsoup.connect("https://rutracker.org/forum/tracker.php?nm=" + searchRequest).get();
        System.out.println(doc);
    }
}

