package Run;

import java.time.LocalDateTime;
import org.lwjgl.glfw.GLFW;
import DisplayManager.display;
import renderEngine.RawVAO;
import renderEngine.Renderer;
import renderEngine.VAOLoader;
import shaders.StaticShader;

//main program loop
public class Main {

	public static void main(String[] args) {
		display.Initialization();
		display.CreateDisplay();
		StaticShader shader = new StaticShader();
		
		
		
		VAOLoader loader = new VAOLoader();
		Renderer renderer = new Renderer();
		
		
		
		float[] vertices = {
				//quad
				-0.5f, 0.5f, 0f, //left top point #0
				-0.5f, -0.5f, 0f, // left bottom point #1
				0.5f, -0.5f, 0f, // right bottom point #2
				0.5f, 0.5f, 0f, // right top point #3
		};
		
		int[] indices = {0,1,2,0,2,3};
		
		RawVAO model = loader.loadToVao(vertices, indices);
		
		
		
		
		
		while (!GLFW.glfwWindowShouldClose(display.windowID)) {
			
			GLFW.glfwPollEvents();
			renderer.prepare();
			
			shader.start(); //RENDER STARTS HERE ==========
			
			
			renderer.render(model);
			
			
			
			shader.stop(); //RENDER ENDS HERE ==========
			
			display.UpdateDisplay();
		}
		shader.clear();
		loader.clearVAOandVBO();
		System.out.println("DEBUG: Closing app! " + LocalDateTime.now());
		GLFW.glfwTerminate();

	}

}
