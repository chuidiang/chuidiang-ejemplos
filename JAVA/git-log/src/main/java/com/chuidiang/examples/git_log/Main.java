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
import java.util.Date;
import java.util.List;

/**
 * Created by JAVIER on 18/02/2017.
 */
public class Main {
    public static String GIT_REPOSITORY="D:/JAVIER/PROYECTOS/vts/.git";

    public static void main(String[] args) throws Exception {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File(GIT_REPOSITORY))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        Ref develop = repository.exactRef("develop");

        RevWalk walk = new RevWalk(repository);
        RevFilter filter = MessageRevFilter.create("(?i)IMARE-23.");
        walk.setRevFilter(filter);
        walk.markStart(walk.parseCommit(repository.resolve("HEAD")));
        walk.forEach((commit)->{
            System.out.println(commit.getAuthorIdent().getWhen()+": "+commit.getAuthorIdent().getName());
            System.out.println(commit.getFullMessage());git
            ObjectId objectId = commit.getTree().getId();
            RevCommit parent = commit.getParent(0);
            PatchIdDiffFormatter formatter = new PatchIdDiffFormatter();
            formatter.setRepository(repository);
            try {
                List<DiffEntry> entries = formatter.scan(parent,objectId);

                entries.forEach((entry)-> {
                    try {
                        FileHeader header = formatter.toFileHeader(entry);
                        System.out.println(header.getChangeType()+" "+getPath(header));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------------------------------");
        });

    }

    private static String getPath (FileHeader header){
        if (DiffEntry.ChangeType.DELETE.equals(header.getChangeType())){
            return header.getOldPath();
        }
        if (DiffEntry.ChangeType.ADD.equals(header.getChangeType())){
            return header.getNewPath();
        }
        if (header.getOldPath().equals(header.getNewPath())){
            return header.getOldPath();
        }
        return (header.getOldPath()+" -> "+header.getNewPath());
    }
}
