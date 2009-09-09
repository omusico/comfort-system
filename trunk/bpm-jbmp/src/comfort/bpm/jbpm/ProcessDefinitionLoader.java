package comfort.bpm.jbpm;

import comfort.bpm.IProcessDefinitionResolver;
import comfort.exceptions.ProgrammerError;
import comfort.exceptions.UnexpectedException;
import org.jbpm.graph.def.ProcessDefinition;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

class ProcessDefinitionLoader {
	static {
        InputStream is = ProcessDefinitionLoader.class.getResourceAsStream("preparation.xml");
        Source source = new StreamSource(is);
		try {
			transformer = TransformerFactory.newInstance().newTransformer(
					source);
		} catch (Exception e) {
			throw ProgrammerError.general(
					"An error occurred during parsing of preparation.xml", e);
		}
	}

	private static Transformer transformer;

	public static ProcessDefinition load(String processName, String superProcessName,
			String processDefinitionResolver, String data) {

		if (processDefinitionResolver == null)
			throw ProgrammerError.nullArgument("processDefinitionResolver");
		if (processDefinitionResolver.equals(""))
			throw ProgrammerError.emptyArgument("processDefinitionResolver");

		if (data == null)
			throw ProgrammerError.nullArgument("data");
		if (data.equals(""))
			throw ProgrammerError.emptyArgument("data");

		Class<?> cls;
		try {
			cls = Class.forName(processDefinitionResolver);
		} catch (ClassNotFoundException e) {
			throw ProgrammerError.classNotFound(processDefinitionResolver);
		}
		
		Object o;
		try {
			o = cls.newInstance();
		} catch (InstantiationException e) {
			throw UnexpectedException.exception(e);
		} catch (IllegalAccessException e) {
			throw UnexpectedException.exception(e);
		}

		IProcessDefinitionResolver r = (IProcessDefinitionResolver) o;

		String s;
		InputStream f = r.resolve(processName, superProcessName, data);
        try {
            try {
                transformer.clearParameters();
                transformer.setParameter("processDefinitionResolver",
                        processDefinitionResolver);
                transformer.setParameter("data", data);
                transformer.setParameter("currentProcessName", processName);
                transformer.setParameter("superProcessName", superProcessName);

                Source src = new StreamSource(f);
                Writer w = new StringWriter();
                Result res = new StreamResult(w);

                try {
                    transformer.transform(src, res);
                    s = w.toString();
                } catch (TransformerException e) {
                    throw ProgrammerError.general(
                            "An error was occur during transformation", e);
                }

            } finally {
                f.close();
            }
        } catch (IOException e) {
            throw UnexpectedException.exception(e);
        }

		return ProcessDefinition.parseXmlString(s);
	}
}