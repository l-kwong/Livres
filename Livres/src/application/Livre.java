package application;

public class Livre 
{
	private String titre;
	private String auteur;
	private String genre;
	private Double pages;
	
	// constructeur vide
	public Livre()
	{
		this(null,null);
	}
	
	// constructeur avec 2 paramètres
	public Livre(String titre, String auteur)
	{
		this.titre=titre;
		this.auteur=auteur;
		this.genre="";
		this.pages=0.0;
	}
	
	// Getters and Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Double getPages() {
		return pages;
	}

	public void setPages(Double pages) {
		this.pages = pages;
	}
	
}
