package comfort.books.general.bookloader;

import comfort.books.general.IGeneralBook;

import javax.xml.bind.annotation.adapters.XmlAdapter;

class BookAdapter extends XmlAdapter<BindedBook, IGeneralBook> {

    public IGeneralBook unmarshal(BindedBook v) throws Exception {
        return (IGeneralBook) v;
    }

    public BindedBook marshal(IGeneralBook v) throws Exception {
        return (BindedBook) v;
    }
}