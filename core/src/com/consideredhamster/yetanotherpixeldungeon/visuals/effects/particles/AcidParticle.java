/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Yet Another Pixel Dungeon
 * Copyright (C) 2015-2016 Considered Hamster
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.consideredhamster.yetanotherpixeldungeon.visuals.effects.particles;

import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.Emitter.Factory;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class AcidParticle extends PixelParticle.Shrinking {

	public static final Factory FACTORY = new Factory() {
		@Override
		public void emit( Emitter emitter, int index, float x, float y ) {
			((AcidParticle)emitter.recycle( AcidParticle.class )).reset( x, y );
		}
	};

    public static final Emitter.Factory BURST = new Factory() {
        @Override
        public void emit( Emitter emitter, int index, float x, float y ) {
            ((AcidParticle)emitter.recycle( AcidParticle.class )).resetBurst( x, y );
        }
    };

	public AcidParticle() {
		super();
		
		color( 0x00c500 );
		lifespan = 0.8f;
		
		acc.set( 0, +40 );
	}
	
	public void reset( float x, float y ) {
		revive();
		
		this.x = x;
		this.y = y;

        size = 3;
        speed.set( 0 );

        left = lifespan;
    }


    public void resetBurst( float x, float y ) {
        revive();

        this.x = x;
        this.y = y;

        size = 3;
        speed.polar( Random.Float( PointF.PI * 2 ), Random.Float( 16, 32 ) );

        left = lifespan;
    }
	
	@Override
	public void update() {
		super.update();
		float p = left / lifespan;
		am = p > 0.6f ? (1 - p) * 2.5f : 1;
        color( ColorMath.interpolate( 0x00c500, 0x006600, am ) );
	}
}