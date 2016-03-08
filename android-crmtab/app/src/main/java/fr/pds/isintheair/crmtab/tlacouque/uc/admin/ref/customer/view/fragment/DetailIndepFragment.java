package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.FormatValidator;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;

/**
 * Created by tlacouque on 01/01/2016.
 * Controller which is used to display an independant. He used to display the view, and to open
 * a web navigator if the user click on the website textview.
 */
public class DetailIndepFragment extends Fragment {

    //Used to have the same key to pass independant from customer list view holder to this fragment
    public static final String KEY_INDEP_ARGS = "INDEP";
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    @Bind(R.id.detail_indep_fragment_name)
    TextView name;
    @Bind(R.id.detail_indep_fragment_siret_number)
    TextView siretNumber;
    @Bind(R.id.detail_indep_fragment_finess_number)
    TextView finessNumber;
    @Bind(R.id.detail_indep_fragment_adress)
    TextView adress;
    @Bind(R.id.detail_indep_fragment_zip_code)
    TextView zipCode;
    @Bind(R.id.detail_indep_fragment_web_site)
    TextView webSite;
    @Bind(R.id.detail_indep_fragment_independant_type)
    TextView independantType;
    @Bind(R.id.detail_indep_fragment_company)
    TextView company;
    @Bind(R.id.detail_indep_fragment_specialty)
    TextView specialty;
    @Bind(R.id.detail_indep_fragment_long_term_fidelisation)
    TextView longTermFidelity;
    @Bind(R.id.detail_indep_fragment_map)
    MapView map;
    private Independant independant;
    private OnFragmentInteractionListener mListener;

    public DetailIndepFragment() {
        // Required empty public constructor
    }

    /**
     * Can be called when a new DetailHCFragment is needed
     *
     * @return DetailHCFragment
     */
    public static DetailIndepFragment newInstance() {
        DetailIndepFragment fragment = new DetailIndepFragment();
        Bundle              args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        independant = this.getArguments().getParcelable(KEY_INDEP_ARGS);
        View v = inflater.inflate(R.layout.fragment_detail_indep, container, false);
        ButterKnife.bind(this, v);
        initView();
        initMap();
        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Initialise the view before displaying it with independant pass by the list
     */
    private void initView() {
        name.setText(independant.getName());
        siretNumber.setText(String.valueOf(independant.getSiretNumber()));
        finessNumber.setText(String.valueOf(independant.getFinessNumber()));
        adress.setText(independant.getAdress());
        zipCode.setText(String.valueOf(independant.getZipCode()));
        webSite.setText(independant.getWebSite());
        independantType.setText(independant.getIndependantType());
        if(independant.getCompany() != null) {
            company.setText(independant.getCompany().getName());
        }
        if(independant.getSpecialty() != null) {
            specialty.setText(independant.getSpecialty().getName());
        }
        longTermFidelity.setText(String.valueOf(independant.getLongTermFidelity()));
    }

    /**
     * Initialise the map in this view
     */
    private void initMap() {
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setUseDataConnection(true);
        IMapController mapController = map.getController();
        mapController.setZoom(10);
        GeoPoint startPoint = new GeoPoint(48.8534100, 2.3488000);
        mapController.setCenter(startPoint);
        map.invalidate();
    }

    /**
     * Open a navigator and with the url pass by the website textview
     */
    @OnClick(R.id.detail_indep_fragment_web_site)
    public void openWebSite() {
        String url       = FormatValidator.formatUrl(webSite.getText().toString());
        Intent intentWeb = new Intent(Intent.ACTION_VIEW);
        intentWeb.setData(Uri.parse(url));
        startActivity(intentWeb);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION and WRITE_EXTERNAL_STORAGE
                Boolean location = perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                Boolean storage = perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (location && storage) {
                    // All Permissions Granted
                    Toast.makeText(getActivity().getApplicationContext(), "All permissions granted", Toast.LENGTH_SHORT).show();
                } else if (location) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Storage permission is required to store map tiles to reduce data usage and for offline usage.",
                            Toast.LENGTH_LONG).show();
                } else if (storage) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Location permission is required to show the user's location on map.",
                            Toast.LENGTH_LONG).show();
                } else { // !location && !storage case
                    // Permission Denied
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Storage permission is required to store map tiles to reduce data usage and for offline usage." +
                                    "\nLocation permission is required to show the user's location on map.",
                            Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        String       message     = "OSMDroid permissions:";
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            message += "\nStorage access to store map tiles.";
        }
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            message += "\nLocation to show user location.";
        }
        if (!permissions.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
            String[] params = permissions.toArray(new String[permissions.size()]);
            requestPermissions(params, REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        } // else: We already have permissions, so handle as normal
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and firstName
        void onFragmentInteraction(Uri uri);
    }


}
