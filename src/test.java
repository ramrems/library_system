import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class test {
    static ArrayList<String> listeMevcut= new ArrayList<>();
    static ArrayList<Object> OduncAlinanlar = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("\n" + "İremin kutuphanesine hosgeldiniz");
        BookFile.getInstance().getBooks();
        MemberFile.getInstance().getMembers();
        Movie.getInstance().getMovies();
        new Member();

        BufferedReader oku1 = new BufferedReader(new FileReader("src/TXT_Dosyalari/java_kitap_isim2.txt"));
        BufferedReader oku2 = new BufferedReader(new FileReader("src/TXT_Dosyalari/odunc_alinanlar.txt"));

        while (true) {
            String kitap = oku1.readLine();
            if (kitap == null) {
                break;
            }
            listeMevcut.add(kitap);
        }
        oku1.close();
        while (true) {
            String odunc = oku2.readLine();
            if (odunc == null) {
                break;
            }
            OduncAlinanlar.add(odunc);
        }
        oku2.close();

        while (true) {
                System.out.println("\nYapmak istediginiz islemi seciniz");
                System.out.println("1) Uye Olma");
                System.out.println("2) Giriş Yapma");
                System.out.println("0) Sistemden Çıkış");
                int y;
                try {
                    Scanner scanner2 = new Scanner(System.in);
                    y = scanner2.nextInt();
                    if (y == 1) {
                        Member yeniUye = new Member();
                        Member.UyelikIslemleri(yeniUye);
                    } else if (y == 2) {
                        Member Uye = new Member();

                        if ((Member.GirisYap(Uye))){
                            System.out.println("Giris Basarili");

                            while (true) {
                                try {
                                    System.out.println("\nYapmak istediginiz islemi seciniz");
                                    System.out.println("1) Aldıklarımı Göster");
                                    System.out.println("2) Kitap Alma");
                                    System.out.println("3) Kitap Iade Etme");
                                    System.out.println("4) Kitap Listeleme");
                                    System.out.println("5) Kitap Arama");
                                    System.out.println("6) Film Listeleme");
                                    System.out.println("7) Film Alma");
                                    System.out.println("8) Film Iade Etme");
                                    System.out.println("9) Mevcut Metaryelleri Listeleme");
                                    System.out.println("10) Alinan Metaryelleri Listeleme");
                                    System.out.println("0) Geri Gitme");
                                    int x;
                                    Scanner scanner1 = new Scanner(System.in);
                                    x = scanner1.nextInt();
                                    if (x == 1) {
                                        if (Uye.getMyList().size() != 0) {
                                            System.out.println("**Ödünç Aldıklarım**");
                                            for (int i = 0; i < (Uye.getMyList().size()); i++) {
                                                System.out.println(Book.ANSI_YELLOW+Uye.getMyList().get(i)+ Book.ANSI_RESET);
                                            }
                                        } else {
                                            System.out.println("Ödünç Aldığınız Materyal Bulunmamaktadır");
                                        }
                                    } else if (x == 2) {
                                        Scanner scanner3 = new Scanner(System.in);
                                        Book odunc = new Book();
                                        System.out.println("Odunc Almak Isteginiz kitabi Giriniz");

                                        String odunc_kitap = scanner3.nextLine().toLowerCase();
                                        odunc.adi = odunc_kitap;

                                        if (listeMevcut.contains(odunc_kitap) && BookFile.kitaplistesi.toString().contains(odunc.adi)) {
                                            MemberFile.oduncAl((Member.GirisKontrol(Member.GirisKontrol(Uye))), odunc);
                                        } else {
                                            System.out.println("Kitap kutuphanede mevcut olmadigi icin odunc alamazsiniz ");
                                        }
                                    } else if (x == 3) {
                                        Scanner scanner3 = new Scanner(System.in);
                                        Book iade = new Book();
                                        System.out.println("Iade Etmek Isteginiz kitabi Giriniz");
                                        String iade_kitap = scanner3.nextLine().toLowerCase();
                                        iade.adi = iade_kitap;

                                        if (OduncAlinanlar.contains(iade_kitap) && BookFile.kitaplistesi.toString().contains(iade.adi)) {
                                            MemberFile.iadeEt((Member.GirisKontrol(Member.GirisKontrol(Uye))), iade);
                                        } else {
                                            System.out.println("Bu kitap sizde olmadigi icin iade edemezsiniz");
                                        }
                                    } else if (x == 4) {
                                        Book.Listele();
                                    } else if (x == 5) {
                                        Book.Search();
                                        Book.Arama.removeAll(Book.Arama);
                                    } else if (x == 6) {
                                        Movie.Listele();
                                    } else if (x == 7) {
                                        Scanner scanner3 = new Scanner(System.in);
                                        Movie odunc = new Movie();
                                        System.out.println("Odunc Almak Isteginiz Filmi Giriniz");

                                        String odunc_film = scanner3.nextLine().toLowerCase();
                                        odunc.adi = odunc_film;
                                        if (listeMevcut.contains(odunc_film) && Movie.filmlistesi.toString().contains(odunc.adi)) {
                                            Movie.oduncAl((Member.GirisKontrol(Member.GirisKontrol(Uye))), odunc);
                                        } else {
                                            System.out.println("Film Kutuphanede Mevcut Olmadigi icin Odunc Alamazsiniz ");
                                        }
                                    } else if (x == 8) {
                                        Scanner scanner3 = new Scanner(System.in);
                                        Movie iade = new Movie();
                                        System.out.println("Iade Etmek Isteginiz Filmi Giriniz");
                                        String iade_film = scanner3.nextLine().toLowerCase();
                                        iade.adi = iade_film;

                                        if (OduncAlinanlar.contains(iade_film) && Movie.filmlistesi.toString().contains(iade.adi)) {
                                            Movie.iadeEt((Member.GirisKontrol(Member.GirisKontrol(Uye))), iade);
                                        } else {
                                            System.out.println("Bu film sizde olmadigi icin iade edemezsiniz");
                                        }
                                    } else if (x == 9) {
                                        System.out.println("**Mevcut Metaryallerin Listesi**");
                                        for (int j = 0; j < test.listeMevcut.size(); j++) {
                                            System.out.println(test.listeMevcut.get(j));
                                        }
                                    } else if (x == 10) {
                                        System.out.println("**Ödünç Alınan Metaryallerin Listesi**");
                                        try {
                                            for (int j = 0; j < test.OduncAlinanlar.size(); j++) {
                                                System.out.println(Book.ANSI_YELLOW+test.OduncAlinanlar.get(j)+ Book.ANSI_RESET);
                                            }
                                        } catch (NoSuchElementException e) {
                                            System.out.println("Ödünç Alınan Meteryal Bulunmamaktadır");
                                        }

                                    } else if (x == 0) {
                                        break;
                                    }
                                }
                                catch (InputMismatchException e) {
                                    System.out.println("Lutfen gecerli bir deger giriniz ");
                                }
                            }
                        }else {
                            System.out.println("Giriş yapmak için üye olunuz ");
                        }
                    } else if (y == 0) {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Lutfen gecerli bir deger giriniz");
                }
            }
        }
    }


