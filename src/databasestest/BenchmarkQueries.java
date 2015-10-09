/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package databasestest;

/**
 *
 * @author macbook
 */
public class BenchmarkQueries {

    public void  BenchmarkInsert(String sql) {
        String human ="human";
        
         for (int i = 0; i < 100; i++) {
                String human2 =human+i;

            System.out.println("print Benchmark"+human2);
            
        }
    }
    
    public static void main(String[] args) {
        BenchmarkQueries newQuery= new BenchmarkQueries();
        newQuery.BenchmarkInsert("");
        
        
    }
    
  
    
    
    
}

