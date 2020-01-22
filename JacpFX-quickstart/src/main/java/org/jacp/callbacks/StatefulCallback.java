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
package org.jacp.callbacks;

import javafx.event.Event;

import org.jacp.spring.services.SimpleSpringBean;
import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.CallbackComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.logging.Logger;



/**
 * A Stateful JacpFX component.
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 *
 */
@Component(id = "id003", name = "statefulCallback", active = false)
public class StatefulCallback implements CallbackComponent {
	private final Logger log = Logger.getLogger(StatefulCallback.class
			.getName());
	@Autowired
	@Qualifier(value="simpleSpringBean")
	private SimpleSpringBean simpleSpringBean;

	@Override
	public Object handle(final Message<Event, Object> arg0) {
		this.log.info(arg0.getMessageBody().toString());
		return "StatefulCallback - "+simpleSpringBean.sayHello();
	}

	@PostConstruct
	public void init() {
		this.log.info("StatefulCallback start");
	}

	@PreDestroy
	public void cleanup(){
		this.log.info("StatefulCallback stop");
	}

}
