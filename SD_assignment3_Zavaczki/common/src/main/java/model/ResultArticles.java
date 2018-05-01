package model;

import java.util.ArrayList;
import java.util.List;

public class ResultArticles {
    private List<Article> articles = new ArrayList<Article>();

    public ResultArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
