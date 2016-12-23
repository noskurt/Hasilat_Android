
package hasilat;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HtmlTable {
    
    private final ArrayList<HtmlTableRow> table = new ArrayList<>();
    
    public HtmlTableRow get(int index){
        return table.get(index);
    }
    
    public void set(int index, HtmlTableRow el){
        table.set(index, el);
    }
    
    public void add(HtmlTableRow row){
        table.add(row);
    }
    
    public void add(int index, HtmlTableRow row){
        table.add(index, row);
    }
    
    public void add(HtmlTable t){
        table.addAll(t.stream().collect(Collectors.toList()));
    }
    
    public boolean contains(HtmlTableRow row){
        return table.contains(row);
    }
    
    public void clear(){
        table.clear();
    }
    
    public int indexOf(HtmlTableRow row){
        return table.indexOf(row);
    }
    
    public int lastIndexOf(HtmlTableRow row){
        return table.lastIndexOf(row);
    }
    
    public boolean remove(HtmlTableRow row){
        return table.remove(row);
    }
    
    public HtmlTableRow remove(int index){
        return table.remove(index);
    }
    
    public int size(){
        return table.size();
    }
    
    public Stream<HtmlTableRow> stream(){
        return table.stream();
    }
    
    @Override
    public String toString() {
        return table.stream().map(row -> row.toString()).collect(Collectors.joining("\r\n"));
    }

}
