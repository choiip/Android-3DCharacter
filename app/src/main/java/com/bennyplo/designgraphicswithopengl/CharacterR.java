package com.bennyplo.designgraphicswithopengl;

import java.util.Arrays;

import static java.lang.Math.PI;

public class CharacterR extends CharacterBase {
    private final int RESOLUTION = 30;
    private final double BEGIN_ANGLE = 270 * PI / 180;
    private final double END_ANGLE = 450 * PI / 180;

    float []vertex = null;
    float []color = null;
    int []index = null;

    public CharacterR(){
        super();
    }

    float[] getCharVertex() {
        if (vertex == null) {
            float vsf[] = {
                    -4, 5, 1,
                    1, 5, 1,
                    1, 3, 1,
                    -1, 3, 1,
                    -1, 1, 1,
                    1, 1, 1,
                    1, -1, 1,
                    4, -5, 1,
                    1, -5, 1,
                    -1, -1, 1,
                    -1, -5, 1,
                    -4, -5, 1,
            };
            float vsb[] = {
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
            float[] innerVertex = Util.createEllipseArc(1, 1, 1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
            float[] outerVertex = Util.createEllipseArc(3, 3, 1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
            float[] vcf = Util.join(innerVertex, outerVertex);
            for (int i=0; i<vcf.length; i+=3) {
                vcf[i] += 1;     // x
                vcf[i+1] += 2;   // y
            }
            float[] vf = Util.join(vsf, vcf);

            float[] backInnerVertex = Util.createEllipseArc(1, 1, -1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
            float[] backOuterVertex = Util.createEllipseArc(3, 3, -1, BEGIN_ANGLE, END_ANGLE, RESOLUTION);
            float[] vcb = Util.join(backInnerVertex, backOuterVertex);
            for (int i=0; i<vcb.length; i+=3) {
                vcb[i] += 1;     // x
                vcb[i+1] += 2;   // y
            }
            float[] vb = Util.join(vsb, vcb);

            vertex = Util.join(vf, vb);
        }
        return vertex;
    }

    float[] getCharColor() {
        if (color == null) {
            int vertexCount = vertex.length / 3;
            float[] cf = new float[vertexCount * 2];
            float[] cb = new float[vertexCount * 2];
            Arrays.fill(cf, 1);
            Arrays.fill(cb, 1);
            for (int i=0; i<cb.length; i+=4) {
                cb[i] = 0;
                cb[i+1] = 0;
            }
            color = Util.join(cf, cb);
        }
        return color;
    }

    int[] getCharIndex() {
        if (index == null) {
            int i_f[] = {
                    0, 1, 3, 1, 2, 3,
                    0, 3, 4, 0, 4, 11,
                    4, 5, 6, 4, 6, 9,
                    6, 7, 8, 6, 8, 9,
                    4, 9, 11, 9, 10, 11,
            };
            int i_b[] = {
                    12,13,15,13,14,15,
                    12,15,16,12,16,23,
                    16,17,18,16,18,21,
                    18,19,20,18,20,21,
                    16,21,23,21,22,23,
            };
            int indexOffset = 12;
            int vertexCount = vertex.length / 3;
            int cvertexCount = vertexCount - (indexOffset*2);
            int halfCvertexCount = cvertexCount / 2;
            int[] icf = new int[3*(cvertexCount -4)/2];

            for (int i=0, currentIndex = indexOffset; i<icf.length-1; i+=6, currentIndex++) {
                icf[i + 0] = currentIndex;
                icf[i + 1] = currentIndex + halfCvertexCount / 2;
                icf[i + 2] = currentIndex + halfCvertexCount / 2 + 1;
                icf[i + 3] = currentIndex;
                icf[i + 4] = currentIndex + 1;
                icf[i + 5] = currentIndex + halfCvertexCount / 2 + 1;
            }

            int[] icb = new int[3*(cvertexCount -4)/2];

            for (int i=0, currentIndex=indexOffset+halfCvertexCount+indexOffset; i<icb.length-1; i+=6, currentIndex++) {
                icb[i + 0] = currentIndex;
                icb[i + 1] = currentIndex + halfCvertexCount / 2;
                icb[i + 2] = currentIndex + halfCvertexCount / 2 + 1;
                icb[i + 3] = currentIndex;
                icb[i + 4] = currentIndex + 1;
                icb[i + 5] = currentIndex + halfCvertexCount / 2 + 1;
            }
            int[] front = Util.join(i_f, icf);
                    for (int i=0; i<i_b.length; i++) {
                i_b[i] += halfCvertexCount;
            }

            int[] back = Util.join(i_b, icb);
            int[] frontback = Util.join(front, back);

            int gi1[] = generateIndex(0, 11, vertexCount / 2, true);
            int gi2[] = generateIndex(12, vertexCount / 4 + 4, vertexCount / 2);
            int gi3[] = generateIndex(vertexCount / 4 + 4 + 2, vertexCount / 2 - 2, vertexCount / 2);

            int gi[] = Util.join(gi1, gi2);
            gi = Util.join(gi, gi3);
            index = Util.join(frontback, gi);
        }
        return index;
    }
}
