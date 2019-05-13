package com.solodilov.lifecycle.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.solodilov.lifecycle.R;
import com.solodilov.lifecycle.bases.BaseDialogFragment;
import com.solodilov.lifecycle.ui.view_model.MainViewModel;

import butterknife.BindView;
import butterknife.OnClick;

public class ForesmaticFragment extends BaseDialogFragment {

    private MainViewModel viewModel;

    @BindView(R.id.tv_author)
    protected TextView mTvAuthor;

    @BindView(R.id.tv_forismatic)
    protected TextView mTvForesmatic;

    @BindView(R.id.tv_link)
    protected TextView mTvLink;

    @BindView(R.id.pb_load)
    protected ProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        getLifecycle().addObserver(viewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("FRRRRRF!!!");
        return inflater.inflate(R.layout.fragment_foresmatic_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgress(true);
        viewModel.getForesmaticItem().observe(this, foresmaticItem -> {
            mTvForesmatic.setText(foresmaticItem.getQuoteText());
            mTvAuthor.setText(foresmaticItem.getQuoteAuthor());
            mTvLink.setText(foresmaticItem.getSenderLink());
            showProgress(false);
        });
        //viewModel.requestRandomForesmatic();
    }

    private void showProgress(boolean isShow) {
        mProgressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @OnClick({R.id.button_save, R.id.button_dont_save})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.button_save:
                viewModel.setNewForesmatic();
                break;
            case R.id.button_dont_save:
                break;
        }
        dismiss();

    }
}
