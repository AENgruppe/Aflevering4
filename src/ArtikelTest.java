
//Emilie
public class ArtikelTest {

	public static void main(String[] args) {
		Forlag forlag1 = new Forlag("University Press", "Denmark");
		
		Tidsskrift tidsskrift1 = new Tidsskrift("Journal of Logic ");
		tidsskrift1.setForlag(forlag1);
	
		Tidsskrift tidsskrift2 = new Tidsskrift("Brain");	
		tidsskrift2.setForlag(forlag1);
		
		String[] fA = {"A. Abe", "A. Turing"};
		String[] fB = {"B. Bim"};
		
		Artikel artikel1 = new Artikel(fA, "A", tidsskrift1);
		
		Artikel artikel2 = new Artikel(fB, "B", tidsskrift1);
		
		Artikel[] artikel1Refs = new Artikel[] {artikel2};
		
		artikel1.setReferenceliste(artikel1Refs);
		
		
		System.out.println(artikel1);
		
		System.out.println(artikel2);
		
		System.out.println("1.st article's refs: " + artikel1.getReferenceList());

		
		

	}

}
