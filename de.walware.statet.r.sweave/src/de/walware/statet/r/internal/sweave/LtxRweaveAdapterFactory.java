/*=============================================================================#
 # Copyright (c) 2011-2014 Stephan Wahlbrink (WalWare.de) and others.
 # All rights reserved. This program and the accompanying materials
 # are made available under the terms of the Eclipse Public License v1.0
 # which accompanies this distribution, and is available at
 # http://www.eclipse.org/legal/epl-v10.html
 # 
 # Contributors:
 #     Stephan Wahlbrink - initial API and implementation
 #=============================================================================*/

package de.walware.statet.r.internal.sweave;

import org.eclipse.core.runtime.IAdapterFactory;

import de.walware.docmlet.tex.core.TexBuildParticipant;
import de.walware.docmlet.tex.core.source.LtxHeuristicTokenScanner;

import de.walware.statet.r.core.rsource.RHeuristicTokenScanner;
import de.walware.statet.r.internal.sweave.model.LtxRweaveTexBuildParticipant;
import de.walware.statet.r.sweave.text.Rweave;
import de.walware.statet.r.sweave.text.RweaveChunkHeuristicScanner;


public class LtxRweaveAdapterFactory implements IAdapterFactory {
	
	
	private static final Class<?>[] ADAPTERS = new Class<?>[] {
		RHeuristicTokenScanner.class,
		LtxHeuristicTokenScanner.class,
		TexBuildParticipant.class,
	};
	
	
	public LtxRweaveAdapterFactory() {
	}
	
	
	@Override
	public Class<?>[] getAdapterList() {
		return ADAPTERS;
	}
	
	@Override
	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
		if (RHeuristicTokenScanner.class.equals(adapterType)) {
			return new RweaveChunkHeuristicScanner();
		}
		if (LtxHeuristicTokenScanner.class.equals(adapterType)) {
			return new LtxHeuristicTokenScanner(Rweave.LTX_PARTITIONING_CONFIG);
		}
		if (TexBuildParticipant.class.equals(adapterType)) {
			return new LtxRweaveTexBuildParticipant();
		}
		return null;
	}
	
}
