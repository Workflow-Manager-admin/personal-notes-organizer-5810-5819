package com.example.androidfrontend;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager2.widget.ViewPager2;

public class CategoriesFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private CategoriesPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        tabLayout = root.findViewById(R.id.tab_layout_categories);
        viewPager = root.findViewById(R.id.view_pager_categories);

        pagerAdapter = new CategoriesPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // Example static categories
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Work"));
        tabLayout.addTab(tabLayout.newTab().setText("Personal"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        return root;
    }
}
