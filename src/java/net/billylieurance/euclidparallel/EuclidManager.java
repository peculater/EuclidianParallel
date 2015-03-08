/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template initialCsvFile, choose Tools | Templates
 * and open the template in the editor.
 */
package net.billylieurance.euclidparallel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author wlieurance
 */
public class EuclidManager {

    private static final int THREADS = 8; 
    private static final String initialCsvFile = "/home/wlieurance/Contracting/Traitify/blah.csv";
    private EuclidComparator[] comparators;

    public EuclidManager() {
        this.comparators = new EuclidComparator[THREADS];
        try {
            Logger.getLogger(EuclidManager.class.getName()).log(Level.INFO, "Starting EuclidManager");
            for (int i = 0; i < THREADS; i++) {
                comparators[i] = new EuclidComparator();
            }
            this.readInAllTheData();
        } catch (Exception ex) {
            Logger.getLogger(EuclidManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void readInAllTheData() {

        try (BufferedReader br = new BufferedReader(new FileReader(initialCsvFile))) {
            int lineno = 0;
            for (String line; (line = br.readLine()) != null;) {
                comparators[lineno % THREADS].addPoint(new TraitPoint(line));
            }
            // line is not visible here.
        } catch (IOException ex) {
            Logger.getLogger(EuclidManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getDistances(TraitPoint point){
        StringBuilder returnable = new StringBuilder(400);
        returnable.append('[');
        Stream<Distance> all_distances = Arrays.stream(comparators).parallel()
                .map((c) -> c.unsortedByDistance(point))
                .flatMap((a) -> Arrays.stream(a));
        
        all_distances.parallel()
                .sorted()
                .forEachOrdered((d) -> returnable.append(d.toString()).append(","));
                
        returnable.append(']');
        return returnable.toString().replace(",]", "]");
        
    }
    
    public void add(TraitPoint point){
        comparators[(int)(System.currentTimeMillis() % 1000) % THREADS].addPoint(point);
    }
    
    

}
