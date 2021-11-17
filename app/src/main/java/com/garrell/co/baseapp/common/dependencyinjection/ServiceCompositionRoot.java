package com.garrell.co.baseapp.common.dependencyinjection;

import android.app.Service;

public class ServiceCompositionRoot extends ComponentCompositionRoot {

    private final Service service;

    public ServiceCompositionRoot(ApplicationCompositionRoot applicationCompositionRoot,
                                  Service service) {
        super(applicationCompositionRoot);
        this.service = service;
    }

}
