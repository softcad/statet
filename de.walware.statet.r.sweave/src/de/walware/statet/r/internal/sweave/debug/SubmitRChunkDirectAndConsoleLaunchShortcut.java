/*=============================================================================#
 # Copyright (c) 2008-2014 Stephan Wahlbrink (WalWare.de) and others.
 # All rights reserved. This program and the accompanying materials
 # are made available under the terms of the Eclipse Public License v1.0
 # which accompanies this distribution, and is available at
 # http://www.eclipse.org/legal/epl-v10.html
 # 
 # Contributors:
 #     Stephan Wahlbrink - initial API and implementation
 #=============================================================================*/

package de.walware.statet.r.internal.sweave.debug;


/**
 * Launch shortcut, which submits the chunks (touched by selection)
 * and goes to the console.
 * 
 * Supports only text editors.
 */
public class SubmitRChunkDirectAndConsoleLaunchShortcut extends SubmitRChunkDirectLaunchShortcut {
	
	
	public SubmitRChunkDirectAndConsoleLaunchShortcut() {
		fGotoConsole = true;
	}
	
}
