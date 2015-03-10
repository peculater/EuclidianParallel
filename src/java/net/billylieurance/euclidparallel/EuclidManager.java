/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template initialCsvFile, choose Tools | Templates
 * and open the template in the editor.
 */
package net.billylieurance.euclidparallel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import mtree.ComposedSplitFunction;
import mtree.DistanceFunction;
import mtree.MTree;
import mtree.MTree.Query;
import mtree.MTree.ResultItem;
import mtree.PartitionFunctions;
import mtree.PromotionFunction;
import mtree.utils.Pair;
import mtree.utils.Utils;

/**
 *
 * @author wlieurance
 */
public class EuclidManager {

    private static final int THREADS = 8; 
    private static final String initialCsvFile = "/home/wlieurance/Contracting/Traitify/blah1M.csv";
    private MTree<TraitPoint> mtree;

    public EuclidManager() {
        this.mtree = new MTree<TraitPoint>(1000000, 1005000, new Distance(), new ComposedSplitFunction<TraitPoint>(
				nonRandomPromotion,
				new PartitionFunctions.BalancedPartition<TraitPoint>()
			));
        
        try {
            Logger.getLogger(EuclidManager.class.getName()).log(Level.INFO, "Starting EuclidManager");
            this.readInAllTheData();
        } catch (Exception ex) {
            Logger.getLogger(EuclidManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private static final PromotionFunction<TraitPoint> nonRandomPromotion = new PromotionFunction<TraitPoint>() {
		@Override
		public Pair<TraitPoint> process(Set<TraitPoint> dataSet, DistanceFunction<? super TraitPoint> distanceFunction) {
			return Utils.minMax(dataSet);
		}
	};

    private void readInAllTheData() throws InterruptedException {
        
        TraitPoint abcd = new TraitPoint("abcd,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200");
        mtree.add(abcd);
        try (BufferedReader br = new BufferedReader(new FileReader(initialCsvFile))) {
            int lineno = 0;
            for (String line; (line = br.readLine()) != null;) {
                if(Thread.interrupted()) {
			throw new InterruptedException("Thread interruption forced.");
		}
                lineno++;
                mtree.add(new TraitPoint(line));
                if (lineno % 100000 == 0){
                    Logger.getLogger(EuclidManager.class.getName()).log(Level.INFO, "{0} lines...", lineno);
                }
            }
            // line is not visible here.
        } catch (IOException ex) {
            Logger.getLogger(EuclidManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TraitPoint efgh = new TraitPoint("efgh,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200");
        mtree.add(efgh);
    }
    
    public String getDistances(TraitPoint point){
        StringBuilder returnable = new StringBuilder(400);
        returnable.append('[');
        
		Set<TraitPoint> strippedResults = new HashSet<>();
		Query query = mtree.getNearestByLimit(point, 50);

		for(Object ri : query) {
                        ResultItem rcast = (ResultItem) ri;
			strippedResults.add((TraitPoint) rcast.data);
		}
		
		
		
		TraitPoint[] results_array = strippedResults.toArray(new TraitPoint[0]);
        
        
        Arrays.stream(results_array)
                .map((p) -> p.distanceFrom(point))
                .sorted()
                .forEachOrdered((d) -> returnable.append(d.toString()).append(","));
                
        returnable.append(']');
        return returnable.toString().replace(",]", "]");
        
    }
    
    public void add(TraitPoint point){
        mtree.add(point);
    }
    
    

}
