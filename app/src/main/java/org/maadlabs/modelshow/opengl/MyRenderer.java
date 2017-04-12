package org.maadlabs.modelshow.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

import org.maadlabs.modelshow.opengl.model.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by brainfreak on 4/2/17.
 */

public class MyRenderer implements GLSurfaceView.Renderer {

    Context mContext;
    Triangle mTriangle;
    float[] mVertices;
    private int mWidth;
    private int mHeight;

    public MyRenderer(Context context, float[] vertices) {
        mContext = context;
        mTriangle = new Triangle(context);
        mVertices = vertices;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        mTriangle.initProgram();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        mWidth = width;
        mHeight = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        mTriangle.draw(mVertices, mWidth, mHeight);
    }
}
