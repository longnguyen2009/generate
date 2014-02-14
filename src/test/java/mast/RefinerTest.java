package mast;

import graph.group.GraphDiscretePartitionRefiner;
import graph.model.Graph;
import group.Permutation;
import group.PermutationGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class RefinerTest {
    
    public void test(Graph tree) {
        GraphDiscretePartitionRefiner refiner = new GraphDiscretePartitionRefiner();
        PermutationGroup group = refiner.getAutomorphismGroup(tree);
        Map<String, List<Permutation>> pmap = new HashMap<String, List<Permutation>>();
        for (Permutation p : group.all()) {
//            System.out.println(counter + "\t" + p.toCycleString());
            String type = Arrays.toString(p.getType());
            List<Permutation> plist;
            if (pmap.containsKey(type)) {
                plist = pmap.get(type);
            } else {
                plist = new ArrayList<Permutation>();
                pmap.put(type, plist);
            }
            plist.add(p);
        }
        int counter = 0;
        for (String type : pmap.keySet()) {
            List<Permutation> plist = pmap.get(type);
            for (Permutation p : plist) {
                System.out.println(counter + "\t" + p.toCycleString() + "\t" + type);
                counter++;
            }
        }
//        System.out.println(tree + "\t" + group.getSize());
    }
    
    @Test
    public void threeClawTest() {
        test(new Graph("0:1,0:2,0:3"));
    }
    
    @Test
    public void twoByTwoTest() {
        test(new Graph("0:1,0:2,1:3,1:4,2:5,2:6"));
    }
    
    @Test
    public void twoTwoTwoTest() {
        test(new Graph("0:1,0:2,1:3,1:4,2:5,2:6,3:7,3:8,4:9,4:10,5:11,5:12,6:13,6:14"));
    }
    
}
