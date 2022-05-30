
package com.digitforce.aip.listener;

import com.digitforce.aip.dto.event.CatalogNameModifiedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class CatalogListener {


    @EventListener
    public void process(CatalogNameModifiedEvent event) {

    }
}
