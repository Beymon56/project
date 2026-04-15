package renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

//work with VAO and VBO
public class VAOLoader {
	
	private List<Integer> VAOs = new ArrayList<Integer>(); //list of all VAOs
	private List<Integer> VBOs = new ArrayList<Integer>(); //list of all VBOs
	
	
	
	//load VAO to OpenGL buffer
	public RawVAO loadToVao(float [] positions, int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, positions);
		unbindVAO();
		return new RawVAO(vaoID, indices.length);
	}
	
	
	
	//create new empty VAO and bind it into OpenGL buffer, return VAO Id in glBuffer
	private int createVAO() { 
		int vaoID = GL30.glGenVertexArrays();
		VAOs.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	
	
	//store VBO in VAO, takes index of VBO position in VAO and data to store
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		int vboID = GL15.glGenBuffers();
		VBOs.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storedataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		//store VBO into VAO, (index of VBO position in VAO, size of VBO, type of data in VBO, normalized?,
							//distance between parts of data, offset)
		GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); // unbind VBO
	}
	
	
	
	//unbind VAO from OpenGL buffer
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	
	
	//turn raw data into float buffer, return prepared float buffer
	private FloatBuffer storedataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length); //create buffer with length of (data.lenght)
		buffer.put(data);
		buffer.flip(); //make buffer read-only
		return buffer;
	}
	
	
	
	//clear VAOs and VBOs lists
	public void clearVAOandVBO() {
		for (int vao:VAOs) {
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo:VBOs) {
			GL30.glDeleteBuffers(vbo);
		}
	}
	
	
	
	//?useless? bind VBOs to index, less vertices needed
	private void bindIndicesBuffer(int[] indices) {
		int VBOid = GL15.glGenBuffers();
		VBOs.add(VBOid);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VBOid);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	
	
	//?useless? store (data) in IntBuffer
	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	
	
	
}
