package databasestest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Aaron Elizondo
 */
public class Graficos {

    
    public static void graficoBarras(String pQuery, Double pTiempoRelacional, 
            Double pTiempoEmpotrada,Double pTiempoMemoria) { 
       
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        
        
        
        dataset.addValue(pTiempoRelacional, "a", "Relacional"); 
        dataset.addValue(pTiempoEmpotrada, "a", "Empotrada"); 
        dataset.addValue(pTiempoMemoria, "a", "En memoria"); 
        
        JFreeChart chart = ChartFactory.createBarChart( 
            "Resultados de la operación", // El titulo de la gráfica 
            "Query ejecutado: "+pQuery, // Etiqueta de categoria 
            "Tiempo (us)", // Etiqueta de valores 
            dataset, // Datos 
            PlotOrientation.VERTICAL, // orientacion 
            false, // Incluye Leyenda 
            true, // Incluye tooltips 
            false // URLs? 
            ); 
        
        ChartFrame frame = new ChartFrame("Graficador", chart); 
        frame.pack(); 
        frame.setVisible(true); 
    }
    
    
    public static void main(String[] args) {
        graficoBarras("ujhyvbeduy", 2.454, 1.456, 4.123);
        
        long start = System.currentTimeMillis();
	//Aqui va lo que quiere medir
	long duracion = System.currentTimeMillis()-start;
    }
    
}
