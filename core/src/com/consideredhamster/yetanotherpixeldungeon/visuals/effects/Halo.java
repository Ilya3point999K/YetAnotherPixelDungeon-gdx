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
package com.consideredhamster.yetanotherpixeldungeon.visuals.effects;


import com.badlogic.gdx.graphics.Pixmap;
import com.watabou.gdx.GdxTexture;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;

public class Halo extends Image {
	
	private static final Object CACHE_KEY = Halo.class;
	
	protected static final int RADIUS	= 64;
	
	protected float radius = RADIUS;
	protected float brightness = 1;

	public Halo() {
		super();
		
		if (!TextureCache.contains( CACHE_KEY )) {
			Pixmap pixmap = new Pixmap(RADIUS * 2, RADIUS * 2, Pixmap.Format.RGBA8888);
			//pixmap.drawCircle( RADIUS, RADIUS, (int) (RADIUS * 0.75f));
			pixmap.setColor( 0xFFFFFFAA );
			pixmap.fillCircle( RADIUS, RADIUS, RADIUS );
			pixmap.setColor( 0xFFFFFFFF );
			pixmap.fillCircle(RADIUS, RADIUS, (int) (RADIUS * 0.75f));
			GdxTexture bmp = new GdxTexture(pixmap);
			TextureCache.add( CACHE_KEY, new SmartTexture( bmp ) );
		}
		
		texture( CACHE_KEY );
		
		origin.set( RADIUS );
	}
	
	public Halo( float radius, int color, float brightness ) {
		
		this();
		
		hardlight( color );
		alpha( this.brightness = brightness );
		radius( radius );
	}
	
	public Halo point( float x, float y ) {
		this.x = x - RADIUS;
		this.y = y - RADIUS;
		return this;
	}
	
	public void radius( float value ) {
		scale.set(  (this.radius = value) / RADIUS );
	}
}
