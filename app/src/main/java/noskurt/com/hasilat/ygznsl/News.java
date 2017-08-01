package noskurt.com.hasilat.ygznsl;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import noskurt.com.hasilat.R;

public final class News implements Serializable {

    private final Resources res;
    private String previewText, title, date, content, imgUrl;
    private final Predicate<Element> p1 = new Predicate<Element>() {
        @Override
        public boolean test(Element t) {
            return t.text().contains("tıkla") ||
                    t.attr("href").contains("seans") ||
                    t.attr("href").contains("haber-meta");
        }
    };


    public News(Fragment fragment, String url) throws IOException {
        if (fragment == null) throw new NullPointerException();
        res = fragment.getResources();
        construct(url);
    }

    public News(Context context, String url) throws IOException {
        if (context == null) throw new NullPointerException();
        res = context.getResources();
        construct(url);
    }

    private void construct(String url) throws IOException {
        final Document doc = Jsoup.connect(url).timeout(0).get();
        final Element newsEl = doc.select(res.getString(R.string.news_selector)).first();
        date = newsEl.getElementsByClass("habertarih").first().text();
        title = newsEl.getElementsByClass("haberbaslik").first().text();
        imgUrl = newsEl.select(res.getString(R.string.news_img_url_selector)).first().attr("abs:src");
        final LinkedList<Element> selected = new LinkedList<>();
        for (Element el : newsEl.select("div")) {
            if (el.className().equals("haberkicerik")) {
                if (el.select("iframe").isEmpty()) {
                    final Elements els = el.select("a");
                    if (Obtain.noneMatch(els, p1)) {
                        selected.add(el);
                    }
                }
            }
        }
        final StringBuilder str = new StringBuilder();
        for (Element el : selected) {
            str.append(el.text()).append("\r\n");
        }
        content = str.toString().trim();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getImageURL() {
        return imgUrl;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.date);
        hash = 83 * hash + Objects.hashCode(this.content);
        hash = 83 * hash + Objects.hashCode(this.imgUrl);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final News other = (News) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
}