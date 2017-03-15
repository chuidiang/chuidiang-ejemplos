package com.chuidiang.examples.git_log;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.*;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.MessageRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.SideBandOutputStream;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JAVIER on 18/02/2017.
 */
public class Main {
    public static String GIT_REPOSITORY="D:/JAVIER/PROYECTOS/vts/.git";

    public static void main(String[] args) throws Exception {
        TheWindow window = new TheWindow();
        window.setVisible(true);
        HtmlFormat format = new HtmlFormat();
        format.open("salida.html");
        GitAnalyzer analyzer = new GitAnalyzer(GIT_REPOSITORY,"develop");
        for (int i=1;i<400;i++) {
            analyzer.analyze(format, "(?i)IMARE-"+i+"\\D", "IMARE-"+i);
        }
        format.close();
    }

}
