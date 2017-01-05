
package com.mobileprogramming.project.ygznsl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsCollection implements Serializable, Iterable<News> {
    
    private final LinkedList<News> news = new LinkedList<>();
    private int currentPage = 1;
    
    public NewsCollection() throws IOException{
        more();
    }
    
    public final void more() throws IOException {
        for (int i = 1; i <= 3; i++, currentPage++){
            final Document doc = Jsoup.connect(String.format("http://boxofficeturkiye.com/haberler/%d", currentPage)).timeout(0).get();
            final Elements newsList = doc.select("#right > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > div.solhaberler");
            for (Element el : newsList.select("div.haberliste")){
                final String newsUrl = el.select("div.haberbaslik > a").first().attr("abs:href");
                final News n = new News(newsUrl);
                n.setPreviewText(el.select("div.haberkicerik").text());
                news.add(n);
            }
        }
    }
    
    public News[] news(){
        News[] array = new News[news.size()];
        news.toArray(array);
        return array;
    }
    
    @Override
    public Iterator<News> iterator() {
        return news.iterator();
    }
    
}
