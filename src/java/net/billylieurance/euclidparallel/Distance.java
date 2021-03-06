/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.billylieurance.euclidparallel;

/**
 *
 * @author wlieurance
 */
public class Distance implements Comparable<Distance> {

    private String id;
    private int distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Distance o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        if (distance < o.getDistance()) {
            return BEFORE;
        } else if (distance > o.getDistance())  {
            return AFTER;
        }
            return EQUAL;

    }
    
    @Override
    public String toString(){
        return "{ id : " + id + ", distance : " + distance + "}";
    }

}
