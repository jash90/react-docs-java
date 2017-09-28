package com.zimny.reactdocs;

import android.os.AsyncTask;

import com.elvishew.xlog.XLog;
import com.zimny.reactdocs.model.Component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GetComponents extends AsyncTask<String, Void, ArrayList<Component>> {
    @Override
    protected ArrayList<Component> doInBackground(String... args) {
        ArrayList<Component> components = new ArrayList<>();
        try {
            Document document = Jsoup.connect(String.format("http://facebook.github.io%s/docs/getting-started.html", args[0])).get();
            Document dd = Jsoup.parse(document.select("div.nav-docs-viewport").outerHtml());
            XLog.d(dd.text());
            Elements elements = dd.select("li");
            for (Element e : elements) {
                Pattern p = Pattern.compile("href=\"(.+.html)\">(.+)<");
                Matcher m = p.matcher(e.html());
                while (m.find()) {
                    components.add(new Component(m.group(2), m.group(1)));
                }
            }


        } catch (Exception e) {
            XLog.d("GC ERROR" + e.getLocalizedMessage());
        }


        return components;
    }
}
