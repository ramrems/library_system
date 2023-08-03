import java.io.*;
import java.util.ArrayList;

public class BookFile implements Instance {
    private static BookFile instance=new BookFile();
    static ArrayList<Book> kitaplistesi = new ArrayList<>();
    private BookFile(){

    }
    public static BookFile getInstance(){
        return instance;
    }
    public void getBooks() throws IOException {
        BufferedReader oku = new BufferedReader(new FileReader("src/TXT_Dosyalari/kutuphane.txt"));
        String kutuphane;
        while ((kutuphane=oku.readLine())!=null) {
            String[] kitapOzellikleri  = kutuphane.split("\t");
            String kitap_isim=String.valueOf(kitapOzellikleri[0]);
            String yazar=kitapOzellikleri[1];
            String tur=kitapOzellikleri[2];
            String sayfa= String.valueOf(kitapOzellikleri[3]);
            String yayin=kitapOzellikleri[4];
            Book book= new Book(kitap_isim,yazar,tur,sayfa,yayin);
            kitaplistesi.add(book);
        }
    }

}
