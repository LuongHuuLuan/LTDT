package Exam;

public class test {
	public static void main(String[] args) {
		Graph g = new Graph(7);
		//1 bfs dfs
//		g.addVertex(0, 1);
//		g.addVertex(0, 2);
//		g.addVertex(0, 6);
//		g.addVertex(1, 2);
//		g.addVertex(1, 3);
//		g.addVertex(1, 5);
//		g.addVertex(2, 3);
//		g.addVertex(2, 6);
//		g.addVertex(3, 5);
//		g.addVertex(3, 6);
//		g.addVertex(4, 6);
		
		//2 euler
//		g.addVertex(0, 2);
//		g.addVertex(0, 5);
//		g.addVertex(1, 3);
//		g.addVertex(1, 4);
//		g.addVertex(1, 5);
//		g.addVertex(1, 6);
//		g.addVertex(2, 3);
//		g.addVertex(2, 5);
//		g.addVertex(2, 6);
//		g.addVertex(3, 4);
//		g.addVertex(3, 6);
//		g.addVertex(5, 6);
		
		g.addVertex(0, 2);
		g.addVertex(0, 5);
		g.addVertex(1, 3);
		g.addVertex(1, 4);
		g.addVertex(1, 5);
		g.addVertex(2, 3);
		g.addVertex(2, 5);
		g.addVertex(3, 4);
		g.addVertex(3, 5);
//		g.addVertex(1, 2);
		
		g.print();
		System.out.println("DFS");
		DFS dfs = new DFS(g);
		dfs.DSF(0);
		System.out.println("BFS");
		BFS bfs = new BFS(g);
		bfs.BSF(0);
		System.out.println("Euler");
		Euler euler =new Euler(g);
		euler.euler(0);
		
	}
}
