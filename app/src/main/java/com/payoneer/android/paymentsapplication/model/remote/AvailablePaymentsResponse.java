package com.payoneer.android.paymentsapplication.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.Map;

/**
 * This class is designed to hold list of payment networks available for particular transaction based on provided information and result of
 * initialized payment session.
 * <p>
 * An instance of this object is returned as a result of new <code>Transaction</code> initialization, or during list status update via GET
 * method.
 */
public class AvailablePaymentsResponse {
    /**
     * Simple API, always present
     */
    @SerializedName("links")
    @Expose
    private Map<String, URL> links;
    /**
     * Simple API, always present
     */
    @SerializedName("resultInfo")
    @Expose
    private String resultInfo;
    /**
     * Simple API, optional, always present in response to action (POST, UPDATE)
     */
    @SerializedName("interaction")
    @Expose
    private Interaction interaction;
    /**
     * Simple API, optional
     */
    @SerializedName("accounts")
    @Expose
    private AccountRegistration accounts;
    /**
     * Simple API, optional, always present in native LIST
     */
    @SerializedName("networks")
    @Expose
    private Networks networks;
    /**
     * Advanced API, optional
     */
    @SerializedName("extraElements")
    @Expose
    private ExtraElements extraElements;
    /**
     * Preset account, Simple API, optional, could present only in the LIST-for-PRESET
     */
    @SerializedName("presetAccount")
    @Expose
    private PresetAccount presetAccount;
    /**
     * LIST type based on operation of next referred actions, could be one of CHARGE, PRESET, PAYOUT, UPDATE.
     */
    @SerializedName("operationType")
    @Expose
    private String operationType;
    /**
     * Indicates whether this LIST is explicitly initialized with permission or denial to delete accounts.
     */
    @SerializedName("allowDelete")
    @Expose
    private Boolean allowDelete;
    /**
     * The style object passed in the transaction.
     */
    @SerializedName("style")
    @Expose
    private Style style;
    /**
     * Payment information, optional
     */
    @SerializedName("payment")
    @Expose
    private Payment payment;
    /**
     * Collections of the products, optional
     */
    @SerializedName("products")
    @Expose
    private Product products;
    /**
     * Integration type used when creating the LIST session, always present
     */
    @SerializedName("integrationType")
    @Expose
    private String integrationType;

    public Networks getNetworks() {
        return networks;
    }
}
