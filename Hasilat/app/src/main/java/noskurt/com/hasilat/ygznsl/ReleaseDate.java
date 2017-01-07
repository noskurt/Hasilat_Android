package noskurt.com.hasilat.ygznsl;

import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import noskurt.com.hasilat.R;

public class ReleaseDate implements Serializable, Iterable<ReleaseWeek> {

    private final LinkedList<ReleaseWeek> weeks = new LinkedList<>();
    private final Resources res;
    private final int year, month;
    private final String url;

    public ReleaseDate(Context context, int year, int month) throws IOException {
        if (context == null) throw new NullPointerException();
        res = context.getResources();
        if (month < 1 || month > 12) throw new IllegalArgumentException();
        if (year < 2005 || year > (Calendar.getInstance().get(Calendar.YEAR) + 2))
            throw new IllegalArgumentException();
        this.year = year;
        this.month = month;
        url = String.format(res.getString(R.string.release_date_url), year, month);
        read();
    }

    public final void read() throws IOException {
        final Document document = Jsoup.connect(url).timeout(0).get();
        final Element elWeeks = document.select(res.getString(R.string.release_date_weeks_selector)).first();
        for (Element el : elWeeks.select("table")) {
            weeks.add(new ReleaseWeek(el, year, month));
        }
    }

    public List<ReleaseWeek> getWeeks() {
        return weeks;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public Month month() {
        final TreeMap<Integer, Month> months = new TreeMap<>();
        for (Month mm : Month.values()) months.put(mm.value(), mm);
        return months.get(month);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder("------").append(year).append(" ").append(month().getName()).append("------").append("\r\n\r\n");
        for (ReleaseWeek week : weeks) str.append(week).append("\r\n");
        return str.append("------").append(year).append(" ").append(month().getName()).append("------").append("\r\n").toString();
    }

    @Override
    public Iterator<ReleaseWeek> iterator() {
        return weeks.iterator();
    }

}
