package com.chuidiang.examples.git_log;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.PatchIdDiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.MessageRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JAVIER on 25/02/2017.
 */
public class GitAnalyzer {
    Repository repository;
    private final Ref branchRef;

    public GitAnalyzer(String gitRepository, String branch) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();

        repository = builder.setGitDir(new File(gitRepository))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        this.branchRef = repository.exactRef(branch);

    }
    public void analyze(HtmlFormat format, String issuePattern, String issueName) throws IOException {

        try (RevWalk walk = new RevWalk(repository)) {
            RevFilter filter = MessageRevFilter.create(issuePattern);
            walk.setRevFilter(filter);
            walk.markStart(walk.parseCommit(repository.resolve("HEAD")));

            Iterator<RevCommit> iterator = walk.iterator();
            if (!iterator.hasNext()) {
                return;
            }

            format.addIssue(issueName);
            while(iterator.hasNext()) {
                RevCommit commit = iterator.next();

                String commitNumber = commit.getId().getName();
                Date date = commit.getAuthorIdent().getWhen();
                String author = commit.getAuthorIdent().getName();
                String comment = commit.getFullMessage();

                ObjectId objectId = commit.getTree().getId();
                RevCommit parent = commit.getParent(0);
                PatchIdDiffFormatter formatter = new PatchIdDiffFormatter();
                formatter.setRepository(repository);

                ArrayList<String> files = new ArrayList<>();

                try {
                    List<DiffEntry> entries = formatter.scan(parent, objectId);

                    entries.forEach((entry) -> {
                        try {
                            FileHeader header = formatter.toFileHeader(entry);
                            files.add(header.getChangeType() + " " + getPath(header));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                format.addChange(commitNumber, date, author, comment, files.toArray(new String[]{}));
            }
            format.endIssue();
        } catch(Exception e) {
            format.endIssue();
        }
    }

    private String getPath (FileHeader header){
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

    @Override
    protected void finalize() throws Throwable {
        repository.close();
        super.finalize();
    }
}
