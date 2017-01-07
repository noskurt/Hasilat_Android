package noskurt.com.hasilat;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import noskurt.com.hasilat.alltime.AlltimeFragmentView;
import noskurt.com.hasilat.distributors.DistributorsFragmentView;
import noskurt.com.hasilat.news.NewsFragmentView;
import noskurt.com.hasilat.ygznsl.release.ReleaseDateView;
import noskurt.com.hasilat.studios.StudiosFragmentView;
import noskurt.com.hasilat.turkish.TurkishFragmentView;
import noskurt.com.hasilat.weekly.WeeklyFragmentView;
import noskurt.com.hasilat.annual.AnnualFragmentView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_haberler) {
            NewsFragmentView fragment = new NewsFragmentView();
            changeFragment(fragment);
        } else if (id == R.id.nav_vizyon) {
            ReleaseDateView fragment = new ReleaseDateView();
            changeFragment(fragment);
        } else if (id == R.id.nav_haftalik) {
            WeeklyFragmentView fragment = new WeeklyFragmentView();
            changeFragment(fragment);
        } else if (id == R.id.nav_yillik) {
            AnnualFragmentView fragment = new AnnualFragmentView();
            changeFragment(fragment);
        } else if (id == R.id.nav_turk) {
            TurkishFragmentView fragment = new TurkishFragmentView();
            changeFragment(fragment);
        } else if (id == R.id.nav_dagitim) {
            DistributorsFragmentView fragment = new DistributorsFragmentView();
            changeFragment(fragment);
        } else if (id == R.id.nav_studyo) {
            StudiosFragmentView fragment = new StudiosFragmentView();
            changeFragment(fragment);
        } else if (id == R.id.nav_tumzaman) {
            AlltimeFragmentView fragment = new AlltimeFragmentView();
            changeFragment(fragment);
        } else {
            aboutUsAlert();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit();
    }

    private void aboutUsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("HAKKIMIZDA");
        builder.setMessage(R.string.about_us);
        builder.setCancelable(true);

        builder.setNeutralButton("TAMAM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}