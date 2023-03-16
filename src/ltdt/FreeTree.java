package ltdt;

import java.util.ArrayList;
import java.util.List;

public class FreeTree extends UnderectGarph {

	public FreeTree(int vertex) {
		super(vertex);
		// TODO Auto-generated constructor stub
	}

	public int doLechTam(int v) {
		int result = 0;
		int max = 0;
		for (int i = 0; i < adjacencyMatrix.length - 1; i++) {
			if (path(v, i).size() - 1 > max) {
				max = path(v, i).size() - 1;
				result = max;
			}
		}
		return result;
	}

	public List<Integer> timCacTam() {
		List<Integer> result = new ArrayList<Integer>();
		int min = doLechTam(0);
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (doLechTam(i) <= min) {
				min = doLechTam(i);
			}
		}
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (doLechTam(i) == min) {
				result.add(i);
			}
		}
		return result;
	}
	public int timBanKinh() {
		int result = 0;
		List<Integer> tam = timCacTam();
		result = doLechTam(tam.get(0));
		return result;
	}

	public static void main(String[] args) {
//		FreeTree g = new FreeTree(8);
//		g.addEdge(0, 1);
//		g.addEdge(0, 2);
//		g.addEdge(0, 3);
//		g.addEdge(1, 4);
//		g.addEdge(1, 5);
//		g.addEdge(2, 6);
//		g.addEdge(3, 7);
		FreeTree g = new FreeTree(11);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(1, 4);
		g.addEdge(1, 5);
		g.addEdge(1, 6);
		g.addEdge(4, 7);
		g.addEdge(4, 8);
		g.addEdge(6, 9);
		g.addEdge(6, 10);
		System.out.println(g.printG());
		System.out.println("DFS: " + g.DFS(0));
		System.out.println("Lien thong? " + g.ktraLienThong());
		System.out.println("Co la cay? " + g.checkTree());
		System.out.println("Do lech tam dinh 1 la: " + g.doLechTam(1));
		System.out.println("Cac tam la: " + g.timCacTam());
		System.out.println("Ban kinh la: " + g.timBanKinh());
	}
}
