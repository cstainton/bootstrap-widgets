/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Reimplements, over TeaVM's JSO libraries, part of the GWT client API. Class,
 * method and package names follow GWT (https://github.com/gwtproject/gwt),
 * Copyright (C) The GWT Project Authors, licensed under the Apache License,
 * Version 2.0. No GWT source is included.
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
package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** Image widget rendered as an {@code img} element. */
public class Image extends Widget {

    public Image() {
        setElement(Document.get().createImageElement());
        setStyleName("gwt-Image");
    }

    public Image(final String url) {
        this();
        setUrl(url);
    }

    public String getUrl() {
        return getElement().getAttribute("src");
    }

    public void setUrl(final String url) {
        getElement().setAttribute("src", url);
    }

    public String getAltText() {
        return getElement().getAttribute("alt");
    }

    public void setAltText(final String altText) {
        getElement().setAttribute("alt", altText == null ? "" : altText);
    }
}
