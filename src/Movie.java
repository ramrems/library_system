import java.io.*;
import java.util.ArrayList;

public class Movie extends Meteryal implements Instance {
    static ArrayList<Movie> filmlistesi = new ArrayList<>();
    public static Movie getInstance() {
        return instance;
    }
    private static Movie instance=new Movie();

    public Movie(String adi, String yazar_yonetmen, String tur) {
        this.adi = adi;
        this.yazar_yonetmen = yazar_yonetmen;
        this.tur = tur;
    }
    public Movie() {

    }
    @Override
    public String toString() {
        return "{" + "film adı='" + Book.ANSI_PURPLE+ adi + '\'' +Book.ANSI_RESET+
                ", yönetmeni='" + Book.ANSI_BLUE+ yazar_yonetmen + '\'' +Book.ANSI_RESET+
                ", türü='" + Book.ANSI_RED+ tur + '\'' + Book.ANSI_RESET+
                '}';
    }
    public void getMovies() throws IOException {
        BufferedReader oku = new BufferedReader(new FileReader("src/TXT_Dosyalari/Movies.txt"));
        String kutuphane;
        while ((kutuphane = oku.readLine()) != null) {
            String[] filmOzellikleri = kutuphane.split("\t");
            String film_isim = filmOzellikleri[0];
            String yazar = filmOzellikleri[1];
            String tur = filmOzellikleri[2];
            Movie movie = new Movie(film_isim, yazar, tur);
            filmlistesi.add(movie);
        }
    }
    public static void Listele() {
        for (int i = 0; i < filmlistesi.size(); i++) {
            System.out.println(filmlistesi.get(i) + "\n");
        }
    }
    public static void iadeEt(Member uye, Movie movie) throws IOException {
        if (uye.getMyList().contains(movie.adi)) {
            uye.getMyList().remove(movie.adi);
        }
        test.listeMevcut.add(movie.adi);
        test.OduncAlinanlar.remove(movie.adi);

        System.out.println(movie.adi + " filmini iade ettiniz");
        File fileAd = new File("src/TXT_Dosyalari/java_kitap_isim2.txt");
        FileWriter fwAd2 = new FileWriter(fileAd, true);
        PrintWriter pwAd2 = new PrintWriter(fwAd2);
        pwAd2.println(movie.adi);
        pwAd2.close();

        File inputFile2 = new File("src/TXT_Dosyalari/odunc_alinanlar.txt");
        File tempFile2 = new File("myTempFile2.txt");

        BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(tempFile2));

        String lineToRemove = movie.adi;
        String currentLine;

        while ((currentLine = reader2.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) continue;
            writer2.write(currentLine + System.getProperty("line.separator"));
        }
        writer2.close();
        reader2.close();
        inputFile2.delete();
        boolean successful = tempFile2.renameTo(inputFile2);
    }
    public static void oduncAl (Member uye, Movie movie) throws IOException {
        uye.getMyList().add(movie.adi);

        test.OduncAlinanlar.add(movie.adi);
        test.listeMevcut.remove(movie.adi);
        System.out.println(movie.adi + " filmini odunc aldiniz");

        File file = new File("src/TXT_Dosyalari/odunc_alinanlar.txt");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(movie.adi);
        pw.close();

        File inputFile = new File("src/TXT_Dosyalari/java_kitap_isim2.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = movie.adi;
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        inputFile.delete();
        boolean successful = tempFile.renameTo(inputFile);
    }
}
