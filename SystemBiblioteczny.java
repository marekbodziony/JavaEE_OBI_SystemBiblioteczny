package systemBiblioteczny;

import java.util.ArrayList;
import java.util.List;

import systemBiblioteczny.Katalog.KsiazkaJuzDodanaException;
import systemBiblioteczny.Katalog.NieMaTakiejKsiazkiException;

public class SystemBiblioteczny {
	
	
	// ------------------------------------------------------------------------------------------------------ DODAJ KSIAZKE
	public static void dodajKsiazkeDoSystemu(Ksiazka ksiazka, Katalog kat){
		
		
		try {
			// spr czy ksiazka ma poprawny nr ISBN : jesli NIE to przerwij wykonywanie bloku, jesli TAK to dodaj ksiazke do katalogu, utworz sygnature ksiazki,
			// zaktualizuj liczbe dostepnych egzemplazy, wygeneruj nr kodu kreskowego ksiazki, itp.
			sprawdzISBN (ksiazka.getTytul(), ksiazka.getISBN());	// ---------------------------------------- wyrzuca wyjatek jesli bledny ISBN 
			kat.dodajDoKatalogu(ksiazka);							// ---------------------------------------- wyrzuca wyjatek, jesli ksiazka jest juz na liscie
			ksiazka.setSygnatura(utworzSygnature(kat));
			aktualizujLiczbeEgzemplarzy(ksiazka,kat);
			ksiazka.setKodKreskowy(generujKodKreskowy(ksiazka));		
			System.out.println("= DODANO KSIAZKE DO SYSTEMU =");
		}
		catch (BlednyNrIsdnException exception){
			System.out.println(">>> " + exception.getMessage() + " <<<");
			System.out.println("= NIE DODANO KSIAZKI DO SYSTEMU ! =");
		}
		catch (KsiazkaJuzDodanaException exception){
			System.out.println(">>> " + exception.getMessage() + " <<<");
			System.out.println("= NIE DODANO KSIAZKI DO SYSTEMU =");
		}
	}
	
	// ------------------------------------------------------------------------------------------------------- USUN KSIAZKE
	public static void usunKsiazkeZSystemu(Ksiazka ksiazka, Katalog kat){
			// jesli ksiazka nie jest aktualnie wypozyczona to usun z katalogu, zaktualizuj liczbe egzemplarzy w systemie
		try{	
			if (!ksiazka.getCzyWypozyczona()){
				kat.usunKsiazke(ksiazka);
				aktualizujLiczbeEgzemplarzy(ksiazka,kat);
				System.out.println("OK! Usunieto ksiazke z systemu");
				// jesli brak ksiazek w katalogu to wyzeruj sygnature dla przyszlych ksiazek
				if (kat.getListaKsiazek().size() == 0) kat.setNrDoSygnatury(0);
			}
			else {
				System.out.println("Blad! Ksiazka jest aktualnie wypozyczona. NIE MOZNA USUNAC Z SYSTEMU!");
			}
		}
		catch (NieMaTakiejKsiazkiException exception) {
			System.out.println(">>> " + exception.getMessage() + " <<<");
			System.out.println("= NIE USUNIETO KSIAZKI Z SYSTEMU =");
		}
	}

	// pomocnicza metoda do sprawdzania poprawnosci nr ISBN
	private static boolean sprawdzISBN(String tytul, int isbn) throws BlednyNrIsdnException {
		if (isbn <= 0) {throw new BlednyNrIsdnException();}
		else {
			System.out.println("OK! Tytul zgodny z nr ISBN");
		}
		return true;
		}
	// pomocnicza metoda do tworzenia sygnatury ksiazki
	private static String utworzSygnature(Katalog kat){
		String sygn = kat.getNazwaKatalogu().substring(0, 3).toUpperCase() + kat.getNrDoSygnatury();
		return sygn;
	}

	// pomocnicza metoda do aktualizacji dostepnych egzemplarzy ksiazki 
	private static void aktualizujLiczbeEgzemplarzy(Ksiazka ksiazka, Katalog kat) {
		List<Ksiazka> listaEgzemplarzy = new ArrayList<Ksiazka>();
		// sprawdz ile ksiazek tego samego autora i o tym samym tytule jest w katalogu
		for (Ksiazka k : kat.getListaKsiazek()){
			if (ksiazka.getTytul() == k.getTytul() && ksiazka.getAutor() == k.getAutor()){
				listaEgzemplarzy.add(k);
			}
		}
		// ustaw dla kazdego egzemplarza ksiazki odpowiednia wartosc okreslajaca ilosc egzemplarzy w katalogu
		for (Ksiazka k : listaEgzemplarzy){
			k.setLiczbaEgzemplarzy(listaEgzemplarzy.size());
		}
		System.out.println("Aktualnie w systemiej jest : " + listaEgzemplarzy.size() + " egz.");
	}
	// pomocnicza metoda do generowania kodu kreskowego
	private static String generujKodKreskowy(Ksiazka ksiazka){
		String kod = "0000000";
		String liczbaKsiazek = ksiazka.liczbaDodanychKsiazek + "";
		kod = kod.substring(0, kod.length()-liczbaKsiazek.length()) + liczbaKsiazek;
		return kod;
	}
	public static class BlednyNrIsdnException extends Exception{
		public BlednyNrIsdnException(){
			super("Blad! Bledny nr ISDN !!!");
		}
	}
}
