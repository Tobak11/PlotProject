/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plot.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tobak11
 */
public class GridLineData {
    public static enum Direction {HORIZONTAL, VERTICAL};
    
    private List<Direction> directions;
    
    public GridLineData(List<Direction> directions){
        this.directions = directions;
    }
}
