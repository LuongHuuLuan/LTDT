package Exam;

public class Graph1 {
	private int vertex;
	private boolean directed;
	private int[][] adjacencyMatrix;
	private int[][] incidenceMatrix;

	public Graph1(int vertex, boolean directed) {
		this.vertex = vertex;
		this.directed = directed;
		createAdjacencyMatrix();
	}

	public void createAdjacencyMatrix() {
		adjacencyMatrix = new int[vertex][vertex];
	}

	public void createicidenceMatrix(int edge) {
		adjacencyMatrix = new int[edge][vertex];
	}

	public void addEdge(int v1, int v2) {
		adjacencyMatrix[v1][v2] = 1;
		if (!directed) {
			adjacencyMatrix[v2][v1] = 1;
		}

	}

	public void addEdgeIncidence(int v1, int v2) {
		int col = getEdge();
		incidenceMatrix[v1][col] = 1;
		if (!directed) {
			incidenceMatrix[v2][col] = 1;
		} else {
			incidenceMatrix[v2][col] = -1;
		}
	}

	public int devHaftIn(int v) {
		int in = 0;
		if (directed) {
			for (int i = 0; i < vertex; i++) {
				in += adjacencyMatrix[i][v];
			}
		}
		return in;
	}

	public int devHaftOut(int v) {
		int out = 0;
		if (directed) {
			for (int i = 0; i < vertex; i++) {
				out += adjacencyMatrix[v][i];
			}
		}
		return out;
	}

	public int degV(int v) {
		int result = 0;
		if (!directed) {
			for (int i = 0; i < vertex; i++) {
				result += adjacencyMatrix[v][i];
			}
		}
		return result;
	}

	public int getEdge() {
		int result = 0;
		if (!directed) {
			for (int i = 0; i < vertex; i++) {
				result += degV(i);
			}
			result /= 2;
		} else {
			int sumIn = 0;
			int sumOut = 0;
			for (int i = 0; i < vertex; i++) {
				sumIn += devHaftIn(i);
				sumOut += devHaftOut(i);
			}
			result = (sumIn + sumOut) / 2;
		}
		return result;
	}

	public void printAdjacencyMatrix() {
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				System.out.print(adjacencyMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public void printIncidenceMatrix() {
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < incidenceMatrix[i].length; j++) {
				System.out.print(incidenceMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
