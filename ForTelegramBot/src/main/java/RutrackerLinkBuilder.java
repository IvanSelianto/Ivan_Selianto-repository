import pw.spn.crawler.rutracker.model.RutrackerLink;
import pw.spn.crawler.rutracker.model.RutrackerTopic;

public  class RutrackerLinkBuilder {
    private RutrackerTopic rutrackerTopic;
    private String title;
    private String url;
    private String downloadUrl;
    private long sizeInBytes;
    private int seeds;
    private int leechs;

    public  RutrackerLinkBuilder setRutrackerTopic(RutrackerTopic rutrackerTopic) {
        this.rutrackerTopic = rutrackerTopic;
        return this;
    }

    public RutrackerLinkBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public RutrackerLinkBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public RutrackerLinkBuilder setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public RutrackerLinkBuilder setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        return this;
    }

    public RutrackerLinkBuilder setSeeds(int seeds) {
        this.seeds = seeds;
        return this;
    }

    public RutrackerLinkBuilder setLeechs(int leechs) {
        this.leechs = leechs;
        return this;
    }

    public RutrackerLink build() {
        return new RutrackerLink(this.rutrackerTopic,
                this.title,
                this.url,
                this.downloadUrl,
                this.sizeInBytes,
                this.leechs,
                this.seeds);
    }
}
