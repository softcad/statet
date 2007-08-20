/*******************************************************************************
 * Copyright (c) 2007 WalWare/StatET-Project (www.walware.de/goto/statet).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Wahlbrink - initial API and implementation
 *******************************************************************************/

package de.walware.eclipsecommons.ltk;

import org.eclipse.jface.text.AbstractDocument;


/**
 *
 */
public interface IWorkingBuffer {
	
	public AbstractDocument getDocument();
	public SourceContent getContent();
	public void saveDocument();
	public void releaseDocument();
	
}