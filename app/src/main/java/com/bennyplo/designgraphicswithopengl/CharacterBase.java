package com.bennyplo.designgraphicswithopengl;

import android.opengl.GLES32;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class CharacterBase {
    private final String vertexShaderCode =
            "attribute vec3 aVertexPosition;"+//vertex of an object
                    " attribute vec4 aVertexColor;"+//the colour  of the object
                    "     uniform mat4 uMVPMatrix;"+//model view  projection matrix
                    "    varying vec4 vColor;"+//variable to be accessed by the fragment shader
                    "    void main() {" +
                    "        gl_PointSize = 10.0;"+
                    "        gl_Position = uMVPMatrix* vec4(aVertexPosition, 1.0);"+//calculate the position of the vertex
                    "        vColor=aVertexColor;}";//get the colour from the application program

    private final String fragmentShaderCode =
            "precision mediump float;"+ //define the precision of float
                    "varying vec4 vColor;"+ //variable from the vertex shader
                    "void main() {"+
                    "   if (vColor.a < 0.5) discard;"+
                    "   gl_FragColor = vColor; }";//change the colour based on the variable from the vertex shader

    private final FloatBuffer vertexBuffer,colorBuffer;
    private final IntBuffer indexBuffer;
    private final int mProgram;
    private int mPositionHandle,mColorHandle;
    private int mMVPMatrixHandle;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static final int COLOR_PER_VERTEX = 4;
    private int indexCount;// number of index

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    private final int colorStride=COLOR_PER_VERTEX*4;//4 bytes per vertex

    public static int getMaxValue(int[] numbers){
        int maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    public CharacterBase(){
        float[] vertex = getCharVertex();
        float[] color = getCharColor();
        int[] index = getCharIndex();
        int maxIndex = getMaxValue(index);
        if (maxIndex >= vertex.length / 3) {
            Log.e("CharacterBase", "Index out of range");
        }
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(vertex.length * 4);// (# of coordinate values * 4 bytes per float)
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertex);
        vertexBuffer.position(0);
        indexCount=index.length;
        ByteBuffer cb=ByteBuffer.allocateDirect(color.length * 4);// (# of coordinate values * 4 bytes per float)
        cb.order(ByteOrder.nativeOrder());
        colorBuffer = cb.asFloatBuffer();
        colorBuffer.put(color);
        colorBuffer.position(0);
        IntBuffer ib=IntBuffer.allocate(index.length);
        indexBuffer=ib;
        indexBuffer.put(index);
        indexBuffer.position(0);
        // prepare shaders and OpenGL program
        int vertexShader = MyRenderer.loadShader(GLES32.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyRenderer.loadShader(GLES32.GL_FRAGMENT_SHADER, fragmentShaderCode);
        mProgram = GLES32.glCreateProgram();             // create empty OpenGL Program
        GLES32.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
        GLES32.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
        GLES32.glLinkProgram(mProgram);                  // link the  OpenGL program to create an executable
        GLES32.glUseProgram(mProgram);// Add program to OpenGL environment
        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES32.glGetAttribLocation(mProgram, "aVertexPosition");
        // Enable a handle to the triangle vertices
        GLES32.glEnableVertexAttribArray(mPositionHandle);
        mColorHandle = GLES32.glGetAttribLocation(mProgram, "aVertexColor");
        // Enable a handle to the  colour
        GLES32.glEnableVertexAttribArray(mColorHandle);
        // Prepare the colour coordinate data
        GLES32.glVertexAttribPointer(mColorHandle, COLOR_PER_VERTEX, GLES32.GL_FLOAT, false, colorStride, colorBuffer);
        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES32.glGetUniformLocation(mProgram, "uMVPMatrix");
        MyRenderer.checkGlError("glGetUniformLocation");
    }

    public void draw(float[] mvpMatrix) {
        GLES32.glUseProgram(mProgram);//use the object's shading programs
        // Apply the projection and view transformation
        GLES32.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        MyRenderer.checkGlError("glUniformMatrix4fv");
        //set the attribute of the vertex to point to the vertex buffer
        GLES32.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES32.GL_FLOAT, false, vertexStride, vertexBuffer);
        GLES32.glVertexAttribPointer(mColorHandle, COORDS_PER_VERTEX,
                GLES32.GL_FLOAT, false, colorStride, colorBuffer);
        // Draw the 3D character A
        GLES32.glDrawElements(GLES32.GL_TRIANGLES,indexCount,GLES32.GL_UNSIGNED_INT,indexBuffer);
    }

    public float getWidth() { return 8; }

    protected int[] generateIndex(int beginIndex, int endIndex, int frontBackOffset) {
        return generateIndex(beginIndex, endIndex, frontBackOffset, false);
    }

    protected int[] generateIndex(int beginIndex, int endIndex, int frontBackOffset, boolean lastToZero) {
        int[] index = new int[6*(endIndex - beginIndex + 1)];
        int currentIndex = beginIndex;
        for (int i=0; i<index.length; i+=6, currentIndex++) {
            index[i] = currentIndex;
            index[i+1] = (currentIndex+1)%frontBackOffset;
            index[i+2] = (currentIndex+1)%frontBackOffset + frontBackOffset;
            index[i+3] = index[i];
            index[i+4] = index[i+2];
            index[i+5] = (currentIndex+frontBackOffset);
        }
        if (lastToZero) {
            int i = index.length - 6;
            index[i] = endIndex;
            index[i+1] = 0;
            index[i+2] = frontBackOffset;
            index[i+3] = index[i];
            index[i+4] = index[i+2];
            index[i+5] = (endIndex+frontBackOffset);
        }
        return index;
    }

    abstract float[] getCharVertex();

    abstract float[] getCharColor();

    abstract int[] getCharIndex();
}
