
public class Tidsskrift {
	String titel;
	Forlag forlag;
	String issn;
	
	public Tidsskrift(String titel) {
		this.titel = titel;
		
	}
	
	public void setIssn(String issn){
		this.issn = issn;
		
	}
	
	public void setForlag(Forlag forlag) {
		this.forlag = forlag;
	}

}
