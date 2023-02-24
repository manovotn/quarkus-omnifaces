package io.quarkus.omnifaces.runtime;

import org.apache.myfaces.cdi.view.ViewScopeBeanHolder;
import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

public class OmniFacesFeature implements Feature {

    private final static String REASON = "OmniFaces runtime initialization";

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        final RuntimeClassInitializationSupport runtimeInit = ImageSingletons.lookup(RuntimeClassInitializationSupport.class);
        
        runtimeInit.initializeAtRunTime("org.omnifaces.config.WebXmlSingleton", REASON);

        // TODO: being fixed in MyFaces 2.3-M8
        runtimeInit.initializeAtRunTime(ViewScopeBeanHolder.class, REASON);
    }

    @Override
    public String getDescription() {
        return REASON;
    }
}
