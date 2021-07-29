package com.papiocloud.sharedlibs.tests

class TestVarSpecificationSpec extends BaseVarSpecification {

    static interface TestVar {
        def call()
    }

    TestVar testVar = loadVar(TestVar)

    def "can call testVar"() {
        when:
        testVar()

        then:
        1 * globals.echo("Test Var!")
    }

}
