package DisplayManager;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import java.time.*;

public class display {
	
	private static final int MON_WIDTH = 800;
	private static final int MON_HEIGHT = 600;
	private static final String windowTitle = "Window emae";
	public static long windowID;
	static GLFWVidMode videoMode;
	
	
	
	public static void Initialization() {
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("ERROR: Failed to initialize GLFW! " + LocalDateTime.now());
		}else {
			System.out.println("DEBUG: GLFW initialized succesfully! " + LocalDateTime.now());
		}
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
	}
	
	
	
	public static void CreateDisplay() {
		windowID  = GLFW.glfwCreateWindow(MON_WIDTH, MON_HEIGHT, windowTitle, 0, 0);
		
		if (windowID == 0) {
			throw new RuntimeException("ERROR: Failed to create window! " + LocalDateTime.now());
		}else {
			System.out.println("DEBUG: Window created successfully! " + LocalDateTime.now());
		}
		
		GLFW.glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
		GLFW.glfwSetWindowPos(windowID, (videoMode.width() - MON_WIDTH) / 2, (videoMode.height() - MON_HEIGHT) / 2);
		GLFW.glfwShowWindow(windowID);
		GLFW.glfwSwapInterval(0);
		
		GL11.glViewport(0, 0, MON_WIDTH, MON_HEIGHT);
	
	}
	
	
	
	public static void UpdateDisplay() {
		GLFW.glfwSwapBuffers(windowID);
	}
	
	
	
}
