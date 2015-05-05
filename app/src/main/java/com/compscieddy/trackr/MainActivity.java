package com.compscieddy.trackr;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.compscieddy.trackr.db.Category;
import com.compscieddy.trackr.db.Item;


public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActiveAndroid.initialize(this);

    setContentView(R.layout.activity_main);

    Category restaurants = new Category();
    restaurants.name = "Restaurants";
    restaurants.save();

    Item item = new Item();
    item.category = restaurants;
    item.name = "Outback Steakhouse";
    item.save();

    item = new Item();
    item.category = restaurants;
    item.name = "Olive Garden";
    item.save();

//    ListView listView = (ListView) findViewById(R.id.list_view);

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
