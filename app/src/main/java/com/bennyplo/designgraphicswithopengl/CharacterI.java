package com.bennyplo.designgraphicswithopengl;

public class CharacterI extends CharacterBase {
    public CharacterI(){
        super();
    }

    @Override
    public float getWidth() {
        return 3;
    }

    float[] getCharVertex() {
        float v[] ={
                -1.5f,5.0f,1.0f,
                1.5f,5.0f,1.0f,
                1.5f,-5.0f,1.0f,
                -1.5f,-5.0f,1.0f,

                -1.5f,5.0f,-1.0f,
                1.5f,5.0f,-1.0f,
                1.5f,-5.0f,-1.0f,
                -1.5f,-5.0f,-1.0f,
        };
        return v;
    }

    float[] getCharColor() {
        float c[]={
                1.0f,1.0f,1.0f,1.0f,//0
                1.0f,1.0f,1.0f,1.0f,//1
                1.0f,1.0f,1.0f,1.0f,//2
                1.0f,1.0f,1.0f,1.0f,//3

                0.0f,0.0f,1.0f,1.0f,//4
                0.0f,0.0f,1.0f,1.0f,//5
                0.0f,0.0f,1.0f,1.0f,//6
                0.0f,0.0f,1.0f,1.0f,//7
        };
        return c;
    }

    int[] getCharIndex() {
        int i[]={
                0,1,2,0,2,3,

                4,5,6,4,6,7,
        };
        int gi[] = generateIndex(0, 3, 4);
        return Util.join(i, gi);
    }
}
