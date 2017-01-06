
package com.mobileprogramming.project.ygznsl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.nodes.Element;

public final class HtmlTableRow implements Serializable, Iterable<Element> {

    private final ArrayList<Element> row = new ArrayList<>();
    
    public Element[] elements(){
        Element[] array = new Element[row.size()];
        row.toArray(array);
        return array;
    }
    
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
    
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder(row.get(0).text());
        for (int i = 1; i < row.size(); i++) str.append(", " + row.get(i).text());
        return str.toString();
    }

    @Override
    public Iterator<Element> iterator() {
        return row.iterator();
    }

}