package fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.model.Message;



    public class ActivityNewMessage extends BaseActivity implements View.OnClickListener {

        private List<Message> mMessageList;

        private ImageButton mAddContacts;
        private ImageButton mSendMessage;
        private TextView mListRecipients;
        private EditText mNewMessage;
        private List<String> mConversation;
        private ListView mConversationView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_message);
            mAddContacts = (ImageButton) findViewById(R.id.add_contacts);
            mListRecipients = (TextView) findViewById(R.id.contacts);
            mNewMessage = (EditText) findViewById(R.id.editText_new_message);
            mConversationView = (ListView) findViewById(R.id.listView_list_message);
            mSendMessage = (ImageButton) findViewById(R.id.send_message);

            mAddContacts.setOnClickListener(this);
            mSendMessage.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                //Ajout de destinataire(s)
                case R.id.add_contacts:

                    break;

                //Envoie du message
                case R.id.send_message:

                    //on r�cup�re le message saisie:
                    String messageToSend = mNewMessage.getText().toString();

                    //R�cup�re la liste des destinataire
                    String recipients = mListRecipients.getText().toString();

                    //s'il ya au moins un contact
                    if (!recipients.isEmpty()) {
                        //r�cup�re les diff�rents contacts s�par�s par un point virgule
                        String[] listRecipient = recipients.split(";");

                        //Au moins un contact renseign�
                        if (listRecipient.length > 0) {
                            for (String contact : listRecipient) {
                                Message message = new Message("0525151220", contact, messageToSend, new Date());

                                //Envoie du message
                                //TODO

                            }
                        }
                    }


                    break;
            }
        }

        /**
         * Permet de quitter normalement l'activit�.
         */
        @Override
        public void onBackPressed() {
            super.onBackPressed();
            finish();
        }
    }

