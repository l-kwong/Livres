package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class PagesStat {
	@FXML
	private BarChart<String, Integer> barChart;
	
	@FXML
	private CategoryAxis xAxis;
	
	private ObservableList<String> intervalPages=FXCollections.observableArrayList();
	
	@FXML 
	private void initialize()
	{
		intervalPages.add("0-100");
		intervalPages.add("100-200");
		intervalPages.add("200-300");
		intervalPages.add("300-400");
		intervalPages.add("400-500");
		intervalPages.add("500-600");
		xAxis.setCategories(intervalPages);
	}
	
	public void SetStats(List<Livre> livres)
	{
		// Compter les livres appartenent à la même tracnhe d'age
		
		int[] pagesCounter= new int[6];	// Tableau pour les nombres tranches de pages
		
		for(Livre e:livres)
		{
			double pages= e.getPages();
			
			if(pages<=100)
				pagesCounter[0]++;
			else
				if(pages<=200)
					pagesCounter[1]++;
				else
					if(pages<=300)
						pagesCounter[2]++;
					else
						if(pages<=400)
							pagesCounter[3]++;
						else
							if(pages<=500)
								pagesCounter[4]++;
							else
								pagesCounter[5]++;
			
		}
		
		XYChart.Series<String, Integer> series=new XYChart.Series<>();
		series.setName("pages"); 	// Leegende pour le graphique
		
		// Création du graphique
		for(int i=0; i<pagesCounter.length;i++)
		{
			series.getData().add(new XYChart.Data<>(intervalPages.get(i), pagesCounter[i]));
		}
		barChart.getData().add(series);
	}

}