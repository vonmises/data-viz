package ml.dev2dev.droiddata;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DisasterActivity extends AppCompatActivity {
    private Disaster event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        event = Disaster.valueOf(intent.getStringExtra(DataSetActivity.DISASTER));

        String event_name = event.name();
        setTitle(event_name.substring(0, 1).toUpperCase() +
                 event_name.substring(1).toLowerCase() + " Disasters");

        setUpChart();
    }

    public void setUpChart(){
        InputStream input_stream = getResources().openRawResource(R.raw.natural_disaster_event_summary);
        List<String> data = getData(input_stream);
        List<String> labels = getHeaders(data.get(0).split(","));
        //bar set
        BarDataSet bar_set = new BarDataSet(
                getEntries(data.get(event.getEventNumber()).split(",")),"");
        bar_set.setColors(new int[]{
                Color.parseColor("red"),
                Color.parseColor("green"),
                Color.parseColor("blue"),
                Color.parseColor("cyan")
        });
        bar_set.setBarSpacePercent(50f);

        BarData bar_data = new BarData(labels, bar_set);

        //bar chart
        BarChart bar_chart = new BarChart(this);
        bar_chart.setData(bar_data);
        bar_chart.setDescription("");
        bar_chart.animateXY(1000, 2000);

        Legend legend = bar_chart.getLegend();
        legend.setCustom(new int[]{
                        Color.parseColor("#00ffffff")}, //transparent
                new String[]{event.name()});
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        XAxis x_axis = bar_chart.getXAxis();
        x_axis.setPosition(XAxis.XAxisPosition.BOTTOM);

        bar_chart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART); //left
        bar_chart.getAxisRight().setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART); //right

        setContentView(bar_chart);
    }

    public List<String> getData(InputStream input_stream){
        List<String> values = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(input_stream));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                values.add(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return values;
    }

    public List<String> getHeaders(String[] array){
        List<String> headers = new ArrayList<>();

        for (int i = 1; i < array.length; i++){
            headers.add(array[i]);
        }

        return headers;
    }

    public ArrayList<BarEntry> getEntries(String[] values){
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 1; i < values.length; i++) {
            entries.add(new BarEntry(Float.parseFloat(values[i]), i - 1));
        }

        return entries;
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
