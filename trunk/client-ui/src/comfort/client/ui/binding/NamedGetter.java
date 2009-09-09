package comfort.client.ui.binding;

import comfort.client.ui.interfaces.IGetter;
import comfort.client.ui.interfaces.INamed;
import comfort.exceptions.ProgrammerError;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 28.01.2008
 * Time: 2:51:37
 */
public abstract class NamedGetter<T> extends NamedElement implements IGetter<T> {
    protected List getInternalList() {
        throw ProgrammerError.general(this.getClass().getName() + ".getInternalList() not implemented");
    }

    private Iterator<T> iterator;
    private HashMap<String, T> map;

    public T get(int index) {
        return (T) getInternalList().get(index);
    }

    public T get(String name) {
        if (map == null) {
            map = new HashMap<String, T>();
            for (T obj: this){
                map.put(((INamed) obj).getName(), obj);
            }
        }

        return map.get(name);
    }

    public int getCount() {
        return getInternalList().size();
    }

    public Iterator<T> iterator() {
        if (iterator == null)
            iterator = new SpecialIterator<T>(getInternalList());
        return iterator;
    }

}
