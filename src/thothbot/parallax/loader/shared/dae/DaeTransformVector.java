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
 * Squirrel. If not, see http://www.gnu.org/licenses/.
 */

package thothbot.parallax.loader.shared.dae;

import thothbot.parallax.core.shared.core.Vector3f;

import com.google.gwt.xml.client.Node;

public class DaeTransformVector extends DaeTransform 
{
	public DaeTransformVector(Node node) {
		super(node);
	}

	public Vector3f getObject() 
	{
		float[] data = getData();
		return new Vector3f( data[ 0 ], data[ 1 ], data[ 2 ] );
	}
}