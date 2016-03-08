package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class AbstractActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
