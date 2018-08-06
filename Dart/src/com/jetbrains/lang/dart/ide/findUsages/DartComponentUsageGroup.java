/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jetbrains.lang.dart.ide.findUsages;

import com.google.dart.server.utilities.general.ObjectUtilities;
import com.intellij.navigation.NavigationItemFileStatus;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.usageView.UsageInfo;
import com.intellij.usages.UsageGroup;
import com.intellij.usages.UsageView;
import com.jetbrains.lang.dart.psi.DartComponent;
import com.jetbrains.lang.dart.psi.DartComponentName;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

class DartComponentUsageGroup implements UsageGroup, DataProvider {
  private final VirtualFile myFile;
  private final SmartPsiElementPointer<DartComponent> myElementPointer;
  private final String myText;
  private final Icon myIcon;

  public DartComponentUsageGroup(@NotNull DartComponent element) {
    myFile = element.getContainingFile().getVirtualFile();
    myText = StringUtil.notNullize(element.getName());
    myElementPointer = SmartPointerManager.getInstance(element.getProject()).createSmartPsiElementPointer(element);
    myIcon = element.getIcon(Iconable.ICON_FLAG_VISIBILITY | Iconable.ICON_FLAG_READ_STATUS);
  }

  @Override
  public boolean canNavigate() {
    return isValid();
  }

  @Override
  public boolean canNavigateToSource() {
    return canNavigate();
  }

  @Override
  public int compareTo(@NotNull UsageGroup usageGroup) {
    return getText(null).compareToIgnoreCase(usageGroup.getText(null));
  }

  public boolean equals(Object object) {
    if (object instanceof DartComponentUsageGroup) {
      final DartComponentUsageGroup other = (DartComponentUsageGroup)object;
      return ObjectUtilities.equals(other.myFile, myFile) && ObjectUtilities.equals(other.myText, myText);
    }
    return false;
  }

  @Nullable
  @Override
  public Object getData(@NotNull @NonNls String dataId) {
    if (CommonDataKeys.PSI_ELEMENT.is(dataId)) {
      final DartComponentName nameElement = getNameElement();
      if (nameElement != null) {
        return nameElement;
      }
    }
    if (UsageView.USAGE_INFO_KEY.is(dataId)) {
      final DartComponentName nameElement = getNameElement();
      if (nameElement != null) {
        return new UsageInfo(nameElement);
      }
    }
    return null;
  }

  @Override
  public FileStatus getFileStatus() {
    return isValid() ? NavigationItemFileStatus.get(getComponentElement()) : null;
  }

  @Override
  public Icon getIcon(boolean isOpen) {
    return myIcon;
  }

  @Override
  @NotNull
  public String getText(UsageView view) {
    return myText;
  }

  public int hashCode() {
    return myText.hashCode();
  }

  @Override
  public boolean isValid() {
    DartComponent componentElement = getComponentElement();
    return componentElement != null && componentElement.isValid();
  }

  @Override
  public void navigate(boolean focus) throws UnsupportedOperationException {
    final DartComponentName nameElement = getNameElement();
    if (nameElement != null && nameElement.isValid()) {
      nameElement.navigate(focus);
    }
  }

  @Override
  public void update() {
  }

  private DartComponent getComponentElement() {
    return myElementPointer.getElement();
  }

  private DartComponentName getNameElement() {
    final DartComponent componentElement = getComponentElement();
    return componentElement != null ? componentElement.getComponentName() : null;
  }
}
