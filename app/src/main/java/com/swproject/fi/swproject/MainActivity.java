package com.swproject.fi.swproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private List<Integer> items;
    private List<String> itemDesc;
    private ImageViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        items = new ArrayList<>();
        itemDesc = new ArrayList<>();
        adapter = new ImageViewAdapter(getApplicationContext(), items, itemDesc);

        gridView.setAdapter(adapter);
        gridView.invalidateViews();

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnRemove = (Button) findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
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

    private void addItem(){
        int item = R.drawable.desktop;
        int index = items.size() + 1;
        items.add(item);
        String description = "Network 1" + "\n" + "Device " + index;
        itemDesc.add(description);
        adapter.notifyDataSetChanged();
    }

    private void removeItem(){
        if (items.size() != 0){
            int last = items.size() - 1;
            items.remove(last);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                addItem();
                break;
            case R.id.btnRemove:
                removeItem();
                break;
        }
    }
}
