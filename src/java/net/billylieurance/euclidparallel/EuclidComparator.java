/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.billylieurance.euclidparallel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author wlieurance
 */
public class EuclidComparator {
    //Start with naive
    private final List<TraitPoint> points = new LinkedList<>();
    
    public void addPoint(TraitPoint thepoint){
        points.add(thepoint);
    }
    
    public Distance[] sortedByDistance(TraitPoint fromPoint){
        Distance[] distances = this.unsortedByDistance(fromPoint);
        Arrays.sort(distances);
        return distances;
    }
    
    public Distance[] unsortedByDistance(TraitPoint fromPoint){
        Distance[] distances;// = new Distance[points.size()];
        distances = points.stream().parallel().map((point) -> point.distanceFrom(fromPoint)).toArray(size -> new Distance[size]);
        return distances;
    }
    
    
}
