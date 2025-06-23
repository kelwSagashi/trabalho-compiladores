package lexer.tag;

public enum Tag {
    // DIV("DIV"),
    // AND("AND"),
    
    // READ("READ"),
    
    // SEMI_COLLON("SEMI_COLLON"),

    //Reserved Words
	// PROGRAM("PROGRAM"), BEGIN("BEGIN"), END("END"),
	// INT("INT"), REAL("REAL"), BOOL("BOOL"),
	// WRITE("WRITE"), IF("IF"), ELSE("ELSE"), WHILE("WHILE"),
	//Assign
	// ASSIGN("ASSIGN"),
	//Arithmetical Operators
	SUM("SUM"), SUB("SUB"), MUL("MUL"), DIV("DIV"),
	//Logical Operators
	// OR("OR"),
	//Relational Operators
	LT("LT"), 
	// LE("LE"), 
	GT("GT"), 
	// GE("GE"),
	//Symbols
	// SEMI("SEMI"), DOT("DOT"), 
	LPAREN("LPAREN"), RPAREN("RPAREN"),
	//Literals and Identifiers
	LIT_INT("LIT_INT"), LIT_REAL("LIT_REAL"), ID("ID"),
	// TRUE("TRUE"), FALSE("FALSE"),
	//Others
	EOF("EOF"), 
	UNK("UNK"), 
	// TEMP("TEMP")
	;


    private String tag;

    Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return tag;
    }
}
