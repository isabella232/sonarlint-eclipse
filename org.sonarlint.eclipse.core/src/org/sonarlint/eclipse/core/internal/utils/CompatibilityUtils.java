/*
 * SonarLint for Eclipse
 * Copyright (C) 2015-2022 SonarSource SA
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
package org.sonarlint.eclipse.core.internal.utils;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Version;

public class CompatibilityUtils {

  private CompatibilityUtils() {
    // utility class, forbidden constructor
  }

  public static boolean supportMarkerResolutionRelevance() {
    var eclipseUiIde = Platform.getBundle("org.eclipse.ui.ide");
    return eclipseUiIde != null && eclipseUiIde.getVersion().compareTo(Version.parseVersion("3.14")) >= 0;
  }

}
