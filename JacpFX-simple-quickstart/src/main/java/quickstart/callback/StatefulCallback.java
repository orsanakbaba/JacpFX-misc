/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [StatefulCallback.java]
 * AHCP Project (http://jacp.googlecode.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 *
 ************************************************************************/
package quickstart.callback;

import java.util.logging.Logger;

import javafx.event.Event;


import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.CallbackComponent;
import quickstart.util.ComponentIds;

/**
 * a stateful JacpFX component
 * @author Andy Moncsek
 *
 */
@Component(id = ComponentIds.STATEFUL_CALLBACK, name = "statefulCallback", active = true, resourceBundleLocation = "bundles.languageBundle", localeID = "en_US")
public class StatefulCallback implements CallbackComponent {
	private Logger log = Logger.getLogger(StatefulCallback.class.getName());
    @Override
    public Object handle(final Message<Event, Object> message) {
        log.info(message.getMessageBody().toString());
		return "StatefulCallback - hello";
	}

}
