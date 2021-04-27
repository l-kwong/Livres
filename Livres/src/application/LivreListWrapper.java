package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="livres")
public class LivreListWrapper 
{
	private List<Livre> livres;
	@XmlElement(name="livres")
	public List<Livres> getLivres()
		{
			return livres;
		}
	public void setEtudiants(List<Livre> livres)
	{
		this.livres=livres;
	}

	
}