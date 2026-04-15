package renderEngine;

public class RawVAO {
	private int vaoID;
	private int vertexCount;
	
	public RawVAO(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
