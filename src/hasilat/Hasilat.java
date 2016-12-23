
package hasilat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;

public class Hasilat {

    public static void main(String[] args) throws IOException {
        final String url = "http://boxofficeturkiye.com/yillik/?yilop=tum&yil=2010";
        final String selector = "#right > table > tbody > tr > td > table > tbody > tr:nth-child(3) > td > table:nth-child(3) > tbody > tr > td > table > tbody > tr > td > table:nth-child(5) > tbody";
        
        new Thread(() -> {
            try {
                System.out.println(Obtain.obtainTable(selector, url, Obtain.obtainPageCount(url)));/*
                System.out.println(Obtain.obtainPageCount(url));
                final HtmlTable table = Obtain.obtainTable(selector, url, Obtain.obtainPageCount(url));
                System.out.println(table);*/
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }).start();
    }
    
}
