package com.papiocloud.sharedlibs.tests

/**
 * Base var specification class, extend if using default MockJenkinsGlobals
 * class for mocking Jenkins global functionality.
 */
abstract class BaseVarSpecification extends AbstractVarSpecification<MockJenkinsGlobals> {

    @Override
    final Class<MockJenkinsGlobals> globalsType() {
        return MockJenkinsGlobals
    }

}
