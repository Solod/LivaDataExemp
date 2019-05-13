package com.solodilov.lifecycle.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solodilov.lifecycle.R;
import com.solodilov.lifecycle.model.SearchRequests;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldSearchAdapter extends RecyclerView.Adapter<OldSearchAdapter.ViewHolder> {

    private List<SearchRequests> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_old_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setListItems(List<SearchRequests> listSearch) {
        this.list = listSearch;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_id)
        TextView mTvId;

        @BindView(R.id.tv_author)
        TextView mTvAuthor;

        @BindView(R.id.tv_forismatic)
        TextView mTvForesmatic;

        @BindView(R.id.tv_link)
        TextView mTvLink;

        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public void bind(SearchRequests searchRequests) {
            itemView.setTag(searchRequests.getId());
            mTvForesmatic.setText(searchRequests.getQuoteText());
            mTvId.setText(String.valueOf(searchRequests.getId()));
            mTvAuthor.setText(searchRequests.getQuoteAuthor());
            mTvLink.setText(searchRequests.getSenderLink());
        }
    }
}
