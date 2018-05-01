package model;

public class Article {
    private String title;
    private String author;
    private String body;

    public Article() {
    }

    public Article(String title, String author, String body) {
        this.title = title;
        this.author = author;
        this.body = body;
    }

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
