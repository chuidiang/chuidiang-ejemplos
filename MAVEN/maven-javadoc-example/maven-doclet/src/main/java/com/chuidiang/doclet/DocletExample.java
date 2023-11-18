package com.chuidiang.doclet;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.util.DocTrees;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
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
        final Set<? extends Element> specifiedElements = docEnv.getSpecifiedElements();
        specifiedElements.forEach(element -> {
            printEnclosedElements(docTrees, element);
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
    /**
     * Una descripción de para qué sirve nuestra opción para enseñársela al usuario.
     * @return
     */
    @Override
    public String getDescription() {
        return "my custom option";
    }

    /**
     * Qué tipo de opcion. Hay STANDARD, EXTENDED y OTHER. Habitualmente usaremos
     * STANDARD
     * @return
     */
    @Override
    public Option.Kind getKind() {
        return Option.Kind.STANDARD;
    }

    /**
     * Debemos devolver todos los pasibles alias para nuestra opción. Por ejemplo
     * puede ser -myCustomOption o su forma abreviada "-co" o su forma expandida
     * "-my-custom-option"
     */
    @Override
    public List<String> getNames() {
        return Arrays.asList(
                "-myCustomOption", "-co", "-my-custom-option");
    }

    /**
     * Indicamos cuantos valores lleva detrás nuestra opción. Por ejemplo, si en
     * línea de comandos ponemos
     * javadoc -co valor1 valor2 valor3
     * y en este método devolvermos 1, solo nos pasarán valor1 como parte de nuestra opción.
     * Si ponemos 2, nos pasaraán valor1 y valor2, etc.
     * @return
     */
    @Override
    public int getArgumentCount() {
        return 1;
    }

    /**
     * Una descripción de qué tipo de valor esperamos para nuestra opción. Algo com "path de los
     * fuentes", "autor", "comentario". Es para mostrarle al usuario y que sepa qué tipo de dato
     * espera nuestra opción.
     * @return
     */
    @Override
    public String getParameters() {
        return "any text";
    }

    /**
     * Cuando se ejecuta el comando javadoc, nos pasarán aquí para cada una de nuestras
     * opciones los valores que el usuario ha escrito.
     * Debemos guardarlos para tenerlos disponibles y usarlos como hayamos previsto y
     * devolver true si está todo bien.
     * @param opt
     * @param arguments
     * @return
     */
    @Override
    public boolean process(String opt, List<String> arguments) {
        if (Arrays.asList(
                "-myCustomOption", "-co", "-my-custom-option").contains(opt)){
            myCustomOption = arguments.get(0);
            System.out.println("My Custom Option : "+myCustomOption);
        }
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
