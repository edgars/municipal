package app.util;

import lombok.Getter;

public class Path {

    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Web {
        @Getter
        public static final String INDEX = "/index/";
        @Getter
        public static final String LOGIN = "/login/";
        @Getter
        public static final String LOGOUT = "/logout/";
        @Getter
        public static final String BOOKS = "/books/";
        @Getter
        public static final String ONE_BOOK = "/books/:isbn/";
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
        public static final String BLANK = "/velocity/forms.vm";
        public static final String STEP2 = "/velocity/step2.vm";
        public static final String STEP3 = "/velocity/step3.vm";
        public static final String GENERATE = "/velocity/generatecode.vm";
    }

}
