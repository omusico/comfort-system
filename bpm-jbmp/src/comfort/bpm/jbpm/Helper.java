package comfort.bpm.jbpm;

import comfort.exceptions.ProgrammerError;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Helper {
    public static class ProcessInformation {
        public String processName;
        public String superProcessName;
    }

    private static Pattern p = Pattern.compile("(.*);(.+)");

    public static ProcessInformation getProcessInformation(String data) {
        Matcher m = p.matcher(data);
        if (!m.matches())
            throw ProgrammerError.invalidArgument("data", data, "valid format is 'superprocess;process'");

        ProcessInformation res = new ProcessInformation();

        res.superProcessName = m.group(1);
        res.processName = m.group(2);

        return res;
    }

}