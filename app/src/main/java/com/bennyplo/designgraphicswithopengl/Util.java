package com.bennyplo.designgraphicswithopengl;

import java.util.Arrays;

import static java.lang.Math.PI;

public final class Util {
    // Function to join two arrays in Java
    public static int[] join(int[] a, int[] b)
    {
        if (a == null) return b;
        if (b == null) return a;
        int[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);

        return result;
    }

    public static float[] join(float[] a, float[] b)
    {
        if (a == null) return b;
        if (b == null) return a;
        float[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);

        return result;
    }

    public static float[] createEllipseArc(float rx, float ry, float z, double beginAngle, double endAngle, int resolution) {
        if (beginAngle < 0) {
            beginAngle = 0;
        }
        if (endAngle > 4*PI) {
            endAngle = 4*PI;
        }
        int i = 0, count = 0;
        for (double t = beginAngle; t < endAngle; t += 1.0/resolution) { // <- or different step
            count++;
        }
        float[] vertex = new float[(count+1)*3];
        for (double t = beginAngle; t < endAngle; t += 1.0/resolution) { // <- or different step
            vertex[i+0] = ((float)(rx * Math.cos(t))); // x
            vertex[i+1] = ((float)(ry * Math.sin(t))); // y
            vertex[i+2] = z;    // z
            i+=3;
        }
        vertex[i] = ((float)(rx * Math.cos(endAngle))); // x
        vertex[i+1] = ((float)(ry * Math.sin(endAngle))); // y
        vertex[i+2] = z;    // z

        return vertex;
    }

    public static Curve createCurve(float controlpts_right[],float controlpts_left[]) {
        float vertices[] = new float[65535];
        float color[] = new float[65535];
        int pindex[] = new int[65535];
        int vertexindex = 0;
        int colorindex = 0;
        int indx = 0;
        int controlptindex=0;
        int nosegments=(controlpts_right.length/2)/3;
        double t,x,y,xl,yl,z;
        z=0.3;
        double centrex=0,centrey=0;
        for (int i=0;i<controlpts_right.length;i+=2)
        {
            centrex+=controlpts_right[i];
            centrey+=controlpts_right[i+1];
        }
        centrex/=(float)(controlpts_right.length/2.0);
        centrey/=(float)(controlpts_right.length/2.0);
        int v0,v1,v2,v3,v4,v5,v6,v7;
        for (int segment=0;segment<nosegments;segment++) {
            for (t = 0; t < 1.0; t += 0.1) {
                x = Math.pow(1.0 - t, 3) * controlpts_right[controlptindex] + controlpts_right[controlptindex + 2] * 3 * t * Math.pow(1 - t, 2) + controlpts_right[controlptindex + 4] * 3 * t * t * (1 - t) + controlpts_right[controlptindex + 6] * Math.pow(t, 3);
                y = Math.pow(1.0 - t, 3) * controlpts_right[controlptindex + 1] + controlpts_right[controlptindex + 3] * 3 * t * Math.pow(1 - t, 2) + controlpts_right[controlptindex + 5] * 3 * t * t * (1 - t) + controlpts_right[controlptindex + 7] * Math.pow(t, 3);
                xl = Math.pow(1.0 - t, 3) * controlpts_left[controlptindex] + controlpts_left[controlptindex + 2] * 3 * t * Math.pow(1 - t, 2) + controlpts_left[controlptindex + 4] * 3 * t * t * (1 - t) + controlpts_left[controlptindex + 6] * Math.pow(t, 3);
                yl = Math.pow(1.0 - t, 3) * controlpts_left[controlptindex + 1] + controlpts_left[controlptindex + 3] * 3 * t * Math.pow(1 - t, 2) + controlpts_left[controlptindex + 5] * 3 * t * t * (1 - t) + controlpts_left[controlptindex + 7] * Math.pow(t, 3);
                vertices[vertexindex++] = (float) (x-centrex);
                vertices[vertexindex++] = (float) (y-centrey);
                vertices[vertexindex++] = (float) (z);
                vertices[vertexindex++] = (float) (xl-centrex);
                vertices[vertexindex++] = (float) (yl-centrey);
                vertices[vertexindex++] = (float) (z);
                vertices[vertexindex++] = (float) (x-centrex);
                vertices[vertexindex++] = (float) (y-centrey);
                vertices[vertexindex++] = (float) (-z);
                vertices[vertexindex++] = (float) (xl-centrex);
                vertices[vertexindex++] = (float) (yl-centrey);
                vertices[vertexindex++] = (float) (-z);
                color[colorindex++] = 1; color[colorindex++] = 1; color[colorindex++] = 0;  color[colorindex++] = 1;
                color[colorindex++] = 1; color[colorindex++] = 1; color[colorindex++] = 0;  color[colorindex++] = 1;
                color[colorindex++] = 1f; color[colorindex++] = 0f; color[colorindex++] = 0;  color[colorindex++] = 1;
                color[colorindex++] = 1f; color[colorindex++] = 0f; color[colorindex++] = 0;  color[colorindex++] = 1;
            }
            controlptindex+=6;
        }
        for (v0=0,v1=1,v2=4,v3=5,v4=2,v5=3,v6=6,v7=7;v7<vertexindex/3;v0+=4,v1+=4,v2+=4,v3+=4,v4+=4,v5+=4,v6+=4,v7+=4)
        {   //the front
            pindex[indx++]=v0;
            pindex[indx++]=v1;
            pindex[indx++]=v2;
            pindex[indx++]=v2;
            pindex[indx++]=v1;
            pindex[indx++]=v3;
            //back
            pindex[indx++]=v4;
            pindex[indx++]=v5;
            pindex[indx++]=v6;
            pindex[indx++]=v6;
            pindex[indx++]=v5;
            pindex[indx++]=v7;
            //bottom
            pindex[indx++]=v4;
            pindex[indx++]=v0;
            pindex[indx++]=v2;
            pindex[indx++]=v2;
            pindex[indx++]=v6;
            pindex[indx++]=v4;
            //top
            pindex[indx++]=v5;
            pindex[indx++]=v1;
            pindex[indx++]=v3;
            pindex[indx++]=v3;
            pindex[indx++]=v7;
            pindex[indx++]=v5;
        }
        //cover bottom end
        pindex[indx++]=1;
        pindex[indx++]=0;
        pindex[indx++]=2;
        pindex[indx++]=2;
        pindex[indx++]=3;
        pindex[indx++]=1;
        //cover the top end
        pindex[indx++]=v1;
        pindex[indx++]=v0;
        pindex[indx++]=v4;
        pindex[indx++]=v4;
        pindex[indx++]=v5;
        pindex[indx++]=v1;

        Curve c = new Curve();
        c.vertex = Arrays.copyOf(vertices,vertexindex);
        c.index = Arrays.copyOf(pindex,indx);
        c.color = Arrays.copyOf(color,colorindex);
        return c;
    }
}
