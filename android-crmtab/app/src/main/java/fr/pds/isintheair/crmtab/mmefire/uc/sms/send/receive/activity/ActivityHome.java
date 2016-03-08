package fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.model.Message;
import fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.view.MessageViewAdapter;


public class ActivityHome extends BaseActivity implements View.OnClickListener{

        private MessageViewAdapter messageAdapter;
        private List<Message> mMessageList;

        private ImageButton mMessageOptions;
        private ImageButton mNewMessage;
        private ListView mListMessageView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_messages);
            mMessageOptions = (ImageButton)findViewById(R.id.options_messages);
            mNewMessage = (ImageButton)findViewById(R.id.new_message);
            mListMessageView = (ListView)findViewById(R.id.listView_list_message);

            mListMessageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent intent = new Intent(view.getContext(),ActivityNewMessage.class);
                    startActivity(intent);
                }
            });

            mMessageOptions.setOnClickListener(this);
            mNewMessage.setOnClickListener(this);


            //initialise une liste de message
            initMessageList();

            messageAdapter = new MessageViewAdapter(this,mMessageList);
            mListMessageView.setAdapter(messageAdapter);

        }

        private void initMessageList(){
            mMessageList = new ArrayList<Message>();
            mMessageList.add(new Message("0684485214","0525151220","Salut comment tu vas",new Date()));
            mMessageList.add(new Message("toto","0525151220","Hello, tu es ou?",new Date()));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.options_messages:

                    break;

                //Ouvre l'activit� pour cr�er un nouveau message
                case R.id.new_message:
                    Intent intent = new Intent(this,ActivityNewMessage.class);
                    startActivity(intent);
                    break;
            }
        }


    }

