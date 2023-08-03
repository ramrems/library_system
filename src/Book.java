import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Book extends Meteryal {
    String yayin;
    String sayfa;
    static ArrayList<Book> Tur_Arama = new ArrayList<>();
    static ArrayList<Book> karisik_Arama = new ArrayList<>();
    static ArrayList<String> listeYazar = new ArrayList<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public Book(String adi, String yazar, String tur,String sayfa,String yayin) {
        this.adi = adi;
        this.yazar_yonetmen = yazar;
        this.tur=tur;
        this.sayfa=sayfa;
        this.yayin = yayin;
    }
    @Override
    public String toString() {
        return "{ " +
                "kitap ismi='" +ANSI_PURPLE+ adi +ANSI_RESET+ '\'' +
                ", yazarı='" +ANSI_BLUE+ yazar_yonetmen +ANSI_RESET+ '\'' +
                ", türü='" +ANSI_RED+ tur +ANSI_RESET+ '\'' +
                ", sayfa sayısı='" +ANSI_PURPLE+ sayfa +ANSI_RESET+ '\'' +
                ", yayını='" +ANSI_BLUE+ yayin +ANSI_RESET+ '\'' +
                '}'+'\n';
    }
       public Book(){
    }
    public static void Search() {
        System.out.println("Neye Göre Arama yapmak istediginiz seciniz");
        System.out.println("1)İsim-Yazar-Yayınevine Göre Kitap Arama");
        System.out.println("2)Türe Göre Kitap Arama");
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        try {
            if (x == 1) {
                System.out.println("Aramak İstediğiniz sözcüğü Giriniz");
                Scanner scannerA = new Scanner(System.in);
                String metin = scannerA.nextLine().toLowerCase();
                for (int i = 0; i < BookFile.kitaplistesi.size(); i++) {
                    if (BookFile.kitaplistesi.get(i).adi.contains(metin)||BookFile.kitaplistesi.get(i).yazar_yonetmen.contains(metin)
                            ||BookFile.kitaplistesi.get(i).yayin.contains(metin)) {
                        karisik_Arama.add(BookFile.kitaplistesi.get(i));
                    }
                }

                if (karisik_Arama.size() > 0) {
                    System.out.println("**Eşleşen Sonuçlar**\n");
                    for (int i = 0; i < karisik_Arama.size(); i++) {
                        System.out.println(
                                //karisik_Arama.get(i).toString().replace(metin,(ANSI_YELLOW+metin+ANSI_BLUE)));
                                // String metonunda aramayı düzeltmeliyiz:aşağıdaki çözüm
                                "{ " +
                                "kitap ismi='" +ANSI_PURPLE+karisik_Arama.get(i).adi.replace(metin,(ANSI_YELLOW+metin+ANSI_PURPLE)) + ANSI_RESET+ '\'' +
                                        ", yazarı='"+ANSI_BLUE+karisik_Arama.get(i).yazar_yonetmen.replace(metin,(ANSI_YELLOW+metin+ANSI_BLUE)) +ANSI_RESET+ '\'' +
                                        ", türü='"+ANSI_RED+karisik_Arama.get(i).tur.replace(metin,(ANSI_YELLOW+metin+ANSI_RED)) +ANSI_RESET+ '\'' +
                                        ", sayfa sayısı='"+ANSI_PURPLE+karisik_Arama.get(i).sayfa.replace(metin,(ANSI_YELLOW+metin+ANSI_PURPLE)) +ANSI_RESET+ '\'' +
                                        ", yayını='"+ANSI_BLUE+karisik_Arama.get(i).yayin.replace(metin,(ANSI_YELLOW+metin+ANSI_BLUE))+ANSI_RESET+ '\'' +
                        '}'+'\n');

                    }

                } else {
                    System.out.println("Aradığınız Kitap/Yazar/Yayınevi Bulunamadı");
                }
            }
            if (x == 2) {
                System.out.println("Aramak İstediğiniz Türü Giriniz");
                System.out.println("(çocuk, bilim-kurgu, fantastik, korku, edebi kurgu, hikaye, roman, dram, biyografi, alegori, gerilim," +
                        "\notobiyofrafi, duygusal kurgu, felsefi, sosyal, polisiye, tiyatro, dünya tarihi, siyaset, genç, psikolojik )");

                Scanner scanner2 = new Scanner(System.in);
                String tur_aranan = scanner2.nextLine().toLowerCase();
                for (int i = 0; i < BookFile.kitaplistesi.size(); i++) {
                    if (BookFile.kitaplistesi.get(i).tur.contains(tur_aranan)) {
                        Tur_Arama.add(BookFile.kitaplistesi.get(i));
                    }
                }
                if (Tur_Arama.size() > 0) {
                    System.out.println("**Eşleşen Sonuçlar**\n");
                    for (int j = 0; j < Tur_Arama.size(); j++) {

                        System.out.println(
                                Tur_Arama.get(j).toString().replace(tur_aranan,(ANSI_YELLOW+tur_aranan+ANSI_RED)));
                    }
                } else {
                    System.out.println("Aradığınız Tür Bulunamadı");
                }
            }
            Tur_Arama.removeAll(BookFile.kitaplistesi);
            karisik_Arama.removeAll(BookFile.kitaplistesi);
        }
        catch (InputMismatchException e) {
            System.out.println("Lutfen gecerli bir deger giriniz");
        }
    }
    public static void Listele() {

            System.out.println("Neyi Listelmek istediginiz seciniz");
            System.out.println("1) Kitaplarin tüm bilgilerini Listele");
            System.out.println("2) Yalniz Kitap isimlerini Listele");
            System.out.println("3) Yalniz Yazarlari Listele");
        try {
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            if (x == 3) {
                System.out.println("**Yazarlar Listesi**");
                for (int i=0;i<BookFile.kitaplistesi.size();i++){
                if (listeYazar.contains(BookFile.kitaplistesi.get(i).yazar_yonetmen) == false) {
                    listeYazar.add(BookFile.kitaplistesi.get(i).yazar_yonetmen);
                }
                }
                for (int j = 0; j < listeYazar.size(); j++) {
                    System.out.println(listeYazar.get(j));
                }
            }
            if (x == 1) {
                for (int i = 0; i < BookFile.kitaplistesi.size(); i++){
                System.out.println(BookFile.kitaplistesi.get(i)+"\n");}
            }
            if (x == 2) {
                for (int i = 0; i < BookFile.kitaplistesi.size(); i++) {
                    System.out.println(BookFile.kitaplistesi.get(i).adi);
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Lutfen gecerli bir deger giriniz");
        }
    }
}
