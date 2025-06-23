package lexer.token;

import lexer.tag.Tag;

public class Token {
    private Tag tag;
    private String lexeme;

    public Token(Tag tag, String lexeme) {
        this.tag = tag;
        this.lexeme = lexeme;
    }

    public Token() {

    }

    public Tag tag() {
        return tag;
    }
    
    @Override
    public String toString() {
        return String.format("<%s, \"%s\">", tag, lexeme);
    }

    /**
     * @return Tag return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * @return String return the lexeme
     */
    public String lexeme() {
        return lexeme;
    }

    /**
     * @param lexeme the lexeme to set
     */
    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

}
