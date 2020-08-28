package com.sbnri.ui.main;

import com.sbnri.ui.list.ListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

}
