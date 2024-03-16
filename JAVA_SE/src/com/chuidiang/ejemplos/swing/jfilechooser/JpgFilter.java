package com.chuidiang.ejemplos.swing.jfilechooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * @author Chuidiang
 * date 16/03/2024
 */
public class JpgFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return f.getName().endsWith(".jpg");
    }

    @Override
    public String getDescription() {
        return "Jpeg Filter";
    }
}
