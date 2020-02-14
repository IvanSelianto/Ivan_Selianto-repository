

import org.jsoup.select.Elements;
import pw.spn.crawler.rutracker.http.RutrackerHttpService;
import pw.spn.crawler.rutracker.model.RutrackerLink;
import pw.spn.crawler.rutracker.model.RutrackerTopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RuTrackerLinksCreator {


    private RutrackerHttpService rutrackerHttpService = new RutrackerHttpService();
    private Elements searchResult;

    private Integer[] topicsArray = createMapTopicIdsToNames().keySet().toArray(new Integer[0]);

    public Integer[] getTopicsArray() {
        return topicsArray;
    }


    public void setSearchResult(Elements searchResult) {

        this.searchResult = searchResult;
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
        return searchResult.stream().map(element -> Integer.parseInt(new StringFinder().find(element, "\\?f=(.+)\">"))).collect(Collectors.toList());
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
                .map(element -> Integer.parseInt(new StringFinder().find(element, RegexEnum.REGEX_FOR_IDS.toString()).split("\\D++")[1]))
                .collect(Collectors.toList());
    }

    private List<String> createLinksTitles(Elements searchResult) {
        return searchResult.stream().map(element -> new StringFinder().find(element.getElementsByAttribute("data-topic_id").first(), RegexEnum.REGEX_FOR_TITLES.toString())
        ).collect(Collectors.toList());
    }

    private List<Integer> createLinksSeeds(Elements searchResult) {

        return searchResult.stream()
                .map(element -> Integer.parseInt(new StringFinder().find(element, RegexEnum.REGEX_FOR_SEEDS.toString())))
                .collect(Collectors.toList());
    }

    private List<Integer> createLinksLeechs(Elements searchResult) {
        return searchResult.stream()
                .map(element -> Integer.parseInt(new StringFinder().find(element, RegexEnum.REGEX_FOR_LEECHS.toString())))
                .collect(Collectors.toList());

    }


    private List<Long> createLinksSizesInBytes(Elements searchResult) {
        return searchResult.stream()
                .map(element -> Long.parseLong(new StringFinder().find(element, RegexEnum.REGEX_FOR_SIZE_IN_BYTES.toString())))
                .collect(Collectors.toList());


    }
}
