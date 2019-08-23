package com.chuidiang.examples.luciad.lsp.interactive_label;

import com.chuidiang.examples.luciad.lsp.bussines_dada_2.MyData;
import com.luciad.view.lightspeed.TLspContext;
import com.luciad.view.lightspeed.controller.manipulation.ALspInteractiveLabelProvider;
import com.luciad.view.lightspeed.painter.label.TLspLabelID;

import javax.swing.*;
import java.awt.*;

public class InteractiveLabelProvider extends ALspInteractiveLabelProvider {

    private JButton jbutton = new JButton();

    public InteractiveLabelProvider(){


    }
    @Override
    public boolean canStartInteraction(TLspLabelID tLspLabelID, TLspContext tLspContext) {
        return tLspLabelID.getDomainObject() instanceof MyData;
    }

    @Override
    public Component startInteraction(TLspLabelID tLspLabelID, TLspContext tLspContext) {
        jbutton.setText(tLspLabelID.getDomainObject().toString());
        fireInteractionStartedEvent(tLspLabelID,tLspContext,jbutton);
        return jbutton;
    }

    @Override
    public boolean canStopInteraction() {
        return true;
    }

    @Override
    public boolean stopInteraction() {

        fireInteractionStoppedEvent();
        return true;
    }

    @Override
    public void cancelInteraction() {
        fireInteractionCancelledEvent();
    }
}
