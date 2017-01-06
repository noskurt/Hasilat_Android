
package noskurt.com.hasilat.ygznsl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ReleaseDate implements Serializable, Iterable<ReleaseWeek> {

    private final LinkedList<ReleaseWeek> weeks = new LinkedList<>();
    private final int year, month;
    private final String url;

    public ReleaseDate(int year, int month) throws IOException {
        if (month < 1 || month > 12) throw new IllegalArgumentException();
        final Calendar cal = Calendar.getInstance();
//        cal.setTime(Date.from(Instant.now()));
        if (year < 2005 || year > (cal.get(Calendar.YEAR) + 2))
            throw new IllegalArgumentException();
        this.year = year;
        this.month = month;
        url = String.format("http://boxofficeturkiye.com/vizyon/?yil=%d&ay=%d", year, month);
        read();
    }

    public final void read() throws IOException {
        final Document document = Jsoup.connect(url).timeout(0).get();
        final Element elWeeks = document.select("#right > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table.navkutu > tbody > tr > td > table:nth-child(5) > tbody > tr > td > table > tbody > tr:nth-child(2) > td").first();
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
