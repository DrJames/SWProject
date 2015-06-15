package com.swproject.fi.swproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private ImageViewAdapter adapter;
    private List<Device> deviceList;
    private int deviceIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        //deviceList = Collections.synchronizedList(new ArrayList<Device>());
        deviceList = new ArrayList<Device>();
        adapter = new ImageViewAdapter(getApplicationContext(), R.layout.grid_item, deviceList);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(deviceClickListener);
        gridView.invalidateViews();

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnRemove = (Button) findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        //this.runOnUiThread();

        new Thread(new Runnable() {
            public void run() {
                while (true)
                {
                    // create random variable
                    Random r = new Random();

                    // decide the action is add new device or delete old one
                    // random = 0 => delete a random old device
                    // random = 1 => create new device
                    // if there are less than 10 devices then chances of adding new device are higher than removing
                    int action = 0;//
                    if (deviceList.size() <= 3)
                        action = 1;
                    else if (deviceList.size() <= 10)
                    {
                        action = r.nextInt(10);
                        if (action <= 7)
                            action = 1;
                        else
                            action = 0;
                    }
                    else
                        action = r.nextInt(1);

                    if (action == 1) // create new device
                    {
                        int deviceSign = 0;
                        String device_name = "";
                        switch(r.nextInt(4))
                        {
                            case 0:
                                deviceSign = R.drawable.desktop;
                                device_name = "Desktop ";
                                break;
                            case 1:
                                deviceSign = R.drawable.laptop;
                                device_name = "Laptop ";
                                break;
                            case 2:
                                deviceSign = R.drawable.phone;
                                device_name = "Phone ";
                                break;
                            case 3:
                                deviceSign = R.drawable.printer;
                                device_name = "Printer ";
                                break;
                            default:
                                deviceSign = R.drawable.desktop;
                                device_name = "Desktop ";
                                break;
                        }
                        //int index = deviceList.size() + 1;
                        deviceIndex++;
                        deviceList.add(new Device(deviceSign, device_name + deviceIndex,
                                "192.168.0." + deviceIndex, "C8:F7:33:06:64:2C"));
                    }
                    else // delete a random old device
                    {
                        if (deviceList.size() != 0)
                        {
                            int randomIndex = r.nextInt(deviceList.size() - 1);
                            deviceList.remove(randomIndex);
                        }
                    }


                    // notify change
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

                    // wait 5 seconds
                    try {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e) {

                    }
                }
            }
        }).start();

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
		deviceIndex++;
        deviceList.add(new Device(R.drawable.desktop, "Device " + deviceIndex,
                "192.168.0." + deviceIndex, "C8:F7:33:06:64:2C"));
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
