/**
*
* @author Sude Çakmak / sude.cakmak1@ogr.sakarya.edu.tr
* @since 19.04.2023
* <p>
* Komut parametresinden gönderilen dosyayı okuyan ve ilgili işlemleri yapan main metodunun bulunduğu sınıf
* </p>
*/
package odev;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fReader = new FileReader(args[0]);   //parametre olarak gönderilen dosyayı oku
		BufferedReader breader = new BufferedReader(fReader);
		String metin = "";    //okunacak dosyayı atamak için boş bir String
		String line;	//dosyayı satır satır okumak için
		//dosyayı satır satır okur ve ilgili Stringe atar
		while((line = breader.readLine())!=null) {
			metin += line + "\n";
		}
		breader.close();   //dosya okuma işlemini sonlandır
		
		Liste fonk = new Liste();  //ayıracağımız fonksiyonları tutmak için liste oluştur
		//String ifadeden fonksiyonları tespit eden regex ifade
		String regex ="(\\/\\*\\*\\n(\\*[^\\n]+\\n)+\\*\\/\\n*)*(public|protected|private|static)\\s(static\\s)?(int|void|String|char|double|float|Object)?[\\s]*[\\w]+[\\w\\d\\_]*\\s?\\([\\w\\d\\_\\.\\(\\)\\s]*\\)\\s?\\{\\s?((\\/\\*\\*\\n(\\*[^\\n]+\\n)+\\*\\/\\n?)|(\\/\\/[^\\n]+\\n)|(\\/\\*\\n?\\*?[^\\/\\*]+\\*\\/\\n?)|[^\\{\\}]|(\\{([^\\{\\}]|(\\{([^\\{\\}]|(\\{([^\\{\\}]|(\\{([^\\{\\}]|(\\{[^\\{\\}]*\\}))*\\}))*\\}))*\\}))*\\}))*\\}";
		Pattern oruntu = Pattern.compile(regex);
		Matcher eslesme = oruntu.matcher(metin);
		while(eslesme.find()) {
			//bulduğumuz her fonksiyon için ilgili sınıftan nesne oluştur ve bu nesneyi listeye ekle
			fonk.add(new Fonksiyon(metin.substring(eslesme.start(), eslesme.end())+"\n"));
		}
		
		//teksatir.txt isminde dosya oluştur
		File tfile = new File("teksatir.txt");
		if(!tfile.exists()) {
			tfile.createNewFile();
		}
		//teksatir.txt dosyasına yazmak için
		FileWriter twrite = new FileWriter(tfile);
		BufferedWriter tekwrite = new BufferedWriter(twrite);
		//coksatir.txt isminde dosya oluştur
		File cfile = new File("cokksatir.txt");
		if(!cfile.exists()) {
			cfile.createNewFile();
		}
		//coksatir.txt dosyasına yazmak için
		FileWriter cwrite = new FileWriter(cfile);
		BufferedWriter cokwrite = new BufferedWriter(cwrite);
		//javadoc.txt isminde dosya oluştur
		File jfile = new File("javadoc.txt");
		if(!jfile.exists()) {
			jfile.createNewFile();
		}
		//javadoc.txt dosyasına yazmak için
		FileWriter jwrite = new FileWriter(jfile);
		BufferedWriter javwrite = new BufferedWriter(jwrite);
		for(int i=0; i<fonk.size(); i++) {   //listedeki elemanları dolaş
			System.out.print(fonk.get(i));    //her eleman için konsola istenilen çıktıyı yaz
			tekwrite.write(fonk.get(i).getTekSatir());  //teksatir.txt'ye tekli satır yorumlarını yaz
			cokwrite.write(fonk.get(i).getCokSatir());  //coksatir.txt'ye çoklu satır yorumlarını yaz
			javwrite.write(fonk.get(i).getJavadoc());  //javadoc.txt'ye javadoc yorumlarını yaz
		}
		tekwrite.close();     //teksatir.txt yazma işlemini kapat
		cokwrite.close();		//coksatir.txt yazma işlemini kapat
		javwrite.close();			//javadoc.txt yazma işlemini kapat
	}

}
