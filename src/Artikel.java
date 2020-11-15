import java.util.ArrayList;

//Emilie
public class Artikel {
	String[] forfattere;
	String titel;
	Tidsskrift tidsskrift;
	String referenceListe;
	
	
	public Artikel(String[] forfattere, String titel, Tidsskrift tidsskrift) {
		this.forfattere = forfattere;
		this.titel = titel;
		this.tidsskrift = tidsskrift;
		this.referenceListe = "";
	}
	
	public void setReferenceliste(Artikel[] reference) {
		String refList = "";
		refList += reference[0];
		for (int i = 1; i < reference.length; i++) {
			refList += ", " + reference[i];
		}
		this.referenceListe = refList;
	}
	
	public String getReferenceList() {
		return this.referenceListe;
	}
	
	public String toString() {
		String out = "";
		out += forfattere[0];
		for(int i = 1; i<forfattere.length;i++) {
				out += " & " + forfattere[i];
		}
		return out + ": " + "\"" + titel + "\"." + " " + tidsskrift.forlag.navn;
	}

}
