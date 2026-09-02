package org.gwtbootstrap5.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap5.client.ui.base.HasPaginationSize;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.PaginationSize;

public class Pagination extends ElementPanel implements HasPaginationSize {

    public Pagination() {
        super("ul");
        addStyleName("pagination");
    }

    public void setLarge(boolean large) {
        setStyleName("pagination-lg", large);
    }

    public void setSmall(boolean small) {
        setStyleName("pagination-sm", small);
    }

    @Override
    public void setPaginationSize(PaginationSize paginationSize) {
        StyleHelper.addUniqueEnumStyleName(this, PaginationSize.class, paginationSize == null ? PaginationSize.NONE : paginationSize);
    }

    @Override
    public PaginationSize getPaginationSize() {
        return PaginationSize.fromStyleName(getStyleName());
    }

    @Override
    public void add(Widget child) {
        decoratePageItem(child);
        super.add(child);
    }

    public AnchorListItem addPreviousLink() {
        AnchorListItem listItem = pageListItem("«");
        insert(listItem, 0);
        return listItem;
    }

    public AnchorListItem addNextLink() {
        AnchorListItem listItem = pageListItem("»");
        add(listItem);
        return listItem;
    }

    public void rebuild(final SimplePager pager) {
        clear();

        if (pager.getPageCount() == 0) {
            return;
        }

        final AnchorListItem previous = addPreviousLink();
        previous.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pager.previousPage();
                updatePaginationState(pager);
            }
        });
        previous.setEnabled(pager.hasPreviousPage());

        for (int i = 0; i < pager.getPageCount(); i++) {
            final int pageIndex = i;
            final AnchorListItem page = pageListItem(String.valueOf(i + 1));
            page.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    pager.setPage(pageIndex);
                    updatePaginationState(pager);
                }
            });
            page.setActive(i == pager.getPage());
            add(page);
        }

        final AnchorListItem next = addNextLink();
        next.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pager.nextPage();
                updatePaginationState(pager);
            }
        });
        next.setEnabled(pager.hasNextPage());
    }

    private AnchorListItem pageListItem(String text) {
        AnchorListItem item = new AnchorListItem(text, "#");
        decoratePageItem(item);
        return item;
    }

    private void decoratePageItem(Widget child) {
        if (child instanceof PageItem) {
            return;
        }
        child.addStyleName("page-item");
        if (child instanceof AnchorListItem) {
            ((AnchorListItem) child).getAnchor().addStyleName("page-link");
        }
    }

    private void updatePaginationState(final SimplePager pager) {
        for (int i = 0; i < getWidgetCount(); i++) {
            Widget widget = getWidget(i);
            if (!(widget instanceof AnchorListItem)) {
                continue;
            }
            AnchorListItem item = (AnchorListItem) widget;
            if (i == 0) {
                item.setEnabled(pager.hasPreviousPage());
            } else if (i == getWidgetCount() - 1) {
                item.setEnabled(pager.hasNextPage());
            } else {
                item.setActive(i - 1 == pager.getPage());
            }
        }
    }
}
