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
package org.sonarlint.eclipse.ui.internal.util;

import java.util.function.Supplier;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Display;

public class DisplayUtils {

  @Nullable
  public static <T> T syncExec(Supplier<T> supplier) {
    var runnable = new RunnableWithResult<>(supplier);
    Display.getDefault().syncExec(runnable);
    return runnable.getResult();
  }

  private static class RunnableWithResult<T> implements Runnable {

    private final Supplier<T> supplier;
    @Nullable
    private T result;

    private RunnableWithResult(Supplier<T> supplier) {
      this.supplier = supplier;
    }

    public T getResult() {
      return result;
    }

    @Override
    public void run() {
      result = supplier.get();
    }
  }

  private DisplayUtils() {
    // utility class
  }

}
