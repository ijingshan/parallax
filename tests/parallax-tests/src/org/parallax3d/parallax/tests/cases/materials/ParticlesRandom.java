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

package org.parallax3d.parallax.tests.cases.materials;

import org.parallax3d.parallax.RenderingContext;
import org.parallax3d.parallax.graphics.cameras.PerspectiveCamera;
import org.parallax3d.parallax.graphics.core.Geometry;
import org.parallax3d.parallax.graphics.core.Object3D;
import org.parallax3d.parallax.graphics.materials.PointsMaterial;
import org.parallax3d.parallax.graphics.objects.Points;
import org.parallax3d.parallax.graphics.scenes.FogExp2;
import org.parallax3d.parallax.graphics.scenes.Scene;
import org.parallax3d.parallax.input.TouchMoveHandler;
import org.parallax3d.parallax.math.Vector3;
import org.parallax3d.parallax.tests.ParallaxTest;
import org.parallax3d.parallax.tests.ThreejsExample;

import java.util.ArrayList;
import java.util.List;

@ThreejsExample("webgl_points_random")
public final class ParticlesRandom extends ParallaxTest implements TouchMoveHandler
{

	Scene scene;
	PerspectiveCamera camera;
	
	List<PointsMaterial> materials;

	int width = 0, height = 0;
	int mouseX = 0;
	int mouseY = 0;

	double[][] parameters = new double[][]{
			{1, 1, 0.5, 5},
			{0.95, 1, 0.5, 4},
			{0.90, 1, 0.5, 3},
			{0.85, 1, 0.5, 2},
			{0.80, 1, 0.5, 1}
	};

	@Override
	public void onResize(RenderingContext context) {
		width = context.getWidth();
		height = context.getHeight();
	}

	@Override
	public void onStart(RenderingContext context)
	{
		scene = new Scene();
		camera = new PerspectiveCamera(
				75, // fov
				context.getAspectRation(), // aspect
				1, // near
				3000 // far 
		); 
		
		camera.getPosition().setZ(1000);
		
		scene.setFog( new FogExp2( 0x000000, 0.0007 ));
		
		Geometry geometry = new Geometry();

		for ( int i = 0; i < 20000; i ++ ) 
		{
			Vector3 vertex = new Vector3();
			vertex.setX( Math.random() * 2000 - 1000 );
			vertex.setY( Math.random() * 2000 - 1000 );
			vertex.setZ( Math.random() * 2000 - 1000 );

			geometry.getVertices().add( vertex );

		}

		materials = new ArrayList<>();

		for ( int i = 0; i < parameters.length; i ++ )
		{
			PointsMaterial material = new PointsMaterial().setSize( parameters[i][3] );
			materials.add(material);

			Points particles = new Points( geometry, material );

			particles.getRotation().setX( Math.random() * 6 );
			particles.getRotation().setY( Math.random() * 6 );
			particles.getRotation().setZ( Math.random() * 6 );

			scene.add( particles );
		}
	}
	
	@Override
	public void onUpdate(RenderingContext context)
	{
		double time = context.getDeltaTime() * 0.00005;

		camera.getPosition().addX( ( mouseX - camera.getPosition().getX() ) * 0.05 );
		camera.getPosition().addY( ( - mouseY - camera.getPosition().getY() ) * 0.05 );

		camera.lookAt( scene.getPosition() );

		for ( int i = 0; i < scene.getChildren().size(); i ++ ) 
		{
			Object3D object = scene.getChildren().get(i);

			if ( object instanceof Points)
			{
				object.getRotation().setY( time * ( i < 4 ? i + 1 : - ( i + 1 ) ) );
			}
		}

		for( int i = 0; i < materials.size(); i ++ )
		{
			PointsMaterial material = materials.get(i);

			double[] color = parameters[i];
			double h = ( 360 * ( color[0] + time ) % 360 ) / 360.;
			material.getColor().setHSL(  h, color[1], color[2]  );
		}
		
		context.getRenderer().render(scene, camera);
	}

	@Override
	public void onTouchMove(int screenX, int screenY, int pointer) {
		mouseX = screenX - width / 2;
		mouseY = screenY - height / 2;
	}

	@Override
	public String getName() {
		return "Random particles";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public String getAuthor() {
		return "<a href=\"http://threejs.org\">threejs</a>";
	}
}
