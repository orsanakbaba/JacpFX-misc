/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [ComponentTop.java]
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

import java.util.logging.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.message.MessageImpl;
import org.jacpfx.rcp.util.FXUtil;
import sun.misc.Contended;


/**
 * Programmatic TopComponent
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 *
 */
@View(initialTargetLayoutId = "PTop", id = "id006", name = "componentTop", active = true, resourceBundleLocation = "bundles.languageBundle", localeID = "en_US")
public class ComponentTop implements FXComponent {
	private AnchorPane pane;
	private TextField textField;
	private final Logger log = Logger.getLogger(ComponentTop.class.getName());

	@Resource
	Context context;
	@Override
	/**
	 * The handleAction method always runs outside the main application thread. You can create new nodes, execute long running tasks but you are not allowed to manipulate existing nodes here.
	 */
	public Node handle(final Message<Event, Object> action) {
		// runs in worker thread
		if (action.getMessageBody().equals(FXUtil.MessageUtil.INIT)) {
			return this.createUI();
		}
		return null;
	}

	@Override
	/**
	 * The postHandleAction method runs always in the main application thread.
	 */
	public Node postHandle(final Node arg0,
			final Message<Event, Object> action) {
		// runs in FX application thread
		if (action.getMessageBody().equals(FXUtil.MessageUtil.INIT)) {
			this.pane = (AnchorPane) arg0;
		} else {
			this.textField.setText(action.getMessageBody().toString());
		}
		return this.pane;
	}

	@PostConstruct
	/**
	 * The @OnStart annotation labels methods executed when the component switch from inactive to active state
	 * @param arg0
	 */
	public void onStartComponent(final FXComponentLayout arg0) {
		this.log.info("run on start of ComponentTop ");

	}

	@PreDestroy
	/**
	 * The @OnTearDown annotations labels methods executed when the component is set to inactive
	 * @param arg0
	 */
	public void onTearDownComponent(final FXComponentLayout arg0) {
		this.log.info("run on tear down of ComponentTop ");

	}

	/**
	 * create the UI on first call
	 * 
	 * @return
	 */
	private Node createUI() {
		final AnchorPane anchor = AnchorPaneBuilder.create()
				.styleClass("roundedAnchorPaneFX").build();
		final Label heading = LabelBuilder.create()
				.text(context.getResourceBundle().getString("javafxCompTop"))
				.alignment(Pos.CENTER).styleClass("propLabel").build();
		final Button top = ButtonBuilder.create()
				.text(context.getResourceBundle().getString("send")).layoutX(120)
				.onMouseClicked(this.getEventHandler()).alignment(Pos.CENTER)
				.build();
		this.textField = TextFieldBuilder.create().text("")
				.styleClass("propTextField").alignment(Pos.CENTER).build();

		AnchorPane.setBottomAnchor(top, 25.0);
		AnchorPane.setRightAnchor(top, 25.0);

		AnchorPane.setRightAnchor(heading, 50.0);
		AnchorPane.setTopAnchor(heading, 10.0);

		AnchorPane.setTopAnchor(this.textField, 50.0);
		AnchorPane.setRightAnchor(this.textField, 25.0);

		anchor.getChildren().addAll(heading, top, this.textField);

		GridPane.setHgrow(anchor, Priority.ALWAYS);
		GridPane.setVgrow(anchor, Priority.ALWAYS);

		return anchor;
	}

	private EventHandler<Event> getEventHandler() {
		return new EventHandler<Event>() {
			@Override
			public void handle(final Event arg0) {
				// fire component event "manually" with performAction(null) or
				// set event handler direct to component
				// onMouseClicked(getActionListener("id01.id003","hello stateful component").getListener())
				ComponentTop.this.context.send("id003", "hello stateful component");


			}
		};
	}

}
