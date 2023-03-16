package Exam;

import java.util.ArrayList;
import java.util.Stack;

public class Euler {
	private Graph g;
	private Stack<Integer> fronter;
	private ArrayList<String> path;
	private ArrayList<String> remove;

	public Euler(Graph g) {
		this.g = g;
		fronter = new Stack<Integer>();
		path = new ArrayList<String>();
		remove = new ArrayList<String>();
	}

	public void euler(int v) {
		fronter.push(v);
		while (!fronter.isEmpty()) {
			v = fronter.peek();
			int k = -1;
			for (int i = 0; i < g.getVertex(); i++) {
				String checkRemove1 = v+","+i;
				String checkRemove2 = i+","+v;
				if (g.getAdjacecencyMatrix()[v][i] > 0 && (!remove.contains(checkRemove1) && !remove.contains(checkRemove2))) {
					fronter.push(i);
					remove.add(checkRemove1);
					k = 1;
					break;
				}
			}
			if (k == -1) {
				int u = fronter.pop();
				path.add((u + 1) + "");
			}
		}
		if (remove.size() == g.getEdge()) {
			System.out.println(path);
		} else {
			System.out.println("k co chu trinh euler");
		}
	}
}
