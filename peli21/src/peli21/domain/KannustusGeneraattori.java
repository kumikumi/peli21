/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import java.util.Random;

/**
 * Tämä luokka generoi pelaajaa kannustavia lauseita.
 */
public class KannustusGeneraattori {
    /**
     * 
     * @return Satunnaisesti valittu lause, josta käy ilmi, että aika on loppunut pelissä.
     */

    public String arvoTimeUpLause() {
        Random arpoja = new Random();
        switch (arpoja.nextInt(5)) {
            case 0:
                return "No need to rush, but your time is up";
            case 1:
                return "Did you fall asleep? Time's up.";
            case 2:
                return "Now why would you let the time run out?";
            case 3:
                return "Watch the red bar, it indicates your time";
            case 4:
                return "You should move every once in a while...";
            default:
                throw new IllegalStateException("Arpoja antoi väärän luvun");
        }
    }

    /**
     * 
     * @return Satunnaisesti valittu lause, josta käy ilmi, että pelaaja on töpeksinyt pelissä.
     */
    public String arvoEndGameLause() {
        Random arpoja = new Random();
        switch (arpoja.nextInt(14)) {
            case 0:
                return "You need to be more careful";
            case 1:
                return "Watch where you're going!";
            case 2:
                return "There are easier games out there...";
            case 3:
                return "Perhaps you should just give up";
            case 4:
                return "What did you expect to happen?";
            case 5:
                return "Watch your step!";
            case 6:
                return "Oh dear! You've made a terrible mistake";
            case 7:
                return "Feeling tired?";
            case 8:
                return "Watch the arrows before you move";
            case 9:
                return "If there's no arrow, don't go that way!";
            case 10:
                return "Nice try, except it failed";
            case 11:
                return "When did anyone fail so hard?";
            case 12:
                return "Mind the gap!";
            case 13:
                return "No, that's now how you win the game";
            default:
                throw new IllegalStateException("Arpoja antoi väärän luvun");
        }
    }
}
