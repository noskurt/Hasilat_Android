package noskurt.com.hasilat.ygznsl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReleaseWeek implements Serializable, Iterable<Movie> {

    private final LinkedList<Movie> movies = new LinkedList<>();
    private final int year, month;
    private final Element root;
    private String week;

    public ReleaseWeek(Element root, int year, int month) {
        if (root == null) throw new NullPointerException();
        if (!root.nodeName().equals("table")) throw new IllegalArgumentException();
        this.root = root.select("tbody").first();
        if (month < 1 || month > 12) throw new IllegalArgumentException();
        if (year < 2005 || year > (Calendar.getInstance().get(Calendar.YEAR) + 2))
            throw new IllegalArgumentException();
        this.year = year;
        this.month = month;
        read();
    }

    public final void read() {
        final Elements trs = root.select("tr");
        week = trs.first().text().replaceAll("\\s+", " ").trim();
        final int day = Integer.parseInt(week.split("\\s+")[1]);
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        for (Element el : trs) {
            if (el.attr("bgcolor").equals("#FFFFFF") || el.attr("bgcolor").equals("#808080"))
                continue;
            final Elements tds = el.select("td");
            final Elements nameAndGenre = tds.get(1).select("font");
            final Movie m = new Movie();
            m.setReleaseDate(cal);
            m.setImgUrl(tds.get(0).select("a > img").attr("abs:src"));
            m.setName(nameAndGenre.get(0).text());
            m.setGenre(nameAndGenre.get(2).text());
            movies.add(m);
        }
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Month month() {
        final TreeMap<Integer, Month> months = new TreeMap<>();
        for (Month mm : Month.values()) months.put(mm.value(), mm);
        return months.get(month);
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder("---").append(week).append("---").append("\r\n");
        for (Movie movie : movies) str.append(movie).append("\r\n");
        return str.append("---").append(week).append("---").append("\r\n").toString();
    }

    @Override
    public Iterator<Movie> iterator() {
        return movies.iterator();
    }

}
