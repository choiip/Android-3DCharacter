package com.bennyplo.designgraphicswithopengl;

public class CharacterL extends CharacterBase {
    public CharacterL(){
        super();
    }

    float[] getCharVertex() {
        float v[] ={
                -4,5,1,
                -1,5,1,
                -1,-2,1,
                4,-2,1,
                4,-5,1,
                -4,-5,1,

                -4,5,-1,
                -1,5,-1,
                -1,-2,-1,
                4,-2,-1,
                4,-5,-1,
                -4,-5,-1,
        };
        return v;
    }

    float[] getCharColor() {
        float c[]={
                1.0f,1.0f,1.0f,1.0f,//0
                1.0f,1.0f,1.0f,1.0f,//1
                1.0f,1.0f,1.0f,1.0f,//2
                1.0f,1.0f,1.0f,1.0f,//3
                1.0f,1.0f,1.0f,1.0f,//4
                1.0f,1.0f,1.0f,1.0f,//5

                0.0f,0.0f,1.0f,1.0f,//6
                0.0f,0.0f,1.0f,1.0f,//7
                0.0f,0.0f,1.0f,1.0f,//8
                0.0f,0.0f,1.0f,1.0f,//9
                0.0f,0.0f,1.0f,1.0f,//10
                0.0f,0.0f,1.0f,1.0f,//11
        };
        return c;
    }

    int[] getCharIndex() {
        int i[]= {
                0,1,2,0,2,5,
                2,4,5,2,3,4,

                6,7,8,6,8,11,
                8,10,11,8,9,10,
        };
        return i;
    }
}
