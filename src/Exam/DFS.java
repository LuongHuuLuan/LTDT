package Exam;

import java.util.ArrayList;
import java.util.Stack;

public class DFS {
	private Stack<Integer> fronter;
	private boolean[] visited;
	private Graph g;
	private ArrayList<Integer> path = new ArrayList<Integer>();

	public DFS(Graph g) {
		fronter = new Stack<Integer>();
		visited = new boolean[g.getVertex()];
		this.g = g;
	}

	public void DSF(int start) {
		fronter.push(start);
		path.add(start + 1);
		visited[start] = true;
		while (!fronter.isEmpty()) {
			int v = fronter.peek();
			boolean check = false;
			for (int i = 0; i < g.getVertex(); i++) {
				if (visited[i] == false && g.getAdjacecencyMatrix()[v][i] > 0) {
					v = i;
					fronter.push(v);
					visited[v] = true;
					check = true;
					path.add(v + 1);
					break;
				}
			}
			if (!check) {
				fronter.pop();
			}
		}
		System.out.println(path);
	}
}
