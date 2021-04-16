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
package com.consideredhamster.yetanotherpixeldungeon.visuals.windows;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.consideredhamster.yetanotherpixeldungeon.Dungeon;
import com.consideredhamster.yetanotherpixeldungeon.misc.utils.Utils;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.consideredhamster.yetanotherpixeldungeon.visuals.Assets;
import com.consideredhamster.yetanotherpixeldungeon.YetAnotherPixelDungeon;
import com.consideredhamster.yetanotherpixeldungeon.scenes.PixelScene;
import com.consideredhamster.yetanotherpixeldungeon.visuals.ui.CheckBox;
import com.consideredhamster.yetanotherpixeldungeon.visuals.ui.RedButton;
import com.consideredhamster.yetanotherpixeldungeon.visuals.ui.Window;
import com.watabou.noosa.ui.Button;

public class WndSettings extends Window {
	
	private static final String TXT_ZOOM_IN			= "+";
	private static final String TXT_ZOOM_OUT		= "-";
	private static final String TXT_ZOOM_DEFAULT	= "Default Zoom";

	private static final String TXT_SCALE_UP		= "Scale up UI";
	private static final String TXT_IMMERSIVE		= "Immersive mode";

	private static final String TXT_MUSIC	        = "Music";
	
	private static final String TXT_SOUND	        = "Sound FX";

	private static final String TXT_BUTTONS         = "Waterskins/lantern: %s";

    private static final String[] TXT_BUTTONS_VAR  = {
            "Right",
            "Left",
    };

	private static final String TXT_BRIGHTNESS	    = "Brightness";

	private static final String TXT_LOADING_TIPS  = "Loading tips: %s";

	private static final String[] TXT_TIPS_DELAY  = {
            "Disabled",
            "Normal delay",
            "Doubled delay",
            "Until tapped",
    };

    private static final String TXT_SEARCH_BTN  = "Search btn: %s";

    private static final String[] TXT_SEARCH_VAR  = {
            "Default behv.",
            "Reversed behv.",
    };

	private static final String TXT_SWITCH_PORT 	= "Switch to portrait";
    private static final String TXT_SWITCH_LAND 	= "Switch to landscape";
    private static final String TXT_BINDINGS	= "Key bindings";
    private static final String TXT_SWITCH_FULL = "Switch to fullscreen";
    private static final String TXT_SWITCH_WIN = "Switch to windowed";

	private static final int WIDTH		= 112;
    private static final int BTN_HEIGHT	= 20;
	private static final int GAP 		= 2;
	
	private RedButton btnZoomOut;
	private RedButton btnZoomIn;
	
	public WndSettings( final boolean inGame ) {
        super();


        if (inGame) {
            int w = BTN_HEIGHT;

            btnZoomOut = new RedButton( TXT_ZOOM_OUT ) {
                @Override
                protected void onClick() {
                    zoom( Camera.main.zoom - 1 );
                }
            };
            add( btnZoomOut.setRect( 0, 0, w, BTN_HEIGHT) );

            btnZoomIn = new RedButton( TXT_ZOOM_IN ) {
                @Override
                protected void onClick() {
                    zoom( Camera.main.zoom + 1 );
                }
            };
            add( btnZoomIn.setRect( WIDTH - w, 0, w, BTN_HEIGHT) );

            add( new RedButton( TXT_ZOOM_DEFAULT ) {
                @Override
                protected void onClick() {
                    zoom( PixelScene.defaultZoom );
                }
            }.setRect( btnZoomOut.right(), 0, WIDTH - btnZoomIn.width() - btnZoomOut.width(), BTN_HEIGHT ) );

            updateEnabled();

        } else {

            CheckBox btnScaleUp = new CheckBox( TXT_SCALE_UP ) {
                @Override
                protected void onClick() {
                    super.onClick();
                    YetAnotherPixelDungeon.scaleUp( checked() );
                }
            };
            btnScaleUp.setRect( 0, 0, WIDTH, BTN_HEIGHT );
            btnScaleUp.checked( YetAnotherPixelDungeon.scaleUp() );
            add( btnScaleUp );

        }

        CheckBox btnMusic = new CheckBox( TXT_MUSIC ) {
            @Override
            protected void onClick() {
                super.onClick();
                YetAnotherPixelDungeon.music( checked() );
            }
        };
        btnMusic.setRect( 0, BTN_HEIGHT + GAP, WIDTH, BTN_HEIGHT );
        btnMusic.checked( YetAnotherPixelDungeon.music() );
        add( btnMusic );

        CheckBox btnSound = new CheckBox( TXT_SOUND ) {
            @Override
            protected void onClick() {
                super.onClick();
                YetAnotherPixelDungeon.soundFx( checked() );
                Sample.INSTANCE.play( Assets.SND_CLICK );
            }
        };
        btnSound.setRect( 0, btnMusic.bottom() + GAP, WIDTH, BTN_HEIGHT );
        btnSound.checked( YetAnotherPixelDungeon.soundFx() );
        add( btnSound );

        Application.ApplicationType type = Gdx.app.getType();

        Button lastBtn = btnSound;
        if (!inGame) {
		/*	if (type == Application.ApplicationType.Android || type == Application.ApplicationType.iOS) {
				RedButton btnOrientation = new RedButton(orientationText()) {
					@Override
					protected void onClick() {
						PixelDungeon.landscape(!PixelDungeon.landscape());
					}
				};
				btnOrientation.setRect(0, btnSound.bottom() + GAP, WIDTH, BTN_HEIGHT);
				add(btnOrientation);
				lastBtn = btnOrientation;
			} else*/ if (type == Application.ApplicationType.Desktop) {

                RedButton btnResolution = new RedButton(resolutionText()) {
                    @Override
                    protected void onClick() {
                        YetAnotherPixelDungeon.fullscreen(!YetAnotherPixelDungeon.fullscreen());
                    }
                };
                btnResolution.enable( YetAnotherPixelDungeon.instance.getPlatformSupport().isFullscreenEnabled() );
                btnResolution.setRect(0, btnSound.bottom() + GAP, WIDTH, BTN_HEIGHT);
                add(btnResolution);

                lastBtn = btnResolution;
            }
        } else {

            CheckBox btnBrightness = new CheckBox( TXT_BRIGHTNESS ) {
                @Override
                protected void onClick() {
                    super.onClick();
                    YetAnotherPixelDungeon.brightness( checked() );
                }
            };
            btnBrightness.setRect( 0, btnSound.bottom() + GAP, WIDTH, BTN_HEIGHT );
            btnBrightness.checked(YetAnotherPixelDungeon.brightness());
            add( btnBrightness );

            lastBtn = btnBrightness;

        }

        if (type == Application.ApplicationType.Desktop) {

            RedButton btnKeymap = new RedButton(TXT_BINDINGS) {
                @Override
                protected void onClick() {
                    parent.add(new WndKeymap());
                }
            };
            btnKeymap.setRect(0, lastBtn.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnKeymap);

            lastBtn = btnKeymap;
        }

        RedButton btnTipsDelay = new RedButton( loadingTipsText( YetAnotherPixelDungeon.loadingTips() ) ) {
            @Override
            protected void onClick() {

                int val = YetAnotherPixelDungeon.loadingTips();

                val = val < 3 ? val + 1 : 0;
                YetAnotherPixelDungeon.loadingTips(val);

                text.text( loadingTipsText( val ) );
                text.measure();
                layout();
            }
        };
        btnTipsDelay.setRect(0, lastBtn.bottom() + GAP, WIDTH, BTN_HEIGHT);
        add(btnTipsDelay);
        lastBtn = btnTipsDelay;

        resize(WIDTH, (int) lastBtn.bottom());
	}
	
	private void zoom( float value ) {

		Camera.main.zoom( value );
		YetAnotherPixelDungeon.zoom((int) (value - PixelScene.defaultZoom));

		updateEnabled();
	}
	
	private void updateEnabled() {
		float zoom = Camera.main.zoom;
		btnZoomIn.enable(zoom < PixelScene.maxZoom);
		btnZoomOut.enable(zoom > PixelScene.minZoom);
	}
	
	private String orientationText() {
		return YetAnotherPixelDungeon.landscape() ? TXT_SWITCH_PORT : TXT_SWITCH_LAND;
	}

    private String resolutionText() {
        return Gdx.graphics.isFullscreen() ? TXT_SWITCH_WIN : TXT_SWITCH_FULL;
    }

    private String searchButtonsText( boolean val ) {
        return Utils.format( TXT_SEARCH_BTN, TXT_SEARCH_VAR[ val ? 1 : 0 ] );
    }

    private String loadingTipsText( int val ) {
        return Utils.format( TXT_LOADING_TIPS, TXT_TIPS_DELAY[ val ] );
    }

    private String buttonsText( boolean val ) {
        return Utils.format( TXT_BUTTONS, TXT_BUTTONS_VAR[ val ? 1 : 0 ] );
    }
}
