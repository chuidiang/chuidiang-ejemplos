package com.chuidiang.doclet;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.util.DocTrees;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.tools.Diagnostic;
import java.util.*;

/**
 * @author Chuidiang
 * date 12/11/2023
 */
public class DocletExample implements Doclet {
    Reporter reporter;
    @Override
    public void init(Locale locale, Reporter reporter) {
        reporter.print(Diagnostic.Kind.NOTE, "Doclet using locale: " + locale);
        this.reporter = reporter;
    }

    @Override
    public boolean run(DocletEnvironment docEnv) {
        reporter.print(Diagnostic.Kind.NOTE, "myCustomOption: " + myCustomOption);
        // get the DocTrees utility class to access document comments
        DocTrees docTrees = docEnv.getDocTrees();
        final Set<? extends Element> specifiedElements = docEnv.getIncludedElements();
        specifiedElements.forEach(element -> {
            if (element.getKind().equals(ElementKind.MODULE)) {
                printEnclosedElements(docTrees, element);
            }
        });

        return true;
    }

    public void printEnclosedElements (DocTrees tree, Element e){
        System.out.println(e.getKind() +": "+e);
        printComments(tree, e);
        final List<? extends Element> enclosedElements = e.getEnclosedElements();
        enclosedElements.forEach(element -> {
            printEnclosedElements(tree, element);
        });

    }

    public void printComments(DocTrees trees, Element e) {
        DocCommentTree docCommentTree = trees.getDocCommentTree(e);
        if (docCommentTree != null) {
            System.out.println("  body comment : " + docCommentTree.getFullBody());
            System.out.println("  tags : " + docCommentTree.getBlockTags());
        } else {
            System.out.println("  No comments");
        }
    }

    @Override
    public String getName() {
        return "Example";
    }

    private String myCustomOption;

    @Override
    public Set<? extends Option> getSupportedOptions() {
        Option[] options = {
                new Option() {
                    private final List<String> someOption = Arrays.asList(
                            "-myCustomOption"
                    );

                    @Override
                    public int getArgumentCount() {
                        return 1;
                    }

                    @Override
                    public String getDescription() {
                        return "my custom option";
                    }

                    @Override
                    public Option.Kind getKind() {
                        return Option.Kind.STANDARD;
                    }

                    @Override
                    public List<String> getNames() {
                        return someOption;
                    }

                    @Override
                    public String getParameters() {
                        return "text";
                    }

                    @Override
                    public boolean process(String opt, List<String> arguments) {
                        myCustomOption = arguments.get(0);
                        System.out.println("My Custom Option : "+myCustomOption);
                        return true;
                    }
                }
        };
        return new HashSet<>(Arrays.asList(options));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        // support the latest release
        return SourceVersion.latest();
    }}
