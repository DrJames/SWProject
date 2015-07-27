package com.swproject.fi.swproject;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidplot.xy.BarFormatter;
import com.androidplot.xy.BarRenderer;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Random;


public class Data extends ActionBarActivity {

    private XYPlot xyPlot;
    private XYPlot aprLevelsPlot = null;

    private class APRIndexFormat extends Format {
        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            Number num = (Number) obj;

            int roundNum = (int) (num.floatValue() + 1);
            switch(roundNum) {
                case 1:
                    toAppendTo.append("Data 1");
                    break;
                case 2:
                    toAppendTo.append("Data 2");
                    break;
                case 3:
                    toAppendTo.append("Data 3");
                    break;
                case 4:
                    toAppendTo.append("Data 4");
                    break;
                case 5:
                    toAppendTo.append("Data 5");
                    break;
                case 6:
                    toAppendTo.append("Data 6");
                    break;
                case 7:
                    toAppendTo.append("Data 7");
                    break;
                case 8:
                    toAppendTo.append("Data 8");
                    break;
                default:
                    toAppendTo.append("Unknown");
            }
            return toAppendTo;
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return null;  // We don't use this so just return null for now.
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        //xyPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        Number[] series1 = dataGenerator();
        Number[] series2 = dataGenerator();
        Number[] series3 = dataGenerator();

        XYSeries xySeries1 = new SimpleXYSeries(Arrays.asList(series1), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Traffic Usage");
        XYSeries xySeries2 = new SimpleXYSeries(Arrays.asList(series2), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Some data");
        XYSeries xySeries3 = new SimpleXYSeries(Arrays.asList(series3), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "App 3");

        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);
        //xyPlot.addSeries(xySeries1, series1Format);

        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        //xyPlot.addSeries(xySeries2, series2Format);

        LineAndPointFormatter series3Format = new LineAndPointFormatter();
        series3Format.setPointLabelFormatter(new PointLabelFormatter());
        series3Format.configure(getApplication(),
                R.xml.line_point_formatter_with_plf3);
        //xyPlot.addSeries(xySeries3, series3Format);

        LineAndPointFormatter ser1Format = new LineAndPointFormatter(Color.RED, Color.BLUE, Color.GREEN, null);
        LineAndPointFormatter ser2Format = new LineAndPointFormatter(Color.WHITE, Color.BLACK, Color.YELLOW, null);
        LineAndPointFormatter ser3Format = new LineAndPointFormatter(Color.GRAY, Color.RED, Color.CYAN, null);

        //xyPlot.addSeries(xySeries1, ser1Format);
        //xyPlot.addSeries(xySeries2, ser2Format);
        //xyPlot.addSeries(xySeries3, ser3Format);


        aprLevelsPlot = (XYPlot) findViewById(R.id.aprLevelsPlot);
        aprLevelsPlot.setDomainValueFormat(new APRIndexFormat());
        aprLevelsPlot.addSeries(xySeries1,
                new BarFormatter(Color.argb(100, 0, 200, 0), Color.rgb(0, 80, 0)));
        aprLevelsPlot.addSeries(xySeries2,
                new BarFormatter(Color.argb(200, 0, 100, 0), Color.rgb(80, 0, 80)));
        aprLevelsPlot.setDomainStepValue(3);
        aprLevelsPlot.setTicksPerRangeLabel(3);

        aprLevelsPlot.setDomainLabel("Axis");
        aprLevelsPlot.getDomainLabelWidget().pack();
        aprLevelsPlot.setRangeLabel("Angle");
        aprLevelsPlot.getRangeLabelWidget().onPostInit();
        aprLevelsPlot.setGridPadding(15, 0, 15, 0);

        BarRenderer barRenderer = (BarRenderer) aprLevelsPlot.getRenderer(BarRenderer.class);
        if(barRenderer != null) {
            // make our bars a little thicker than the default so they can be seen better:
            barRenderer.setBarWidth(25);
        }

    }

    private Number[] dataGenerator(){
        Random random = new Random();
        Number[] numbers = new Number[10];

        for (int i = 0; i <8; i++){
            numbers[i] = random.nextInt(50);
        }
        return numbers;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data, menu);
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
