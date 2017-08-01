package noskurt.com.hasilat.ygznsl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public final class HtmlTable implements Serializable, Iterable<HtmlTableRow> {

    private final ArrayList<HtmlTableRow> table = new ArrayList<>();

    public void sort(Comparator<? super HtmlTableRow> c) {
        Collections.sort(table, c);
    }

    public HtmlTableRow[] rows() {
        HtmlTableRow[] array = new HtmlTableRow[table.size()];
        table.toArray(array);
        return array;
    }

    public HtmlTableRow get(int index) {
        return table.get(index);
    }

    public void set(int index, HtmlTableRow el) {
        table.set(index, el);
    }

    public void add(HtmlTableRow row) {
        table.add(row);
    }

    public void add(int index, HtmlTableRow row) {
        table.add(index, row);
    }

    public void add(HtmlTable t) {
        for (HtmlTableRow row : t.rows()) {
            table.add(row);
        }
    }

    public boolean contains(HtmlTableRow row) {
        return table.contains(row);
    }

    public void clear() {
        table.clear();
    }

    public int indexOf(HtmlTableRow row) {
        return table.indexOf(row);
    }

    public int lastIndexOf(HtmlTableRow row) {
        return table.lastIndexOf(row);
    }

    public boolean remove(HtmlTableRow row) {
        return table.remove(row);
    }

    public HtmlTableRow remove(int index) {
        return table.remove(index);
    }

    public int size() {
        return table.size();
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        for (HtmlTableRow row : table) str.append(row).append("\r\n");
        return str.toString().trim();
    }

    @Override
    public Iterator<HtmlTableRow> iterator() {
        return table.iterator();
    }

}