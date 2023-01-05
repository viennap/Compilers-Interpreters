
/**
* This file defines a simple lexer for the compilers course 2014-2015
*
* @author  Saahil Thoppay, Vienna Parnell, Esha Gohil
* @version 2/10/2022
* 
*/
import java.io.*;

%%
/* lexical functions */
/* specify that the class will be called Scanner and the function to get the next
 * token is called nextToken.  
 */
%class Scanner
%unicode
%line
%column
%public
%function nextToken
/*  return String objects - the actual lexemes */
/*  returns the String "END: at end of file */
%type String
%eofval{
return "END";
%eofval}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/**
 * Pattern definitions
 */
 
 
%%
/**
 * lexical rules
 */
"$"[0-9,]+\.[0-9]+.*	    {return "Price: " + yytext();}
.*(Mon | Tue | Wed | Thu | Fri | Sat | Sun)", "(Jan | Feb | Mar | Apr | Jun | Jul | Aug | Sep | Oct | Nov | Dec)" "[0-9]+ {return "Shipping:" + yytext();}
[0-9]\.[0-9]" out of 5 stars".* {return "Rating: "+yytext();}
"FREE Shipping".* {return "Free shipping";}
.+	{return "Product Name:" + yytext();}
{WhiteSpace}		{}
.			{ /* do nothing */ }
