package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap ngm;
    public HistoryHandler(NGramMap ngm){
        this.ngm=ngm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for(String word: q.words()){
            TimeSeries draw=new TimeSeries();
            TimeSeries t=new TimeSeries(ngm.countHistory(word, q.startYear(), q.endYear()),q.startYear(),q.endYear());
            for(int i=q.startYear();i<=q.endYear();++i){
                draw.put(i,t.get(i));
            }
            lts.add(draw);
            labels.add(word+"~count");
        }
//        for(String word: q.words()){
//            TimeSeries draw=new TimeSeries();
//            TimeSeries t=new TimeSeries(ngm.weightHistory(word, q.startYear(), q.endYear()),q.startYear(),q.endYear());
//            for(int i=q.startYear();i<=q.endYear();++i){
//                draw.put(i,t.get(i));
//            }
//            lts.add(draw);
//            labels.add(word+"~weight");
//        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);

        return Plotter.encodeChartAsString(chart);
    }
}
