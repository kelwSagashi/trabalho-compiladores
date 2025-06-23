package inter.expr;

import lexer.tag.Tag;
import lexer.token.Token;

public class Literal extends Expr {

    public Literal(Token op, Tag type) {
        super(op, type);
    }

    @Override
    public String toString() {
        switch (op.tag()) {
            // case Tag.TRUE:
            //     return "true";
            // case Tag.FALSE:
            //     return "false";
            default:
                return op.lexeme();
        }
    }
    
}
