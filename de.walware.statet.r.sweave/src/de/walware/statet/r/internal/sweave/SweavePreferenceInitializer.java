/*=============================================================================#
 # Copyright (c) 2007-2014 Stephan Wahlbrink (WalWare.de) and others.
 # All rights reserved. This program and the accompanying materials
 # are made available under the terms of the Eclipse Public License v1.0
 # which accompanies this distribution, and is available at
 # http://www.eclipse.org/legal/epl-v10.html
 # 
 # Contributors:
 #     Stephan Wahlbrink - initial API and implementation
 #=============================================================================*/

package de.walware.statet.r.internal.sweave;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import de.walware.ecommons.preferences.PreferencesUtil;

import de.walware.statet.r.internal.sweave.editors.SweaveEditorOptions;


public class SweavePreferenceInitializer extends AbstractPreferenceInitializer {
	
	
	public SweavePreferenceInitializer() {
	}
	
	
	@Override
	public void initializeDefaultPreferences() {
		final DefaultScope scope = new DefaultScope();
		PreferencesUtil.setPrefValue(scope, SweaveEditorOptions.MARKOCCURRENCES_ENABLED_PREF, Boolean.TRUE);
		PreferencesUtil.setPrefValue(scope, SweaveEditorOptions.PREF_SPELLCHECKING_ENABLED, Boolean.FALSE);
		
		final IEclipsePreferences node = scope.getNode(NewSweaveDocGenerateWizardPage.PREF_QUALIFIER);
		node.put(NewSweaveDocGenerateWizardPage.DEFAULT_NEWDOC_KEY, "ltx-rweave.NewDoc:Article"); //$NON-NLS-1$
	}
	
}
