package org.jenkinsci.plugins.uicontrol.demo;

import hudson.model.RootAction;

/**
 * @author Kohsuke Kawaguchi
 */
public class Demo implements RootAction {
    @Override
    public String getIconFileName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Demo";
    }

    @Override
    public String getUrlName() {
        return "ui-control";
    }
}
