package com.intellij.dts.inspections

class DtsPropertyEnumInspectionTest : DtsInspectionTest(DtsPropertyEnumInspection::class) {
  override fun getBasePath(): String = "inspections/propertyEnum"

  override fun setUp() {
    super.setUp()
    addZephyr()
  }

  fun `test valid`() = doTest()
  fun `test invalid`() = doTest()
}