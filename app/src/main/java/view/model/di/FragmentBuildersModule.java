package view.model.di;

import dagger.Module;
import view.model.view.ui.ProjectFragment;
import view.model.view.ui.ProjectListFragment;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProjectFragment contributeProjectFragment();

    @ContributesAndroidInjector
    abstract ProjectListFragment contributeProjectListFragment();

}
