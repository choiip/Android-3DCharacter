package com.bennyplo.designgraphicswithopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends GLSurfaceView {
    private final MyRenderer mRenderer;
    public MyView(Context context) {
        super(context);
        final MyView thisview=this;
        setEGLContextClientVersion(2);// Create an OpenGL ES 2.0 context.
        mRenderer = new MyRenderer();// Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mRenderer.update();
                thisview.requestRender();
            }
        };
        timer.scheduleAtFixedRate(task,100,100);
    }
}

