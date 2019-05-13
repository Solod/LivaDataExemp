package com.solodilov.lifecycle.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.solodilov.lifecycle.R;
import com.solodilov.lifecycle.bases.BaseActivity;
import com.solodilov.lifecycle.ui.view.ForesmaticFragment;
import com.solodilov.lifecycle.ui.view.MainFragment;
import com.solodilov.lifecycle.ui.view_model.MainViewModel;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getLifecycle().addObserver(viewModel);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setFragment();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.requestRandomForesmatic();
                DialogFragment dialogFragment = new ForesmaticFragment();
                dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

                dialogFragment.show(getSupportFragmentManager(), ForesmaticFragment.class.getSimpleName());
                //addFragment(MainActivity.this, ForesmaticFragment.class, R.id.fragment_container, true);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setFragment() {
        addFragment(this, MainFragment.class, R.id.fragment_container, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
