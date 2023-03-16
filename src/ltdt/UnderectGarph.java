
package ltdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnderectGarph {
	protected int vertex;
	protected int[][] adjacencyMatrix;

	public UnderectGarph(int vertex) {
		this.vertex = vertex;
		adjacencyMatrix = new int[vertex][vertex];
	}

	public void addEdge(int v1, int v2) {
		adjacencyMatrix[v1][v2]++;
		adjacencyMatrix[v2][v1]++;
	}

	public void removeEdge(int v1, int v2) {
		adjacencyMatrix[v1][v2]--;
		adjacencyMatrix[v2][v1]--;
	}

	public int deg(int v) {
		int result = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			result += adjacencyMatrix[v][i];
		}
		return result;
	}

	// so canh cua do thi
	public int numberOfEdge() {
		int result = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			result += deg(i);
		}
		return result / 2;
	}

	// in ma tran ke
	public String printG() {
		String s = "";
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (i != 0)
				s += "\n";
			for (int j = 0; j < adjacencyMatrix[i].length; j++)
				s += adjacencyMatrix[i][j] + "\t";
		}
		return s;

	}

	// duyet chieu sau
	public List<Integer> DFS(int start) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		int v = start;
		visited.add(v);
		stack.push(v);
		while (!stack.isEmpty()) {
			v = stack.peek();
			int k = -1;
			// tim dinh ke chua dc them
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				if (adjacencyMatrix[v][i] == 1 && !visited.contains(i)) {
					k = i;
					break;
				}
			}
			// tim thay dinh ke
			if (k >= 0 && !visited.contains(k)) {
				visited.add(k);
				stack.push(k);
			} else {
				stack.pop();
			}
		}
		return visited;
	}

	// duyet chieu rong
	public List<Integer> BFS(int start) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		int v = start;
		visited.add(v);
		stack.push(v);
		while (!stack.isEmpty()) {
			v = stack.peek();
			int k = -1;
			// tim dinh ke chua dc them
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				if (adjacencyMatrix[v][i] == 1 && !visited.contains(i)) {
					k = i;
					visited.add(i);
					if (!stack.contains(i))
						stack.push(i);
				}
			}
			// tim thay dinh ke
			if (k < 0) {
				stack.pop();
			}

		}
		return visited;
	}

	public boolean ktraLienThong() {
		if (DFS(0).size() == vertex)
			return true;
		else
			return false;
	}

	public Map<Stack<Integer>, Boolean> kiemTraXtoiY(int x, int y) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		Boolean bl = false;
		Stack<Integer> stack = new Stack<Integer>();
		visited.add(x);
		stack.push(x);
		while (!stack.isEmpty()) {
			x = stack.peek();
			int k = -1;
			// tim dinh ke
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				if (adjacencyMatrix[x][i] == 1 && !visited.contains(i)) {
					k = i;
					break;
				}
			}
			// tim thay dinh ke
			if (k >= 0) {
				// neu k = y thi ngung vong lap
				if (k == y) {
					bl = true;
					visited.add(k);
					stack.push(k);
					break;
				} else {
					stack.push(k);
					visited.add(k);
				}
				// khong tim thay
			} else {
				stack.pop();
			}
		}
		HashMap<Stack<Integer>, Boolean> result = new HashMap<Stack<Integer>, Boolean>();
		result.put(stack, bl);
		return result;
	}

	public List<Integer> path(int x, int y) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		visited.add(x);
		stack.push(x);
		while (!stack.isEmpty()) {
			x = stack.peek();
			int k = -1;
			// tim dinh ke
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				if (adjacencyMatrix[x][i] == 1 && !visited.contains(i)) {
					k = i;
					break;
				}
			}
			// tim thay dinh ke
			if (k >= 0) {
				// neu k = y thi ngung vong lap
				if (k == y) {
					visited.add(k);
					stack.push(k);
					break;
				} else {
					stack.push(k);
					visited.add(k);
				}
				// khong tim thay
			} else {
				stack.pop();
			}
		}
		return stack;
	}

	// kiem tra euler
	public boolean checkEuler() {
		int count = 0;
		if (ktraLienThong()) {
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				if (deg(i) % 2 != 0) {
					count++;
					break;
				}
			}
			if (count == 0)
				return true;
		}
		return false;
	}

	// kiem tra nua euler
	public boolean checHalfEuler() {
		int count = 0;
		if (ktraLienThong()) {
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				if (deg(i) % 2 != 0)
					count++;
			}
			if (count == 2)
				return true;
		}
		return false;
	}

	public List<Integer> duyetTatCaDinh(int start) {
		List<Integer> visited = new ArrayList<Integer>();
		int v = start;
		while (visited.size() < adjacencyMatrix.length) {
			if (!visited.contains(v)) {
				List<Integer> visited1 = DFS(v);
				for (Integer i : visited1) {
					visited.add(i);
				}
				for (int i = 0; i < adjacencyMatrix.length; i++) {
					if (!visited.contains(i)) {
						v = i;
						break;
					}
				}
			} else {
				break;
			}
		}
		return visited;
	}

	public int timTPLienThong() {
		int count = 0;
		List<Integer> visited = new ArrayList<Integer>();
		int v = 0;
		while (visited.size() < adjacencyMatrix.length) {
			if (!visited.contains(v)) {
				List<Integer> visited1 = DFS(v);
				count++;
				for (Integer i : visited1) {
					visited.add(i);
				}
				for (int i = 0; i < adjacencyMatrix.length; i++) {
					if (!visited.contains(i)) {
						v = i;
						break;
					}
				}
			} else {
				break;
			}
		}
		return count;
	}

	public void checkUndirectedGarp() {
		boolean check = true;
		for (int i = 0; i < adjacencyMatrix.length % 2; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				if (adjacencyMatrix[i][j] != adjacencyMatrix[j][i])
					check = false;
			}
		}
		if (check == false)
			System.out.println("khong la do thi co huong");
		else
			System.out.println("la do thi co huong");
	}

	public boolean checkTree() {
		if (ktraLienThong() && numberOfEdge() == this.vertex - 1)
			return true;
		return false;
	}

	public List<Integer> duongDiEuler() {
		List<Integer> result = new ArrayList<Integer>();
		if (checkEuler()) {
			int[][] save = new int[adjacencyMatrix.length][adjacencyMatrix.length];
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				for (int j = 0; j < adjacencyMatrix.length; j++) {
					save[i][j] = adjacencyMatrix[i][j];
				}
			}
			Stack<Integer> stack = new Stack<Integer>();
			int v = 0;
			result.add(0);
			stack.push(v);
			while (!stack.isEmpty()) {
				v = stack.peek();
				int k = -1;

				for (int i = 0; i < save.length; i++) {
					if (save[v][i] == 1) {
						k = i;
						break;
					}
				}
				if (k != -1) {
					stack.push(k);
					result.add(k);
					save[v][k]--;
					save[k][v]--;
				} else {
					stack.pop();
				}
			}
		}
		return result;
	}


	public static void main(String[] args) {
		UnderectGarph g1 = new UnderectGarph(9);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(0, 8);
		g1.addEdge(1, 4);
		g1.addEdge(1, 2);
		g1.addEdge(2, 4);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);
		g1.addEdge(3, 5);
		g1.addEdge(6, 7);
		g1.printG();
		System.out.println("Bac cua dinh 0: " + g1.deg(0));
		System.out.println("So canh cua do thi: " + g1.numberOfEdge());
		System.out.println("Duyet theo chieu sau: " + g1.DFS(0));
		System.out.println("Duyet theo chieu rong: " + g1.BFS(1));
		System.out.println("Kiem tra co lien thong hay khong: " + g1.ktraLienThong());
		System.out.println("kiem tra tu 0 den 5: " + g1.kiemTraXtoiY(1, 8));
		System.out.println("Kiem tra euler: " + g1.checkEuler());
		System.out.println("Kiem tra nua euler: " + g1.checHalfEuler());
		System.out.println("Duyet toan bo do thi: " + g1.duyetTatCaDinh(0));
		System.out.println("Tim thanh phan lien thong: " + g1.timTPLienThong());
		g1.checkUndirectedGarp();
		System.out.println(g1.duongDiEuler());
		System.out.println("\n");
		UnderectGarph g2 = new UnderectGarph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(0, 3);
		g2.addEdge(0, 4);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.addEdge(2, 4);
		System.out.println(g2.printG());
		System.out.println("Bac cua dinh 0: " + g2.deg(0));
		System.out.println("So canh cua do thi: " + g2.numberOfEdge());
		System.out.println("Duyet theo chieu sau: " + g2.DFS(0));
		System.out.println("Duyet theo chieu rong: " + g2.BFS(1));
		System.out.println("Kiem tra co lien thong hay khong: " + g2.ktraLienThong());
		System.out.println("kiem tra tu 0 den 5: " + g2.kiemTraXtoiY(1, 8));
		System.out.println("Kiem tra euler: " + g2.checkEuler());
		System.out.println("Kiem tra nua euler: " + g2.checHalfEuler());
		System.out.println("Duyet toan bo do thi: " + g2.duyetTatCaDinh(0));
		System.out.println("Tim thanh phan lien thong: " + g2.timTPLienThong());
		g2.checkUndirectedGarp();
		System.out.println(g2.duongDiEuler());
//		UnderectGarph g = new UnderectGarph(3);
//		g.addEdge(0, 1);
//		g.addEdge(0, 2);
//		g.addEdge(1, 2);
//		System.out.println(g.printG());
//		System.out.println(g.duongDiEuler());
	}
}
