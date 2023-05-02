package com.example.wsrfood.api.version;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VersionResponse {
    @SerializedName("versions")
    private List<String> versions;

    public VersionResponse(List<String> versions) {
        this.versions = versions;
    }

    public List<String> getVersions() {
        return versions;
    }

    @Override
    public String toString() {
        return "VersionsResponse{" +
                "versions=" + versions +
                '}';
    }
}
