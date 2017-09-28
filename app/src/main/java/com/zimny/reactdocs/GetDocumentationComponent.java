package com.zimny.reactdocs;

import android.os.AsyncTask;

import com.elvishew.xlog.XLog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class GetDocumentationComponent extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... versions) {
        String html = "";
        try {
            XLog.d(String.format("http://facebook.github.io%s/docs/%s.html", versions[0], versions[1]));
            Document document = Jsoup.connect(String.format("http://facebook.github.io%s/%s", versions[0], versions[1])).get();
            String s = document.select("div.inner-content").outerHtml();
            if (s.contains("<p class=\"edit-page-block\">")) {
                s = s.substring(0, s.indexOf("<p class=\"edit-page-block\">"));
            }
            if (s.contains("<a class=\"anchor\" name=\"examples\">")) {
                s = s.substring(0, s.indexOf("<a class=\"anchor\" name=\"examples\">"));
            }
            html = s;
        } catch (Exception e) {
            XLog.d("GDC ERROR " + e.getLocalizedMessage());
        }


        return html;
    }

}
