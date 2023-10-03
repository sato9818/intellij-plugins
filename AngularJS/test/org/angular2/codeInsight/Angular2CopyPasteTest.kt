// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.angular2.codeInsight

import com.intellij.webSymbols.configureAndCopyPaste
import com.intellij.webSymbols.performCopyPaste
import org.angular2.Angular2TestCase
import org.angular2.Angular2TestModule

class Angular2CopyPasteTest : Angular2TestCase("copyPaste") {

  fun testBasic() {
    doTest("html", "html")
  }

  fun testBasicToInjected() {
    doTest("html", "ts")
  }

  fun testInjected() {
    doTest("ts", "ts")
  }

  fun testInjectedToBasic() {
    doTest("ts", "html")
  }

  fun testExpression() {
    doTest("html", "html")
  }

  fun testExpressionFromInjected() {
    doTest("ts", "html")
  }

  fun testExpressionToInjected() {
    doTest("html", "ts")
  }

  fun testExpressionToHtml() {
    doTest("html", "html")
  }

  fun testHtmlToExpression() {
    doTest("html", "html")
  }

  fun testNgFor() {
    doTest("html", "html")
  }

  private fun doTest(srcExt: String, destExt: String) {
    doConfiguredTest(Angular2TestModule.ANGULAR_CORE_13_3_5,
                     Angular2TestModule.ANGULAR_COMMON_13_3_5,
                     Angular2TestModule.ANGULAR_CDK_14_2_0,
                     configureFile = false, dir = true, checkResult = true) {
      configureAndCopyPaste("source.component.$srcExt", "destination.component.$destExt")
    }
  }

}