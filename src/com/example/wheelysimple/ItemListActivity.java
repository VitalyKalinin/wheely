package com.example.wheelysimple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.wheelysimple.dummy.DummyContent;
import com.example.wheelysimple.dummy.DummyContent.DummyItem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity implements
		ItemListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	
	private ProgressBar mProgress;
	
	//public ArrayList<DummyItem> items = new ArrayList<DummyItem>();
	//public Map<String, DummyItem> itemsMap = new HashMap<String, DummyItem>();
	String lastID=new String();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		mProgress=(ProgressBar) findViewById(R.id.progress);
		/*DummyItem a1=new DummyItem("1","+1","...");
		items.add(a1);
		itemsMap.put(a1.id, a1);
		DummyItem a2=new DummyItem("2","+2","...");
		items.add(a2);
		itemsMap.put(a2.id, a2);
		DummyItem a3=new DummyItem("3","+3","...");
		items.add(a3);
		itemsMap.put(a3.id, a3);*/
		
		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, fragment).commit();
			lastID=id;

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_refresh:
	      Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT)
	          .show();
	      
	      LongAndComplicatedTask longTask = new LongAndComplicatedTask(); // Создаем экземпляр
	      longTask.execute(); // запускаем */

	      
	      break;

	    default:
	      break;
	    }

	    return true;
	  } 


	public void updateData()
	{
	    Log.v("TAXI","UpdateData");
	    //ArrayList<DummyItem> dummyList=new ArrayList();
		try{			
					URL url = new URL("http://crazy-dev.wheely.com/");
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					urlConnection.setRequestMethod("GET");
					urlConnection.setDoInput(true);
					urlConnection.setDoOutput(false);
					urlConnection.connect();
				    InputStreamReader in = new InputStreamReader((InputStream) urlConnection.getContent());
				    String line="";
				    BufferedReader buff = new BufferedReader(in);
				    StringBuilder buffer = new StringBuilder();
				    while ((line = buff.readLine()) != null) {
				            buffer.append(line);
				    }				    
				    String data=buffer.toString();
				    Log.v("TAXI","data="+data);

				    JSONArray json = new JSONArray(data);
				    ArrayList<DummyItem> items=new ArrayList();
					Map<String, DummyItem> itemsMap = new HashMap<String, DummyItem>();

					DummyContent.clear();
					for(int i=0;i<json.length();i++)
				    {                        
				    	HashMap<String, String> map = new HashMap<String, String>();    
				    	JSONObject e = json.getJSONObject(i);

				    	DummyItem c=new DummyItem(e.getString("id"),e.getString("title"),e.getString("text"));
				    	DummyContent.addItem(c);
				    	items.add(c);       
						itemsMap.put(c.id, c);
				    }
				    
				    //DummyContent.ITEMS = items;
				    //DummyContent.ITEM_MAP = itemsMap;
				    
				} catch (Exception ex) {			
					ex.printStackTrace();
					Log.v("TAXI",ex.getClass().getName());
				}		
	}


	class LongAndComplicatedTask extends AsyncTask<Void, Void, String> {
	    
	    @Override
	    protected String doInBackground(Void... noargs) {
			updateData();
	        return "";
	    }

	    @Override
	    protected void onPreExecute()
	    {
	    	//mProgress.show();
			//mProgress.show(ItemListActivity.this, null, null, true);
	        mProgress.setVisibility(View.VISIBLE);
	    }

	    @Override
	    protected void onPostExecute(String result) 
	    {
	    	Log.v("TAXI","Items2:"+DummyContent.ITEMS.size());
	    	ItemListFragment t=(ItemListFragment)getSupportFragmentManager().findFragmentById(R.id.item_list);
	    	ArrayAdapter a=(ArrayAdapter) t.getListAdapter();
	    	a.notifyDataSetChanged();
	    	
	        mProgress.setVisibility(View.GONE);

	    }
	}

}
