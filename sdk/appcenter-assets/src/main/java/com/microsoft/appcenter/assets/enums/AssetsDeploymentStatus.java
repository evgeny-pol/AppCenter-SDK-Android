package com.microsoft.appcenter.assets.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Indicates the status of a deployment (after installing and restarting).
 */
public enum AssetsDeploymentStatus {

    /**
     * The deployment succeeded.
     */
    @SerializedName("DeploymentSucceeded")
    SUCCEEDED("DeploymentSucceeded"),

    /**
     * The deployment failed (and was rolled back).
     */
    @SerializedName("DeploymentFailed")
    FAILED("DeploymentFailed");

    private final String value;

    AssetsDeploymentStatus(String value) {
        this.value = value;
    }

    /**
     * Gets the assigned enum value.
     *
     * @return string assigned to enum item.
     */
    public String getValue() {
        return this.value;
    }
}