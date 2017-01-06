
package noskurt.com.hasilat.ygznsl;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import noskurt.com.hasilat.R;

public class NewsCollection implements Serializable, Iterable<News> {
    
    private final LinkedList<News> news = new LinkedList<>();
    private final Context context;
    private final Fragment fragment;
    private final Resources res;
    private int currentPage = 1;

    public NewsCollection(Fragment fragment) throws IOException{
        if (fragment == null) throw new NullPointerException();
        this.fragment = fragment;
        res = fragment.getResources();
        context = null;
        more();
    }

    public NewsCollection(Context context) throws IOException{
        if (context == null) throw new NullPointerException();
        this.context = context;
        res = context.getResources();
        fragment = null;
        more();
    }
    
    public final void more() throws IOException {
        for (int i = 1; i <= 3; i++, currentPage++){
            final Document doc = Jsoup.connect(String.format(res.getString(R.string.news_list_url), currentPage)).timeout(0).get();
            final Elements newsList = doc.select(res.getString(R.string.news_list_table_selector));
            for (Element el : newsList.select(res.getString(R.string.news_preview_selector))){
                final String newsUrl = el.select(res.getString(R.string.news_url_selector)).first().attr("abs:href");
                final News n = (context == null) ? new News(fragment, newsUrl) : new News(context, newsUrl);
                n.setPreviewText(el.select(res.getString(R.string.news_preview_text_selector)).text());
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
