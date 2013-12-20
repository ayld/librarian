package net.librarian.app.web.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by siliev on 12/18/13.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Repo {

    private String name;
    private String html_url;

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
