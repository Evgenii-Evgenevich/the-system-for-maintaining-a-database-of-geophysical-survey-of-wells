package com.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 * Created by EE on 13.12.2017.
 */
@SpringUI
@Title("The SfMDoGSoWalls")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class MyVaadinUI extends UI {

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MyVaadinUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
    }

}
