package com.solodilov.lifecycle.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.solodilov.lifecycle.R;
import com.solodilov.lifecycle.model.SearchRequests;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldSearchAdapter extends PagedListAdapter<SearchRequests, OldSearchAdapter.ViewHolder> {

    private static final DiffUtil.ItemCallback<SearchRequests> DIFF_CALLBACK = new DiffUtil.ItemCallback<SearchRequests>() {
        @Override
        public boolean areItemsTheSame(@NonNull SearchRequests oldItem, @NonNull SearchRequests newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull SearchRequests oldItem, @NonNull SearchRequests newItem) {
            return oldItem.equals(newItem);
        }
    };


    public OldSearchAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_old_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchRequests requests = getItem(position);
        if (requests != null) {
            holder.bind(getItem(position));
        }else {
            holder.clear();
        }
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

        public void clear() {
            Log.d(this.getClass().getSimpleName(), "CLEAR");
        }
    }
}
