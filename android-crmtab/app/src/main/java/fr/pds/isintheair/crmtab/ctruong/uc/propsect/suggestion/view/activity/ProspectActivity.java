package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.adapter.ProspectAdapter;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.config.ProspectRestConfig;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.domain.Prospect;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.rest.ProspectRetrofitAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ProspectActivity extends Activity {

    @Bind(R.id.btn_prospect)
    Button btn_prospect;
    @Bind(R.id.lview_prospect)
    ListView lview_prospect;
    List<Prospect> prospects;
    ProspectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospect);
        ButterKnife.bind(this);
        btn_prospect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ProspectRestConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                ProspectRetrofitAPI api = retrofit.create(ProspectRetrofitAPI.class);
                Call call = api.getProspect();
                call.enqueue(new Callback<List<Prospect>>() {

                    @Override
                    public void onResponse(Response<List<Prospect>> response, Retrofit retrofit) {

                        adapter = new ProspectAdapter(ProspectActivity.this, 0, prospects);
                        lview_prospect.setAdapter(adapter);
                        lview_prospect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), ConsultProspect.class);
                                intent.putExtra("prospect", position);
                                startActivityForResult(intent, 0);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });
    }


}
