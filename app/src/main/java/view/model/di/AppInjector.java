package view.model.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import view.model.MVVMApplication;

public class AppInjector {

    private AppInjector(){}

    public static void init(MVVMApplication mvvmApplication){
        DaggerAppComponent.builder().application(mvvmApplication)
                .build().inject(mvvmApplication);

        mvvmApplication
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                        handleActivity(activity);
                    }

                    @Override
                    public void onActivityStarted(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(@NonNull Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(@NonNull Activity activity) {

                    }
                });
    }

    private static void handleActivity(Activity activity){
        if (activity instanceof HasSupportFragmentInjector){
            AndroidInjection.inject(activity);
        }

        if (activity instanceof FragmentActivity){
            ((FragmentActivity)activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                            new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment fragment, @Nullable Bundle savedInstanceState) {
                                    super.onFragmentCreated(fm, fragment, savedInstanceState);
                                    if (fragment instanceof Injectable){
                                        AndroidSupportInjection.inject(fragment);
                                    }
                                }
                            },true);
        }

    }

}
