package Exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	private Queue<Integer> fronter;
	private boolean[] visited;
	private Graph g;
	private ArrayList<Integer> path = new ArrayList<Integer>();

	public BFS(Graph g) {
		fronter = new LinkedList<Integer>();
		visited = new boolean[g.getVertex()];
		this.g = g;
	}

	public void BSF(int start) {
		fronter.add(start);
		path.add(start + 1);
		visited[start] = true;
		while (!fronter.isEmpty()) {
			int v = fronter.peek();
			boolean check = false;
			for (int i = 0; i < g.getVertex(); i++) {
				if (visited[i] == false && g.getAdjacecencyMatrix()[v][i] > 0) {
					v = i;
					fronter.add(v);
					visited[v] = true;
					check = true;
					path.add(v + 1);
					break;
				}
			}
			if (!check) {
				fronter.poll();
			}
		}
		System.out.println(path);
	}
}
