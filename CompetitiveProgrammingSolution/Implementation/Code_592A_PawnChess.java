import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author o_panda_o(emailofpanda@yahoo.com)
 */
public class Code_592A_PawnChess{
    public static void main(String[] args){
        InputStream inputStream=System.in;
        OutputStream outputStream=System.out;
        InputReader in=new InputReader(inputStream);
        OutputWriter out=new OutputWriter(outputStream);
        _592A_ solver=new _592A_();
        solver.solve(1,in,out);
        out.close();
    }

    static class _592A_{
        public void solve(int testNumber,InputReader in,OutputWriter out){
            char[][] chessBoard=new char[8][8];
            for(int i=0;i<8;++i)
                chessBoard[i]=in.next().toCharArray();
            boolean[] blackPresent=new boolean[8];
            int minWhiteSteps=Integer.MAX_VALUE;
            for(int i=0;i<8;++i){
                for(int j=0;j<8;++j){
                    if(chessBoard[i][j]=='B')
                        blackPresent[j]=true;
                    else if(chessBoard[i][j]=='W' && !blackPresent[j]){
                        minWhiteSteps=Math.min(minWhiteSteps,i);
                        break;
                    }
                }
            }
            boolean[] whitePresent=new boolean[8];
            int minBlackSteps=Integer.MAX_VALUE;
            for(int i=7;i>=0;--i){
                for(int j=0;j<8;++j){
                    if(chessBoard[i][j]=='W')
                        whitePresent[j]=true;
                    else if(chessBoard[i][j]=='B' && !whitePresent[j]){
                        minBlackSteps=Math.min(minBlackSteps,7-i);
                        break;
                    }
                }
            }
            //out.println(minWhiteSteps,minBlackSteps);
            if(minWhiteSteps<=minBlackSteps) out.print('A');
            else out.print('B');
        }

    }

    static class OutputWriter{
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream){
            writer=new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer){
            this.writer=new PrintWriter(writer);
        }

        public void print(char i){
            writer.print(i);
        }

        public void close(){
            writer.close();
        }

    }

    static class InputReader{
        private InputStream stream;
        private byte[] buf=new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream){
            this.stream=stream;
        }

        public int read(){
            if(numChars==-1){
                throw new InputMismatchException();
            }
            if(curChar>=numChars){
                curChar=0;
                try{
                    numChars=stream.read(buf);
                }catch(IOException e){
                    throw new InputMismatchException();
                }
                if(numChars<=0){
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public String nextString(){
            int c=read();
            while(isSpaceChar(c)){
                c=read();
            }
            StringBuilder res=new StringBuilder();
            do{
                if(Character.isValidCodePoint(c)){
                    res.appendCodePoint(c);
                }
                c=read();
            }while(!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c){
            if(filter!=null){
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c){
            return c==' ' || c=='\n' || c=='\r' || c=='\t' || c==-1;
        }

        public String next(){
            return nextString();
        }

        public interface SpaceCharFilter{
            public boolean isSpaceChar(int ch);

        }

    }
}

