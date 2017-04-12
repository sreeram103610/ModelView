package org.maadlabs.modelshow.opengl.model;

import android.content.Context;
import android.opengl.GLES30;

import org.maadlabs.modelshow.R;
import org.maadlabs.modelshow.opengl.Utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by brainfreak on 4/2/17.
 */

public class Triangle {


    private static final int BUFFER_CAPACITY = 32;
    private int mProgram;
    private Context mContext;
    FloatBuffer mBuffer;

    public Triangle(Context context) {

        mContext = context;
    }

    public void initProgram() {

        int vertexShader = Utils.createShader(mContext, GLES30.GL_VERTEX_SHADER, R.raw.basic_vertex_shader);
        int fragmentShader = Utils.createShader(mContext, GLES30.GL_FRAGMENT_SHADER, R.raw.basic_fragment_shader);
        mProgram = Utils.createProgram(vertexShader, fragmentShader);

        if (mProgram == 0)
            return;
        GLES30.glBindAttribLocation(mProgram, 0, "vPosition");
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void draw(float[] vertices, int width, int height) {
        if (mBuffer == null) {
            mBuffer = ByteBuffer.allocateDirect(4 * vertices.length).order(ByteOrder.nativeOrder()).asFloatBuffer();
            mBuffer.put(vertices).position(0);
        }
        draw(width, height);
    }

    private void draw(int width, int height) {

        GLES30.glViewport(0, 0, width, height);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        if (mProgram == 0)
            return;

        GLES30.glUseProgram(mProgram);
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, mBuffer);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);
    }
}
