package ltdt;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private double[] L;
	private double[] P;
	private List<Vertex> vertexs = new ArrayList<Vertex>();
	private double[][] w;
	private String source;
	private boolean chuTrinhAm = false;

	public Result(double[] l, double[] p, List<Vertex> vertexs, double[][] w, String source) {
		this.L = l;
		this.P = p;
		this.vertexs = vertexs;
		this.w = w;
		this.source = source;
	}

	public Result() {
		// TODO Auto-generated constructor stub
	}

	public List<Vertex> getVertexs() {
		return vertexs;
	}

	public void setVertexs(List<Vertex> vertexs) {
		this.vertexs = vertexs;
	}

	public double[][] getW() {
		return w;
	}

	public void setW(double[][] w) {
		this.w = w;
	}

	public double[] getL() {
		return L;
	}

	public void setL(double[] l) {
		L = l;
	}

	public double[] getP() {
		return P;
	}

	public void setP(double[] p) {
		P = p;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isChuTrinhAm() {
		return chuTrinhAm;
	}

	public void setChuTrinhAm(boolean chuTrinhAm) {
		this.chuTrinhAm = chuTrinhAm;
	}

	public String pathToN(String vertex) {
		String result = "";
		if (this.source == null) {
			result = "Do thi co trong so am khong the dung dijstra";
		} else {
			if (!chuTrinhAm) {
				result = "Duong di ngan nhat tu " + this.source + " toi " + vertex + " la: ";
				// lay index tu ten cua dinh dich
				int n = -1;
				int sourceIndex = -1;
				String path = "";
				for (Vertex v : vertexs) {
					if (v.getName().equals(vertex)) {
						n = v.getIndex();
					}
					if (v.getName().equals(source)) {
						sourceIndex = v.getIndex();
					}
					if (n != -1 && sourceIndex != -1) {
						break;
					}
				}
				double doDai = L[n];
				while (n != sourceIndex) {
					n = (int) P[n];
					for (Vertex v : vertexs) {
						if (v.getIndex() == n) {
							path = v.getName() + " " + path;
						}
					}
				}
				path += vertex;
				result += path + " co do dai la: " + doDai;
			} else {
				result = "Do thi co chu trinh am";
			}
		}
		return result;

	}

	public String pathToAll() {
		String result;
		if (this.source == null) {
			result = "Do thi co trong so am khong the dung dijstra";
		} else {
			result = "Duong di tu " + this.source + " den cac canh trong do thi:" + "\n";
			for (Vertex v : vertexs) {
				result += pathToN(v.getName()) + "\n";
			}
		}
		return result;
	}

}
