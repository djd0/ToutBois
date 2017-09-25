package controller.model;




import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <b>Client est la classe qui représente une entité client</b>
 * <p>
 * Un client est caractérisé par les informations suivantes :
 * <ul>
 * <li>Des donnees de la classe personne : à savoir, nom, prenom, rue, ville, codePostal, pays</li>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>Un numero de telephone</li>
 * <li>Une adresse email</li>
 * <li>Une société</li>
 * <li>Un numero de SIRET</li>
 * <li>Un nombre de commande défini à zéro</li>
 * <li>Un numero du représentant qui le suit</li>
 * </ul>
 * </p>
 * 
 * @see Personnes
 * @author quentin et bilal
 * @version 1.0
 */

public class Client extends Personnes {

	/**
	 * L'ID du client. Il ne peut etre changé et s'incremente à chaque instance d'un objet client
	 * 
	 * @see Client#getNumeroClient()
	 * @see Client#Client(String, String, String, String, String, int, String, String, String, String, int, int)
	 */
	// Init du compteur client
	protected IntegerProperty numeroClient;
	public static int numClient = 0;

	
	/**
	 * Le telephone du client. Ce numero est changeable
	 * 
	 * @see Client#getTelephone()
	 * @see Client#setTelephone(String)
	 */
	protected StringProperty telephone;
	
	
	/**
	 * Le mail du client. Ce mail est changeable
	 * 
	 * @see Client#getEmail()
	 * @see Client#setEmail(String)
	 */
	protected StringProperty email;
	
	
	
	/**
	 * La societe du client. Changeable
	 * 
	 * @see Client#getEnseigne()
	 * @see Client#setEnseigne(String)
	 */
	protected StringProperty enseigne;
	
	
	/**
	 * Le siret de la société du client. Changeable
	 * 
	 * @see Client#getSiret()
	 * @see Client#setSiret(String)
	 */
	protected StringProperty siret;
	
	
	/**
	 * Le nombre de commande du client. Changeable
	 * 
	 * @see Client#getNbCommande()
	 * @see Client#setNbCommande(int)
	 */
	protected IntegerProperty nbCommande;
	
	
	/**
	 * Le numero du représentant associé au client. Changeable
	 * 
	 * @see Client#getNumeroRepresentant()
	 * @see Client#setNumeroRepresentant(int)
	 */
	protected IntegerProperty numeroRepresentant;
	
	
	/**
	 * Reference au representant de la classe representant
	 * 
	 * @see Representant
	 * @see Representant#Representant(String, String, String, String, String, int, double, double)
	 */
	protected Representant rep;
	
	
	

	/**
	 * Constructeur Client
	 * <p>
	 * A la construction d'un objet Client, les attributs du client sont instancier en new properties
	 * </p>
	 * 
	 * @param nom
	 * @param prenom
	 * @param rue
	 * @param ville
	 * @param pays
	 * @param codePostal
	 * @param telephone
	 * @param email
	 * @param enseigne
	 * @param siret
	 * @param nbCommande
	 * @param numeroRepresentant
	 * 
	 * @see Client#telephone
	 * @see Client#email
	 * @see Client#enseigne
	 * @see Client#siret
	 * @see Client#nbCommande
	 * @see Client#numeroRepresentant
	 */
	//Constructeur
	public Client(String nom, String prenom, String rue, String ville,
			String pays, int codePostal, String telephone,
			String email, String enseigne, String siret, int nbCommande, int numeroRepresentant) 
	{
		super(nom, prenom, rue, ville, pays, codePostal);

		this.telephone = new SimpleStringProperty(telephone);
		this.email = new SimpleStringProperty(email);
		this.enseigne = new SimpleStringProperty(enseigne);
		this.siret = new SimpleStringProperty(siret);
		this.nbCommande = new SimpleIntegerProperty(nbCommande);
		this.numeroRepresentant =  new SimpleIntegerProperty(numeroRepresentant);
		
		//Incrementation compteur clients
		numClient ++;
		this.numeroClient = new SimpleIntegerProperty(numClient);
		
		

	}
	/**
	 * Constructeur vide Client
	 */
	public Client()
	{
		this(null, null, null, null, null, 0, null, null, null, null, 0, 0);
	}
	
	
	/**
	 * Constructeur Client avec un prospect en parametre
	 * <p>
	 * A la creation d'un objet client, les donnees du prospects sont récupérées et utilisé pour définir le nouveau client
	 * </p>
	 * @param prospect
	 * 
	 * @see Prospect
	 * @see Prospect#Prospect()
	 * 
	 */
	public Client(Prospect prospect) 
	{
		
		this.telephone = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.enseigne = new SimpleStringProperty();
		this.siret = new SimpleStringProperty();
		this.nbCommande = prospect.nbCommandeProperty();
		this.nom = prospect.nomProperty();
		this.prenom = prospect.prenomProperty();
		this.ville = prospect.villeProperty();
		this.pays = prospect.paysProperty();
		this.rue = prospect.rueProperty();
		this.codePostal = prospect.codePostalProperty();
		numClient++;
		this.numeroClient = new SimpleIntegerProperty(numClient);
		this.numeroRepresentant =  new SimpleIntegerProperty();
		
		
		
		
		
		
		
	}


	//GET / SET
	public int getNumeroClient() {
		return numeroClient.get();
	}


	public void setNumeroClient(int numeroClient) {
		this.numeroClient.set(numeroClient);
	}
	
	public IntegerProperty numeroClientProperty() {
		return numeroClient;
	}


	public String getTelephone() {
		return telephone.get();
	}


	public void setTelephone(String telephone) {
		this.telephone.set(telephone);
	}
	
	public StringProperty telephoneProperty() {
		return telephone;
	}


	public String getEmail() {
		return email.get();
	}


	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public StringProperty emailProperty() {
		return email;
	}


	public String getEnseigne() {
		return enseigne.get();
	}


	public void setEnseigne(String enseigne) {
		this.enseigne.set(enseigne);
	}
	
	public StringProperty enseigneProperty() {
		return enseigne;
	}


	public String getSiret() {
		return siret.get();
	}


	public void setSiret(String siret) {
		this.siret.set(siret);
	}

	public StringProperty siretProperty() {
		return siret;
	}

	public int getNbCommande() {
		return nbCommande.get();
	}


	public void setNbCommande(int nbCommandes) {
		this.nbCommande.set(nbCommandes);
	}
	
	public IntegerProperty nbCommandeProperty() {
		return nbCommande;
	}

	public int getNumeroRepresentant() {
		return numeroRepresentant.get();
	}


	public void setNumeroRepresentant(int numeroRepresentant) {
		this.numeroRepresentant.set(numeroRepresentant);
	}
	
	public IntegerProperty numeroRepresentantProperty() {
		return numeroRepresentant;
	}


	
	
	
	
}
