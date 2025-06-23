package inter.expr;

import lexer.tag.Tag;
import lexer.token.Token;

public class Id extends Expr {

    public Id(Token op, Tag type) {
        super(op, type);
    }
    
    @Override
    public String toString() {
        return "%" + op.lexeme();
    }
}
