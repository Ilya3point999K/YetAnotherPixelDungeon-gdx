package com.consideredhamster.yetanotherpixeldungeon.input;

public enum GameAction {
	BACK( null ),

	HERO_INFO("Hero Info"),
	CATALOGUS("Catalogus"),
	JOURNAL("Journal"),

	REST("Wait"),
	SEARCH("Search / Look at"),
	RESUME("Resume motion"),

	BACKPACK("Backpack"),
	QUICKSLOT0("Offhand"),
	QUICKSLOT1("Quickslot 1"),
	QUICKSLOT2("Quickslot 2"),
	QUICKSLOT3("Quickslot 3"),


	TAG_ATTACK("Attack"),
	TAG_DANGER("Visible Enemies"),

	ZOOM_IN("Zoom In"),
	ZOOM_OUT("Zoom Out"),
	ZOOM_DEFAULT("Default Zoom"),

	MOVE_UP("Move North"), MOVE_DOWN("Move South"), MOVE_LEFT("Move West"), MOVE_RIGHT("Move East"),
	MOVE_TOP_LEFT("Move NW"), MOVE_TOP_RIGHT("Move NE"), MOVE_BOTTOM_LEFT("Move SW"), MOVE_BOTTOM_RIGHT("Move SE"),
	OPERATE("Current Cell"),

	UNKNOWN(null);

	private final String description;

	GameAction(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
