package com.papiocloud.sharedlibs.tests

/**
 * This class is used to provide default implementations of any methods that are called within a var.
 *
 * Var specs can use this for expectations.  Var tests can also extend this class and add
 * more methods to it depending on the plugins they are using.  When doing so, extend
 * AbstractVarSpecification<YourMockJenkinsGlobals> instead of BaseVarSpecification and declare the type
 * using: final Class<YourMockJenkinsGlobals> globalsType = YourMockJenkinsGlobals.class
 */
class MockJenkinsGlobals {

    void echo(String message) {
        println message
    }

    void error(String error) {
        throw new RuntimeException(error)
    }

}
