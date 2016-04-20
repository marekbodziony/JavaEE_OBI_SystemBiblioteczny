package systemBiblioteczny;

import java.util.ArrayList;
import java.util.List;

public class Katalog {

	private String nazwaKatalogu;
	private List<Ksiazka> listaKsiazek = new ArrayList<Ksiazka>();
	private int nrDoSygnatury; 
	
	// konstruktor
	public Katalog (String nazwaKat){
		nazwaKatalogu = nazwaKat;
	}
	
	// metoda dodaje ksiazke do katalogo (do listy ksiazek)
	public void dodajDoKatalogu (Ksiazka nowaKsiazka) throws KsiazkaJuzDodanaException {
		for (Ksiazka k : listaKsiazek){
			if (nowaKsiazka.equals(k)){throw new KsiazkaJuzDodanaException();}
		}
		listaKsiazek.add(nowaKsiazka);
		System.out.println("Dodano ksiazke do katalogu " + nazwaKatalogu);
		nrDoSygnatury +=1;
	}
	// metoda usuwa ksiazke z katalogu (z listy ksiazek)
	public void usunKsiazke (Ksiazka ksiazka) throws NieMaTakiejKsiazkiException{
		int i = 0;
		for (Ksiazka k : listaKsiazek){
			if (ksiazka.equals(k)){i++;}
		}
		if (i < 1) { throw new NieMaTakiejKsiazkiException();}
		listaKsiazek.remove(ksiazka);
		System.out.println("Usunieto ksiazke z katalogu " + nazwaKatalogu);
	}
	// metoda do wyswietlania ksiazek z katalogu
	public void wyswietlKsiazki(){
		System.out.println("Ksiazki w katalogu <" + nazwaKatalogu + "> :");
		for (int i = 0; i < listaKsiazek.size(); i++){
			System.out.println((i+1) + ". \""  + listaKsiazek.get(i).getTytul() + "\", " + listaKsiazek.get(i).getAutor() + ", ISBN: " + listaKsiazek.get(i).getISBN());
		}
	}
	
	// getters
	public String getNazwaKatalogu() {return nazwaKatalogu;}
	public List<Ksiazka> getListaKsiazek() {return listaKsiazek;}
	public int getNrDoSygnatury() {return nrDoSygnatury;};
	// setters
	public void setNazwaKatalogu(String nazwaKat) {nazwaKatalogu = nazwaKat;}
	public void setNrDoSygnatury (int nrSygn) {nrDoSygnatury = nrSygn;}
	
	// wyjatek wystepuje kiedy chcemy dodac do katalogu ksiazke, ktora jest juz dodana
	public class KsiazkaJuzDodanaException extends Exception{
		public KsiazkaJuzDodanaException(){
			super("Wyjatek! Ksiazka o podanym ISDN jest juz w katalogu!");
		}
	}
	// wyjatek wystepuje kiedy chcemy usunac z katalogu ksiazke, ktorej tam nie ma
	public class NieMaTakiejKsiazkiException extends Exception{
		public NieMaTakiejKsiazkiException(){
			super("Wyjatek! Ksiazki o podanym ISDN nie ma w katalogu!");
		}
	}
}
