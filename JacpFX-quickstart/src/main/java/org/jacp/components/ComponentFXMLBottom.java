/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [ComponentFXMLBottom.java]
 * AHCP Project (http://jacp.googlecode.com/)
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
package org.jacp.components;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;


import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;

import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.FXUtil;

/**
 * A simple FXML component
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 *
 */
@DeclarativeView(initialTargetLayoutId = "PBottom", id = "id005", name = "componentBottom", active = true, viewLocation = "/fxml/ComponentBottomFXML.fxml", resourceBundleLocation = "bundles.languageBundle", localeID = "en_US")
public class ComponentFXMLBottom implements FXComponent {
	@FXML
	private TextField textField;
	private final Logger log = Logger.getLogger(ComponentFXMLBottom.class
			.getName());

	@Resource
	private Context context;
	@Override
	/**
	 * The handleAction method always runs outside the main application thread. You can create new nodes, execute long running tasks but you are not allowed to manipulate existing nodes here.
	 */
	public Node handle(final Message<Event, Object> action) {
		// runs in worker thread

		return null;
	}

	@Override
	/**
	 * The postHandleAction method runs always in the main application thread.
	 */
	public Node postHandle(final Node arg0,
			final Message<Event, Object> action) {
		// runs in FX application thread
		if (!action.getMessageBody().equals(FXUtil.MessageUtil.INIT)) {
			this.textField.setText(action.getMessageBody().toString());
		}
		return null;
	}

	@PostConstruct
	/**
	 * The @OnStart annotation labels methods executed when the component switch from inactive to active state
	 * @param arg0
	 * @param resourceBundle
	 */
	public void onStartComponent(final FXComponentLayout arg0,
			final ResourceBundle resourceBundle) {
		this.log.info("run on start of ComponentFXMLBottom ");

	}

	@PreDestroy
	/**
	 * The @OnTearDown annotations labels methods executed when the component is set to inactive
	 * @param arg0
	 */
	public void onTearDownComponent(final FXComponentLayout arg0) {
		this.log.info("run on tear down of ComponentFXMLBottom ");

	}

	@FXML
	private void handleSend(final ActionEvent event) {
		this.context.getEventHandler("id01.id004", "hello stateless component")
				.handle(event);
	}

}
