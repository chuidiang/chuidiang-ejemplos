package com.chuidiang.examples.drag_and_drop;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author fjabellan 26/10/2023
 */
public class JTextFieldDropTargetListener implements DropTargetListener {
    private final JTextField textField;

    public JTextFieldDropTargetListener(JTextField textField){
        this.textField = textField;
        textField.paste();
    }
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("drag enter");
        Transferable transferable = dtde.getTransferable();
        DataFlavor[] transferDataFlavors = transferable.getTransferDataFlavors();
        for (DataFlavor transferDataFlavor : transferDataFlavors) {
            System.out.println(transferDataFlavor.getMimeType());
            if (transferDataFlavor.isFlavorJavaFileListType()){
                try {
                    System.out.println(transferable.getTransferData(transferDataFlavor));
                } catch (UnsupportedFlavorException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
//        System.out.println("drag over");
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
//        System.out.println("drop changed");
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
//        System.out.println("drag exit");
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
//        System.out.println("drop");
    }
}
