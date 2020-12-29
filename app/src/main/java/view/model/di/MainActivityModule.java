package view.model.di;

import dagger.Module;
import view.model.view.ui.MainActivity;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(moduler = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

}
