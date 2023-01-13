// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.astro

import org.jetbrains.astro.lang.sfc.AstroSfcHighlightingLexerTest
import org.jetbrains.astro.lang.sfc.AstroSfcLexerTest
import org.jetbrains.astro.lang.sfc.AstroSfcParserTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
  AstroSfcLexerTest::class,
  AstroSfcHighlightingLexerTest::class,
  AstroSfcParserTest::class,
)
class AstroTestSuite
