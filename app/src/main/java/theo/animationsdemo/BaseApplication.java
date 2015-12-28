package theo.animationsdemo;


import android.app.Application;

import theo.animationsdemo.dagger.BaseComponent;
import theo.animationsdemo.dagger.BaseModule;
import theo.animationsdemo.dagger.DaggerBaseComponent;

public class BaseApplication extends Application {

    private BaseComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerBaseComponent.builder()
                .baseModule(new BaseModule(this))
                .build();
        component.inject(this);
    }
    public BaseComponent getComponent() {
        return component;
    }
}
