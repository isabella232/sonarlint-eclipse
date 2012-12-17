/*
 * Sonar Eclipse
 * Copyright (C) 2010-2012 SonarSource
 * dev@sonar.codehaus.org
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
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.ide.eclipse.ui.internal.wizards.associate;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

public class TextCellEditorWithContentProposal extends TextCellEditor {

  public TextCellEditorWithContentProposal(Composite parent, IContentProposalProvider contentProposalProvider,
      ProjectAssociationModel sonarProject) {
    super(parent);

    enableContentProposal(contentProposalProvider, sonarProject);
  }

  private void enableContentProposal(IContentProposalProvider contentProposalProvider, ProjectAssociationModel sonarProject) {
    ContentProposalAdapter contentProposalAdapter = new ContentAssistCommandAdapter(
        text,
        new RemoteSonarProjectTextContentAdapter(sonarProject),
        contentProposalProvider,
        ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS,
        null,
        true);
    contentProposalAdapter.setAutoActivationCharacters(null);
  }
}
