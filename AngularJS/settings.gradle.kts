// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
buildscript {
  repositories {
    mavenCentral()
    maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
    maven("https://www.jetbrains.com/intellij-repository/snapshots")
    maven("https://packages.jetbrains.team/maven/p/grazi/grazie-platform-public")
    maven("https://download.jetbrains.com/teamcity-repository")
  }
}
