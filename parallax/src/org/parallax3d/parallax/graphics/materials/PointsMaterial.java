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

package org.parallax3d.parallax.graphics.materials;

import org.parallax3d.parallax.Log;
import org.parallax3d.parallax.system.ThreejsObject;
import org.parallax3d.parallax.system.ViewportResizeBus;
import org.parallax3d.parallax.system.ViewportResizeListener;
import org.parallax3d.parallax.graphics.renderers.shaders.ParticleBasicShader;
import org.parallax3d.parallax.graphics.renderers.shaders.Shader;
import org.parallax3d.parallax.graphics.renderers.shaders.Uniform;
import org.parallax3d.parallax.graphics.textures.Texture;
import org.parallax3d.parallax.graphics.cameras.Camera;
import org.parallax3d.parallax.math.Color;
import org.parallax3d.parallax.system.FastMap;

@ThreejsObject("THREE.PointsMaterial")
public final class PointsMaterial extends Material implements HasFog,
		HasColor, HasMap, HasVertexColors, ViewportResizeListener {
	private boolean isFog;

	private Color color;

	private Texture map;

	private Material.COLORS vertexColors;

	private double size;

	private boolean sizeAttenuation;

	public PointsMaterial() {

		setFog(true);

		setColor( 0xffffff );

		setSize(1.0);
		setSizeAttenuation(true);

		setVertexColors(Material.COLORS.NO);

		ViewportResizeBus.addViewportResizeListener(this);

	}

	public double getSize() {
		return this.size;
	}

	public PointsMaterial setSize(double size) {
		this.size = size;
		return this;
	}

	public boolean isSizeAttenuation() {
		return sizeAttenuation;
	}

	public PointsMaterial setSizeAttenuation(boolean sizeAttenuation) {
		this.sizeAttenuation = sizeAttenuation;
		return this;
	}

	public Shader getAssociatedShader() {
		return new ParticleBasicShader();
	}

	@Override
	public boolean isFog() {
		return this.isFog;
	}

	@Override
	public PointsMaterial setFog(boolean fog) {
		this.isFog = fog;
		return this;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public PointsMaterial setColor(Color color) {
		this.color = color;
		return this;
	}

	@Override
	public PointsMaterial setColor(int color) {
		this.color = new Color( color );
		return this;
	}

	@Override
	public Texture getMap() {
		return this.map;
	}

	@Override
	public PointsMaterial setMap(Texture map) {
		this.map = map;
		return this;
	}

	@Override
	public Material.COLORS isVertexColors() {
		return this.vertexColors;
	}

	@Override
	public PointsMaterial setVertexColors(Material.COLORS vertexColors) {
		this.vertexColors = vertexColors;
		return this;
	}

	@Override
	public PointsMaterial clone () {

		PointsMaterial material = new PointsMaterial();

		super.clone(material);

		material.color.copy( this.color );

		material.map = this.map;

		material.size = this.size;
		material.sizeAttenuation = this.sizeAttenuation;

		material.vertexColors = this.vertexColors;

		material.isFog = this.isFog;

		return material;

	}

	@Override
	public void onViewportResize(int newWidth, int newHeight) {
		final FastMap<Uniform> uniforms = getShader().getUniforms();

		uniforms.get("scale").setValue(newHeight / 2.0);

	}

	@Override
	public void finalize() {
		ViewportResizeBus.removeViewportResizeListener(this);
		try {
			super.finalize();
		} catch (Throwable throwable) {
			Log.error("Exception in PointsMaterial.finalize:", throwable);
		}
	}
}
