package com.bennyplo.designgraphicswithopengl;

public class CharacterH extends CharacterBase {
    CharacterH() {
        super();
    }

    @Override
    public float getWidth() {
        return 8;
    }

    float[] getCharVertex() {
        float v[] ={
                -4,5,1,
                -1,5,1,
                -1,1.5f,1,
                1,1.5f,1,
                1,5,1,
                4,5,1,
                4,-5,1,
                1,-5,1,
                1,-1.5f,1,
                -1,-1.5f,1,
                -1,-5,1,
                -4,-5,1,

                -4,5,-1,
                -1,5,-1,
                -1,1.5f,-1,
                1,1.5f,-1,
                1,5,-1,
                4,5,-1,
                4,-5,-1,
                1,-5,-1,
                1,-1.5f,-1,
                -1,-1.5f,-1,
                -1,-5,-1,
                -4,-5,-1,
        };
        return v;
    }

    float[] getCharColor() {
        float c[]={
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,

                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
        };
        return c;
    }

    int[] getCharIndex() {
        int i[]={
                0,1,2,0,2,9,
                0,9,11,9,10,11,
                2,8,9,2,3,8,
                4,5,3,3,5,8,
                5,6,8,6,7,8,

                12,13,14,12,14,21,
                12,21,23,21,22,23,
                14,20,21,14,15,20,
                16,17,15,15,17,20,
                17,18,20,18,19,20,
        };
        int gi[] = generateIndex(0, 11, 12);
        return Util.join(i, gi);
    }
}
