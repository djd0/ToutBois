package controller.model;



import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Representant extends Personnes {

	
	

	// init du compteur de representants
	protected IntegerProperty numeroRepresentant;
	//public static int numeroRep = 0;
	
	protected DoubleProperty tauxCom;
	protected DoubleProperty salaire;

	

	public Representant(String nom, String prenom, String rue, String ville, String pays, int codePostal,
						double tauxCom, double salaire)
	{
		super(nom, prenom, rue, ville, pays, codePostal);
		
		//Incrementation compteur clients
		//numeroRep ++;
		
		this.numeroRepresentant = new SimpleIntegerProperty();
		this.tauxCom = new SimpleDoubleProperty(tauxCom);
		
		this.salaire = new SimpleDoubleProperty(salaire);
		
		
		

	}
	

	public Representant() 
	{
		this(null, null, null, null, null, 0, 0, 0);
	}



	
	//Get / Set
	public int getNumeroRepresentant() {
		return numeroRepresentant.get();
	}


	public void setNumeroRepresentant(int numeroRepresentant) {
		this.numeroRepresentant.set(numeroRepresentant);
	}
	
	public IntegerProperty numeroRepresentantProperty() {
		return numeroRepresentant;
	}


	public double getTauxCom() {
		return tauxCom.get();
	}


	public void setTauxCom(double tauxCom) {
		this.tauxCom.set(tauxCom);
	}
	
	public DoubleProperty tauxComProperty() {
		return tauxCom;
	}


	public double getSalaire() {
		return salaire.get();
	}


	public void setSalaire(double salaire) {
		this.salaire.set(salaire);
	}
	
	public DoubleProperty salaireProperty() {
		return salaire;
	}

	@Override
	public String toString() {
		return this.getNumeroRepresentant() + " - " + this.getNom() + " " + this.getPrenom();
	}

	
}
