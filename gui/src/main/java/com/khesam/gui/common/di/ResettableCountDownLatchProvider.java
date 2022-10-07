package com.khesam.gui.common.di;

import com.khesam.gui.common.utils.ResettableCountDownLatch;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class ResettableCountDownLatchProvider {

    @Produces
    @ApplicationScoped
    @Named("synchronized-gui-thread")
    public ResettableCountDownLatch provideSingleCountResettableCountDownLatch() {
        return new ResettableCountDownLatch(1);
    }
}
