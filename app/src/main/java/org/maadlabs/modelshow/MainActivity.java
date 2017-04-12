package org.maadlabs.modelshow;

import android.app.ActivityManager;
import android.app.NativeActivity;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.maadlabs.modelshow.opengl.MyRenderer;

public class MainActivity extends NativeActivity {

    GLSurfaceView mGLSurfaceView;
    MyRenderer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        float[] vertices = {0.0f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f};


        if (isGLVersionSupported() || Build.FINGERPRINT.startsWith("generic")) {
            mGLSurfaceView = new GLSurfaceView(this);
            mRenderer = new MyRenderer(this, vertices);
            mGLSurfaceView.setEGLContextClientVersion(3); // opengl es 3.0
            mGLSurfaceView.setRenderer(mRenderer);
        }
        if (mGLSurfaceView != null)
            setContentView(mGLSurfaceView);
        else
            setContentView(R.layout.support_simple_spinner_dropdown_item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    public boolean isGLVersionSupported() {
        ActivityManager am =
                ( ActivityManager ) getSystemService ( Context.ACTIVITY_SERVICE );
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return ( info.reqGlEsVersion >= 0x30000 );
    }
}
