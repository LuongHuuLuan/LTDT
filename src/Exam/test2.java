package Exam;

public class test2 {
	public static void main(String[] args) {
		Graph1 g = new Graph1(7, false);
		// 1 bfs dfs
//	g.addVertex(0, 1);
//	g.addVertex(0, 2);
//	g.addVertex(0, 6);
//	g.addVertex(1, 2);
//	g.addVertex(1, 3);
//	g.addVertex(1, 5);
//	g.addVertex(2, 3);
//	g.addVertex(2, 6);
//	g.addVertex(3, 5);
//	g.addVertex(3, 6);
//	g.addVertex(4, 6);

		// 2 euler
//	g.addVertex(0, 2);
//	g.addVertex(0, 5);
//	g.addVertex(1, 3);
//	g.addVertex(1, 4);
//	g.addVertex(1, 5);
//	g.addVertex(1, 6);
//	g.addVertex(2, 3);
//	g.addVertex(2, 5);
//	g.addVertex(2, 6);
//	g.addVertex(3, 4);
//	g.addVertex(3, 6);
//	g.addVertex(5, 6);

		g.addEdge(0, 2);
		g.addEdge(0, 5);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(1, 5);
		g.addEdge(2, 3);
		g.addEdge(2, 5);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
//	g.addVertex(1, 2);

		g.printAdjacencyMatrix();

	}
}
