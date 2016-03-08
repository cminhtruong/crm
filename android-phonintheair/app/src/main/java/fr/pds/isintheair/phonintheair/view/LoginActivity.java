package fr.pds.isintheair.phonintheair.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.phonintheair.R;
import fr.pds.isintheair.phonintheair.controller.service.CallService;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;

public class LoginActivity extends Activity {
    @Bind(R.id.email_edittext)
    EditText email;

    @Bind(R.id.password_edtitext)
    EditText password;

    private Integer userId;

    @OnClick(R.id.connexion_button)
    public void onConnectionClick() {
        userId = email.getText().toString().hashCode();
        SharedPreferencesHelper.writeInteger("userId", userId);

        Intent callServiceIntent = new Intent(this, CallService.class);
        Intent dashboardIntent   = new Intent(this, DashboardActivity.class);

        startService(callServiceIntent);
        startActivity(dashboardIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        userId = SharedPreferencesHelper.readInteger("userId", 0);

        if (!userId.equals(0)) {
            startActivity(new Intent(this, DashboardActivity.class));
        }
    }

}
