package comfort.books.general;

import comfort.exceptions.ProgrammerError;

class ProcessNameResolver {
    public static class Result {
        public String process;
        public String book;

        public Result(String process, String book) {
            this.process = process;
            this.book = book;
        }
    }
    public static Result resolve(String processName, String superProcessName) {
        String bookName;

        int i = processName.indexOf('.');
        if (i != -1) {
            bookName = processName.substring(0, i);
            processName = processName.substring(i + 1);
        } else
        {
            if (superProcessName.equals(""))
                throw ProgrammerError.emptyArgument("superProcessName");

            i = superProcessName.indexOf('.');
            if (i == -1)
                throw ProgrammerError.invalidArgument("superProcessName", superProcessName,
                        "If the processName doesn't contain the book name, the superProcessName must correspond to pattern 'book.process'.");

            bookName = superProcessName.substring(0, i);

        }

        return new Result(processName, bookName);
    }
}