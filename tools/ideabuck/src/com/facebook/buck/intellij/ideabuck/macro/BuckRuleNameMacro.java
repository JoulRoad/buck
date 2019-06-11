/*
 * Copyright 2019-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.facebook.buck.intellij.ideabuck.macro;

import com.intellij.openapi.actionSystem.DataContext;
import org.jetbrains.annotations.Nullable;

/**
 * For a selection that resolves to a {@link
 * com.facebook.buck.intellij.ideabuck.api.BuckTargetPattern}, expands to the target's rule.
 *
 * <p>Example: for {@code foo//bar/baz:qux/file.txt}, returns {@code qux/file.txt}.
 */
public class BuckRuleNameMacro extends AbstractBuckTargetPatternMacro {

  @Override
  public String getName() {
    return "BuckRuleName";
  }

  @Override
  public String getDescription() {
    return "Relative path to the selected file from its build file"
        + " (or from the cell root, if no build file exists) to the selected file.";
  }

  @Nullable
  @Override
  public String expand(DataContext dataContext) {
    return expandToTargetPattern(dataContext)
        .map(pattern -> pattern.getRuleName().orElse(""))
        .orElse(null);
  }
}