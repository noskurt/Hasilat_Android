
package hasilat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Obtain {

    public synchronized static HtmlTable obtainTable(String tableSelector, String url) throws IOException {
        final Document html = Jsoup.connect(url).timeout(0).get();
        final Element tableEl = html.select(tableSelector).first();
        final Stream<Element> rows = tableEl.select("tr")
                .stream()
                .filter(row -> row.hasAttr("bgcolor"))
                .filter(row -> !row.attr("bgcolor").equals("#dcdcdc"));
        final HtmlTable table = new HtmlTable();
        rows.forEach(tr -> {
            final HtmlTableRow row = new HtmlTableRow();
            tr.select("td").stream().forEach(td -> {
                row.add(td);
            });
            table.add(row);
        });
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
    
    public synchronized static HtmlTable obtainTable(String tableSelector, String baseUrl, long pageCount) throws IOException {
        final HtmlTable table = new HtmlTable();
        LongStream
                .range(1, pageCount + 1)
                .mapToObj(p -> String.format("%s&sayfa=%d", baseUrl, p))
                .forEach(url -> {
                    try {
                        table.add(obtainTable(tableSelector, url));
                    } catch (IOException ex) {

                    }
                });
        return table;
    }
    
    public synchronized static long obtainPageCount(String url) throws IOException {
        return Jsoup
                .connect(url)
                .timeout(0)
                .get()
                .body()
                .getElementsByTag("a")
                .stream()
                .filter(el -> el.className().startsWith("secenek"))
                .filter(el -> el.attr("href").contains("sayfa"))
                .count();
    }
    
}
