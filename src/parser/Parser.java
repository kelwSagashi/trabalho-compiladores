// package parser;

// import lexer.Lexer;
// import lexer.token.Token;
// import lexer.tag.Tag;

// public class Parser {
//     private Lexer lexer;
//     private Token look;

//     public Parser(Lexer lex) {
//         lexer = lex;
//         move();
//     }

//     private Token move() {
//         Token save = look;
//         look = lexer.nextToken();
//         System.out.println(look);
//         return save;
//     }

//     private void error(String s) {
//         // throw new Error("near line " + Lexer.line() + ": " + s);
//         System.err.println("Linha " + Lexer.line() + ": " + s);
//         System.exit(0);
//     }

//     private Token match(Tag tag) {
//         if (look.tag() == tag)
//             return move();
//         error("syntax error");
//         return null;
//     }

//     public void parse() {
//         program();
//     }

//     private void program() {
//         match(Tag.PROGRAM);
//         match(Tag.ID);
//         block();
//         match(Tag.DOT);
//         match(Tag.EOF);
//     }

//     private void block() {
//         match(Tag.BEGIN);
//         while (look.tag() != Tag.END) {
//             stmt();
//             match(Tag.SEMI);
//         }
//         match(Tag.END);
//     }

//     private void stmt() {
//         switch (look.tag()) {
//             case BEGIN:
//                 block();
//                 break;
//             case INT:
//             case REAL:
//             case BOOL:
//                 decl();
//                 break;
//             case ID:
//                 assign();
//                 break;
//             case IF:
//                 ifstmt();
//                 break;
//             case WRITE:
//                 writeStmt();
//                 break;
//             default:
//                 error("comando inválido");
//                 break;
//         }
//     }

//     private void decl() {
//         move();
//         match(Tag.ID);
//     }

//     private void assign() {
//         match(Tag.ID);
//         match(Tag.ASSIGN);
//         expr();
//     }

//     private void expr() {
//         rel();
//         while (look.tag() == Tag.OR) {
//             move();
//             rel();
//         }
//     }

//     private void rel() {
//         arith();
//         while (look.tag() == Tag.LT ||
//                 look.tag() == Tag.LE ||
//                 look.tag() == Tag.GT ||
//                 look.tag() == Tag.GE) {
//             move();
//             arith();
//         }
//     }

//     private void arith() {
//         term();
//         while (look.tag() == Tag.SUM ||
//                 look.tag() == Tag.SUB) {
//             move();
//             term();
//         }
//     }

//     private void term() {
//         factor();
//         while (look.tag() == Tag.MUL) {
//             move();
//             factor();
//         }
//     }

//     private void factor() {
//         switch (look.tag()) {
//             case LPAREN:
//                 move();
//                 expr();
//                 match(Tag.RPAREN);
//                 break;
//             case LIT_INT:
//                 move();
//                 break;
//             case LIT_REAL:
//                 move();
//                 break;
//             case TRUE:
//             case FALSE:
//                 move();
//                 break;
//             case ID:
//                 match(Tag.ID);
//                 break;
//             default:
//                 error("Expressão (fator) Inválida! " + look);
//                 break;
//         }
//     }

//     private void ifstmt() {
//         match(Tag.IF);
//         match(Tag.LPAREN);
//         expr();
//         match(Tag.RPAREN);
//         stmt();
//     }

//     private void writeStmt() {
//         move();
//         match(Tag.LPAREN);
//         expr();
//         match(Tag.RPAREN);
//     }
// }
