/*=============================================================================#
 # Copyright (c) 2005-2014 Stephan Wahlbrink (WalWare.de) and others.
 # All rights reserved. This program and the accompanying materials
 # are made available under the terms of the Eclipse Public License v1.0
 # which accompanies this distribution, and is available at
 # http://www.eclipse.org/legal/epl-v10.html
 # 
 # Contributors:
 #     Stephan Wahlbrink - initial API and implementation
 #=============================================================================*/

package de.walware.statet.r.internal.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

import de.walware.ecommons.ICommonStatusConstants;
import de.walware.ecommons.ltk.ui.templates.TemplatesUtil.EvaluatedTemplate;
import de.walware.ecommons.text.TextUtil;
import de.walware.ecommons.ui.util.DialogUtil;

import de.walware.statet.ext.ui.wizards.NewElementWizard;

import de.walware.statet.r.codegeneration.CodeGeneration;
import de.walware.statet.r.core.RResourceUnit;
import de.walware.statet.r.core.model.IRSourceUnit;
import de.walware.statet.r.core.model.RModel;
import de.walware.statet.r.internal.ui.RUIPlugin;
import de.walware.statet.r.ui.RUI;


public class NewRFileCreationWizard extends NewElementWizard {
	
	
	private static class NewRFileCreator extends NewFileCreator {
		
		public NewRFileCreator(final IPath containerPath, final String resourceName) {
			super(containerPath, resourceName);
		}
		
		@Override
		public String getContentType(final IFile newFileHandle) {
			return IRSourceUnit.R_CONTENT;
		}
		
		@Override
		protected String getInitialFileContent(final IFile newFileHandle) {
			final String lineDelimiter = TextUtil.getLineDelimiter(newFileHandle.getProject());
			try {
				final RResourceUnit rcu = RResourceUnit.createTempUnit(newFileHandle,
						RModel.TYPE_ID, IRSourceUnit.RD_CONTENT );
				final EvaluatedTemplate data = CodeGeneration.getNewRFileContent(rcu, lineDelimiter);
				if (data != null) {
					fInitialSelection = data.getRegionToSelect();
					return data.getContent();
				}
			}
			catch (final CoreException e) {
				RUIPlugin.logError(ICommonStatusConstants.INTERNAL_TEMPLATE, "An error occured when applying template to new R script file.", e); //$NON-NLS-1$
			}
			return null;
		}
	}
	
	
	private NewRFileCreationWizardPage fFirstPage;
	private NewFileCreator fNewRFile;
	
	
	public NewRFileCreationWizard() {
		setDialogSettings(DialogUtil.getDialogSettings(RUIPlugin.getDefault(), "NewElementWizard")); //$NON-NLS-1$
		setDefaultPageImageDescriptor(RUI.getImageDescriptor(RUIPlugin.IMG_WIZBAN_NEWRFILE));
		setWindowTitle(Messages.NewRScriptFileWizard_title);
	}
	
	@Override
	public void addPages() {
		super.addPages();
		fFirstPage = new NewRFileCreationWizardPage(getSelection());
		addPage(fFirstPage);
	}
	
	@Override
	protected ISchedulingRule getSchedulingRule() {
		final ISchedulingRule rule = createRule(fNewRFile.getFileHandle());
		if (rule != null) {
			return rule;
		}
		
		return super.getSchedulingRule();
	}
	
	@Override
	public boolean performFinish() {
		// befor super, so it can be used in getSchedulingRule
		fNewRFile = new NewRFileCreator(
				fFirstPage.fResourceGroup.getContainerFullPath(),
				fFirstPage.fResourceGroup.getResourceName() );
		
		final boolean result = super.performFinish();
		
		if (result && fNewRFile.getFileHandle() != null) {
			// select and open file
			selectAndReveal(fNewRFile.getFileHandle());
			openResource(fNewRFile);
		}
		
		return result;
	}
	
	@Override
	protected void doFinish(final IProgressMonitor monitor) throws InterruptedException, CoreException, InvocationTargetException {
		try {
			monitor.beginTask("Create new file...", 1000); //$NON-NLS-1$
			
			fNewRFile.createFile(new SubProgressMonitor(monitor, 900) );
			
			fFirstPage.saveSettings();
			monitor.worked(100);
		}
		finally {
			monitor.done();
		}
	}
	
}
