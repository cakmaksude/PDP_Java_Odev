/**
*
* @author Sude Çakmak / sude.cakmak1@ogr.sakarya.edu.tr
* @since 20.04.2023
* <p>
* Fonksiyon sınıfından oluşturulan nesneleri tutmak için sadece kullanılan metotlarla oluşturulan Liste sınıfı
* </p>
*/
package odev;

public class Liste {        
	private Fonksiyon fonk[];     //Fonksiyon sınıfından oluşturulan nesneler dizisi
	private int uzunluk;		//Dizinin uzunluğunu tuttuğumuz değişken
	private int kapasite;	//Dizinin ulaşabileceği maksimum uzunluk miktarı
	
	public Liste() {
		//Nesne oluşturulduğunda ilk değer atamaları yapılıyor
		this.uzunluk = 0;
		this.kapasite = 20;
		this.fonk = new Fonksiyon[20];
	}
	
	//Oluşturduğumuz dizinin kapasitesi yeterli olmazsa diye kapasiteyi 2 katına çıkaran fonksiyon
	private void reserve() {
		int new_capacity = 2*kapasite;   //yeni kapasite belirleniyor
		Fonksiyon yedek[] = new Fonksiyon[new_capacity];    //Eski diziyi taşımak için yeni kapasiteyle yedek oluşturuluyor
		//Eski dizi yedek diziye kopyalanıyor
		for(int i=0; i<uzunluk; i++) {
			yedek[i] = fonk[i];
		}
		fonk = yedek;   //Sınıfımızın değişkeni yeni diziyi gösteriyor
		kapasite = new_capacity;   //kapasite yeni kapasiteye yükseltiliyor
	}
	
	//Liste'ye Fonksiyon nesnesi eklemek için kullanılıyor
	public void add(Fonksiyon fnk) {
		if(uzunluk == kapasite) reserve();  //kapasite yeterli değilse reserve fonksiyonunu çağırır
		fonk[uzunluk] = fnk;   //sıradaki indekse yeni gelen nesneyi ekler
		uzunluk++;		//uzunluğu bir arttırır aynı zamanda sıradaki indekse geçmiş olur
	}
	
	//İlgili indeksteki nesne dışarıdan görüntülenmek istendiği zaman kullanılır
	public Fonksiyon get(int index) {
		return fonk[index];
	}
	
	//Dizide kaç tane eleman olduğunu döndürür
	public int size() {
		return uzunluk;
	}
}
