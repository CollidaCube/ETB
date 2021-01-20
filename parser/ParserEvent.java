package parser;

import script_manager.Statement;

public interface ParserEvent {

    String perParserPacket(Statement line);

}
