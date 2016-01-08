package com.ds.ce.diy.domain;

import org.junit.Test;

public class LombokEntitiesTest {

    @Test
    public void lombok() throws Exception {
        Notification notification = new Notification();
        Accessory accessory = new Accessory();
        Tool tool = new Tool();
        Lifespan lifespan = new Lifespan(null);
    }
}
