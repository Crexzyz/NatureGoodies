package crexzyzdev.ng.platform;

import crexzyzdev.ng.Constants;
import crexzyzdev.ng.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> classToLoad) {

        final T loadedService = ServiceLoader.load(classToLoad)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + classToLoad.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, classToLoad);
        return loadedService;
    }
}
