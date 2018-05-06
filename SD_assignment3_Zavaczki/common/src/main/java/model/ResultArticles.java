package model;

import java.util.ArrayList;
import java.util.List;

public class ResultArticles {
    private List<Article> articles = new ArrayList<Article>();

    public ResultArticles() {
    }

    public ResultArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        String result = "";
        for(Article a : articles)
        {
            result.concat(a.toString() + "\n");
        }
        return result;
    }
}
