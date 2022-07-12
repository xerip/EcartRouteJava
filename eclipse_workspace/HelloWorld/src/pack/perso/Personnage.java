package pack.perso;

@SuppressWarnings("unused")
public class Personnage {

	protected String name_;
	private int pv_;
	private int att_;
	private int def_;
	
	Personnage()
	{
		this.name_="Unamed";
		this.pv_=0;
		this.att_=0;
		this.def_=0;
	}
	
	Personnage(String name, int pv, int att, int def)
	{
		this.name_=name;
		this.pv_=pv;
		this.att_=att;
		this.def_=def;
	}
	
	protected void SayReady()
	{
		System.out.println(this.name_+" ready for battle !");
	}
	
	protected void finalize() {
	    System.out.print("je vais disparaître !");
	  }
	
	static void hello() {System.out.println("hello");}
}
