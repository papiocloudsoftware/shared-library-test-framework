package com.papiocloud.sharedlibs.tests

import org.codehaus.groovy.control.CompilerConfiguration
import spock.lang.Specification

/**
 * Abstract var specification, extend with a custom implementation of MockJenkinsGlobals to
 * provide functionality not built into MockJenkinsGlobals
 *
 * It is recommended to create your own AbstractVarSpecification all other specifications extend
 * if using a custom implementation of MockJenkinsGlobals.
 *
 * @param <T>
 */
abstract class AbstractVarSpecification<T extends MockJenkinsGlobals> extends Specification {

    final T globals = Spy(this.globalsType())
    final Binding vars = loadAllVars()

    abstract Class<T> globalsType()

    private Binding loadAllVars() {
        Binding vars = new Binding()
        // Load all vars into the binding
        File varsReadme = new File(this.class.getResource("/vars.md").file)
        File varsDir = varsReadme.parentFile
        for (File var : varsDir.listFiles()) {
            if (var.name.endsWith(".groovy")) {
                String functionName = var.name - ".groovy"
                vars.setVariable(functionName, loadVar(var.text))
            }
        }
        return vars
    }

    def <VarType> VarType loadVar(Class<VarType> type) {
        // Convert the var class name to script name
        String scriptName = "/${type.simpleName.uncapitalize()}.groovy"
        Script script = loadVar(this.class.getResource(scriptName).text)
        // Cast to type
        return script.asType(type)
    }

    Script loadVar(String scriptText) {
        CompilerConfiguration compilerConfiguration = new CompilerConfiguration()
        compilerConfiguration.setScriptBaseClass(DelegatingScript.class.name)
        GroovyShell shell = new GroovyShell(vars, compilerConfiguration)
        DelegatingScript script = shell.parse(scriptText)
        script.setDelegate(this.globals)
        return script
    }

}
