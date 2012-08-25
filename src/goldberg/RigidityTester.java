package goldberg;

import group.SSPermutationGroup;
import model.Graph;
import model.GraphDiscretePartitionRefiner;

/**
 * Not really intended for any real use, but for testing a set of graphs to see which are 
 * 'rigid' (have a AUT group of just {I}).
 * 
 * @author maclean
 *
 */
public class RigidityTester {
    
    public static boolean isRigid(Graph g) {
        GraphDiscretePartitionRefiner refiner = new GraphDiscretePartitionRefiner();
        SSPermutationGroup autG = refiner.getAutomorphismGroup(g);
//        System.out.println("|AUT| = " + autG.order() + " for " + g);
        return autG.order() == 1;
    }
    
    public static int findUniqueMaxVert(Graph g) {
        int n = g.vsize();
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            int d = g.degree(i); 
            if (d > max) {
                max = d;
                maxIndex = i;
            } else if (d == max) {
                // another vertex with this degree - so it's not unique
                maxIndex = -1;  
            }
        }
        return maxIndex;
    }
    
    public static boolean hasVertexOfDegreeNMinusOne(Graph g) {
        int n = g.vsize();
        for (int i = 0; i < n; i++) {
            if (g.degree(i) == n - 1) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isEasy(Graph g) {
        if (hasVertexOfDegreeNMinusOne(g)) {
            return true;
        } else {
            int i = findUniqueMaxVert(g);
            if (i == -1) {
                return false;
            } else {
                return isRigid(g.minus(i));
            }
        }
    }
    
}
