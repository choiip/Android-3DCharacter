package com.bennyplo.designgraphicswithopengl;

import java.util.Arrays;

import static java.lang.Math.PI;

public class CharacterR extends CharacterBase {
    private final int RESOLUTION = 30;
    private final double BEGIN_ANGLE = 270 * PI / 180;
    private final double END_ANGLE = 450 * PI / 180;

    Curve curve = null;
    public CharacterR(){
        super();
    }

    private void createCurve(int indexOffset) {
        curve = new Curve();
        float[]innerVertex = Util.createEllipseArc(1,1, 1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
        float[]outerVertex = Util.createEllipseArc(3,3, 1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
        float[]backInnerVertex = Util.createEllipseArc(1,1, -1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
        float[]backOuterVertex = Util.createEllipseArc(3,3, -1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
        float[]frontVertex = Util.join(innerVertex, outerVertex);
        float[]backVertex = Util.join(backInnerVertex, backOuterVertex);
        curve.vertex = Util.join(frontVertex, backVertex);
        // offset the vertex
        int vertexCount = curve.vertex.length / 3;
        int halfVertexCount = vertexCount / 2;
        for (int i=0; i<vertexCount; i++) {
            curve.vertex[3*i] += 1;     // x
            curve.vertex[3*i+1] += 2;   // y
        }

        curve.color = new float[vertexCount * 4];
        Arrays.fill(curve.color, 1.0f);
        for (int i=vertexCount*2; i < curve.color.length; i+=4) {
            curve.color[i] = 0.f;
            curve.color[i+1] = 0.f;
        }
        curve.index = new int[3*(vertexCount -4)];
        for (int i=0, currentIndex = 0; i<curve.index.length/2; i+=6, currentIndex++) {
            curve.index[i + 0] = indexOffset + currentIndex;
            curve.index[i + 1] = indexOffset + currentIndex + halfVertexCount / 2;
            curve.index[i + 2] = indexOffset + currentIndex + halfVertexCount / 2 + 1;
            curve.index[i + 3] = indexOffset + currentIndex;
            curve.index[i + 4] = indexOffset + currentIndex + 1;
            curve.index[i + 5] = indexOffset + currentIndex + halfVertexCount / 2 + 1;
        }

        for (int i=curve.index.length/2, currentIndex=halfVertexCount; i<curve.index.length; i+=6, currentIndex++) {
            curve.index[i + 0] = indexOffset + currentIndex;
            curve.index[i + 1] = indexOffset + currentIndex + halfVertexCount / 2;
            curve.index[i + 2] = indexOffset + currentIndex + halfVertexCount / 2 + 1;
            curve.index[i + 3] = indexOffset + currentIndex;
            curve.index[i + 4] = indexOffset + currentIndex + 1;
            curve.index[i + 5] = indexOffset + currentIndex + halfVertexCount / 2 + 1;
        }
    }

    float[] getCharVertex() {
        if (curve == null) {
            createCurve(24);
        }
        float v[] ={
                -4,5,1,
                1,5,1,
                1,3,1,
                -1,3,1,
                -1,1,1,
                1,1,1,
                1,-1,1,
                4,-5,1,
                1,-5,1,
                -1,-1,1,
                -1,-5,1,
                -4,-5,1,

                -4,5,-1,
                1,5,-1,
                1,3,-1,
                -1,3,-1,
                -1,1,-1,
                1,1,-1,
                1,-1,-1,
                4,-5,-1,
                1,-5,-1,
                -1,-1,-1,
                -1,-5,-1,
                -4,-5,-1,
        };

        return Util.join(v, curve.vertex);
    }

    float[] getCharColor() {
        if (curve == null) {
            createCurve(24);
        }
        float c[]={
                1.0f,1.0f,1.0f,1.0f,//0
                1.0f,1.0f,1.0f,1.0f,//1
                1.0f,1.0f,1.0f,1.0f,//2
                1.0f,1.0f,1.0f,1.0f,//3
                1.0f,1.0f,1.0f,1.0f,//4
                1.0f,1.0f,1.0f,1.0f,//5
                1.0f,1.0f,1.0f,1.0f,//6
                1.0f,1.0f,1.0f,1.0f,//7
                1.0f,1.0f,1.0f,1.0f,//8
                1.0f,1.0f,1.0f,1.0f,//9
                1.0f,1.0f,1.0f,1.0f,//10
                1.0f,1.0f,1.0f,1.0f,//11

                0.0f,0.0f,1.0f,1.0f,//12
                0.0f,0.0f,1.0f,1.0f,//13
                0.0f,0.0f,1.0f,1.0f,//14
                0.0f,0.0f,1.0f,1.0f,//15
                0.0f,0.0f,1.0f,1.0f,//16
                0.0f,0.0f,1.0f,1.0f,//17
                0.0f,0.0f,1.0f,1.0f,//18
                0.0f,0.0f,1.0f,1.0f,//19
                0.0f,0.0f,1.0f,1.0f,//20
                0.0f,0.0f,1.0f,1.0f,//21
                0.0f,0.0f,1.0f,1.0f,//22
                0.0f,0.0f,1.0f,1.0f,//23
        };
        return Util.join(c, curve.color);
    }

    int[] getCharIndex() {
        if (curve == null) {
            createCurve(24);
        }
        int i[]={
                0,1,3,1,2,3,
                0,3,4,0,4,11,
                4,5,6,4,6,9,
                6,7,8,6,8,9,
                4,9,11,9,10,11,

                12,13,15,13,14,15,
                12,15,16,12,16,23,
                16,17,18,16,18,21,
                18,19,20,18,20,21,
                16,21,23,21,22,23,
        };
        return Util.join(i, curve.index);
    }
}
