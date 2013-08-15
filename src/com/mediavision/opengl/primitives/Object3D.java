package com.mediavision.opengl.primitives;

import com.mediavision.opengl.common.GlVertex;
import com.mediavision.opengl.common.GlMatrix;

import javax.microedition.khronos.opengles.GL10;


public abstract class Object3D {
	public abstract void draw(GL10 gl);
	public abstract void calculateReflectionTexCoords(GlVertex vEye, GlMatrix mInvRot);
}
