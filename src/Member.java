import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
public class Member {
    private String Adi;
    private String SoyAdi;
    private String id;
    private ArrayList<String> MyList = new ArrayList<>();

    public Member(String adi, String soyAdi, String id) {
        Adi = adi;
        SoyAdi = soyAdi;
        this.id = id;
    }
    @Override
    public String toString() {
        return "Member{" +
                "ad='" + Adi + '\'' +
                ", soyad='" + SoyAdi + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
    public Member(){

    }
    public void setAdi(String adi) {
        this.Adi = adi;
    }

    public void setSoyAdi(String soyAdi) {
        this.SoyAdi = soyAdi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdi() {
        return Adi;
    }

    public String getSoyAdi() {
        return SoyAdi;
    }

    public String getId() {
        return id;
    }
    public ArrayList<String> getMyList() {
        return MyList;
    }
    public static void UyelikIslemleri(Member yeniUye) throws IOException {
        System.out.println("Üye olmak için aşağıdaki bilgileri doldurunuz ve su faturanızı teslim ediniz");
        if(GirisYap(yeniUye)){
            System.out.println("Zaten Kutuphaneye Uyesiniz");
            //System.out.println(MemberFile.getInstance().uyeListesi);
        }
        else {
            MemberFile.uyeListesi.add(yeniUye);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/TXT_Dosyalari/UyeListesi.txt"))) {
                Iterator<Member> iterator = MemberFile.getInstance().uyeListesi.iterator();
                while (iterator.hasNext()) {
                    Member newMember = iterator.next();
                    writer.write(String.format("%s\t%s\t%s", newMember.getAdi(), newMember.getSoyAdi(), newMember.getId()));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            };
            System.out.println("Başarıyla üye oldunuz");
        }
    }
    public static boolean GirisYap(Member Uye) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("isminizi giriniz:");
        String isim = scanner.nextLine().toLowerCase();
        Uye.setAdi(isim);

        System.out.println("Soy isminizi giriniz:");
        String soyisim = scanner.nextLine().toLowerCase();
        Uye.setSoyAdi(soyisim);

        System.out.println("id giriniz:");
        String id = scanner.nextLine();
        Uye.setId(id);
        if (GirisKontrol(Uye) != null) {
            return true;
        } else return false;
    }
    public static Member GirisKontrol(Member Uye) throws IOException {

        BufferedReader read = new BufferedReader(new FileReader("src/TXT_Dosyalari/UyeListesi.txt"));
        String Uyeler;
        int x=0;
        while ((Uyeler=read.readLine())!=null) {
            String[] uyeBilgi  = Uyeler.split("\t");
            String ad=uyeBilgi[0];
            String soyad=uyeBilgi[1];
            String id1 =String.valueOf(uyeBilgi[2]);
            if((Objects.equals(Uye.Adi, ad)) && (Objects.equals(Uye.SoyAdi, soyad)) && (Objects.equals(Uye.id, id1))){
                x=1;
            }
        }
        if (x==1){
            return Uye;
        }
        else {
            return null;
        }
    }

}
