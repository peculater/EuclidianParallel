/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.billylieurance.euclidparallel;

import java.util.stream.IntStream;

/**
 *
 * @author wlieurance
 */
public class TraitPoint {
    private static final int DIMENSIONS = 201;
    private int[] _points = new int[DIMENSIONS];
    private String id;
    
    public TraitPoint(String stringpoints){
        this.setPoints(stringpoints);
    }
    
    public Distance distanceFrom(TraitPoint otherpoint){
        Distance returnable = new Distance();
        returnable.setDistance(
                (int) Math.sqrt(
                        IntStream.range(0, DIMENSIONS)
                                .map( i-> (_points[i] - otherpoint.getPoints()[i]) * (_points[i] - otherpoint.getPoints()[i]))
                                .sum()
                )
        );
        returnable.setId(id);
        return returnable;
    }
    
    public int[] getPoints(){
        return _points;
    }
    
    public void setPoints(int[] points){
        _points = points;
        
    }
    
    public final void setPoints(String pointsCsv){
        int index  = 0;
        for (String coord : pointsCsv.split(",")){
            if (index == 0){
                this.setId(coord);
            }else{
                _points[index-1] = Integer.parseInt(coord);
            }
            index++;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
