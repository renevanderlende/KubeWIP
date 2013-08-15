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

import android.util.Log;

import java.nio.ShortBuffer;
import java.util.ArrayList;

public class Face3D {

	public Face3D() {
		
	}
	
	// for triangles
	public Face3D(Vertex3D v1, Vertex3D v2, Vertex3D v3) {
		addVertex(v1);
		addVertex(v2);
		addVertex(v3);
	}	
	// for quadrilaterals
	public Face3D(Vertex3D v1, Vertex3D v2, Vertex3D v3, Vertex3D v4) {
		addVertex(v1);
		addVertex(v2);
		addVertex(v3);
		addVertex(v4);
	}
		
	public void addVertex(Vertex3D v) {
		mVertexList.add(v);
	}
	
	// must be called after all vertices are added
	public void setColor(Color3D c) {
		
		int last = mVertexList.size() - 1;
		if (last < 2) {
			Log.e("GLFace", "not enough vertices in setColor()");
		} else {
			Vertex3D vertex = mVertexList.get(last);
			
			// only need to do this if the color has never been set
			if (mColor == null) {
				while (vertex.color != null) {
					mVertexList.add(0, vertex);
					mVertexList.remove(last + 1);
					vertex = mVertexList.get(last);
				}
			}
			
			vertex.color = c;
		}

		mColor = c;
	}
	
	public int getIndexCount() {
		return (mVertexList.size() - 2) * 3;
	}
	
	public void putIndices(ShortBuffer buffer) {
		int last = mVertexList.size() - 1;

		Vertex3D v0 = mVertexList.get(0);
		Vertex3D vn = mVertexList.get(last);
		
		// push triangles into the buffer
		for (int i = 1; i < last; i++) {
			Vertex3D v1 = mVertexList.get(i);
			buffer.put(v0.index);
			buffer.put(v1.index);
			buffer.put(vn.index);
			v0 = v1;
		}
	}
	
	private ArrayList<Vertex3D> mVertexList = new ArrayList<Vertex3D>();
	private Color3D mColor;
}
