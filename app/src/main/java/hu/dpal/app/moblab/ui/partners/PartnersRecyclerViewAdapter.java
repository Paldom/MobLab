package hu.dpal.app.moblab.ui.partners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hu.dpal.app.moblab.R;
import hu.dpal.app.moblab.model.Partner;

import java.util.List;

public class PartnersRecyclerViewAdapter extends RecyclerView.Adapter<PartnersRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Partner> partnersList;

    public PartnersRecyclerViewAdapter(Context context, List<Partner> partnersList) {
        this.context = context;
        this.partnersList = partnersList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_partner, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Partner partner = partnersList.get(position);
        if (partner.getImg() != null) {
            Glide.with(context).load(partner.getImg()).into(holder.ivImg);
        }
        holder.tvTitle.setText(partner.getTitle());
        holder.tvDesc.setText(partner.getDesc());
    }

    @Override
    public int getItemCount() {
        return partnersList.size();
    }

    public Partner getItem(int position) {
        return partnersList.get(position);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImg;
        public TextView tvTitle;
        public TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
        }
    }
}