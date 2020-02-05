import org.apache.log4j.BasicConfigurator;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pw.spn.crawler.rutracker.http.RutrackerHttpService;
import pw.spn.crawler.rutracker.model.RutrackerLink;
import pw.spn.crawler.rutracker.model.RutrackerTopic;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RuTracker {


    private int topicId;

    private Integer[] topicsArray;


    private RutrackerHttpService rutrackerHttpService = new RutrackerHttpService();
    private Elements searchResult;
    private Integer[] films = new Integer[]{22, 941, 1666, 772, 789, 7, 1640, 187, 2200, 1950, 2540, 934, 505, 185, 254, 771, 44, 124, 1543, 709, 1577, 149, 186, 905, 101, 100, 1576, 572, 2198
            , 1457, 2199, 313, 312, 1247, 2201, 2339, 352, 549, 1213, 2109, 514, 2097, 4, 2343, 930, 2365, 1900, 521, 2258, 208, 539, 209, 484, 822, 921, 815, 816, 1460, 33, 705, 599, 1105, 1389, 1391,
            2491, 893, 9, 1535, 188, 805, 172, 119, 935, 1408, 123, 189, 842, 235, 242, 819, 1531, 1102, 1214, 387, 2366, 1669, 265, 2406, 2396, 2398, 911, 1301, 1574, 1539, 704, 1500, 2100, 1242, 2104
            , 2103, 670, 1475, 2107, 294, 1453, 46, 671, 656, 2076, 56, 2123, 876, 2139, 249, 552, 500, 2112, 314, 1278, 1281, 2110, 979, 2169, 2166, 2164, 24, 1959, 113, 114, 1332, 1495, 610, 1568, 1542,
            1544, 1549, 1552, 1553, 1554, 1257, 1255, 1261, 1259, 1254, 1260, 1547, 2135, 2136, 1581, 1590, 1594, 1591, 1588, 1586, 1592, 1556, 1560, 1561, 1562, 1563, 1626, 1565, 1566,};

    private Integer[] books = new Integer[]{1411, 21, 2157, 765, 2019, 31, 1427, 2422, 2521, 2223, 2447, 1101, 1689, 2336, 2337, 1400, 1415, 2046, 1802, 2189, 2190, 2443, 669, 2196,
            2056, 1436, 2191, 1680, 1684, 2524, 2525, 995, 2022, 2471, 764, 1688, 2020, 1967, 1341, 2049, 2319, 2434, 2452, 2435, 2436, 2320, 2023, 2026, 2192, 2027, 295, 2028, 2029, 1325, 2030, 2526,
            2254, 2494, 919, 944, 980, 946, 977, 2074, 2349, 768, 2099, 2021, 2434, 1337, 1447, 2468, 2469, 2470, 1686, 2215, 2216, 2217, 2218, 2252, 767, 2515, 2515, 2516, 2517, 2520, 2253, 2033, 1412,
            1446, 753, 2037, 2224, 2194, 2433, 1961, 565, 1520, 769, 2038, 2043, 2042, 2041, 2044, 2045, 2193, 1418, 1422, 1425, 1426, 1428, 1429, 1433, 1432, 862, 2461, 2462, 2463, 281, 2465, 2458, 2048,
            1238, 2055, 754, 2114, 2438, 2439, 2440, 2441, 2442, 2125, 2133, 2130, 21313, 2129, 2141, 2314, 71,
//audiobooks
            2167, 1993, 395, 2326, 574, 1036, 400, 2389, 2388, 2387, 661, 2348, 2327, 695, 399, 402, 490, 499, 2137, 2324, 2328, 403, 416
    };

    private Integer[] music = new Integer[]{1874, 409, 560, 436, 969, 1125, 1130, 1132, 1866, 1136, 2530, 1849, 1126, 1121, 2352, 2351, 408, 441, 446, 1107, 2529, 1760, 1772, 1773, 416,
            2377, 468, 691, 1388, 784, 1215, 1220, 1224, 413, 988, 1842, 1648, 2495, 424, 425, 1634, 2497, 429, 714, 1330, 1452, 2499, 2503, 2502, 2500, 2507, 1121, 431, 986, 2267, 2277, 2278, 2279, 2280,
            2268, 2293, 2292, 2290, 2269, 2297, 2295, 2296, 2298, 1698, 1704, 1707, 2329, 2330, 1713, 1715, 1716, 1778, 1727, 1728, 1729, 1732, 1745, 1747, 1749, 722, 738, 740, 952, 1821, 1807, 1808, 797, 1805,
            1809, 1810, 1811, 1812, 1913, 1299, 1884, 2512, 1885, 2302, 1755, 2219, 1625, 974, 1444, 2301, 1756, 1754, 860, 453, 1170, 1759, 1852,
//audiobooks
            2167, 1993, 395, 2326, 574, 1036, 400, 2389, 2388, 2387, 661, 2348, 2327, 695, 399, 402, 490, 499, 2137, 2324, 2328, 403, 416

    };

    public Integer[] getFilms() {
        return films;
    }

    public Integer[] getBooks() {
        return books;
    }

    public Integer[] getMusic() {
        return music;
    }

    public Integer[] getTopicsArray() {
        return topicsArray;
    }

    public void setTopicsArray(Integer[] topicsArray) {
        this.topicsArray = topicsArray;
    }

    public void setSearchResult(Elements searchResult) {

        this.searchResult = searchResult;
    }

    public Elements getSearchResult() {
        return searchResult;
    }

    public void login() {
        BasicConfigurator.configure();
        rutrackerHttpService.login("pashka-one", "3210310");
    }

    public List<RutrackerLink> createRutrackerLinks() {

        List<RutrackerLink> rutrackerLinks = new ArrayList<>();

        List<Integer> linksIds = createLinksIds(searchResult);
        for (int i = 0; i < linksIds.size(); i++) {
            rutrackerLinks.add(new RutrackerLinkBuilder()
                    .setRutrackerTopic(new RutrackerTopic(createClientTopicIds().get(i)
                            , createMapTopicIdsToNames().get(createClientTopicIds().get(i))))
                    .setTitle(createLinksTitles(searchResult).get(i))
                    .setUrl(createLinksIds(searchResult).get(i).toString())
                    .setDownloadUrl(createDownloadURLs(searchResult).get(i))
                    .setSizeInBytes(createLinksSizesInBytes(searchResult).get(i))
                    .setSeeds(createLinksSeeds(searchResult).get(i))
                    .setLeechs(createLinksLeechs(searchResult).get(i)).build());


        }
        return rutrackerLinks;
    }

    private List<Integer> createClientTopicIds() {
        return searchResult.stream().map(element -> Integer.parseInt(finder(element, "\\?f=(.*)\">"))).collect(Collectors.toList());
    }

    private List<String> createDownloadURLs(Elements searchResult) {
        return createLinksIds(searchResult).stream().map(id -> "https://rutracker.org/forum/dl.php?t=" + id).collect(Collectors.toList());
    }

    private Map<Integer, String> createMapTopicIdsToNames() {
        return rutrackerHttpService.loadTopics().stream()
                .collect(Collectors.toMap(element -> (Integer.parseInt(element.attr("href").split("\\D++")[1])), element -> element.text()));

    }

    private List<Integer> createLinksIds(Elements searchResult) {
        return searchResult.stream()
                .map(element -> Integer.parseInt(finder(element, REGEX.REGEX_FOR_IDS.toString()).split("\\D++")[1]))
                .collect(Collectors.toList());
    }

    private List<String> createLinksTitles(Elements searchResult) {
        return searchResult.stream().map(element -> finder(element.getElementsByAttribute("data-topic_id").first(), REGEX.REGEX_FOR_TITLES.toString())
        ).collect(Collectors.toList());
    }

    private List<Integer> createLinksSeeds(Elements searchResult) {
        return searchResult.stream()
                .map(element -> Integer.parseInt(finder(element, REGEX.REGEX_FOR_SEEDS.toString())))
                .collect(Collectors.toList());
    }

    private List<Integer> createLinksLeechs(Elements searchResult) {
        return searchResult.stream()
                .map(element -> Integer.parseInt(finder(element, REGEX.REGEX_FOR_LEECHS.toString())))
                .collect(Collectors.toList());

    }


    private List<Long> createLinksSizesInBytes(Elements searchResult) {
        return searchResult.stream()
                .map(element -> Long.parseLong(finder(element, REGEX.REGEX_FOR_SIZE_IN_BYTES.toString())))
                .collect(Collectors.toList());
    }

    private String finder(Element element, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element.toString());
        matcher.find();
        return matcher.group(1);

    }

    public String sizeConversion(long size) {
        double convertedSize;
        if (size > Math.pow(2, 10) && size < Math.pow(2, 20)) {
            convertedSize = (double) size / Math.pow(2, 10);
            return round(convertedSize, 2) + " Kb";
        }
        if (size > Math.pow(2, 20) && size < Math.pow(2, 30)) {
            convertedSize = (double) size / Math.pow(2, 20);
            return round(convertedSize, 2) + " Mb";
        }
        convertedSize = (double) size / Math.pow(2, 30);
        return round(convertedSize, 2) + "Gb";

    }

    private double round(double value, int places) {
        return new BigDecimal(Double.toString(value)).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }


}
