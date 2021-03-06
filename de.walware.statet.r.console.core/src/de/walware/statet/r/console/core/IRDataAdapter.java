/*=============================================================================#
 # Copyright (c) 2009-2014 Stephan Wahlbrink (WalWare.de) and others.
 # All rights reserved. This program and the accompanying materials
 # are made available under the terms of the Eclipse Public License v1.0
 # which accompanies this distribution, and is available at
 # http://www.eclipse.org/legal/epl-v10.html
 # 
 # Contributors:
 #     Stephan Wahlbrink - initial API and implementation
 #=============================================================================*/

package de.walware.statet.r.console.core;

import de.walware.rj.services.RService;

import de.walware.statet.r.core.tool.IRConsoleService;


/**
 * Adapter interface for {@link RConsoleTool#R_DATA_FEATURESET_ID}.
 * 
 * Makes {@link RService} available.
 */
public interface IRDataAdapter extends IRBasicAdapter, IRConsoleService {
	
	
}
