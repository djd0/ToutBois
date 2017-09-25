package controller.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Personnes {


	protected StringProperty nom;
	protected StringProperty prenom;
	protected StringProperty rue;
	protected StringProperty ville;
	protected StringProperty pays;
	
	protected IntegerProperty codePostal;

	//Constructeur
	public Personnes(String nom, String prenom, String rue, String ville,
					 String pays, int codePostal) 
	{
		super();
		
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.rue = new SimpleStringProperty(rue);
		this.ville = new SimpleStringProperty(ville);
		this.pays = new SimpleStringProperty(pays);
		this.codePostal = new SimpleIntegerProperty(codePostal);
	}

	public Personnes()
	{
		
	}
	
	// GET / SET
	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);;
	}

	public StringProperty nomProperty() {
        return nom;
    }
	
	public String getPrenom() {
		return prenom.get();
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}
	
	public StringProperty prenomProperty() {
        return prenom;
    }

	public String getRue() {
		return rue.get();
	}

	public void setRue(String rue) {
		this.rue.set(rue);
	}
	
	public StringProperty rueProperty() {
        return rue;
    }

	public String getVille() {
		return ville.get();
	}

	public void setVille(String ville) {
		this.ville.set(ville);
	}
	
	public StringProperty villeProperty() {
        return ville;
    }

	public String getPays() {
		return pays.get();
	}

	public void setPays(String pays) {
		this.pays.set(pays);
	}
	
	public StringProperty paysProperty() {
        return pays;
    }

	public int getCodePostal() {
		return codePostal.get();
	}

	public void setCodePostal(int codePostal) {
		this.codePostal.set(codePostal);
	}
	
	public IntegerProperty codePostalProperty() {
        return codePostal;
    }

	

	

	
}
