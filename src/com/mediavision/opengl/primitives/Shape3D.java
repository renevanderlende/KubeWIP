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

import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import com.mediavision.opengl.matrix.M4;

public class Shape3D {

	public Shape3D(World3D world) {
		mWorld = world;
	}
	
	public void addFace(Face3D face) {
		mFaceList.add(face);
	}
	
	public void setFaceColor(int face, Color3D color) {
		mFaceList.get(face).setColor(color);
	}
			
	public void putIndices(ShortBuffer buffer) {
		Iterator<Face3D> iter = mFaceList.iterator();
		while (iter.hasNext()) {
			Face3D face = iter.next();
			face.putIndices(buffer);
		}		
	}
	
	public int getIndexCount() {
		int count = 0;
		Iterator<Face3D> iter = mFaceList.iterator();
		while (iter.hasNext()) {
			Face3D face = iter.next();
			count += face.getIndexCount();
		}		
		return count;
	}

	public Vertex3D addVertex(float x, float y, float z) {
		
		// look for an existing GLVertex first
		Iterator<Vertex3D> iter = mVertexList.iterator();
		while (iter.hasNext()) {
			Vertex3D vertex = iter.next();
			if (vertex.x == x && vertex.y == y && vertex.z == z) {
				return vertex;
			}
		}
		
		// doesn't exist, so create new vertex
		Vertex3D vertex = mWorld.addVertex(x, y, z);
		mVertexList.add(vertex);
		return vertex;
	}

	public void animateTransform(M4 transform) {
		mAnimateTransform = transform;
		
		if (mTransform != null)
			transform = mTransform.multiply(transform);

		Iterator<Vertex3D> iter = mVertexList.iterator();
		while (iter.hasNext()) {
			Vertex3D vertex = iter.next();
			mWorld.transformVertex(vertex, transform);
		}
	}
	
	public void startAnimation() {
	}

	public void endAnimation() {
		if (mTransform == null) {
			mTransform = new M4(mAnimateTransform);
		} else {
			mTransform = mTransform.multiply(mAnimateTransform);
		}
	}

	public M4						mTransform;
	public M4						mAnimateTransform;
	protected ArrayList<Face3D>		mFaceList = new ArrayList<Face3D>();
	protected ArrayList<Vertex3D>	mVertexList = new ArrayList<Vertex3D>();
	protected ArrayList<Integer>	mIndexList = new ArrayList<Integer>();	// make more efficient?
	protected World3D mWorld;
}
