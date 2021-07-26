package com.harismexis.creditscore.setup

import com.harismexis.creditscore.parser.MockCreditResponseProvider
import com.harismexis.creditscore.setup.util.InstrumentedFileReader

abstract class InstrumentedTestSetup {

    protected val mockProvider = MockCreditResponseProvider(InstrumentedFileReader())
}

