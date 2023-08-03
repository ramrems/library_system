import java.io.*;
import java.util.ArrayList;

public class MemberFile implements Instance{
    private static MemberFile instance=new MemberFile();
    static ArrayList<Member> uyeListesi = new ArrayList<>();
        public MemberFile() {
    }
    public static MemberFile getInstance() {
        return instance;
    }
    public void getMembers() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("src/TXT_Dosyalari/UyeListesi.txt"));
        String Uyeler;
        while ((Uyeler=read.readLine())!=null) {
            String[] uyeBilgi  = Uyeler.split("\t");
            String ad=uyeBilgi[0];
            String soyad=uyeBilgi[1];
            String id=String.valueOf(uyeBilgi[2]);
            Member member= new Member(ad,soyad,id);
            uyeListesi.add(member);
        }
    }
    public static void iadeEt(Member uye, Book kitap) throws IOException {
        if (uye.getMyList().contains(kitap.adi)) {
            uye.getMyList().remove(kitap.adi);
        }
        test.listeMevcut.add(kitap.adi);
        test.OduncAlinanlar.remove(kitap.adi);

        System.out.println(kitap.adi + " kitabini iade ettiniz");
        File fileAd = new File("src/TXT_Dosyalari/java_kitap_isim2.txt");
        FileWriter fwAd2 = new FileWriter(fileAd, true);
        PrintWriter pwAd2 = new PrintWriter(fwAd2);
        pwAd2.println(kitap.adi);
        pwAd2.close();

        File inputFile2 = new File("src/TXT_Dosyalari/odunc_alinanlar.txt");
        File tempFile2 = new File("myTempFile2.txt");

        BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(tempFile2));

        String lineToRemove = kitap.adi;
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

    public static void oduncAl (Member uye, Book kitap) throws IOException {
        uye.getMyList().add(kitap.adi);

        test.OduncAlinanlar.add(kitap.adi);
        test.listeMevcut.remove(kitap.adi);
        System.out.println(kitap.adi + " kitabini odunc aldiniz");

        File file = new File("src/TXT_Dosyalari/odunc_alinanlar.txt");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(kitap.adi);
        pw.close();

        File inputFile = new File("src/TXT_Dosyalari/java_kitap_isim2.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = kitap.adi;
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
