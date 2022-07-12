package pack.perso;

public class Mini_game {

	public static void main(String[] args) {
		Personnage P1 = new Personnage("Pierre", 100, 30, 20);
		Personnage P2 = new Personnage();
		P1.SayReady();
		Personnage.hello();
		P2.SayReady();
		
		Salarie s1 = new Salarie();
		s1.SayReady();
		
		Salarie s2 = new Salarie(P1, "AKKA", "Ingénieur");
		s2.Say_complete_hello();
	}
	protected void finalize() {
	    System.out.print("je vais disparaître !");
	  }
}
