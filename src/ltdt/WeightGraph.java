package ltdt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class WeightGraph {
	private boolean directed = false;
	private List<Vertex> vertexs;
	private List<Edge> edges;
	private double[][] w;
	private final double INF = Double.POSITIVE_INFINITY;

	public WeightGraph(int number_Of_Vertex) {
		w = new double[number_Of_Vertex][number_Of_Vertex];
		vertexs = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < number_Of_Vertex; i++) {
			Vertex vertex = new Vertex(i + "");
			vertex.setIndex(i);
			vertexs.add(vertex);
			for (int j = 0; j < number_Of_Vertex; j++) {
				w[i][j] = INF;
			}
		}

	}

	public WeightGraph() {

	}

	public static void main(String[] args) {
		WeightGraph wGarph = new WeightGraph();
		wGarph.importFormFile("E:\\TaiLieuHocTap\\Nam2\\Ki2\\LTDT_ThayViet\\Graph\\g5.txt");
		System.out.println(wGarph.printGraph());
		wGarph.floyd("4", "3");

//		System.out.println(wGarph.printGraph());
//		System.out.println("DFS: " + wGarph.printPath(wGarph.DFS("s")));
//		System.out.println("BFS: " + wGarph.printPath(wGarph.BFS("s")));
		Result dijstra = wGarph.dijstra("4");
		System.out.println("Dijstra " + "\n" + dijstra.pathToAll());
		Result Bellman_ford = wGarph.Bellman_ford("4");
		System.out.println("Bellman_ford" + "\n" + Bellman_ford.pathToAll());
		WeightGraph kruskal = wGarph.kruskal();
		System.out.println(kruskal.printGraph());
	}

	public void setNameEdge(String[] listName) {
		int i = 0;
		for (Vertex v : vertexs) {
			v.setName(listName[i]);
			i++;
		}
	}

	public void setNameEdge(String name, int id) {
		for (Vertex v : vertexs) {
			if (v.getIndex() == id) {
				v.setName(name);
			}
		}
	}

	public void changeName(String oldName, String newName) {
		for (Vertex v : vertexs) {
			if (v.getName() == oldName) {
				v.setName(newName);
			}
		}
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	public List<Vertex> getVertexs() {
		return vertexs;
	}

	public void setVertexs(List<Vertex> vertexs) {
		this.vertexs = vertexs;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public void updateW() {
		w = new double[this.vertexs.size()][this.vertexs.size()];
		for (int i = 0; i < this.vertexs.size(); i++) {
			for (int j = 0; j < this.vertexs.size(); j++) {
				w[i][j] = INF;
			}
		}
		for (Edge e : edges) {
			w[e.getVertex1().getIndex()][e.getVertex2().getIndex()] = e.getW();
		}
	}

	public double[][] getW() {
		return w;
	}

	public void setW(double[][] w) {
		this.w = w;
	}

	public void addEdge(String vertex1, String vertex2, double w) {
		Vertex v1 = null;
		Vertex v2 = null;
		for (Vertex v : vertexs) {
			if (v.getName().equals(vertex1)) {
				v1 = v;
			}
			if (v.getName().equals(vertex2)) {
				v2 = v;
			}
			if (v1 != null && v2 != null) {
				break;
			}
		}
		edges.add(new Edge(v1, v2, w));
		this.w[v1.getIndex()][v2.getIndex()] = w;
		if (!directed) {
			edges.add(new Edge(v2, v1, w));
			this.w[v2.getIndex()][v1.getIndex()] = w;
		}
	}

	public void removeEdge(String vertex1, String vertex2, double w) {
		Vertex v1 = null;
		Vertex v2 = null;
		for (Vertex v : vertexs) {
			if (v.getName().equals(vertex1)) {
				v1 = v;
			}
			if (v.getName().equals(vertex2)) {
				v2 = v;
			}
			if (v1 != null && v2 != null) {
				break;
			}
		}
		edges.remove(new Edge(v1, v2, w));
		this.w[v1.getIndex()][v2.getIndex()] = INF;
		if (!directed) {
			edges.remove(new Edge(v2, v1, w));
			this.w[v2.getIndex()][v1.getIndex()] = INF;
		}
	}

	public int devVertex(String vertex) {
		int result = 0;
		int indexOfVertex = getIndexOfVertex(vertex);
		for (int i = 0; i < w.length; i++) {
			if (w[indexOfVertex][i] != INF)
				result++;
		}
		return result;
	}

	public int sumDevVertex() {
		int result = 0;
		for (Vertex v : vertexs) {
			result += devVertex(v.getName());
		}
		return result;
	}

	public int numberOfEdge() {
		int result = 0;
		if (!directed) {
			result = sumDevVertex() / 2;
		}
		return result;
	}

	public Vertex getVertexbyName(String name) {
		Vertex result = null;
		for (Vertex v : vertexs) {
			if (v.getName().equals(name)) {
				result = v;
			}
		}
		return result;
	}

	public List<Vertex> DFS(String source) {
		// khoi tao
		List<Vertex> visited = new ArrayList<Vertex>();
		if (!directed) {
			Stack<Vertex> stack = new Stack<Vertex>();
			// gan v bang dinh nguon, them v vao visited va push v vao stack
			Vertex v = getVertexbyName(source);
			visited.add(getVertexbyName(source));
			stack.push(getVertexbyName(source));
			// lap lai khi stack khong rong
			while (!stack.isEmpty()) {
				// lay dinh dau tien trong stack ra
				v = stack.peek();
				// tim cac dinh ke voi v
				Vertex k = null;
				for (Vertex vertex : vertexs) {
					if (w[v.getIndex()][vertex.getIndex()] != INF && !visited.contains(vertex)) {
						k = vertex;
						break;
					}
				}
				if (k != null) {
					visited.add(k);
					stack.push(k);
				} else {
					stack.pop();
				}
			}
		}
		return visited;
	}

	public List<Vertex> BFS(String source) {
		// khoi tao
		List<Vertex> visited = new ArrayList<Vertex>();
		if (!directed) {
			List<Vertex> queue = new LinkedList<Vertex>();
			Vertex v = getVertexbyName(source);
			visited.add(getVertexbyName(source));
			queue.add(getVertexbyName(source));
			// lap lai khi stack khong rong
			while (!queue.isEmpty()) {
				// lay dinh dau tien trong stack ra
				v = queue.get(0);
				// tim cac dinh ke voi v
				Vertex k = null;
				for (Vertex vertex : vertexs) {
					if (w[v.getIndex()][vertex.getIndex()] != INF && !visited.contains(vertex)) {
						k = vertex;
						break;
					}
				}
				if (k != null) {
					visited.add(k);
					queue.add(k);
				} else {
					queue.remove(0);
				}

			}
		}
		return visited;
	}

	public boolean isConection() {
		if (DFS(vertexs.get(0).getName()).size() == vertexs.size())
			return true;
		else
			return false;
	}

	public String printPath(List<Vertex> vertex) {
		String result = "";
		for (Vertex v : vertex) {
			result += v.getName() + "=>";
		}
		return result;
	}

	public String printGraph() {
		String result = "\t";
		for (Vertex v : vertexs) {
			result += v.getName() + "\t";
		}
		result += "\n";
		for (int i = 0; i < w.length; i++) {
			result += "\n" + vertexs.get(i).getName() + "\t";
			for (int j = 0; j < w.length; j++) {
				String s = "";
				if (w[i][j] == INF) {
					s = "-";
				} else {
					s = w[i][j] + "";
				}
				result += s + "\t";
			}
		}
		return result;
	}

	public int getIndexOfVertex(String nameOfVertex) {
		int result = 0;
		for (Vertex v : vertexs) {
			if (v.getName().equals(nameOfVertex)) {
				result = v.getIndex();
				break;
			}
		}
		return result;
	}


	public WeightGraph kruskal() {
		WeightGraph result = new WeightGraph(vertexs.size());
		List<Edge> edges = new ArrayList<Edge>();
		List<Vertex> vertexs = new ArrayList<Vertex>();

		Collections.sort(this.edges);
//		while (edges.size() != this.vertexs.size() - 1) {
		for (Edge e : this.edges) {
			if (!(vertexs.contains(e.getVertex1()) && vertexs.contains(e.getVertex2()))) {
				edges.add(e);
				vertexs.add(e.getVertex1());
				vertexs.add(e.getVertex2());
				result.setDirected(this.directed);
				result.setVertexs(vertexs);
				result.setEdges(edges);
			} else {
				List<Vertex> list = result.BFS(e.getVertex1().getName());
				for (Vertex v : list) {
					if (!v.getName().equals(e.getVertex2().getName())) {
						edges.add(e);
						result.addEdge(e.getVertex1().getName(), e.getVertex2().getName(), e.getW());
					}
				}
			}
		}
		if (edges.size() < this.vertexs.size() - 1) {
			System.out.println("Do thi khong lien thong");
			result = null;
//				break;
		} else {
			result.updateW();
//			}
		}

		return result;
	}

	public Result dijstra(String source) {
		Result result = null;
		if (!checkCanhAm()) {
			// khoi tao mang R, L va P
			ArrayList<Vertex> R = new ArrayList<Vertex>();
			double[] L = new double[w.length];
			double[] P = new double[w.length];
			for (Vertex v : vertexs) {
				R.add(v);
			}
			for (int i = 0; i < w.length; i++) {
				L[i] = INF;
				P[i] = -1;
			}
			// b1 gan s bang index dinh bat dau
			int s = getIndexOfVertex(source);
			// gang L dinh bat dau bang 0
			L[s] = 0;
			// khi R khac rong
			while (!R.isEmpty()) {
				Vertex v = R.get(0);
				// xem xet con L nao trong R nho hon khong
				for (Vertex v1 : R) {
					if (L[v.getIndex()] > L[v1.getIndex()]) {
						v = v1;
					}
				}
				// xoa dinh vt
				R.remove(v);
				// tim dinh ke voi v
				for (Vertex v1 : R) {
					if (w[v.getIndex()][v1.getIndex()] != INF) {
						if (L[v1.getIndex()] > L[v.getIndex()] + w[v.getIndex()][v1.getIndex()]) {
							L[v1.getIndex()] = L[v.getIndex()] + w[v.getIndex()][v1.getIndex()];
//						System.out.println(L[v1.getIndex()]);
							P[v1.getIndex()] = v.getIndex();

						}
					}
				}

			}
			result = new Result(L, P, vertexs, w, source);
		}
		if (result == null) {
			result = new Result();
		}
		return result;
	}

	public boolean checkCanhAm() {
		for (Edge e : edges) {
			if (e.getW() < 0) {
				return true;
			}
		}
		return false;
	}

	public Result Bellman_ford(String source) {
		// khoi tao giong dijstra
		// khoi tao mang R, L va P
		boolean chuTrinhAm = false;
		Result result = null;
		ArrayList<Vertex> R = new ArrayList<Vertex>();
		double[] L = new double[w.length];
		double[] P = new double[w.length];
		for (Vertex v : vertexs) {
			R.add(v);
		}
		for (int i = 0; i < w.length; i++) {
			L[i] = INF;
			P[i] = -1;
		}
		// b1 gan s bang index dinh bat dau
		int s = getIndexOfVertex(source);
		// gang L dinh bat dau bang 0
		L[s] = 0;
		// khoi tao stop = false; k = 0
		boolean stop = false;
		int k = 0;
		while (stop == false) {
			stop = true;
			k++;
			// voi moi canh trong V thi
			for (int i = 0; i < w.length; i++) {
				for (int j = 0; j < w.length; j++) {
					if (L[j] > L[i] + w[i][j]) {
						L[j] = L[i] + w[i][j];
						P[j] = i;
						stop = false;
					}
				}
			}
			if (k > w.length) {
				if (stop == false) {
					chuTrinhAm = true;
					stop = true;
				}
			}

		}
		if (!chuTrinhAm) {
			result = new Result(L, P, R, w, source);
		} else {
			result = new Result(L, P, R, w, source);
			result.setChuTrinhAm(chuTrinhAm);
		}
		return result;
	}

	public void floyd(String source, String destination) {
		// khoi tao mang W va P
		double[][] w1 = new double[w.length][w.length];
		double[][] P = new double[w.length][w.length];
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				w1[i][j] = w[i][j];
				if (w[i][j] != INF) {
					P[i][j] = j;
				} else {
					P[i][j] = INF;
				}
			}
		}
//		System.out.println("W0");
//		toStringW(w1);
//		System.out.println();
//		System.out.println("P0");
//		toStringW(P);
//		System.out.println();
		for (int k = 0; k < w.length; k++) {
			for (int i = 0; i < w.length; i++) {
				for (int j = 0; j < w.length; j++) {
					if (w1[i][j] > w1[i][k] + w1[k][j]) {
						w1[i][j] = w1[i][k] + w1[k][j];
						P[i][j] = P[i][k];
					} else {
						w1[i][j] = w1[i][j];
						P[i][j] = P[i][j];
					}
				}
			}
//			System.out.println("W" + (k + 1));
//			toStringW(w1);
//			System.out.println();
//			System.out.println("P" + (k + 1));
//			toStringW(P);
//			System.out.println();
		}
		int nguon = getIndexOfVertex(source);
		int dich = getIndexOfVertex(destination);
		double doDai = w1[nguon][dich];
		String path = vertexs.get(nguon).getName() + " ==> ";
		while (nguon != dich) {
			if (P[nguon][dich] == INF) {
				path = "Khong co duong di tu " + vertexs.get(nguon).getName() + " toi " + vertexs.get(dich).getName();
				break;
			} else {
				nguon = (int) P[nguon][dich];
				path += vertexs.get(nguon).getName() + " ==> ";
			}
		}
		if (doDai != INF) {
			path += " do dai la: " + doDai;
		}
		System.out.println("Floyd mo rong");
		System.out.println(path);

	}

	public void toStringW(double[][] w) {
		for (int i = 0; i < w.length; i++) {
			if (i != 0) {
				System.out.println();
			}
			System.out.println();
			for (int j = 0; j < w.length; j++) {
				if (w[i][j] == INF) {
					System.out.print("-" + "\t");
				} else
					System.out.print(w[i][j] + "\t");
			}
		}
	}

	public void importFormFile(String fileURLName) {
		vertexs = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		WeightGraph wg = null;
		try {
			// doc file
			File file = new File(fileURLName);
			FileReader reader = new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(reader);
			String line = bufferReader.readLine();
			// khoi tao
			String line1 = "";
			String line2 = "";
			String line3 = "";
			int lineNumber = 1;
			while (line != null) {
				if (lineNumber == 1) {
					line1 = line;
				} else if (lineNumber == 2) {
					line2 = line;
				} else {
					line3 += line + "\n";
				}
				lineNumber++;
				line = bufferReader.readLine();
			}
			// tao huong
			boolean directed = false;
			if (line1.equals("1")) {
				directed = true;
			}
			StringTokenizer tokenLine2 = new StringTokenizer(line2, " ");
			List<String> vertexs = new ArrayList<String>();
			while (tokenLine2.hasMoreElements()) {
				vertexs.add(tokenLine2.nextToken());
			}
			// tao mang ten dinh
			String[] nameOfVertexs = new String[vertexs.size()];
			for (int i = 0; i < nameOfVertexs.length; i++) {
				nameOfVertexs[i] = vertexs.get(i);
			}
			wg = new WeightGraph(nameOfVertexs.length);
			wg.setDirected(true);
			wg.setNameEdge(nameOfVertexs);
			// tao canh
			StringTokenizer tokenLine3 = new StringTokenizer(line3, "\n");
			int i = 0;
			int j = 0;
			while (tokenLine3.hasMoreElements()) {
				String lineNumber3 = tokenLine3.nextToken();
				StringTokenizer tokenRow3 = new StringTokenizer(lineNumber3, " ");
				while (tokenRow3.hasMoreElements()) {
					String s = tokenRow3.nextToken();
					if (!s.equals("-")) {
						wg.addEdge(nameOfVertexs[i], nameOfVertexs[j], Double.parseDouble(s));
					}
					j++;
				}
				j = 0;
				i++;
			}
			wg.setDirected(directed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.directed = wg.directed;
		this.vertexs = wg.vertexs;
		this.edges = wg.edges;
		this.w = wg.w;
	}
}
