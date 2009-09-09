@XmlJavaTypeAdapters(
        value = {
        @XmlJavaTypeAdapter(type = IGeneralBook.class, value = BookAdapter.class),
        @XmlJavaTypeAdapter(type = IBookGroup.class, value = GroupAdapter.class)
                }
) package comfort.books.general.bookloader;

import comfort.books.IBookGroup;
import comfort.books.general.IGeneralBook;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;