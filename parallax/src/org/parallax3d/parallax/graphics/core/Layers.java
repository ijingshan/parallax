/*
 * Copyright 2016 Alex Usachev, thothbot@gmail.com
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

package org.parallax3d.parallax.graphics.core;

import org.parallax3d.parallax.system.ThreejsObject;

/**
 * @author mrdoob / http://mrdoob.com/
 */
@ThreejsObject("THREE.Layers")
public class Layers {

    int mask = 1;

    public int getMask() {
        return mask;
    }

    public Layers set( int channel ) {

        this.mask = 1 << channel;

        return this;
    }

    public Layers enable( int channel ) {

        this.mask |= 1 << channel;

        return this;
    }

    public Layers toggle( int channel ) {

        this.mask ^= 1 << channel;

        return this;
    }

    public Layers disable( int channel ) {

        this.mask &= ~ ( 1 << channel );

        return this;

    }

    public boolean test( Layers layers ) {

        return ( this.mask & layers.getMask() ) != 0;

    }
}