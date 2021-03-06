package com.example.wheelysimple.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static ArrayList<DummyItem> ITEMS = new ArrayList<DummyItem>();
	public static ArrayList<DummyItem> ITEMS_TEMP = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
	public static Map<String, DummyItem> ITEM_MAP_TEMP = new HashMap<String, DummyItem>();

	static {
		// Add 3 sample items.
		/*addItem(new DummyItem("1", "Item 1", "Content@1"));
		addItem(new DummyItem("2", "Item 2", "Content@2"));
		addItem(new DummyItem("3", "Item 3", "Content@3"));*/
	}

	public static void addItem(DummyItem item) {
		ITEMS_TEMP.add(item);
		ITEM_MAP_TEMP.put(item.id, item);
	}

	public static void clear() {
		ITEMS.clear();
		ITEM_MAP.clear();
		ITEMS_TEMP.clear();
		ITEM_MAP_TEMP.clear();
	}

	public static void update()
	{
		ITEMS.clear();
		ITEM_MAP.clear();
		ITEMS.addAll(ITEMS_TEMP);
		ITEM_MAP.putAll(ITEM_MAP_TEMP);
	}
	
	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public String title;
		public String text;

		public DummyItem(String id, String title, String text) {
			this.id = id;
			this.title = title;
			this.text = text;
		}
	}
}
