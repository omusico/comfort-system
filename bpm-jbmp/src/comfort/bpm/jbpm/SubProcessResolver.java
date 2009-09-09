package comfort.bpm.jbpm;

import comfort.exceptions.ProgrammerError;
import org.dom4j.Element;
import org.jbpm.graph.def.ProcessDefinition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubProcessResolver implements
		org.jbpm.graph.node.SubProcessResolver {

	private static Pattern p = Pattern.compile("(.+);(.*);(.+);(.+)");

    public ProcessDefinition findSubProcess(Element subProcessElement) {
	    String subProcessName = subProcessElement.attributeValue("name");
	    
	    Matcher m = p.matcher(subProcessName);
	    if (!m.matches())
	    	throw ProgrammerError.general("Invalid sub-process name format");
	    
		subProcessName = m.group(1);
		String superProcessName = m.group(2);
	    String processDefinitionResolver = m.group(3);
		String data = m.group(4);
		ProcessDefinition def;
        def = ProcessDefinitionLoader.load(subProcessName, superProcessName, processDefinitionResolver, data);
        return def;
	}

}