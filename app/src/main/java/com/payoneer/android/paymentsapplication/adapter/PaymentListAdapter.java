package com.payoneer.android.paymentsapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.payoneer.android.paymentsapplication.R;
import com.payoneer.android.paymentsapplication.databinding.PaymentListCardBinding;
import com.payoneer.android.paymentsapplication.model.remote.ApplicableNetwork;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.PaymentListViewHolder> {
    private final Context context;
    private final List<ApplicableNetwork> applicableNetworkList;

    public PaymentListAdapter(List<ApplicableNetwork> availablePaymentsResponseArrayList, Context context) {
        applicableNetworkList = availablePaymentsResponseArrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public PaymentListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new PaymentListViewHolder(PaymentListCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PaymentListViewHolder holder, int position) {
        ApplicableNetwork applicableNetwork = applicableNetworkList.get(position);
        Glide.with(context).load(applicableNetwork.getLinks().get("logo").toString()).placeholder(R.drawable.ic_credit_card).error(R.drawable.ic_credit_card).into(holder.paymentListCardBinding.paymentOptionImage);
        holder.paymentListCardBinding.paymentOptionTextView.setText(applicableNetwork.getLabel());
    }

    @Override
    public int getItemCount() {
        return applicableNetworkList == null ? 0 : applicableNetworkList.size();
    }

    public static class PaymentListViewHolder extends RecyclerView.ViewHolder {
        private final PaymentListCardBinding paymentListCardBinding;

        public PaymentListViewHolder(@NonNull @NotNull PaymentListCardBinding itemView) {
            super(itemView.getRoot());
            this.paymentListCardBinding = itemView;
        }
    }
}
