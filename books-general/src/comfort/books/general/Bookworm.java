package comfort.books.general;

import comfort.books.IBookworm;
import comfort.books.ILibrary;
import comfort.bpm.IActionHandler;
import comfort.bpm.IStateMachine;
import comfort.bpm.IUI;
import comfort.exceptions.ProgrammerError;
import comfort.exceptions.UnexpectedException;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

class Bookworm implements IBookworm {
    Bookworm(IStateMachine stateMachine, IUI ui, Library library) {
        this.sm = stateMachine;
        this.ui = ui;
        this.library = library;
    }

    private IUI ui;
    private IStateMachine sm;
    private Library library;

    public ILibrary getLibrary() {
        return library;
    }

    public String readBook() {
        return readBook(null);
    }

    public String readBook(String book) {
		return readBook(book, null, ui);
	}

	public String readBook(String book, String processName) {
		return readBook(book, processName, ui);		
	}

	private String readBook(String name, String processName, IUI ui) {
        if (name == null) {
            name = library.getInitialBook();
            if (name == null || name.length() == 0)
                throw ProgrammerError.general("The initial book is not specified");
        }

        IGeneralBook book = library.getBook(name);
        if (book == null)
            throw ProgrammerError.general("The initial book '%s' is not found", name);

        if (processName == null)
            processName = book.getInitialProcess();

        if (processName == null || processName.length() == 0)
            throw ProgrammerError.general("An initial process is not specified for the book '%s'", name);

        return sm.run(book.getName() + '.' + processName,
				new ActionHandler(book), ui,
				library.getProcessDefinitionResolver(),
				library.getProcessDefinitionResolverData());
	}

	private class ActionHandler implements IActionHandler {
		public ActionHandler(IGeneralBook IGeneralBook) {
			ClassLoader loader;

			String classPath = IGeneralBook.getClassPath();
			if (classPath.equals(""))
				loader = getClass().getClassLoader();
			else {
				URL url;
				try {
					url = new File(classPath).toURI().toURL();
				} catch (MalformedURLException e) {
					throw UnexpectedException.exception(e);
				}

				loader = new URLClassLoader(new URL[] { url });
			}

			Class<?> cls;
			try {
				cls = loader.loadClass(IGeneralBook.getActionHandler());
			} catch (ClassNotFoundException e) {
				throw ProgrammerError.classNotFound(IGeneralBook
						.getActionHandler());
			}

			try {
				handler = cls.newInstance();
			} catch (InstantiationException e) {
				throw UnexpectedException.exception(e);
			} catch (IllegalAccessException e) {
				throw UnexpectedException.exception(e);
			}
		}

		private Object handler;

		public String execute(String action, Map<String, Object> data) {
			Method m;
			Class<?>[] parameterTypes = { Map.class };
			try {
				m = handler.getClass().getMethod(action, parameterTypes);
			} catch (SecurityException e) {
				throw UnexpectedException.exception(e);
			} catch (NoSuchMethodException e) {
				throw ProgrammerError.general(String.format(
						"The action '%s' is not found", action), e);
			}
			Object res;
			Object[] args = { data };
			try {
				res = m.invoke(handler, args);
			} catch (IllegalArgumentException e) {
				throw UnexpectedException.exception(e);
			} catch (IllegalAccessException e) {
				throw UnexpectedException.exception(e);
			} catch (InvocationTargetException e) {
				throw UnexpectedException.exception(e);
			}

			if (res == null || !(res instanceof String))
				throw ProgrammerError.general(String.format(
						"The action '%s' must return a string", action));

			return (String) res;
		}

	}


	
}