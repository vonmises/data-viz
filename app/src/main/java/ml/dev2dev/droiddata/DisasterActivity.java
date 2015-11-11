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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DisasterActivity extends AppCompatActivity {
    private Disaster event;
    private InputStream input_stream;
    private BarDataSet bar_set;
    private BarChart bar_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        event = intent.getStringExtra(DataSetActivity.DISASTER) == null ? null
                : Disaster.valueOf(intent.getStringExtra(DataSetActivity.DISASTER));

        if (event == null){
            setTitle("Gender Inequality Index");
            input_stream = getResources().openRawResource(R.raw.kenya_gender_inequality_index_per_county);
        }
        else {
            String event_name = event.name();
            setTitle(event_name.substring(0, 1).toUpperCase() +
                     event_name.substring(1).toLowerCase() + " Disasters");

            input_stream = getResources().openRawResource(R.raw.natural_disaster_event_summary);
        }
        setUpChart();
        setContentView(bar_chart);
    }

    public void setUpChart(){
        List<String> data = FileUtils.getCSVData(input_stream);
        List<String> labels = event == null ? getGenderXLabels(data)
                                            : getDisasterXLabels(data.get(0).split(","));
        createBarDataSet(data);

        BarData bar_data = new BarData(labels, bar_set);
        createBarChart(bar_data);

        setXAxes();

        if (event == null){
            createLegend(new String[]{getString(R.string.gender_inequality_title)});
        }
        else {
            createLegend(new String[]{event.name()});
            bar_chart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
            bar_chart.getAxisRight().setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        }
    }

    private void createBarDataSet(List<String> data){
        if (event != null) {
            bar_set = new BarDataSet(
                    getDisasterYValues(data.get(event.getEventNumber()).split(",")), "");
        }
        else{
            bar_set = new BarDataSet(
                    getGenderYValues(data), "");
        }
        bar_set.setColors(ColorTemplate.JOYFUL_COLORS);
        bar_set.setBarSpacePercent(50f);
    }

    private void createBarChart(BarData barData){
        bar_chart = new BarChart(this);
        bar_chart.setData(barData);
        bar_chart.setDescription("");
        bar_chart.animateXY(1000, 2000);
    }

    private void createLegend(String[] labels){
        Legend legend = bar_chart.getLegend();
        legend.setCustom(new int[]{ /*transparent*/
                Color.parseColor("#00000000")}, labels);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
    }

    private void setXAxes(){
        XAxis x_axis = bar_chart.getXAxis();
        x_axis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    private List<String> getGenderXLabels(List<String> values){
        List<String> labels = new ArrayList<>();

        for (int i = 1; i < values.size(); i++){
            labels.add(values.get(i).split(",")[1]);
        }

        return labels;
    }

    private ArrayList<BarEntry> getGenderYValues(List<String> values){
        ArrayList<BarEntry> entries = new ArrayList<>();
        float x;

        for (int i = 1; i < values.size(); i++){
            x = Float.parseFloat(values.get(i).split(",")[2]);
            entries.add(new BarEntry(x, i - 1));
        }

        return entries;
    }

    public List<String> getDisasterXLabels(String[] array){
        List<String> labels = new ArrayList<>();

        for (int i = 1; i < array.length; i++){
            labels.add(array[i]);
        }

        return labels;
    }

    public ArrayList<BarEntry> getDisasterYValues(String[] values){
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
