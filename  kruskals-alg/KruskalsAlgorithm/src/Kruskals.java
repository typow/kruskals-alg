

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
	
	private static int NUM_VERTICES;
	private static Comparable[] nodes;
	private static DisjointSet s;
	private static BinaryHeap heap;
	
	public void kruskal() {  
		int edgesAccepted = 0;
		//int counter = 0;
		s = new DisjointSet(NUM_VERTICES); 
		
		while (edgesAccepted < NUM_VERTICES - 1) {
			EdgeHeapNode e = null;
			try {
				e = (EdgeHeapNode) heap.deleteMin();
			} catch (EmptyHeapException e1) {
				System.out.println("Error deleting from heap");
			}  
			int u = Integer.parseInt((String) e.getVertexOne().getName());
			int v = Integer.parseInt((String) e.getVertexTwo().getName());

			int uset = s.find(u);
			int vset = s.find(v);   
			if (uset != vset) {
				edgesAccepted++;
				System.out.print("u = " + u + "  ");
				System.out.print("v = " + v);
				System.out.println(" w = " + e.getWeight());
				s.union(uset, vset);    			
			}
			//counter++;
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
	    heap = BinaryHeap.buildHeap(nodes);
	    //Arrays.sort(nodes);
	    System.out.println("Iterating through nodes...");
	    for (Comparable node : nodes) {
	    	EdgeHeapNode edgeNode = (EdgeHeapNode) node;
	    	System.out.println("w = " + edgeNode.getWeight() + "  u = " + 
	    			edgeNode.getVertexOne().getName() + "  v = " + edgeNode.getVertexTwo().getName());
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
