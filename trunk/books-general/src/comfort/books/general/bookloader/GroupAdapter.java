package comfort.books.general.bookloader;

import comfort.books.IBookGroup;

import javax.xml.bind.annotation.adapters.XmlAdapter;

class GroupAdapter extends XmlAdapter<BindedGroup, IBookGroup> {

    public IBookGroup unmarshal(BindedGroup v) throws Exception {
        return (IBookGroup) v;
    }

    public BindedGroup marshal(IBookGroup v) throws Exception {
        return (BindedGroup) v;
    }
}