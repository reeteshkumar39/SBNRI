package com.sbnri.di.module;

import com.sbnri.ui.main.SBNRIActivity;
import com.sbnri.ui.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract SBNRIActivity bindMainActivity();
}
