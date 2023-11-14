package edu.project3.Parser;

import edu.project3.model.LogRecord;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class ParserImpl implements Parser {

    @Override
    public List<LogRecord> parse(Path path) {
        try {
            URL.of(path);
        }
        return null;
    }
}
