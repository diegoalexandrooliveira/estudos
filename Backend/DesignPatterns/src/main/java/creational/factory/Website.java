package creational.factory;

import java.util.ArrayList;
import java.util.List;

public abstract class Website {

    private List<Page> pages = new ArrayList();

    public List<Page> getPages() {
        return pages;
    }

    public void addPage(Page page){
        this.pages.add(page);
    }

    public Website() {
        this.createWebsite();
    }

    protected abstract void createWebsite();


}
