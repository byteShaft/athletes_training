package com.pits.athletestraining.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.pits.athletestraining.R;
import com.pits.athletestraining.fragments.Method1Fragment;
import com.pits.athletestraining.fragments.Method2Fragment;
import com.pits.athletestraining.fragments.Method3Fragment;

import java.util.ArrayList;
import java.util.List;


public class TabsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_activity);
        getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tableLayout = (TabLayout) findViewById(R.id.tab_layout);
        tableLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Method1Fragment(), "Method1");
        adapter.addFrag(new Method2Fragment(), "Method2");
        adapter.addFrag(new Method3Fragment(), "Method3");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
