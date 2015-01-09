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

package thothbot.parallax.core.client.renders.shaders;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.TextResource;

public class DashedShader extends Shader 
{

	interface Resources extends DefaultResources
	{
		Resources INSTANCE = GWT.create(Resources.class);

		@Source("source/dashed.vs")
		TextResource getVertexShader();

		@Source("source/dashed.fs")
		TextResource getFragmentShader();
	}

	public DashedShader() 
	{
		super(Resources.INSTANCE);
	}

	@Override
	protected void initUniforms()
	{
		this.setUniforms(UniformsLib.getCommon());
		this.setUniforms(UniformsLib.getFog());
		this.addUniform("scale",     new Uniform(Uniform.TYPE.F, 1.0 ));
		this.addUniform("dashSize",  new Uniform(Uniform.TYPE.F, 1.0 ));
		this.addUniform("totalSize", new Uniform(Uniform.TYPE.F, 2.0 ));
	}
	
	@Override
	protected void updateVertexSource(String src)
	{
		super.updateFragmentSource(Shader.updateShaderSource(src, ChunksVertexShader.COLOR_PARS, ChunksVertexShader.COLOR));	
	}
	
	@Override
	protected void updateFragmentSource(String src)
	{
		List<String> vars = Arrays.asList(
			ChunksFragmentShader.COLOR_PARS,
			ChunksFragmentShader.FOG_PARS
		);
			
		List<String> main = Arrays.asList(
			ChunksFragmentShader.COLOR,
			ChunksFragmentShader.FOG
		);
			
		super.updateFragmentSource(Shader.updateShaderSource(src, vars, main));	
	}
}