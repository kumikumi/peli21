/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

/**
 *
 * @author mikko
 */
public class Pelihahmo {
    private int x;
    private int y;
    
    public Pelihahmo(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
