package comfort.books.general;

import comfort.bpm.IUI;
import comfort.bpm.ITransition;

/**
 * Реализует IUI
 */
class UI implements IUI {
    private IBookViewer bookViewer;

    public UI(IBookViewer bookViewer) {
        this.bookViewer = bookViewer;
    }

    public void show(String frameName, String processName, String superProcessName, ITransition transition) {
        ProcessNameResolver.Result res = ProcessNameResolver.resolve(processName, superProcessName);
        String bookName = res.book;
        bookViewer.show(frameName, bookName, transition);
    }
}