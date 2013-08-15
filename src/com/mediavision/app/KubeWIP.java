/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mediavision.app;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;

import com.mediavision.app.R;
import com.mediavision.opengl.primitives.Cube;
import com.mediavision.opengl.primitives.Color3D;
import com.mediavision.opengl.primitives.Shape3D;
import com.mediavision.opengl.primitives.World3D;
import com.mediavision.opengl.primitives.Layer3D;
import com.mediavision.opengl.renderer.KubeRenderer;
import com.mediavision.opengl.view.KubeGLSurfaceView;


public class KubeWIP extends Activity implements KubeRenderer.AnimationCallback {

	public Window mMainAppShell;
	public WindowManager mWManager;
	public int mX, mY;
	public LayoutParams mLayoutParams;
	public Gravity mGravity; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mMainAppShell = getWindow();				// Convenient handle to the main app window
//        mLayoutParams = mMainAppShell.getAttributes();
        
//        mWManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        
/*        mMainAppShell = getWindow();				// Convenient handle to the main app window
//        mLayoutParams = mMainAppShell.getAttributes();
        
//        mWManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        
        mMainAppShell.setLayout(360, 640); 		// Set the maximum with/height of the app Window
        mMainAppShell.setFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL ); // Enable touch outside window
//        mMainAppShell.setFlags( WindowManager.LayoutParams.FLAG_SCALED, WindowManager.LayoutParams.FLAG_SCALED ); 			

//        mMainAppShell.setAttributes(mLayoutParams);
        
//        mMainAppShell.requestFeature(.....); // Must always be called before "setContentView". Can't be reset after!!!
//        setContentView(R.layout. ...);
 
//		  openActivity(R.id.<some_button>, <some_activity>.class);

 * 		If you set the maximum width an height of the main app window, you will have to be aware of scaling!
 * 		When the phone/tablet hardware is different from the WxH that is being set, scaling of your
 * 		window content will take place.
 * 
 * 		Example: Samsung Galaxy S3 has a WxH = 720x1280 so 'setLayout(360, 640)' will scale the content
 * 				 of the app window to half it's original size. 
 */     
        mMainAppShell.setLayout(360, 640); 		// Set the maximum with/height of the app Window
/*
 * 		Our window is not full screen AND we want to be able to touch other apps/icons, so we need
 * 		to tell the WindowManager to stop caturing ALL touches.
 */     
        mMainAppShell.setFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL ); // Enable touch outside window
//        mMainAppShell.setFlags( WindowManager.LayoutParams.FLAG_SCALED		   , WindowManager.LayoutParams.FLAG_SCALED ); 			

     
//        mMainAppShell.setAttributes(mLayoutParams);
        
//      mMainAppShell.requestFeature(.....); // Must always be called before "setContentView". Can't be reset after!!!
        setContentView(R.layout.kube);
 
		openActivity(R.id.custom_button, Hello.class);
        
        
        /*
         *  For a translucent OpenGL view only steps (1) and (2) are needed (next to a translucent app THEME) 
        */
		mView = (KubeGLSurfaceView) findViewById(R.id.bucketFrame);
        mView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);			// **** (1) Set for TRANSLUCENCY (alpha 0) ****
		
        mRenderer = new KubeRenderer(makeGLWorld(), this);

        mView.setRenderer(mRenderer);
        mView.getHolder().setFormat(PixelFormat.TRANSLUCENT);	// **** (2) MUST be set AFTER setRenderer ****

   }

    @Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}

	private void openActivity(int buttonId, final Class<?> activityClass) {
		findViewById(buttonId).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), activityClass);
				startActivity(intent);
			}
		});
	}

	private boolean InitializeMainWindow() {
		boolean mSuccess = false;
/*		
	    mComponentName 	= new ComponentName(this, getClass());
	    mWindowManager 	= ((WindowManager)getSystemService("window"));
	    mPackageManager = getPackageManager();
//	    mAppManager 	= getAppManager();
	    mMainActivity 	= getMainActivityComponent();
	    
	    try
	    {
	      mResources = mPackageManager.getResourcesForApplication("com.mediavision.app.res");
	      mContext = this;
	    }		
		catch
		{
			try	{
				
			}
			finally {
				if (ResetMainWindow()) { mSuccess = true; };
			}
		}
*/	    
		return mSuccess;
	}
	
	private boolean ResetMainWindow() {
		boolean mSuccess = true;
		return mSuccess;
	}
	
    private World3D makeGLWorld()
    {
        World3D world = new World3D();

        int one  = 0x10000;
        int half = 0x08000;
        
        Color3D red    = new Color3D(one, 0   , 0  );
        Color3D green  = new Color3D(0  , one , 0  );
        Color3D blue   = new Color3D(0  , 0   , one);
        Color3D yellow = new Color3D(one, one , 0  );
        Color3D orange = new Color3D(one, half, 0  );
        Color3D white  = new Color3D(one, one , one);
        Color3D black  = new Color3D(0  , 0   , 0  );

        // coordinates for our cubes
        float c0 = -1.00f;
        float c1 = -0.38f;
        float c2 = -0.32f;
        float c3 =  0.32f;
        float c4 =  0.38f;
        float c5 =  1.00f;

        // top back, left to right
        mCubes[0]  = new Cube(world, c0, c4, c0, c1, c5, c1);
        mCubes[1]  = new Cube(world, c2, c4, c0, c3, c5, c1);
        mCubes[2]  = new Cube(world, c4, c4, c0, c5, c5, c1);
        // top middle, left to right
        mCubes[3]  = new Cube(world, c0, c4, c2, c1, c5, c3);
        mCubes[4]  = new Cube(world, c2, c4, c2, c3, c5, c3);
        mCubes[5]  = new Cube(world, c4, c4, c2, c5, c5, c3);
        // top front, left to right
        mCubes[6]  = new Cube(world, c0, c4, c4, c1, c5, c5);
        mCubes[7]  = new Cube(world, c2, c4, c4, c3, c5, c5);
        mCubes[8]  = new Cube(world, c4, c4, c4, c5, c5, c5);
        // middle back, left to right
        mCubes[9]  = new Cube(world, c0, c2, c0, c1, c3, c1);
        mCubes[10] = new Cube(world, c2, c2, c0, c3, c3, c1);
        mCubes[11] = new Cube(world, c4, c2, c0, c5, c3, c1);
        // middle middle, left to right
        mCubes[12] = new Cube(world, c0, c2, c2, c1, c3, c3);
        mCubes[13] = null;
        mCubes[14] = new Cube(world, c4, c2, c2, c5, c3, c3);
        // middle front, left to right
        mCubes[15] = new Cube(world, c0, c2, c4, c1, c3, c5);
        mCubes[16] = new Cube(world, c2, c2, c4, c3, c3, c5);
        mCubes[17] = new Cube(world, c4, c2, c4, c5, c3, c5);
        // bottom back, left to right
        mCubes[18] = new Cube(world, c0, c0, c0, c1, c1, c1);
        mCubes[19] = new Cube(world, c2, c0, c0, c3, c1, c1);
        mCubes[20] = new Cube(world, c4, c0, c0, c5, c1, c1);
        // bottom middle, left to right
        mCubes[21] = new Cube(world, c0, c0, c2, c1, c1, c3);
        mCubes[22] = new Cube(world, c2, c0, c2, c3, c1, c3);
        mCubes[23] = new Cube(world, c4, c0, c2, c5, c1, c3);
        // bottom front, left to right
        mCubes[24] = new Cube(world, c0, c0, c4, c1, c1, c5);
        mCubes[25] = new Cube(world, c2, c0, c4, c3, c1, c5);
        mCubes[26] = new Cube(world, c4, c0, c4, c5, c1, c5);

        // paint the sides
        int i, j;
        // set all faces black by default
        for (i = 0; i < 27; i++) {
            Cube cube = mCubes[i];
            if (cube != null) {
                for (j = 0; j < 6; j++)
                    cube.setFaceColor(j, black);
            }
        }

        // paint top
        for (i = 0; i < 9; i++)
            mCubes[i].setFaceColor(Cube.kTop, orange);
        // paint bottom
        for (i = 18; i < 27; i++)
            mCubes[i].setFaceColor(Cube.kBottom, red);
        // paint left
        for (i = 0; i < 27; i += 3)
            mCubes[i].setFaceColor(Cube.kLeft, yellow);
        // paint right
        for (i = 2; i < 27; i += 3)
            mCubes[i].setFaceColor(Cube.kRight, white);
        // paint back
        for (i = 0; i < 27; i += 9)
            for (j = 0; j < 3; j++)
                mCubes[i + j].setFaceColor(Cube.kBack, blue);
        // paint front
        for (i = 6; i < 27; i += 9)
            for (j = 0; j < 3; j++)
                mCubes[i + j].setFaceColor(Cube.kFront, green);

        for (i = 0; i < 27; i++)
            if (mCubes[i] != null)
                world.addShape(mCubes[i]);

        // initialize our permutation to solved position
        mPermutation = new int[27];
        for (i = 0; i < mPermutation.length; i++)
            mPermutation[i] = i;

        createLayers();
        updateLayers();

        world.generate();

        return world;
    }

    private void createLayers() {
        mLayers[kUp]      = new Layer3D(Layer3D.kAxisY);
        mLayers[kDown]    = new Layer3D(Layer3D.kAxisY);
        mLayers[kLeft]    = new Layer3D(Layer3D.kAxisX);
        mLayers[kRight]   = new Layer3D(Layer3D.kAxisX);
        mLayers[kFront]   = new Layer3D(Layer3D.kAxisZ);
        mLayers[kBack]    = new Layer3D(Layer3D.kAxisZ);
        mLayers[kMiddle]  = new Layer3D(Layer3D.kAxisX);
        mLayers[kEquator] = new Layer3D(Layer3D.kAxisY);
        mLayers[kSide] 	  = new Layer3D(Layer3D.kAxisZ);
    }

    private void updateLayers() {
        Layer3D layer3D;
        Shape3D[] shapes;
        int i, j, k;

        // up layer
        layer3D = mLayers[kUp];
        shapes = layer3D.mShapes;
        for (i = 0; i < 9; i++)
            shapes[i] = mCubes[mPermutation[i]];

        // down layer
        layer3D = mLayers[kDown];
        shapes = layer3D.mShapes;
        for (i = 18, k = 0; i < 27; i++)
            shapes[k++] = mCubes[mPermutation[i]];

        // left layer
        layer3D = mLayers[kLeft];
        shapes = layer3D.mShapes;
        for (i = 0, k = 0; i < 27; i += 9)
            for (j = 0; j < 9; j += 3)
                shapes[k++] = mCubes[mPermutation[i + j]];

        // right layer
        layer3D = mLayers[kRight];
        shapes = layer3D.mShapes;
        for (i = 2, k = 0; i < 27; i += 9)
            for (j = 0; j < 9; j += 3)
                shapes[k++] = mCubes[mPermutation[i + j]];

        // front layer
        layer3D = mLayers[kFront];
        shapes = layer3D.mShapes;
        for (i = 6, k = 0; i < 27; i += 9)
            for (j = 0; j < 3; j++)
                shapes[k++] = mCubes[mPermutation[i + j]];

        // back layer
        layer3D = mLayers[kBack];
        shapes = layer3D.mShapes;
        for (i = 0, k = 0; i < 27; i += 9)
            for (j = 0; j < 3; j++)
                shapes[k++] = mCubes[mPermutation[i + j]];

        // middle layer
        layer3D = mLayers[kMiddle];
        shapes = layer3D.mShapes;
        for (i = 1, k = 0; i < 27; i += 9)
            for (j = 0; j < 9; j += 3)
                shapes[k++] = mCubes[mPermutation[i + j]];

        // equator layer
        layer3D = mLayers[kEquator];
        shapes = layer3D.mShapes;
        for (i = 9, k = 0; i < 18; i++)
            shapes[k++] = mCubes[mPermutation[i]];

        // side layer
        layer3D = mLayers[kSide];
        shapes = layer3D.mShapes;
        for (i = 3, k = 0; i < 27; i += 9)
            for (j = 0; j < 3; j++)
                shapes[k++] = mCubes[mPermutation[i + j]];
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mView.onPause();
    }

    public void animate() {
        // change our angle of view
        mRenderer.setAngle(mRenderer.getAngle() + 1.2f);

        if (mCurrentLayer == null) {
            int layerID = mRandom.nextInt(9);
            mCurrentLayer = mLayers[layerID];
            mCurrentLayerPermutation = mLayerPermutations[layerID];
            mCurrentLayer.startAnimation();
            boolean direction = mRandom.nextBoolean();
            int count = mRandom.nextInt(3) + 1;

            count = 1;
            direction = false;
            mCurrentAngle = 0;
             if (direction) {
                mAngleIncrement = (float)Math.PI / 50;
                   mEndAngle = mCurrentAngle + ((float)Math.PI * count) / 2f;
               } else {
                mAngleIncrement = -(float)Math.PI / 50;
                   mEndAngle = mCurrentAngle - ((float)Math.PI * count) / 2f;
            }
        }

         mCurrentAngle += mAngleIncrement;

         if ((mAngleIncrement > 0f && mCurrentAngle >= mEndAngle) ||
                 (mAngleIncrement < 0f && mCurrentAngle <= mEndAngle)) {
             mCurrentLayer.setAngle(mEndAngle);
             mCurrentLayer.endAnimation();
             mCurrentLayer = null;

             // adjust mPermutation based on the completed layer rotation
             int[] newPermutation = new int[27];
             for (int i = 0; i < 27; i++) {
                newPermutation[i] = mPermutation[mCurrentLayerPermutation[i]];
 //    			newPermutation[i] = mCurrentLayerPermutation[mPermutation[i]];
             }
             mPermutation = newPermutation;
             updateLayers();

         } else {
             mCurrentLayer.setAngle(mCurrentAngle);
         }
    }

    static int[][] mLayerPermutations = { 
    		// permutations corresponding to a pi/2 rotation of each layer about its axis
            // permutation for UP layer
            { 2, 5, 8, 1, 4, 7, 0, 3, 6, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 },
            // permutation for DOWN layer
            { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20, 23, 26, 19, 22, 25, 18, 21, 24 },
            // permutation for LEFT layer
            { 6, 1, 2, 15, 4, 5, 24, 7, 8, 3, 10, 11, 12, 13, 14, 21, 16, 17, 0, 19, 20, 9, 22, 23, 18, 25, 26 },
            // permutation for RIGHT layer
            { 0, 1, 8, 3, 4, 17, 6, 7, 26, 9, 10, 5, 12, 13, 14, 15, 16, 23, 18, 19, 2, 21, 22, 11, 24, 25, 20 },
            // permutation for FRONT layer
            { 0, 1, 2, 3, 4, 5, 24, 15, 6, 9, 10, 11, 12, 13, 14, 25, 16, 7, 18, 19, 20, 21, 22, 23, 26, 17, 8 },
            // permutation for BACK layer
            { 18, 9, 0, 3, 4, 5, 6, 7, 8, 19, 10, 1, 12, 13, 14, 15, 16, 17, 20, 11, 2, 21, 22, 23, 24, 25, 26 },
            // permutation for MIDDLE layer
            { 0, 7, 2, 3, 16, 5, 6, 25, 8, 9, 4, 11, 12, 13, 14, 15, 22, 17, 18, 1, 20, 21, 10, 23, 24, 19, 26 },
            // permutation for EQUATOR layer
            { 0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 14, 17, 10, 13, 16, 9, 12, 15, 18, 19, 20, 21, 22, 23, 24, 25, 26 },
            // permutation for SIDE layer
            { 0, 1, 2, 21, 12, 3, 6, 7, 8, 9, 10, 11, 22, 13, 4, 15, 16, 17, 18, 19, 20, 23, 14, 5, 24, 25, 26 }
    };

    KubeGLSurfaceView mView;
    KubeRenderer 	  mRenderer;
    
    Cube[]  mCubes  = new Cube[27];
    Layer3D[] mLayers = new Layer3D[9]; 							  // a Layer for each possible move
    
    int[] mPermutation; 										  // current permutation of starting position
    int[] mCurrentLayerPermutation;

    Random mRandom      = new Random(System.currentTimeMillis()); // for random cube movements
    Layer3D mCurrentLayer = null; 								  // currently turning layer

    float mCurrentAngle, mEndAngle; 							  // current and final angle for current Layer animation
    float mAngleIncrement; 										  // amount to increment angle

    static final int kUp      = 0;								  // names for our 9 layers 
    static final int kDown    = 1;								  // (based on notation from http://www.cubefreak.net/notation.html)
    static final int kLeft    = 2;
    static final int kRight   = 3;
    static final int kFront   = 4;
    static final int kBack    = 5;
    static final int kMiddle  = 6;
    static final int kEquator = 7;
    static final int kSide    = 8;

}
