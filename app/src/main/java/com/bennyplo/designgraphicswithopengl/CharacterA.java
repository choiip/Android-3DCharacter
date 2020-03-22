package com.bennyplo.designgraphicswithopengl;

public class CharacterA extends CharacterBase {
    public CharacterA(){
        super();
    }

    @Override
    public float getWidth() {
        return 10;
    }

    float[] getCharVertex() {
        float v[] ={
                -2.5f,5,1,
                2.5f,5,1,
                5,-5,1,
                2,-5,1,
                1.25f,-2,1,
                -1.25f,-2,1,
                -2,-5,1,
                -5,-5,1,
                0,3.25f,1,
                0.75f,0.25f,1,
                -0.75f,0.25f,1,

                -2.5f,5,-1,
                2.5f,5,-1,
                5,-5,-1,
                2,-5,-1,
                1.25f,-2,-1,
                -1.25f,-2,-1,
                -2,-5,-1,
                -5,-5,-1,
                0,3.25f,-1,
                0.75f,0.25f,-1,
                -0.75f,0.25f,-1
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
                1.0f,1.0f,1.0f,1.0f,//6
                1.0f,1.0f,1.0f,1.0f,//7
                1.0f,1.0f,1.0f,1.0f,//8
                1.0f,1.0f,1.0f,1.0f,//9
                1.0f,1.0f,1.0f,1.0f,//10

                0.0f,0.0f,1.0f,1.0f,//11
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
        };
        return c;
    }

    int[] getCharIndex() {
        int i[]={
                0,1,8,0,8,10,
                0,10,7,10,5,7,
                5,6,7,10,4,5,
                10,9,4,1,9,8,
                1,4,9,1,2,4,
                4,2,3,

                11,12,19,11,19,21,
                11,21,18,21,16,18,
                16,17,18,21,15,16,
                21,20,15,12,20,19,
                12,15,20,12,13,15,
                15,13,14,
        };
        int gi1[] = generateIndex(0, 7, 11);
        int gi2[] = generateIndex(8, 10, 11);
        int gi[] = Util.join(gi1, gi2);
        return Util.join(i, gi);
    }
}
