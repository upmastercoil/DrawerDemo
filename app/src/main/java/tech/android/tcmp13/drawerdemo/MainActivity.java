package tech.android.tcmp13.drawerdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String[] strs = {"Shuki", "Muki", "Duki", "Pinuki"};

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);

        final ListView drawerListView = (ListView) findViewById(R.id.drawerListView);
        drawerListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strs));
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar snackbar = Snackbar.make(drawerLayout, //the view that the snack will appear at its bottom
                        strs[i], //The snackbar's message
                        Snackbar.LENGTH_LONG);//The showing duration
                snackbar.setAction("Undo",//The action button title
                        new View.OnClickListener() { //The action button click listener
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "THE BOYS ARE BACK", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        });

        //Instead of using the default open/close drawer listener, use an implemented one that will include
        //action bar functionality
        drawerToggle = new ActionBarDrawerToggle(this, // the drawer's containing activity
                drawerLayout, //The drawer layout itself
                R.string.open_sesami,
                R.string.close_sesami);
        drawerLayout.addDrawerListener(drawerToggle);

        //show the home button
        //We use the support library activity therefore we must call the support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    //Called when all creation phases are over (onCreate and onRestoreInstanceState).
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        //Must call the super method otherwise an exception will be thrown
        super.onPostCreate(savedInstanceState);
        //Check if the drawer is open or not and update the ui appropriately
        drawerToggle.syncState();
    }

    //This method is called right after the right resources have been loaded to
    // this device config (screen orientation, locale)
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //must override to handle "hamburger" clicks
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
