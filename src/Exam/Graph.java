package Exam;

public class Graph {
	private int vertex;
	private int[][] adjacencyMatrix;

	public Graph(int edge) {
		this.vertex = edge;
		adjacencyMatrix = new int[vertex][vertex];
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int[][] getAdjacecencyMatrix() {
		return adjacencyMatrix;
	}

	public Graph copyMatrix() {
		int[][] matrix = new int[vertex][vertex];
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				matrix[i][j] = adjacencyMatrix[i][j];
			}
		}
		Graph result = new Graph(vertex);
		result.setAdjacecencyMatrix(matrix);
		return result;
	}
	public void removeEdge(int src, int des) {
		adjacencyMatrix[src][des] = 0;
		adjacencyMatrix[des][src] = 0;
	}

	public void setAdjacecencyMatrix(int[][] adjacecencyMatrix) {
		this.adjacencyMatrix = adjacecencyMatrix;
	}

	public void addVertex(int src, int des) {
		adjacencyMatrix[src][des] = 1;
		adjacencyMatrix[des][src] = 1;
	}

	public void print() {
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				System.out.print(adjacencyMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public int degV(int v) {
		int result = 0;
		for (int i = 0; i < vertex; i++) {
			result += adjacencyMatrix[v][i];
		}
		return result;
	}

	public int getEdge() {
		int result = 0;
		for (int i = 0; i < vertex; i++) {
			result += degV(i);
		}
		return result / 2;
	}

}
