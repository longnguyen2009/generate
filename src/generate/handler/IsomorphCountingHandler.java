package generate.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Graph;
import model.GraphSignature;

public class IsomorphCountingHandler implements GeneratorHandler {
    
    private Map<String, Graph> signatures;
    
    private Map<String, Integer> counts;
    
    private boolean ignoreDisconnected;
    
    private boolean reset;
    
    public IsomorphCountingHandler() {
        this(false);
    }
        
    public IsomorphCountingHandler(boolean ignoreDisconnected) {
        this.signatures = new HashMap<String, Graph>();
        this.counts = new HashMap<String, Integer>();
        this.ignoreDisconnected = ignoreDisconnected;
    }
    
    public void handle(Graph parent, Graph graph) {
        if (reset) {
            signatures.clear();
            counts.clear();
            reset = false;
        }
        if (ignoreDisconnected && !graph.isConnected()) {
            return;
        }
        String signature = new GraphSignature(graph).toCanonicalString();
        if (signatures.containsKey(signature)) {
            counts.put(signature, counts.get(signature) + 1);
        } else {
            signatures.put(signature, graph);
            counts.put(signature, 1);
        }
    }
    
    public Map<String, Graph> getSignatureMap() {
        return signatures;
    }
    
    public List<Graph> getNonIsomorphicGraphs() {
        return new ArrayList<Graph>(signatures.values());
    }
    
    public Map<Graph, Integer> getNonIsomorphicGraphCount() {
        Map<Graph, Integer> graphCounts = new HashMap<Graph, Integer>();
        for (String key : signatures.keySet()) {
            graphCounts.put(signatures.get(key), counts.get(key));
        }
        return graphCounts;
    }
    
    public void finish() {
        reset = true;
    }
    
}
