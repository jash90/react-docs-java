package com.zimny.reactdocs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.elvishew.xlog.XLog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.zimny.reactdocs.model.Component;
import com.zimny.reactdocs.model.Version;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.spinner1)
    SearchableSpinner spinner1;
    ArrayList<Version> versions;
    ArrayAdapter<Version> versionArrayAdapter;
    ArrayList<Component> components;
    ArrayAdapter<Component> componentArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        versions = new ArrayList<>();
        components = new ArrayList<>();
        try {
            versions = new GetVersions().execute().get();
            versionArrayAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, versions);
            versionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(versionArrayAdapter);
            XLog.d(versions.toString());
            versionArrayAdapter.notifyDataSetChanged();
            components = new GetComponents().execute(versions.get(0).getUrlDocumentation()).get();
            XLog.d(components.toString());
            componentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, components);
            componentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(componentArrayAdapter);
            componentArrayAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    webView.loadData(new GetDocumentationComponent().execute(((Version) spinner2.getSelectedItem()).getUrlDocumentation(), ((Component) spinner1.getSelectedItem()).getLink()).get(), "text/html", null);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    webView.loadData(new GetDocumentationComponent().execute(((Version) spinner2.getSelectedItem()).getUrlDocumentation(), ((Component) spinner1.getSelectedItem()).getLink()).get(), "text/html", null);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
