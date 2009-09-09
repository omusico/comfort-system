package comfort.client.ui.binding;

import java.util.Iterator;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 28.01.2008
 * Time: 2:34:17
 */
public class SpecialIterator<T> implements Iterator<T> {
    private List list;

    public boolean hasNext() {
        return list.iterator().hasNext();
    }

    public T next() {
        return (T) list.iterator().next();
    }

    public void remove() {
        list.iterator().remove();
    }

    public SpecialIterator(List list) {
        this.list = list;
    }
}
