

import java.util.Arrays;
import java.util.Iterator;
/**
 * 
 */

/**
 * @author David Kim
 * @author Tyler Powers
 * @author Mahir Ahmed
 */
public class Kruskals {
	public Kruskals() {
		kruskal();
	}
	
	static int NUM_VERTICES;
	static EdgeHeapNode[] nodes;
	static DisjointSet s;
	
	public void kruskal() {  
		int edgesAccepted = 0;
		int counter = 0;
		s = new DisjointSet(NUM_VERTICES); 
		while (edgesAccepted < NUM_VERTICES - 1) {
			EdgeHeapNode e = nodes[counter];  
			int u = Integer.parseInt((String) e.getVertexOne().getName());
			int v = Integer.parseInt((String) e.getVertexTwo().getName());
			System.out.print("u = " + u + "  ");
			System.out.println("v = " + v);
			int uset = s.find(u);
			int vset = s.find(v);   
			if (uset != vset) {
				edgesAccepted++;
				s.union(uset, vset);    			
			}
			counter++;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {   
		SimpleGraph G = new SimpleGraph(); 
		GraphInput.LoadSimpleGraph(G);  
		nodes = new EdgeHeapNode[G.numEdges()]; 
		Edge e;
	    int counter = 0;
	    Iterator k;
	    for (k = G.edges(); k.hasNext(); ) {
	    	e = (Edge) k.next();  
	    	nodes[counter] = new EdgeHeapNode((double) e.getData(), e.getFirstEndpoint(), 
	    			e.getSecondEndpoint());
	    	counter++;
	    }
	    Arrays.sort(nodes);
	    System.out.println("Iterating through nodes...");
	    for (EdgeHeapNode node : nodes) {
	    	System.out.println("w = " + node.getWeight() + "  u = " + 
	    			node.getVertexOne().getName() + "  v = " + node.getVertexTwo().getName());
	    }
	    System.out.println();
	    NUM_VERTICES = G.numVertices();
	    System.out.println("Number of vertices = " + NUM_VERTICES);
	    System.out.println("Number of edges = " + G.numEdges());
	    System.out.println();
	    new Kruskals(); 
	    System.out.println();
	    for(int i = 0; i < NUM_VERTICES; i++) {
			System.out.println("root of " + i + ": " + s.find(i));
		}
	    System.out.println();
//	    System.out.println("Check data in uptree");
//	    for (int i = 0; i < NUM_VERTICES; i++) { 
//			System.out.println("uptree[" + i + "]: " + s.getUptree()[i]);
//	    } 
	} 
}
