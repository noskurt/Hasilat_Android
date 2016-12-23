
package hasilat;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jsoup.nodes.Element;

public final class HtmlTableRow {

    private final ArrayList<Element> row = new ArrayList<>();
    
    public Element get(int index){
        return row.get(index);
    }
    
    public void set(int index, Element el){
        row.set(index, el);
    }
    
    public void add(Element el){
        row.add(el);
    }
    
    public void add(int index, Element el){
        row.add(index, el);
    }
    
    public boolean contains(Element o){
        return row.contains(o);
    }
    
    public void clear(){
        row.clear();
    }
    
    public int indexOf(Element el){
        return row.indexOf(el);
    }
    
    public int lastIndexOf(Element el){
        return row.lastIndexOf(el);
    }
    
    public boolean remove(Element el){
        return row.remove(el);
    }
    
    public Element remove(int index){
        return row.remove(index);
    }
    
    public int size(){
        return row.size();
    }
    
    public Stream<Element> stream(){
        return row.stream();
    }
    
    @Override
    public String toString() {
        return row.stream().map(el -> el.text()).collect(Collectors.joining(", "));
    }

}
