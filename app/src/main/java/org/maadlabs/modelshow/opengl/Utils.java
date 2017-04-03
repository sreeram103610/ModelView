package org.maadlabs.modelshow.opengl;

import android.content.Context;
import android.opengl.GLES30;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by brainfreak on 4/2/17.
 */

public class Utils {

    private static final String SHADER_TAG = "SHADER_DEBUG";
    private static final String PROGRAM_TAG = "PROGRAM_DEBUG";

    public static int createShader(Context context, int type, int shaderId) {

        int shader = GLES30.glCreateShader(type);

        if (shader == 0) {
            return 0;
        }

        String shaderSource = fileToString(context, shaderId);

        GLES30.glShaderSource(shader, shaderSource);
        GLES30.glCompileShader(shader);

        int[] compilationStatus = new int[1];
        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compilationStatus, 0);

        if (compilationStatus[0] == 0) {

            Log.d(SHADER_TAG, GLES30.glGetShaderInfoLog(shader));
            GLES30.glDeleteShader(shader);
            return 0;
        }

        return shader;
    }

    public static int createProgram(int vertexShader, int fragmentShader) {

        int program;

        program = GLES30.glCreateProgram();

        if (program == 0) {
            return 0;
        }

        int linkStatus[] = new int[1];
        GLES30.glAttachShader(program, vertexShader);
        GLES30.glAttachShader(program, fragmentShader);

        GLES30.glLinkProgram(program);

        GLES30.glGetProgramiv(program, GLES30.GL_LINK_STATUS, linkStatus, 0);

        if (linkStatus[0] == 0) {

            Log.d(PROGRAM_TAG, GLES30.glGetProgramInfoLog(program));
            GLES30.glDeleteProgram(program);
            return 0;
        }
        return program;
    }

    private static String fileToString(Context context, int id) {

        InputStream stream = context.getResources().openRawResource(id);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
