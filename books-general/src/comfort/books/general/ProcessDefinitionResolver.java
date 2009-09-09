package comfort.books.general;

import comfort.bpm.IProcessDefinitionResolver;
import comfort.exceptions.ProgrammerError;
import comfort.exceptions.UnexpectedException;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessDefinitionResolver implements IProcessDefinitionResolver {
	public ProcessDefinitionResolver() {
	}

	public InputStream resolve(String processName, String superProcessName, String data) {
		ProcessNameResolver.Result res = ProcessNameResolver.resolve(processName, superProcessName);
        processName = res.process;
        String bookName = res.book;

	    Pattern p = Pattern.compile("(.*),(.*)");
	    Matcher m = p.matcher(data);
	    if (!m.matches())
	    	throw ProgrammerError.invalidArgument("data", "Invalid data format");
	    
	    String path = m.group(1);
	    String ext = m.group(2);
	    
		path += bookName + ext;
		
		ClassLoader loader;

		URL url;
		try {
			url = new File(path).toURI().toURL();
		} catch (MalformedURLException e) {
			throw UnexpectedException.exception(e);
		}

		loader = new URLClassLoader(new URL[] { url });

		String s = "processes/"	+ processName + "/processdefinition.xml";
		InputStream is = loader.getResourceAsStream(s);
		if (is == null)
			throw ProgrammerError.resourceNotFound(path + s);
		
		return is;
	}

}