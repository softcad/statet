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

package de.walware.statet.r.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;


// TODO: add all escape codes
public class RUtil {
	
	public static final Pattern BACKSLASH_PATTERN = Pattern.compile("\\\\"); //$NON-NLS-1$
	public static final String BACKSLASH_REPLACEMENT = "\\\\\\\\"; //$NON-NLS-1$
	public static final Pattern LINE_SEPARATOR_PATTERN = Pattern.compile("\\r[\\n]?|\\n"); //$NON-NLS-1$
	
	
	public static String escapeDoubleQuote(final String s) {
		if (s.indexOf('"') < 0) {
			return s;
		}
		final StringBuilder result = new StringBuilder(s.length()+1);
		boolean nextEscaped = false;
		for (int i = 0; i < s.length(); ) {
			final char c = s.charAt(i++);
			switch (c) {
			case '\\':
				nextEscaped = !nextEscaped;
				result.append(c);
				break;
			case '"':
				if (!nextEscaped) {
					result.append('\\');
				}
				//$FALL-THROUGH$
			default:
				nextEscaped = false;
				result.append(c);
				break;
			}
		}
		return result.toString();
	}
	
	public static String escapeBackslash(final String s) {
		if (s.indexOf('\\') < 0) {
			return s;
		}
		final StringBuilder result = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); ) {
			final char c = s.charAt(i++);
			switch (c) {
			case '\\':
				result.append("\\\\"); //$NON-NLS-1$
				break;
			default:
				result.append(c);
				break;
			}
		}
		return result.toString();
	}
	
	public static String escapeCompletely(final String s) {
		final StringBuilder result = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); ) {
			final char c = s.charAt(i++);
			switch (c) {
			case '\\':
			case '\'':
			case '"':
				result.append('\\');
				result.append(c);
				continue;
			default:
				result.append(c);
				continue;
			}
		}
		return result.toString();
	}
	
	public static String unescapeCompletely(final String s) {
		final StringBuilder result = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); ) {
			final char c = s.charAt(i++);
			if (c == '\\') {
				if (i < s.length()) {
					final char e = s.charAt(i++);
					switch(e) {
					case 'n':
						result.append('\n');
						continue;
					default:
						result.append(e);
						continue;
					}
				}
				else {
					break;
				}
			}
			else {
				result.append(c);
				continue;
			}
		}
		return result.toString();
	}
	
	
	public static String formatVarName(final String name) {
		return '\'' + name + '\'';
	}
	
	public static String formatVarNames(final Collection<String> names) {
		return formatVarNames(names, "", ", "); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public static String formatVarNames(final Collection<String> names,
			final String prefix, final String separator) {
		final int size= names.size();
		if (names.size() == 0) {
			return ""; //$NON-NLS-1$
		}
		final StringBuilder sb= new StringBuilder(size * (10 + prefix.length() + separator.length()));
		final Iterator<String> iter= names.iterator();
		if (prefix.isEmpty()) {
			sb.append('\'');
			sb.append(iter.next());
			sb.append('\'');
			while (iter.hasNext()) {
				sb.append(separator);
				sb.append('\'');
				sb.append(iter.next());
				sb.append('\'');
			}
		}
		else {
			sb.append(prefix);
			sb.append('\'');
			sb.append(iter.next());
			sb.append('\'');
			while (iter.hasNext()) {
				sb.append(separator);
				sb.append(prefix);
				sb.append('\'');
				sb.append(iter.next());
				sb.append('\'');
			}
		}
		return sb.toString();
	}
	
}
