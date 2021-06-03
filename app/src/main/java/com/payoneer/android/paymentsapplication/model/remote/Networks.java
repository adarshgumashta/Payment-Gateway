/*
 * Copyright (c) 2020 Payoneer Germany GmbH
 * https://www.payoneer.com
 *
 * This file is open source and available under the MIT license.
 * See the LICENSE file for more information.
 */

package com.payoneer.android.paymentsapplication.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is designed to hold list of applicable and registered payment network descriptions.
 */
@Getter
@Setter
public class Networks {
    /**
     * Simple API, always present
     */
    @Getter
    @SerializedName("applicable")
    @Expose
    private List<ApplicableNetwork> applicable;
    /**
     * Simple API, always present
     */
    private Date resourcesLastUpdate;

    public List<ApplicableNetwork> getApplicable() {
        return applicable;
    }
}
