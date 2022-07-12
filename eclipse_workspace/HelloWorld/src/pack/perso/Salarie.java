package pack.perso;

public class Salarie extends Personnage{

	private String entreprise_;
	private String metier_;
	
	public Salarie()
	{
		this.entreprise_="Sans nom";
		this.metier_="Sans nom";
	}
	
	Salarie(Personnage p, String str1, String str2)
	{
		this.entreprise_=str1;
		this.metier_=str2;
		this.name_=p.name_;
	}
	
	public void Say_complete_hello()
	{
		this.SayReady();
		System.out.println("I'm " + this.name_+ " and i'm " + this.metier_ + " for " + this.entreprise_);
	}
	
	protected void finalize() {
	    System.out.print("je vais disparaître !");
	  }
}
