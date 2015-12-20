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

package org.parallax3d.parallax.graphics.extras.geometries;

import org.parallax3d.parallax.graphics.core.Geometry;
import org.parallax3d.parallax.math.Vector3;

public class AsteriskGeometry extends Geometry
{

	public AsteriskGeometry(float innerRadius, float outerRadius )
	{
		float sd = innerRadius;
		float ed = outerRadius;

		float sd2 = 0.707f * sd;
		float ed2 = 0.707f * ed;

		float[][] rays = { { sd, 0, 0 }, { ed, 0, 0 }, { -sd, 0, 0 }, { -ed, 0, 0 },
					 { 0, sd, 0 }, { 0, ed, 0 }, { 0, -sd, 0 }, { 0, -ed, 0 },
					 { 0, 0, sd }, { 0, 0, ed }, { 0, 0, -sd }, { 0, 0, -ed },
					 { sd2, sd2, 0 }, { ed2, ed2, 0 }, { -sd2, -sd2, 0 }, { -ed2, -ed2, 0 },
					 { sd2, -sd2, 0 }, { ed2, -ed2, 0 }, { -sd2, sd2, 0 }, { -ed2, ed2, 0 },
					 { sd2, 0, sd2 }, { ed2, 0, ed2 }, { -sd2, 0, -sd2 }, { -ed2, 0, -ed2 },
					 { sd2, 0, -sd2 }, { ed2, 0, -ed2 }, { -sd2, 0, sd2 }, { -ed2, 0, ed2 },
					 { 0, sd2, sd2 }, { 0, ed2, ed2 }, { 0, -sd2, -sd2 }, { 0, -ed2, -ed2 },
					 { 0, sd2, -sd2 }, { 0, ed2, -ed2 }, { 0, -sd2, sd2 }, { 0, -ed2, ed2 }
		};

		for ( int i = 0, il = rays.length; i < il; i ++ ) 
		{
			float x = rays[ i ][ 0 ];
			float y = rays[ i ][ 1 ];
			float z = rays[ i ][ 2 ];

			this.getVertices().add( new Vector3( x, y, z ) );
		}
	}
}
