package com.zimny.reactdocs.model;



public class Version {
    private String version;
    private String urlDocumentation;

    public Version(String version) {
        this.version = version;
        this.urlDocumentation = "";
    }



    public String getVersion() {
        return version;
    }



    public String getUrlDocumentation() {
        return urlDocumentation;
    }

    public void setUrlDocumentation(String urlDocumentation) {
        this.urlDocumentation = urlDocumentation;
    }

    @Override
    public String toString() {
        return version;
    }
}
