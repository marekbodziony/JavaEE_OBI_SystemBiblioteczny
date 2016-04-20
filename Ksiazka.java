package systemBiblioteczny;

public class Ksiazka {
	
	private String tytul;
	private String autor;
	private int isbn;
	private String sygnatura;
	private int liczbaEgzemplarzy = 1;
	private boolean czyWypozyczona;
	private String kodKreskowy = "0000000";
	public static int liczbaDodanychKsiazek;
	
	// konstruktor 
	public Ksiazka (String tytulK, String autorK, int isbnK){
		tytul = tytulK;
		autor = autorK;
		isbn = isbnK;
		sygnatura = "brak";
		liczbaDodanychKsiazek++;
	}
	
	// getters i setters
	public String getTytul(){ return tytul;}
	public String getAutor(){ return autor;}
	public int getISBN(){ return isbn;}
	public String getSygnatura() { return sygnatura;}
	public int getLiczbaEgzemplarzy() {return liczbaEgzemplarzy;}
	public boolean getCzyWypozyczona(){ return czyWypozyczona;}
	public String getKodKreskowy() {return kodKreskowy;}
	// setters
	public void setTytul(String tytulK){ tytul = tytulK; }
	public void setAutor(String autorK){ autor = autorK;}
	public void setISBN(int isbnK) { isbn = isbnK;}
	public void setSygnatura (String sygnaturaK){ sygnatura = sygnaturaK;}
	public void setLiczbaEgzemplarzy (int ilEgz) {liczbaEgzemplarzy = ilEgz;}
	public void setCzyWypozyczona(boolean wypozyczona) {czyWypozyczona = wypozyczona;}
	public void setKodKreskowy(String kod) { kodKreskowy = kod;}
	
	// metoda do wyswietlania informacji o ksiazce
	public void wyswietlInfo (){
		System.out.println("\"" + tytul +"\", "  + autor + ", ISBN: " + isbn + "; Sygnatura: " + sygnatura + "; Liczba egzemplarzy: " + liczbaEgzemplarzy 
				+ "; kod: " + kodKreskowy + "; WYPOZYCZONA: " + czyWypozyczona);
	}
	
	
}
