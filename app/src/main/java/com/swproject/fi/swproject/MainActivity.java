package com.swproject.fi.swproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private ImageViewAdapter adapter;
    private List<Device> deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        deviceList = new ArrayList<>();
        adapter = new ImageViewAdapter(getApplicationContext(), R.layout.grid_item, deviceList);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(deviceClickListener);
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
        int index = deviceList.size() + 1;
        deviceList.add(new Device(getResources().getDrawable(R.drawable.desktop), "name " + index,
                "192.168.0." + index, "Network 1"));
        adapter.notifyDataSetChanged();
    }

    private void removeItem(){
        if (deviceList.size() != 0){
            int last = deviceList.size() - 1;
            deviceList.remove(last);
            adapter.notifyDataSetChanged();
        }
    }

    private AdapterView.OnItemClickListener deviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent activity = new Intent(getApplicationContext(), SomeActivity.class);
            startActivity(activity);
        }
    };

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
