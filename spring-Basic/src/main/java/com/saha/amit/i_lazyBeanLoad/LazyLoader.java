package com.saha.amit.i_lazyBeanLoad;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyLoader {
    public LazyLoader() {
        System.out.println("LazyLoader ..");
    }
}
