package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public abstract class ShaderProgram {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	
	
	//attach shaders to OpenGL
	public ShaderProgram(String vertexFile, String fragmentFile) {
		int log;
		vertexShaderID = loadShader(vertexFile, GL30.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL30.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		
		
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		
		bindAttributes();
		
		GL20.glLinkProgram(programID);
		log = GL30.glGetProgrami(programID, GL30.GL_LINK_STATUS);
		if (log == GL30.GL_FALSE) {
			System.err.println(GL30.glGetProgramInfoLog(programID));
		}else {
			System.out.println("Shaders linked successfully!");
		}
		
		
		GL20.glValidateProgram(programID);
		log = GL30.glGetProgrami(programID, GL30.GL_VALIDATE_STATUS);
		if (log == GL30.GL_FALSE) {
			System.err.println(GL30.glGetProgramInfoLog(programID));
		}else {
			System.out.println("Shaders validated successfully!");
		}
		
		
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
	}
	
	
	
	public void start() {
		GL20.glUseProgram(programID);
	}
	
	
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	
	
	//clear all shaders from memory
	public void clear() {
		stop();
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
		System.out.println("DEBUG: Shaders cleared! " + LocalDateTime.now());
	}
	
	
	
	protected abstract void bindAttributes();
	
	
	
	//bind any attribute to shader program
	protected void bindAttribute(int attributeIndex, String variableName) {
		GL20.glBindAttribLocation(programID, attributeIndex, variableName);
	}
	
	
	
	//parse shader.txt file and load it into OpenGL buffer, return shaderID
	private static int loadShader(String file, int type) {
		
		//parse shader.txt
		StringBuilder shaderSource = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("ERROR: Could not read file! " + LocalDateTime.now());
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		//create shader from parsed shader.txt and try to compile it
		int shaderID = GL30.glCreateShader(type);
		GL30.glShaderSource(shaderID, shaderSource);
		GL30.glCompileShader(shaderID);
		if (GL30.glGetShaderi(shaderID, GL30.GL_COMPILE_STATUS) == GL30.GL_FALSE) {
			System.out.println(GL30.glGetShaderInfoLog(shaderID, 500));
			System.err.println("ERROR: Could not compile shader!");
			System.exit(-1);
		}
		
		System.out.println("shader compiled!");
		return shaderID;
	}
	
}
