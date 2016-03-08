package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import java.util.Calendar;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.common.view.fragment.ContactListFragment;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.activity.ProspectActivity;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.AndroidBus;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayAddLogFragmentEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayPopUpFragmentEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.ListennerCallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.callsnotregistered.PendingLogsFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.displaycalls.CallDetailsFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.displaycalls.DisplayCallLogFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.registeracall.AddLogFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.registeracall.PopUpFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.enums.CallType;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view.ClientListFragment;
import fr.pds.isintheair.crmtab.mmefire.uc.sms.send.receive.activity.ActivityHome;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller.CrvController;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateCustomerAlertDialog;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateHCFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateIndepFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.ListCustomerFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DisplayCallLogFragment.OnListFragmentInteractionListener,
        PendingLogsFragment.OnListFragmentInteractionListener, CreateCustomerAlertDialog.AlertPositiveListener,
        ListCustomerFragment.OnListFragmentInteractionListener {

    Toolbar toolbar;
    // UC Register a call
    private PendingLogsFragment pend;
    private AndroidBus bus;
    public static String TagAddLogFragment = "FRAGMENT_AJOUT";
    User              currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Crm Tab");
        //start local services
        startService(new Intent(this, ListennerCallEndedEvent.class));
        bus = Constants.getInstance().getCurrentBusInstance();
        bus.register(this);

                          currentUser = new User();
        SharedPreferences prefs       = PreferenceManager.getDefaultSharedPreferences(this);

        currentUser.setEmail(prefs.getString("email", null));
        currentUser.setPassword(prefs.getString("password", null));
        currentUser.setTel(prefs.getString("tel", null));
        currentUser.setFname(prefs.getString("fname", null));
        currentUser.setId(prefs.getString("id", null));
        currentUser.setLname(prefs.getString("lname", null));

        Constants.getInstance().setCurrentUser(currentUser);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MainLogoFragment    mainLogoFragment = new MainLogoFragment();
        FragmentTransaction transaction      = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mainLogoFragment,"TAG");
        transaction.addToBackStack(null);
        transaction.commit();

        if (getIntent().hasExtra("msg"))
            showNotificationListFrag();
    }

    /**
     * @Override public void onBackPressed() {
     * DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
     * if (drawer.isDrawerOpen(GravityCompat.START)) {
     * drawer.closeDrawer(GravityCompat.START);
     * } else {
     * super.onBackPressed();
     * }
     * }
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.deco) {
            PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_passer_appel) {
            fragment = new ContactListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment).addToBackStack(null);
            transaction.commit();

        }
        else if (id == R.id.nav_historiser_appel) {
            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "0610772364"));
            bus.post(new CallEndedEvent(CallType.OUTGOING, Calendar.getInstance().getTime().toLocaleString(), "502", "0684894378"));
            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1038", "0778801708"));
            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "0610772364"));
            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "0620584913"));

            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "0620123456"));

        }
        else if (id == R.id.nav_lister_appel) {
            fragment = DisplayCallLogFragment.newInstance() ;
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
            ft.replace(R.id.container, fragment, "FRAGMENT_LISTE_CRA").addToBackStack(null).commit();
        }
        else if (id == R.id.nav_editer_crv) {
            new CrvController().getClientsForUser(currentUser.getId(), this);
           /* ClientListFragment clientListFragment = new ClientListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, clientListFragment);
            transaction.addToBackStack(null);
            transaction.commit();*/

        }
        else if (id == R.id.nav_ref_client) {
            // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
            // setSupportActionBar(toolbar);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new ListCustomerFragment());
            fragmentTransaction.addToBackStack("menu");
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_envoyer_sms) {

            startActivity(new Intent(this, ActivityHome.class));

        }
        else if (id == R.id.nav_suggestion_prospect){
            startActivity(new Intent(this, ProspectActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void showNotificationListFrag() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        pend = PendingLogsFragment.newInstance();
        ft.replace(R.id.container, pend, "FRAGMENT_LISTE_NOTIF").commit();
    }


    @Subscribe
    public void showpopup(DisplayPopUpFragmentEvent event) {
        PopUpFragment pop = PopUpFragment.newInstance(event);
        pop.show(getFragmentManager(), "dialog");
        //make popup not cancellable
        pop.setCancelable(false);
    }

    @Subscribe
    public void showaddlogfragment(DisplayAddLogFragmentEvent event) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);

        AddLogFragment fragment = AddLogFragment.newInstance(event.getCallEndedEvent().getIdcontact()
                , event.getCallEndedEvent().getDate()
                , event.getCallEndedEvent().getDuration()
                , event.getCallEndedEvent().getCalltype() == CallType.INCOMING ? "Entrant" : "Sortant"
                , true);

        ft.replace(R.id.container, fragment,TagAddLogFragment).addToBackStack(null).commit();
    }


    @Override
    public void onListFragmentInteraction(Cra cra) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
        CallDetailsFragment fragment = CallDetailsFragment.newInstance(cra);
        ft.replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        }

       /* else {

            MainLogoFragment mainLogoFragment = new MainLogoFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, mainLogoFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }*/
    }

    @Override
    public void onPositiveClick(int position) {
        switch (position) {
            case 0:
                CreateHCFragment createHCFragment = new CreateHCFragment();
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
                toolbar.setTitle(R.string.create_he_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.create_customer_fragment_container, createHCFragment).commit();
                break;
            case 1:

                CreateIndepFragment createIndepFragment = new CreateIndepFragment();
                Toolbar toolbarindep = (Toolbar) findViewById(R.id.toolbar1);
                toolbarindep.setTitle(R.string.create_indep_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.create_customer_fragment_container, createIndepFragment)
                                    .commit();
                break;
        }
    }

}

