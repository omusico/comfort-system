package comfort.ui;

import java.io.Reader;

/**
 * Элемент страницы.
 */
public class Widget {
    public Widget(String name, String code) {
        this.name = name;
        this.code = code;
        this.codeReader = null;
    }

    public Widget(String name, Reader codeReader) {
        this.name = name;
        this.codeReader = codeReader;
        this.code = null;
    }

    private String name;

    /**
     * Имя элемента.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    private String code;

    /**
     * Код элемента.
     * Содержит некие данные, определяющие, каким образом следует отображать этот элемент.
     * @return код в виде строки, или null.
     * В случае null, нужно использовать {@link #getCodeReader()}.
     */
    public String getCode() {
        return code;
    }

    private Reader codeReader;

    /**
     * {@link java.io.Reader} кода элемента.
     * @return {@link java.io.Reader}, который может использоваться для чтения кода,
     * или null.
     * В случае null, нужно использовать {@link #getCode()}.
     * @see #getCode() 
     */
    public Reader getCodeReader() {
        return codeReader;
    }
}