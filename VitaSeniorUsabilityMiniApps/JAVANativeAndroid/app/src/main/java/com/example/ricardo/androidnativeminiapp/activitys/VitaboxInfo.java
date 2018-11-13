package com.example.ricardo.androidnativeminiapp.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ricardo.androidnativeminiapp.Fragments.BoardsFragment;
import com.example.ricardo.androidnativeminiapp.Fragments.PatientsFragment;
import com.example.ricardo.androidnativeminiapp.Fragments.UsersFragment;
import com.example.ricardo.androidnativeminiapp.R;

public class VitaboxInfo extends AppCompatActivity {

    private TextView mTextMessage;
    private String vitaBoxId;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            Bundle bundle = new Bundle();
            bundle.putString("VitaboxID",vitaBoxId);
            switch (item.getItemId()) {
                case R.id.navigation_patients:
                    fragment = new PatientsFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_boards:
                    fragment = new BoardsFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_users:
                    fragment = new UsersFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vitabox_info_activity);
        getSupportActionBar().hide();
        vitaBoxId = getIntent().getExtras().getString("VitaboxID");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_patients);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

}
