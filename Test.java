package systemBiblioteczny;

public class Test {

	public static void main(String[] args){
		
		// katalogi
		Katalog historyczne = new Katalog("Historyczne");
		
		// ksiazki
		Ksiazka k1 = new Ksiazka("Krzyzacy","Henryk Sienkiewicz",43552);
		Ksiazka k2 = new Ksiazka("Krzyzacy","Henryk Sienkiewicz",67890);
		Ksiazka k3 = new Ksiazka("Potop","Henryk Sienkiewicz",34567);
		
		// --- obsluga Systemu Bibliotecznego ---
		
		// --- dodaj ksiazke do systemu ---------
		SystemBiblioteczny.dodajKsiazkeDoSystemu(k1, historyczne);
		SystemBiblioteczny.dodajKsiazkeDoSystemu(k1, historyczne);
		SystemBiblioteczny.dodajKsiazkeDoSystemu(k2, historyczne);
		historyczne.wyswietlKsiazki();
		
		System.out.println();
		
		
		// --- usun ksiazke z systemu
		SystemBiblioteczny.usunKsiazkeZSystemu(k1, historyczne);
		SystemBiblioteczny.usunKsiazkeZSystemu(k1, historyczne);
		historyczne.wyswietlKsiazki();
	}
}
