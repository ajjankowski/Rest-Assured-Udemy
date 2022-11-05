package model;

import com.fasterxml.jackson.annotation.JsonInclude;

// Does not send 'null' parameters in body
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
