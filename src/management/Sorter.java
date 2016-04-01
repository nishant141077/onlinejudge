/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Problem;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author nishant
 */
public class Sorter {

    public static void sortProblemsByName(List<Problem> problemsList, int mode) {
        if(mode == 1) { //increasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    int returnVal = p1.name.compareTo(p2.name);
                    if(returnVal < 0) return -1;
                    else if(returnVal > 0) return 1;
                    else return 0;
                }              
            });
        }
        else if(mode == 2) {
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    int returnVal = p1.name.compareTo(p2.name);
                    if(returnVal < 0) return 1;
                    else if(returnVal > 0) return -1;
                    else return 0;
                }              
            });
        }
    }

    public static void sortProblemsByCode(List<Problem> problemsList, int mode) {
        if(mode == 1) { //increasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    int returnVal = p1.code.compareTo(p2.code);
                    if(returnVal < 0) return -1;
                    else if(returnVal > 0) return 1;
                    else return 0;
                }              
            });
        }
        else if(mode == 2) {
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    int returnVal = p1.code.compareTo(p2.code);
                    if(returnVal < 0) return 1;
                    else if(returnVal > 0) return -1;
                    else return 0;
                }              
            });
        }
    }

    public static void sortProblemsByDifficulty(List<Problem> problemsList, int mode) {
        if(mode == 1) { //increasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    return (p1.difficulty < p2.difficulty) ? -1 : 1;
                }
            });
        }
        else if(mode == 2) { //decreasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    return (p1.difficulty < p2.difficulty) ? 1 : -1;
                }
            });
        }
    }

    public static void sortProblemsBySolvedBy(List<Problem> problemsList, int mode) {
        if(mode == 1) { //increasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    return (p1.solvedBy < p2.solvedBy) ? -1 : 1;
                }
            });
        }
        else if(mode == 2) { //decreasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    return (p1.solvedBy < p2.solvedBy) ? 1 : -1;
                }
            });
        }
    }

    public static void sortProblemsByAccuracy(List<Problem> problemsList, int mode) {
        if(mode == 1) { //increasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    return (p1.accuracy < p2.accuracy) ? -1 : 1;
                }
            });
        }
        else if(mode == 2) { //decreasing
            Collections.sort(problemsList, new Comparator<Problem>() {
                @Override
                public int compare(Problem p1, Problem p2) {
                    return (p1.accuracy < p2.accuracy) ? 1 : -1;
                }
            });
        }
    }
    
}
