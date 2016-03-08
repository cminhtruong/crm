package fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.model.Message;

/**
 * Created by Maimouna MEFIRE on 24/01/2016.
 */
public class MessageViewAdapter extends ArrayAdapter<Message> {

        /**
         *Permet de garder la r�f�rence sur la liste des messages � afficher
         */
        private List<Message> mMessages;

        /**
         *Permet de garder la r�f�rence sur le Context de l'activit� appelante
         */
        private Context mContext;

        /**
         * Constructeur avec param�tres
         * @param context
         * @param messages : liste des messages � afficher
         */
        public MessageViewAdapter(Context context,List<Message> messages) {

            super(context,0,messages);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // R�cup�raation du message � cette position
            Message message = getItem(position);
            // v�rifie si la vue existe d�ja, sinon on la cr�e.
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_sms, parent, false);
            }

            TextView recipient = (TextView) convertView.findViewById(R.id.user_contact);
            TextView content = (TextView) convertView.findViewById(R.id.message_bigining);
            TextView date = (TextView) convertView.findViewById(R.id.message_time);

            recipient.setText(message.recipient);
            content.setText(message.content);
            date.setText(message.date.getTime()+"");
            // Retourne la vue � afficher � l'�cran
            return convertView;
        }
    }


