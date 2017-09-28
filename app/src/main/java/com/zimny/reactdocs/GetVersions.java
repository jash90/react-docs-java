package com.zimny.reactdocs;

import android.os.AsyncTask;

import com.elvishew.xlog.XLog;
import com.zimny.reactdocs.model.Version;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



class GetVersions extends AsyncTask<Void, Void, ArrayList<Version>> {
    @Override
    protected ArrayList<Version> doInBackground(Void... voids) {
        ArrayList<Version> versions = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://facebook.github.io/react-native/versions.html").get();
            Elements elements1 = document.select("td");
            Elements elements2 = document.select("th");
            for (Element e : elements2) {
                XLog.d(e.html());
                versions.add(new Version(e.html()));
            }
            int i = 0;
            for (Element e : elements1) {
                Pattern p = Pattern.compile("\"(.+)\"");
                Matcher m = p.matcher(e.html());
                while (m.find()) {
                    if (m.group().substring(1, m.group().length() - 1).contains("github")) {
                      //  versions.get(i).setUrlRealeseNotes(m.group().substring(1, m.group().length() - 1));
                        i++;
                    } else {
                        versions.get(i).setUrlDocumentation(m.group().substring(1, m.group().length() - 1));
                        if (versions.get(i).getVersion().equals("master")) {
                            i++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            XLog.d("GV ERROR" + e.getLocalizedMessage());
        }


        return versions;
    }
}
