package ltdt;

public class Edge implements Comparable<Edge> {
	Vertex vertex1, vertex2;
	double w;

	public Edge(Vertex vertex1, Vertex vertex2, double w) {
		super();
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.w = w;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public void setVertex1(Vertex vertex1) {
		this.vertex1 = vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public void setVertex2(Vertex vertex2) {
		this.vertex2 = vertex2;
	}

	public double getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return Double.compare(w, o.getW());
	}

}
