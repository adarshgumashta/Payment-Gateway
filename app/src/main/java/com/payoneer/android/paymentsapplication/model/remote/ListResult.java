/*
 * Copyright (c) 2020 Payoneer Germany GmbH
 * https://www.payoneer.com
 *
 * This file is open source and available under the MIT license.
 * See the LICENSE file for more information.
 */

package com.payoneer.android.paymentsapplication.model.remote;

import java.net.URL;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is designed to hold list of payment networks available for particular transaction based on provided information and result of
 * initialized payment session.
 * <p>
 * An instance of this object is returned as a result of new <code>Transaction</code> initialization, or during list status update via GET
 * method.
 */
@Getter
@Setter
public class ListResult {
    /**
     * Simple API, always present
     */
    private Map<String, URL> links;
    /**
     * Simple API, always present
     */
    private String resultInfo;
    /** Simple API, optional, always present in response to action (POST, UPDATE)
     * Integration type used when creating the LIST session, always present
     */
    private String integrationType;
}
