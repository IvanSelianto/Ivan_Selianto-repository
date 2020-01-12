import okhttp3.*;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.spn.crawler.rutracker.RutrackerCrawler;
import pw.spn.crawler.rutracker.exception.RutrackerCrawlerException;
import pw.spn.crawler.rutracker.http.RutrackerHttpService;
import pw.spn.crawler.rutracker.http.RutrackerRequestInterceptor;
import pw.spn.crawler.rutracker.model.RutrackerLink;
import pw.spn.crawler.rutracker.model.RutrackerTopic;

import javax.swing.text.html.parser.Element;
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static javax.xml.transform.OutputKeys.ENCODING;

public class Main extends RutrackerHttpService {

    private static final String BASE_URL = "http://rutracker.org/forum/";
    private static final String INDEX_URL = BASE_URL + "index.php";
    private static final String LOGIN_URL = BASE_URL + "login.php";
    private static final String SEARCH_URL = BASE_URL + "tracker.php";
    private static final String ENCODING = "cp1251";
    private static final String SET_COOKIE = "Set-Cookie";
    private static final String COOKIE = "Cookie";
    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String GZIP = "gzip";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_FORM_VALUE = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_TORRENT_VALUE = "application/x-bittorrent";
    private static final String CSS_SELECTOR_TOPICS = "table.forums a[href^=\"viewforum.php?f=\"]";
    private static final String CSS_SELECTOR_SEARCH_RESULTS = "table.forumline.tablesorter > tbody > tr";

    private static final Logger logger = LoggerFactory.getLogger(RutrackerHttpService.class);

    private static OkHttpClient httpClient;
    private String cookies;


    public static void main(String[] args) throws IOException {

        BasicConfigurator.configure();

        RutrackerHttpService rutrackerHttpService = new RutrackerHttpService();
        rutrackerHttpService.login("pashka-one", "3210310");
        Class concreteClass = new RutrackerHttpService().getClass();

        File file = new File("RutrackerHttpService.java");
        file.setWritable(true);
        System.out.println(file.canWrite());


        try {
            Field[] fields = concreteClass.getDeclaredFields();
            Field cookies = fields[fields.length - 1];
            cookies.setAccessible(true);
            System.out.println(cookies.get(concreteClass));

        } catch (Exception e) {
            e.printStackTrace();
        }


        String searchRequest = "";
        BasicConfigurator.configure();
        RutrackerCrawler rutrackerCrawler = new RutrackerCrawler("pashka-one", "3210310");

        //   rutrackerCrawler.downloadTorrent("https://rutracker.org/forum/dl.php?t=5831567");
        List<RutrackerLink> irisman = rutrackerCrawler.search("Irisman");
        System.out.println(irisman);

        // RutrackerHttpService rutrackerHttpService = new RutrackerHttpService();
        //  rutrackerHttpService.login("pashka-one", "3210310");
        //    rutrackerHttpService.downloadFile("https://rutracker.org/forum/dl.php?t=5831567");


        String query = "login_username=" + "pashka-one" + "&login_password=" + "3210310" + "&login=%C2%F5%EE%E4";
        String link = "https://rutracker.org/forum/tracker.php?nm=Irishman";
        HttpURLConnection connection = null;


        RequestBody requestBody = RequestBody.create(MediaType.parse(CONTENT_TYPE_FORM_VALUE),
                query.getBytes(ENCODING));
        Response response = httpClient.newCall(new Request.Builder().url(LOGIN_URL).post(requestBody).build())
                .execute();

        System.out.println(response.isRedirect());
        String message = response.message();
        System.out.println(message);
        response.close();
        try {
            connection = (HttpURLConnection) new URL(link).openConnection();
            connection.setRequestProperty("login_username", "pashka-one");
            connection.setRequestProperty("login_password", "3210310");
            connection.setRequestMethod("POST");

            connection.setConnectTimeout(2500);
            connection.setReadTimeout(2500);
            System.out.println(connection.getRequestProperties());
            connection.connect();

            StringBuilder sb = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line)
                            .append("\n");


                }
                FileWriter fileWriter = new FileWriter("output.html");
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


        //   Document document = Jsoup.connect("https://rutracker.org/forum/tracker.php?nm=Irishman").get();
        //   System.out.println(document);

        rutrackerHttpService.downloadFile("https://rutracker.org/forum/dl.php?t=5137567");

    }

    public void login(String username, String password) {
        String query = "login_username=" + username + "&login_password=" + password + "&login=%C2%F5%EE%E4";
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(CONTENT_TYPE_FORM_VALUE),
                    query.getBytes(ENCODING));
            Response response = httpClient.newCall(new Request.Builder().url(LOGIN_URL).post(requestBody).build())
                    .execute();
            if (response.isRedirect()) {
                logger.info("Login succeded.");
                List<String> cookies = response.headers(SET_COOKIE);
                if (cookies == null || cookies.isEmpty()) {
                    throw new RutrackerCrawlerException("Unable to login.");
                }
                this.cookies = cookies.stream().collect(Collectors.joining(","));
            }
        } catch (IOException e) {
            throw new RutrackerCrawlerException(e);
        }
    }
}

