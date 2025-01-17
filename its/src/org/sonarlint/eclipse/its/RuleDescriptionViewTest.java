/*
 * SonarLint for Eclipse ITs
 * Copyright (C) 2009-2022 SonarSource SA
 * sonarlint@sonarsource.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarlint.eclipse.its;

import org.eclipse.reddeer.eclipse.ui.perspectives.JavaPerspective;
import org.eclipse.reddeer.workbench.impl.editor.DefaultEditor;
import org.eclipse.reddeer.workbench.impl.editor.Marker;
import org.junit.Test;
import org.sonarlint.eclipse.its.reddeer.views.OnTheFlyView;
import org.sonarlint.eclipse.its.reddeer.views.RuleDescriptionView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class RuleDescriptionViewTest extends AbstractSonarLintTest {

  @Test
  public void openRuleDescription() {
    new JavaPerspective().open();
    var ruleDescriptionView = new RuleDescriptionView();
    ruleDescriptionView.open();
    var onTheFlyView = new OnTheFlyView();
    onTheFlyView.open();

    var project = importExistingProjectIntoWorkspace("java/java-simple", "java-simple");

    openFileAndWaitForAnalysisCompletion(project.getResource("src", "hello", "Hello.java"));

    var defaultEditor = new DefaultEditor();
    assertThat(defaultEditor.getMarkers())
      .extracting(Marker::getText, Marker::getLineNumber)
      .containsExactly(tuple("Replace this use of System.out or System.err by a logger.", 9));

    onTheFlyView.selectItem(0);
    ruleDescriptionView.open();

    assertThat(ruleDescriptionView.getContent()).contains("java:S106");
    assertThat(ruleDescriptionView.getContent()).contains("Sensitive data must only be logged securely");
    assertThat(ruleDescriptionView.getContent()).contains("CERT, ERR02-J");
  }

}
