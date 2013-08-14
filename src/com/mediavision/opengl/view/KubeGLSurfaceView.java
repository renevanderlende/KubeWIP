/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.mediavision.opengl.view;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;

import android.util.AttributeSet;

public class KubeGLSurfaceView extends GLSurfaceView {
    
	public KubeGLSurfaceView(Context context) 
	{
		super(context);		
	}
	
	public KubeGLSurfaceView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);		
	}
/*    
    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawARGB(0, 128, 128, 128); // Transparent medium grey
    }
*/
}

