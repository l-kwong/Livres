package application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="livres")
public class LivreListWrapper 
{
	private List<Livre> livres;
	@XmlElement(name="livres")
	public List<Livre> getLivres()
		{
			return livres;
		}
	public void setLivres(List<Livre> livres)
	{
		this.livres=livres;
	}

	
}