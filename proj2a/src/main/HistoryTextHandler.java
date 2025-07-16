package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler
{
    private NGramMap ngm;
    public HistoryTextHandler(NGramMap ngm) {
        super();
        this.ngm=ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        StringBuilder response = new StringBuilder();
        for(String word:words){
            response.append(word).append(": {");
            int s=startYear;
            while(ngm.weightHistory(word,s,s).get(s)==null)s++;
            response.append(s).append("=").append(ngm.weightHistory(word,s,s).get(s));
            for(int year = s+1; year <=endYear; ++year){
                if(ngm.weightHistory(word,year,year).get(year)==null)continue;
                response.append(", ").append(year).append("=").append(ngm.weightHistory(word,year,year).get(year));
            }
            response.append("}\n");
        }
        return response.toString();
    }
}
