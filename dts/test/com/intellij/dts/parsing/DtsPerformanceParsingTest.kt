package com.intellij.dts.parsing

import com.intellij.testFramework.PlatformTestUtil

class DtsPerformanceParsingTest : DtsParsingTestBase("") {
  fun testPerformance() {
    val file = loadFile("Performance.dts")

    PlatformTestUtil.startPerformanceTest("parser", 40000) {
      val root = createFile("file.dts", file)
      ensureParsed(root)
    }.assertTiming()
  }
}