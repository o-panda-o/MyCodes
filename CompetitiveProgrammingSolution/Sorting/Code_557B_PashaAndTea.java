import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.io.InputStream;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author o_panda_o(emailofpanda@yahoo.com)
 */
public class Code_557B_PashaAndTea{
    public static void main(String[] args){
        InputStream inputStream=System.in;
        OutputStream outputStream=System.out;
        InputReader in=new InputReader(inputStream);
        OutputWriter out=new OutputWriter(outputStream);
        _557B_ solver=new _557B_();
        solver.solve(1,in,out);
        out.close();
    }
    static class _557B_{
        public void solve(int testNumber,InputReader in,OutputWriter out){
            double n=in.nextDouble();
            double w=in.nextDouble();
            ArrayList<Double> capacity=new ArrayList<>();
            for(int i=1;i<=2*n;++i) capacity.add(in.nextDouble());
            Collections.sort(capacity);
            double minG=capacity.get(0), minB=capacity.get((int)n);
            double pour=2*minG<=minB?minG:minB/2;
            out.print(Math.min(w,3*n*pour));
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
        public int nextInt(){
            int c=read();
            while(isSpaceChar(c)){
                c=read();
            }
            int sgn=1;
            if(c=='-'){
                sgn=-1;
                c=read();
            }
            int res=0;
            do{
                if(c<'0' || c>'9'){
                    throw new InputMismatchException();
                }
                res*=10;
                res+=c-'0';
                c=read();
            }while(!isSpaceChar(c));
            return res*sgn;
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
        public double nextDouble(){
            int c=read();
            while(isSpaceChar(c)){
                c=read();
            }
            int sgn=1;
            if(c=='-'){
                sgn=-1;
                c=read();
            }
            double res=0;
            while(!isSpaceChar(c) && c!='.'){
                if(c=='e' || c=='E'){
                    return res*Math.pow(10,nextInt());
                }
                if(c<'0' || c>'9'){
                    throw new InputMismatchException();
                }
                res*=10;
                res+=c-'0';
                c=read();
            }
            if(c=='.'){
                c=read();
                double m=1;
                while(!isSpaceChar(c)){
                    if(c=='e' || c=='E'){
                        return res*Math.pow(10,nextInt());
                    }
                    if(c<'0' || c>'9'){
                        throw new InputMismatchException();
                    }
                    m/=10;
                    res+=(c-'0')*m;
                    c=read();
                }
            }
            return res*sgn;
        }
        public interface SpaceCharFilter{
            public boolean isSpaceChar(int ch);
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
        public void print(Object... objects){
            for(int i=0;i<objects.length;i++){
                if(i!=0){
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }
        public void close(){
            writer.close();
        }
    }
}

