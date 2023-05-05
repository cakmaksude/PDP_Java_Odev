/**
*
* @author Sude Çakmak / sude.cakmak1@ogr.sakarya.edu.tr
* @since 18.04.2023
* <p>
* Dosyadan okuduğumuz fonksiyonlarla ilgili işlemleri yapan ve ilgili değişkenleri içerisinde tutan sınıf
* </p>
*/
package odev;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Fonksiyon {
	private String fonk;            //dosyadan okunan fonksiyonu ve fonksiyon yorumlarını içinde tutan değişken
	private int tek_satir_say;			//tekli satır yorum sayısı
	private int cok_satir_say;			//çoklu satır yorum sayısı
	private int javadoc_say;			//javadoc yorum sayısı
	private String tek_satir;		//tekli yorum satırlarının hepsi
	private String cok_satir;		//çoklu yorum satırlarının hepsi
	private String javadoc;			//javadoc yorumların hepsi
	private String fonkİsmi;	//Fonksiyonun ismi
	
	
	public Fonksiyon(String fonk) {		//kurucu fonksiyon   //dosyadan gelen string ifadeyi parametre olarak alıyor
		this.fonk = fonk;
		
		//ileride çağırılan fonksiyonlarda sorun olmaması için tüm değişkenlere ilk değer ataması yapılıyor
		this.tek_satir_say=0;
		this.cok_satir_say = 0;
		this.javadoc_say = 0;
		this.tek_satir = "";
		this.cok_satir = "";
		this.javadoc = "";
		tekliSatirSay();
		cokluSatirSay();
		javadocSay();
		isimBul();
	}
	
	//tekli yorum satırlarını bulup ilgili Stringe atayan ve sayısını hesaplayan fonksiyon
	private void tekliSatirSay() { 
		String regex = "/{2}[^\n]*[\\w\\d]+[^\n]*\n";  //tekli yorum satırını bulan regex ifade
		Pattern oruntu = Pattern.compile(regex);
		Matcher eslesme = oruntu.matcher(fonk);
		while(eslesme.find()) {
			this.tek_satir_say++;
			tek_satir += (fonk.substring(eslesme.start(), eslesme.end())+"\n");
		}
		
	}
	
	//çoklu yorum satırlarını bulup ilgili Stringe atayan ve sayısını hesaplayan fonksiyon
	private void cokluSatirSay() { 
		String regex = "\\/\\*\\n?\\*?[^\\/\\*]+\\*\\/\\n?";  //çoklu yorum satırını bulan regex ifade
		Pattern oruntu = Pattern.compile(regex);
		Matcher eslesme = oruntu.matcher(fonk);
		while(eslesme.find()) {
			this.cok_satir_say++;
			cok_satir += (fonk.substring(eslesme.start(), eslesme.end())+"\n");
		}
		
	}
	
	//javadoc yorum satırlarını bulup ilgili Stringe atayan ve sayısını hesaplayan fonksiyon
	private void javadocSay() { 
		String regex = "\\/\\*\\*\\n(\\*[^\\n]+\\n)+\\*\\/\\n?";  //javadoc yorum satırını bulan regex ifade
		Pattern oruntu = Pattern.compile(regex);
		Matcher eslesme = oruntu.matcher(fonk);
		while(eslesme.find()) {
			this.javadoc_say++;
			javadoc += (fonk.substring(eslesme.start(), eslesme.end())+"\n");
		}
		
	}
	
	//dosyadan okunan fonksiyonun ismini bulan fonksiyon
	private void isimBul() {
		String regex = "(?<=\s)[\\w\\d_ıİöÖüÜçÇşŞğĞ]*(?=\\()";  //fonksiyon ismini bulmayı sağlayan regex ifade
		Pattern oruntu = Pattern.compile(regex);
		Matcher eslesme = oruntu.matcher(fonk);
		while(eslesme.find()) {
			this.fonkİsmi = fonk.substring(eslesme.start(), eslesme.end());
		}
	}
	
	//main fonksiyonunda teksatir.txt dosyasına yazdırmak için değer döndüren fonksiyon 
	public String getTekSatir() {
		return "Fonksiyon: "+ fonkİsmi + "\n" + tek_satir + "------------------------------\n";
		
	}
	
	//main fonksiyonunda coksatir.txt dosyasına yazdırmak için değer döndüren fonksiyon 
	public String getCokSatir() {
		return "Fonksiyon: "+ fonkİsmi + "\n" + cok_satir + "-------------------------------\n";
	}
	
	//main fonksiyonunda javadoc.txt dosyasına yazdırmak için değer döndüren fonksiyon 
	public String getJavadoc() {
		return "Fonksiyon: "+ fonkİsmi + "\n" + javadoc + "---------------------------------\n";
		
	}
	
	//Fonksiyon ile ilgili bilgilerin ekrana istenilen formatta yazılmasını sağlayan fonksiyon
	@Override
	public String toString() {
		return "\t Fonksiyon: "+fonkİsmi +"\n\t\t Tek Satır Yorum Sayısı: "+tek_satir_say
				+"\n\t\t Çok Satırlı Yorum Sayısı:  "+cok_satir_say
				+"\n\t\t Javadoc Yorum Sayısı:   "+javadoc_say
				+"\n-------------------------------------------------------------------------\n";
	}

	
}
