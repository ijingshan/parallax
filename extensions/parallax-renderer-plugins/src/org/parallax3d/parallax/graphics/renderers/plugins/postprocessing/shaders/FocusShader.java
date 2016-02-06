/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file is part of Parallax project.
 * 
 * Parallax is free software: you can redistribute it and/or modify it 
 * under the terms of the Creative Commons Attribution 3.0 Unported License.
 * 
 * Parallax is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the Creative Commons Attribution 
 * 3.0 Unported License. for more details.
 * 
 * You should have received a copy of the the Creative Commons Attribution 
 * 3.0 Unported License along with Parallax. 
 * If not, see http://creativecommons.org/licenses/by/3.0/.
 */

package org.parallax3d.parallax.graphics.renderers.plugins.postprocessing.shaders;

import org.parallax3d.parallax.graphics.renderers.shaders.Shader;
import org.parallax3d.parallax.graphics.renderers.shaders.Uniform;

import org.parallax3d.parallax.system.ClassUtils;
import org.parallax3d.parallax.system.SourceTextResource;

/**
 * Focus shader
 * <p>
 * Based on three.js code<br>
 * Based on PaintEffect postprocess from ro.me <a href="http://code.google.com/p/3-dreams-of-black/source/browse/deploy/js/effects/PaintEffect.js">code.google.com/p/3-dreams-of-black</a>
 * 
 * @author thothbot
 *
 */
public final class FocusShader extends Shader
{
	interface Resources extends DefaultResources
	{
		Resources INSTANCE = ClassUtils.newProxyInstance(Resources.class);
		
		@Source("source/defaultUv.vs")
		SourceTextResource getVertexShader();

		@Source("source/focus.fs")
		SourceTextResource getFragmentShader();
	}

	public FocusShader()
	{
		super(Resources.INSTANCE);
	}

	@Override
	protected void initUniforms()
	{
		this.addUniform("tDiffuse", new Uniform(Uniform.TYPE.T ));
		this.addUniform("screenWidth", new Uniform(Uniform.TYPE.F, 1024.0));
		this.addUniform("screenHeight", new Uniform(Uniform.TYPE.F, 1024.0));
		this.addUniform("sampleDistance", new Uniform(Uniform.TYPE.F, 0.94));
		this.addUniform("waveFactor", new Uniform(Uniform.TYPE.F, 0.00125));
	}
}