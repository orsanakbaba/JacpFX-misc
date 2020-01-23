/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [Workbench.java]
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
package org.jacp.workbench;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import org.jacp.main.ApplicationLauncher;
import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.api.util.ToolbarPosition;
import org.jacpfx.controls.optionPane.JACPDialogButton;
import org.jacpfx.controls.optionPane.JACPDialogUtil;
import org.jacpfx.controls.optionPane.JACPOptionPane;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.menuBar.JACPMenuBar;
import org.jacpfx.rcp.components.modalDialog.JACPModalDialog;
import org.jacpfx.rcp.components.toolBar.JACPToolBar;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.FXUtil;
import org.jacpfx.rcp.workbench.FXWorkbench;

/**
 * A simple JacpFX workbench. Define basic UI settings like size, menus and
 * toolbars here.
 * 
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 * 
 */
@org.jacpfx.api.annotations.workbench.Workbench(name = "Workbench", id = "id0", perspectives = {"id01","id02"})
public class Workbench implements FXWorkbench, FXComponent {
	private Stage stage;

	@Resource
	Context context;

	@Override
	public void handleInitialLayout(
			final Message<Event, Object> action,
			final WorkbenchLayout<Node> layout, final Stage stage) {
		layout.setWorkbenchXYSize(1024, 600);
		layout.registerToolBar(ToolbarPosition.NORTH);
		layout.setStyle(StageStyle.DECORATED);
		layout.setMenuEnabled(true);
		this.stage = stage;

	}

	@Override
	public void postHandle(final FXComponentLayout layout) {
		final JACPMenuBar menu = layout.getMenu();
		final Menu menuFile = new Menu("File");
		final Menu menuStyles = new Menu("Styles");
		menuFile.getItems().add(getHelpItem());
		menuFile.getItems().add(getLangItem());
		// add style selection
		for (int i = 0; i < ApplicationLauncher.STYLES.length; i++) {
			menuStyles.getItems().add(getStyle(i));
		}

		menu.getMenus().addAll(menuFile, menuStyles);

		// define toolbars and menu entries
		final JACPToolBar toolbar = layout
				.getRegisteredToolBar(ToolbarPosition.NORTH);

		toolbar.add(getFXMLPerspectiveButton());
		toolbar.add(getPerspectiveButton());

		// show windowButtons
		menu.registerWindowButtons();
	}

	private MenuItem getLangItem() {
		final MenuItem menuItem = new MenuItem("ENG");
		menuItem.setOnAction(arg0 -> {
			//this.

		});
		return menuItem;

	}

	private Button getFXMLPerspectiveButton() {
		final Button perspectiveOne = new Button("FXMLPerspective");
		perspectiveOne.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent arg0) {
				Workbench.this.context.send("id01", "switch");
						//.performAction(arg0);

			}
		});
		return perspectiveOne;
	}

	private Button getPerspectiveButton() {
		final Button perspectiveTwo = new Button("Perspective");
		perspectiveTwo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent arg0) {
				Workbench.this.context.send("id02", "switch");
						//.performAction(arg0);

			}
		});
		return perspectiveTwo;
	}

	private MenuItem getStyle(final int count) {
		final MenuItem itemHelp = new MenuItem(count == 0 ? "Light" : "Dark");
		itemHelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent arg0) {
				final Scene scene = Workbench.this.stage.getScene();
				// index 0 is always the default JACP style
				scene.getStylesheets().remove(1);
				scene.getStylesheets().add(ApplicationLauncher.STYLES[count]);

			}
		});
		return itemHelp;
	}

	private MenuItem getHelpItem() {
		final MenuItem itemHelp = new MenuItem("Help");
		itemHelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent arg0) {
				// create a modal dialog
				final JACPOptionPane dialog = JACPDialogUtil.createOptionPane(
						"Help", "Add some help text ");
				dialog.setDefaultButton(JACPDialogButton.NO);
				dialog.setDefaultCloseButtonOrientation(Pos.CENTER_RIGHT);
				dialog.setOnYesAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(final ActionEvent arg0) {
						Workbench.this.context.hideModalDialog();
					}
				});
				Workbench.this.context.showModalDialog(dialog);

			}
		});
		return itemHelp;
	}

	@Override
	public Node postHandle(Node node, Message<Event, Object> message) throws Exception {
		return null;
	}

	@Override
	public Node handle(Message<Event, Object> message) throws Exception {
		return null;
	}
}
