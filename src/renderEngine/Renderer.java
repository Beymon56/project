package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

//renders from VAO
public class Renderer {
	
	public void prepare() {
		GL11.glClearColor(1f, 1f, 0.5f, 1);
		GL11.glClear(GL15.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	
	//renders stuff from VAO
	public void render(RawVAO VAO) {
		GL30.glBindVertexArray(VAO.getVaoID()); //bind VAO
		GL20.glEnableVertexAttribArray(0); //enable list of VAO with index(0)
		//render(type(triangles), where start render(index), how much render)
		GL11.glDrawElements(GL15.GL_TRIANGLES, VAO.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL30.glBindVertexArray(0); //unbind VAO
		GL20.glDisableVertexAttribArray(0); //disable list of VAO with index(0)
	}

}
