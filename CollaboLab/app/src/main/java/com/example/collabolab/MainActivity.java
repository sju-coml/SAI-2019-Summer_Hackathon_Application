package com.example.collabolab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.collabolab.com.example.collabolab.service.Service;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Service service = null;

    private WebView webView = null;

    private LineChart chart1 = null;
    private BarChart chart2 = null;

    private final int count = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new Service();

        reflash();

        chart2();

    }

    private void reflash(){
        loadPopulation();
        loadTablePCnt();
        loadTableStatus();
        loadWeek();
    }

    private void loadPopulation(){
        TextView textView = (TextView) findViewById(R.id.population);

        int population = service.population();

        String stmp = String.valueOf(population) + "/76";

        textView.setText(stmp);
    }

    private void loadTime() {

        // service

        chart2 = findViewById(R.id.chart2);
        chart2.setTouchEnabled(false);
        chart2.setPinchZoom(false);

        chart2.highlightValue(null);

        chart2.getXAxis().setDrawLabels(true);
        chart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value) {
                return (int)value + "h";
            }

        });
        chart2.getXAxis().setDrawGridLines(false);

        chart2.getAxisRight().setDrawGridLines (false);
        chart2.getAxisRight().setDrawLabels(false);

        chart2.getAxisLeft().setDrawGridLines(false);
        chart2.getAxisLeft().setDrawLabels(false);

        chart2.getDescription().setEnabled(false);
        chart2.getLegend().setEnabled(false);

        chart2.getXAxis().setDrawAxisLine(false);
        chart2.getAxisLeft().setDrawAxisLine(false);
        chart2.getAxisRight().setDrawAxisLine(false);


        ArrayList<BarEntry> values = new ArrayList<>();
        /*
        for(int i=0;i<24;i++){
            values.add(new BarEntry());
        }
        */
        values.add(new BarEntry(7f, 100));
        values.add(new BarEntry(7.5f, 70));
        values.add(new BarEntry(8f, 120));
        values.add(new BarEntry(8.5f, 55));
        values.add(new BarEntry(9f, 50));
        values.add(new BarEntry(9.5f, 100));
        values.add(new BarEntry(10f, 70));
        values.add(new BarEntry(10.5f, 100));
        values.add(new BarEntry(11f, 70));
        values.add(new BarEntry(11.5f, 75));
        values.add(new BarEntry(12f, 80));
        values.add(new BarEntry(12.5f, 25));
        values.add(new BarEntry(13f, 90));
        values.add(new BarEntry(13.5f, 65));
        values.add(new BarEntry(14f, 52));
        values.add(new BarEntry(14.5f, 15));
        values.add(new BarEntry(15f, 95));
        values.add(new BarEntry(15.5f, 45));
        values.add(new BarEntry(16f, 80));
        values.add(new BarEntry(16.5f, 50));
        values.add(new BarEntry(17f, 100));
        values.add(new BarEntry(17.5f, 70));
        values.add(new BarEntry(18f, 120));
        values.add(new BarEntry(18.5f, 55));
        values.add(new BarEntry(19f, 55));

        BarDataSet set1 = null;
        if (chart2.getData() != null &&
                chart2.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart2.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart2.getData().notifyDataChanged();
            chart2.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "data");
            set1.setDrawIcons(false);
            set1.setColor(Color.WHITE);
            set1.setValueTextSize(0);
            set1.setFormSize(15.f);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setBarWidth(0.3f);
            chart2.setData(data);
        }
    }

    private void loadTablePCnt(){

        int iarr[] = service.tablePCnt();

        TextView tarr[] = {
                (TextView) findViewById(R.id.t1),
                (TextView) findViewById(R.id.t2),
                (TextView) findViewById(R.id.t5),
                (TextView) findViewById(R.id.t6)
        };

        for(int i=0;i<4;i++) {
            tarr[i].setText(String.valueOf(iarr[i]));
        }
    }

    private void loadTableStatus(){

        int iarr[] = service.tableStatus();

        TextView tarr[] = {
                (TextView) findViewById(R.id.t1),
                (TextView) findViewById(R.id.t2),
                (TextView) findViewById(R.id.t5),
                (TextView) findViewById(R.id.t6)
        };

        for(int i=0;i<4;i++){
            if(iarr[i] == 0) {
                tarr[i].setBackgroundResource(R.drawable.table);
            }else if(iarr[i] == 1) {
                tarr[i].setBackgroundResource(R.drawable.table_u);
            }else if(iarr[i] == 2) {
                tarr[i].setBackgroundResource(R.drawable.table_w);
            }
        }
    }

    private void chart2(){
        chart2 = findViewById(R.id.chart2);
        chart2.setTouchEnabled(false);
        chart2.setPinchZoom(false);


        chart2.highlightValue(null);

        chart2.getXAxis().setDrawLabels(true);
        chart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value) {
                return (int)value + "h";
            }

        });
        chart2.getXAxis().setDrawGridLines(false);

        chart2.getAxisRight().setDrawGridLines (false);
        chart2.getAxisRight().setDrawLabels(false);

        chart2.getAxisLeft().setDrawGridLines(false);
        chart2.getAxisLeft().setDrawLabels(false);

        chart2.getDescription().setEnabled(false);
        chart2.getLegend().setEnabled(false);

        chart2.getXAxis().setDrawAxisLine(false);
        chart2.getAxisLeft().setDrawAxisLine(false);
        chart2.getAxisRight().setDrawAxisLine(false);


        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(7f, 4));
        values.add(new BarEntry(7.5f, 4));
        values.add(new BarEntry(8f, 6));
        values.add(new BarEntry(8.5f, 12));
        values.add(new BarEntry(9f, 22));
        values.add(new BarEntry(9.5f, 34));
        values.add(new BarEntry(10f, 19));
        values.add(new BarEntry(10.5f, 20));
        values.add(new BarEntry(11f, 40));
        values.add(new BarEntry(11.5f, 65));
        values.add(new BarEntry(12f, 70));
        values.add(new BarEntry(12.5f, 74));
        values.add(new BarEntry(13f, 74));
        values.add(new BarEntry(13.5f, 70));
        values.add(new BarEntry(14f, 52));
        values.add(new BarEntry(14.5f, 22));
        values.add(new BarEntry(15f, 66));
        values.add(new BarEntry(15.5f, 63));
        values.add(new BarEntry(16f, 55));
        values.add(new BarEntry(16.5f, 55));
        values.add(new BarEntry(17f, 34));
        values.add(new BarEntry(17.5f, 12));
        values.add(new BarEntry(18f, 4));
        values.add(new BarEntry(18.5f, 3));
        values.add(new BarEntry(19f, 3));

        BarDataSet set1 = null;
        if (chart2.getData() != null &&
                chart2.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart2.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart2.getData().notifyDataChanged();
            chart2.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "data");
            set1.setDrawIcons(false);
            set1.setColor(Color.WHITE);
            set1.setValueTextSize(0);
            set1.setFormSize(15.f);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setBarWidth(0.3f);
            chart2.setData(data);
        }
    }

    // 요일별
    private void loadWeek(){

        int iarr[] = service.week();

        chart1 = findViewById(R.id.chart1);
        chart1.setTouchEnabled(false);
        chart1.setPinchZoom(false);


        chart1.getXAxis().setDrawLabels(false);
        chart1.getXAxis().setDrawGridLines(false);

        chart1.getAxisRight().setDrawGridLines (false);
        chart1.getAxisRight().setDrawLabels(false);

        chart1.getAxisLeft().setDrawGridLines(false);
        chart1.getAxisLeft().setDrawLabels(false);

        chart1.getDescription().setEnabled(false);
        chart1.getLegend().setEnabled(false);

        chart1.getXAxis().setDrawAxisLine(false);
        chart1.getAxisLeft().setDrawAxisLine(false);
        chart1.getAxisRight().setDrawAxisLine(false);

        ArrayList<Entry> values = new ArrayList<>();
        for(int i=0;i<5;i++) {
            values.add(new Entry(i, iarr[i]));
        }
        LineDataSet set1 = null;
        if (chart1.getData() != null && chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart1.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "data");
            set1.setDrawIcons(false);
            set1.setColor(Color.WHITE);
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(1f);
            set1.setCircleRadius(5f);
            set1.setDrawCircleHole(true);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormSize(15.f);

            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.transparency);
            set1.setFillDrawable(drawable);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            chart1.setData(data);
        }
    }

}
