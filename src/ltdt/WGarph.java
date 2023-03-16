package ltdt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WGarph {
	boolean directed = false;
	final double INF = Double.POSITIVE_INFINITY;
	double w[][];

	public WGarph(int n) {
		// TODO Auto-generated constructor stub
		w = new double[n][n];
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				w[i][j] = INF;
			}
		}
	}

	public void setDereced(boolean directed) {
		this.directed = directed;
	}

	public void addEdge(int u, int v, double w) {
		this.w[u][v] = w;
		if (!this.directed) {
			this.w[v][u] = w;
		}
	}

	public String printG() {
		String s = "";
		for (int i = 0; i < w.length; i++) {
			if (i != 0)
				s += "\n";
			for (int j = 0; j < w[i].length; j++)
				s += w[i][j] + "\t\t";
		}
		return s;

	}

	public String dijkstra(int n, int d) {
		String result = "";
		// khoi tao
		ArrayList<Integer> R = new ArrayList<Integer>();
		double[] L = new double[w.length];
		int[] P = new int[w.length];
		for (int i = 0; i < w.length; i++) {
			R.add(i);
			L[i] = INF;
			P[i] = -1;
		}
		// cho L cua dinh bat dau = 0
		L[n] = 0;
		// lap lai khi R khong rong
		while (!R.isEmpty()) {
			// tim phan tu L[v] nho nhat
			// lay ra dinh o dau R
			Integer v = R.get(0);
			// Kiem tra trong L co duong di ngan hon khong
			for (Integer i : R) {
				if (L[v] > L[i]) {
					v = i;
				}
			}
			// xoa dinh v trong R
			R.remove(v);
			// tim dinh trong R ke voi v de cap nhat L, P
			for (Integer i : R) {
				if (w[v][i] != INF)
					if (L[i] > L[v] + w[v][i]) {
						L[i] = L[v] + w[v][i];
						P[i] = v;
					}
			}
		}
		// lay duong di tu s den d dua tren P
		// i = d; path = rong
		// while (i!=s)
		// chen i vao phia ben trai trong path
		// i = p[i]
		// them din s vao ben trai
		int a = d;
		String path = "";
		while (n != a) {
			path = a + " " + path;
			a = P[a];
		}
		path = n + " " + path;
		System.out.println(path);
		// in duong di tat ca cac dinh con lai
		for (int i = 0; i < L.length; i++) {
			result += "Dinh: " + i + " (" + L[i] + ", " + P[i] + ") ";
		}
		return result;
	}

	public void Bellman_Ford(int n) {
		// khoi tao
		// moi dinh i, gan nhan boi cap (pre[i], l[i])
		double[] L = new double[w.length];
		double[] P = new double[w.length];
		String result = "";
		for (int i = 0; i < w.length; i++) {
			L[i] = INF;
			P[i] = -1;
		}
		L[n] = 0;
		//
		boolean stop = false;
		int k = 0;
		while (!stop) {
			stop = true;
			k++;
			for (int i = 0; i < w.length; i++) {
				for (int j = 0; j < w.length; j++) {
					if (L[j] > L[i] + w[i][j]) {
						L[j] = L[i] + w[i][j];
						P[j] = i;
						stop = false;
					}
				}
			}
			if (k > n) {
				if (stop == false) {
					System.out.println("do thi co chu trinh am");
					stop = true;
				}
			}
		}
	}
	public List<Edge> Kruskal(WGarph G) {
		return null;
		
	}

	public static void main(String[] args) {
		WGarph wGarph = new WGarph(5);
		wGarph.addEdge(0, 1, 1);
		wGarph.addEdge(0, 2, 5);
		wGarph.addEdge(0, 4, 6);
		wGarph.addEdge(1, 2, 3);
		wGarph.addEdge(1, 4, 2);
		wGarph.addEdge(2, 3, 4);
		wGarph.addEdge(2, 4, 3);
		wGarph.addEdge(3, 4, 2);

		System.out.println(wGarph.printG());
		System.out.println(wGarph.dijkstra(1, 2));
		wGarph.Bellman_Ford(0);

	}
}
