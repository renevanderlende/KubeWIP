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

package com.mediavision.opengl.primitives;

public class Cube extends Shape3D {

	public Cube(World3D world, float left, float bottom, float back, float right, float top, float front) {

		super(world);
		
       	Vertex3D leftBottomBack   = addVertex(left , bottom, back);
        Vertex3D rightBottomBack  = addVertex(right, bottom, back);
       	Vertex3D leftTopBack 	  = addVertex(left , top   , back);
        Vertex3D rightTopBack 	  = addVertex(right, top   , back);
       	Vertex3D leftBottomFront  = addVertex(left , bottom, front);
        Vertex3D rightBottomFront = addVertex(right, bottom, front);
       	Vertex3D leftTopFront 	  = addVertex(left , top   , front);
        Vertex3D rightTopFront 	  = addVertex(right, top   , front);

        // vertices are added in a clockwise orientation (when viewed from the outside)
        addFace(new Face3D(leftBottomBack , leftBottomFront , rightBottomFront, rightBottomBack)); // bottom
        addFace(new Face3D(leftBottomFront, leftTopFront	, rightTopFront	  , rightBottomFront));// front
        addFace(new Face3D(leftBottomBack , leftTopBack		, leftTopFront	  , leftBottomFront)); // left
        addFace(new Face3D(rightBottomBack, rightBottomFront, rightTopFront	  , rightTopBack));	   // right
        addFace(new Face3D(leftBottomBack , rightBottomBack , rightTopBack	  , leftTopBack));	   // back
        addFace(new Face3D(leftTopBack    , rightTopBack	, rightTopFront	  , leftTopFront));	   // top
	}
	
    public static final int kBottom = 0;
    public static final int kFront  = 1;
    public static final int kLeft   = 2;
    public static final int kRight  = 3;
    public static final int kBack   = 4;
    public static final int kTop    = 5;
	
}
