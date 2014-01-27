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
     * @return Satunnaisesti valittu lause, josta käy ilmi, että aika on
     * loppunut pelissä.
     */
    public String arvoTimeUpLause() {
        Random arpoja = new Random();
        switch (arpoja.nextInt(6)) {
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
            case 5:
                return "Now you were just TOO careful! Time's up.";
            default:
                throw new IllegalStateException("Arpoja antoi väärän luvun");
        }
    }

    /**
     *
     * @return Satunnaisesti valittu lause, josta käy ilmi, että pelaaja on
     * töpeksinyt pelissä.
     */
    public String arvoEndGameLause() {
        Random arpoja = new Random();
        switch (arpoja.nextInt(25)) {
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
            case 14:
                return "Seriously? Are you even trying?";
            case 15:
                return "Why would you do that?";
            case 16:
                return "I believe that's the wrong way, sir!";
            case 17:
                return "A surprising move you decided to make..";
            case 18:
                return "Go play something that requires no skill";
            case 19:
                return "Not bad for someone with no hands..";
            case 20:
                return "You should learn to play or go outside";
            case 21:
                return "Hm. It appears you may have failed.";
            case 22:
                return "Your friends must be laughing at you";
            case 23:
                return "Defeated by your own lack of skill";
            case 24:
                return "Oh well. At least you tried.";
            default:
                throw new IllegalStateException("Arpoja antoi väärän luvun");
        }
    }

    public String arvoDeadLockLause() {
        Random arpoja = new Random();
        switch (arpoja.nextInt(5)) {
            case 0:
                return "Was that your last straw?";
            case 1:
                return "Feeling deadlocked?";
            case 2:
                return "You didn't happen to get stuck, did you?";
            case 3:
                return "How did you get in a spot like that?";
            case 4:
                return "Feeling like you had nowhere to go?";
            default:
                throw new IllegalStateException("Arpoja antoi väärän luvun");
        }
    }
}
