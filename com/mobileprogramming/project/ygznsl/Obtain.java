package com.mobileprogramming.project.ygznsl;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Obtain {

    public synchronized static HtmlTable obtainTable(String tableSelector, String url) throws IOException {
        final Document html = Jsoup.connect(url).timeout(0).get();
        final Element tableEl = html.select(tableSelector).first();
        final LinkedList<Element> rows = new LinkedList<>();
        for (Element row : tableEl.select("tr")){
            if (!row.attr("bgcolor").equals("#dcdcdc")){
                rows.add(row);
            }
        }
        final HtmlTable table = new HtmlTable();
        for (Element tr : rows){
            final HtmlTableRow row = new HtmlTableRow();
            for (Element td : tr.select("td")){
                row.add(td);
            }
            table.add(row);
        }
        return table;
    }
    
    public synchronized static HtmlTable obtainTable(String tableSelector, String[] urls) throws IOException {
        final HtmlTable table = new HtmlTable();
        for (String url : urls){
            table.add(Obtain.obtainTable(tableSelector, url));
        }
        return table;
    }
    
    public synchronized static HtmlTable obtainTable(String tableSelector, Collection<? extends String> urls) throws IOException {
        String[] urlArray = new String[urls.size()];
        urls.toArray(urlArray);
        return Obtain.obtainTable(tableSelector, urlArray);
    }
    
    public synchronized static HtmlTable obtainTable(String tableSelector, String baseUrl, int pageCount) throws IOException {
        final HtmlTable table = new HtmlTable();
        for (int i = 1; i <= pageCount; i++){
            table.add(obtainTable(tableSelector, String.format("%s&sayfa=%d", baseUrl, i)));
        }
        return table;
    }
    
    public synchronized static int obtainPageCount(String url) throws IOException {
        int count = 0;
        for (Element el : Jsoup.connect(url).timeout(0).get().body().getElementsByTag("a")){
            if (el.className().startsWith("secenek")){
                if (el.attr("href").contains("sayfa")) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public synchronized static <T> boolean noneMatch(Collection<T> list, Predicate<T> predicate){
        for (T el : list){
            if (predicate.test(el)) return false;
        }
        return true;
    }
    
    public synchronized static <T> boolean anyMatch(Collection<T> list, Predicate<T> predicate){
        for (T el : list){
            if (predicate.test(el)) return true;
        }
        return false;
    }
    
    public synchronized static <T> boolean allMatch(Collection<T> list, Predicate<T> predicate){
        for (T el : list){
            if (!predicate.test(el)) return false;
        }
        return true;
    }
    
}