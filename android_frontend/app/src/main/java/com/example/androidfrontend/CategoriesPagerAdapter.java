package com.example.androidfrontend;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CategoriesPagerAdapter extends FragmentStateAdapter {
    private static final String[] CATEGORIES = {"All", "Work", "Personal"};

    public CategoriesPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CategoryNotesFragment.newInstance(CATEGORIES[position]);
    }

    @Override
    public int getItemCount() {
        return CATEGORIES.length;
    }
}
