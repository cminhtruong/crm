package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.helper.NetworkHelper;
import fr.pds.isintheair.crmtab.common.helper.ResourceHelper;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.CallController;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/2016         *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class ContactDetailActivity extends Activity {
    Contact currentContact;

    @Bind(R.id.call_information_textview)
    TextView callInformationTextview;

    @OnClick(R.id.sms_imageview)
    public void onSmsClick() {
        //TODO Implement click event
    }

    @OnClick(R.id.phone_imageview)
    public void onPhoneClick() {
        if (currentContact != null) {
            if (NetworkHelper.isNetworkAvailable()) {
                CallController.call(currentContact.getPhoneNumber());
            }

            else {
                Toast.makeText(this, ResourceHelper.getString(R.string.message_failed_no_internet), Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);

        currentContact = getIntent().getParcelableExtra("contact");

        if (currentContact != null) {
            String callInformations = currentContact.getFirstName() + " " + currentContact.getLastName() + " - " + currentContact.getPhoneNumber();
            callInformationTextview.setText(callInformations);
        }
    }
}
