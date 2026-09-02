/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.gwtbootstrap3.teavm.demo;

import org.gwtbootstrap3.teavm.ui.Button;
import org.gwtbootstrap3.teavm.ui.Column;
import org.gwtbootstrap3.teavm.ui.Container;
import org.gwtbootstrap3.teavm.ui.Heading;
import org.gwtbootstrap3.teavm.ui.Mount;
import org.gwtbootstrap3.teavm.ui.Paragraph;
import org.gwtbootstrap3.teavm.ui.Row;

/**
 * TeaVM compile smoke app for the widget backend prototype.
 */
public final class TeaVmSmokeApp {

    private TeaVmSmokeApp() {
    }

    public static void main(final String[] args) {
        final Container container = new Container();
        container.add(new Heading(1, "GWT Bootstrap Modern TeaVM"));
        container.add(new Paragraph("TeaVM-rendered Bootstrap widgets using the non-GWT DOM backend."));

        final Row row = new Row();
        row.add(Column.md(6).add(new Button("Primary action")));
        row.add(Column.md(6).add(new Paragraph("This is compiled by TeaVM, not the GWT compiler.")));
        container.add(row);

        Mount.toBody(container);
    }
}
