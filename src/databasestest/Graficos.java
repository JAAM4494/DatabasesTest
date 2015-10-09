package databasestest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Clase encargada de generar graficos.
 * @author Aaron Elizondo
 * @version 2.0
 */
public class Graficos {

    /**
     * Genera un grafico con los parametros especificados.
     * @param pQuery Operacion realizada.
     * @param pTiempoRelacional Duracion de mySQL.
     * @param pTiempoEmpotrada Duracion de SQLite.
     * @param pTiempoMemoria Duracion de H2DB.
     */
    public void graficoBarras(String pQuery, Double pTiempoRelacional,
            Double pTiempoEmpotrada, Double pTiempoMemoria) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(pTiempoRelacional, "a", "Relacional");
        dataset.addValue(pTiempoEmpotrada, "a", "Empotrada");
        dataset.addValue(pTiempoMemoria, "a", "En memoria");

        JFreeChart chart = ChartFactory.createBarChart(
                "Resultados de la operación", // El titulo de la gráfica 
                "Query ejecutado: " + pQuery, // Etiqueta de categoria 
                "Tiempo (ms)", // Etiqueta de valores 
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

    /*
     public static void main(String[] args) {
     graficoBarras("ujhyvbeduy", 2.454, 1.456, 4.123);
        
     long start = System.currentTimeMillis();
     //Aqui va lo que quiere medir
     long duracion = System.currentTimeMillis()-start;
     }*/
}
