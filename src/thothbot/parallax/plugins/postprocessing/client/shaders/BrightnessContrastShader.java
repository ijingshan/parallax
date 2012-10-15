/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file is part of Parallax project.
 * 
 * Parallax is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 * 
 * Parallax is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * Parallax. If not, see http://www.gnu.org/licenses/.
 */

package thothbot.parallax.plugins.postprocessing.client.shaders;

import thothbot.parallax.core.client.shaders.Shader;
import thothbot.parallax.core.client.shaders.Uniform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.TextResource;

/**
 * Brightness and contrast adjustment
 * <p>
 * <a href="https://github.com/evanw/glfx.js">github.com</a>
 * <ul>
 * <li>brightness: -1 to 1 (-1 is solid black, 0 is no change, and 1 is solid white)</li>
 * <li>contrast: -1 to 1 (-1 is solid gray, 0 is no change, and 1 is maximum contrast)</li>
 * </ul>
 * 
 * @author thothbot
 * @author tapio / http://tapio.github.com/
 */
public final class BrightnessContrastShader extends Shader 
{

	interface Resources extends DefaultResources
	{
		Resources INSTANCE = GWT.create(Resources.class);
		
		@Source("source/defaultUv.vs")
		TextResource getVertexShader();

		@Source("source/brightnessContrastShader.fs")
		TextResource getFragmentShader();
	}
	
	public BrightnessContrastShader() 
	{
		super(Resources.INSTANCE);
	}

	@Override
	protected void initUniforms()
	{
		this.addUniform("tDiffuse", new Uniform(Uniform.TYPE.T ));
		this.addUniform("brightness", new Uniform(Uniform.TYPE.F, 0.0));
		this.addUniform("contrast", new Uniform(Uniform.TYPE.F, 0.0));
	}

}