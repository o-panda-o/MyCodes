import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author o_panda_o(emailofpanda @ yahoo.com)
 */
public class Code_289B_PoloThePenguinAndMatrix{
	public static void main(String[] args){
		InputStream inputStream=System.in;
		OutputStream outputStream=System.out;
		InputReader in=new InputReader(inputStream);
		OutputWriter out=new OutputWriter(outputStream);
		_289B_ solver=new _289B_();
		solver.solve(1,in,out);
		out.close();
	}

	static class _289B_{
		public void solve(int testNumber,InputReader in,OutputWriter out){
			int n=in.nextInt(), m=in.nextInt(), d=in.nextInt();
			int a[][]=in.nextIntMatrix(n,m);
			int[] num=new int[n*m];
			int idx=0;
			for(int i=0;i<n;++i)
				for(int j=0;j<m;++j)
					num[idx++]=a[i][j];
			Arrays.sort(num);
			int count=0;
			if((n*m)%2==0){
				int answer1=0;
				int answer2=0;
				int pivot1=num[(n*m)/2];
				int pivot2=num[(n*m)/2-1];
				for(int i=0;i<n*m;++i){
					answer1+=Math.abs(num[i]-pivot1);
					answer2+=Math.abs(num[i]-pivot2);
				}
				count=Math.min(answer1,answer2);
			}else{
				int answer=0;
				int pivot=num[(n*m)/2];
				for(int i=0;i<n*m;++i){
					answer+=Math.abs(num[i]-pivot);
				}
				count=answer;
			}
			out.print(count%d==0?count/d:-1);
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
			if(curChar >= numChars){
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
				if(c<'0'||c>'9'){
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
			return c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1;
		}

		public int[][] nextIntMatrix(int n,int m){
			int[][] matrix=new int[n][m];
			for(int i=0;i<n;++i)
				for(int j=0;j<m;++j)
					matrix[i][j]=nextInt();
			return matrix;
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

		public void close(){
			writer.close();
		}

		public void print(int i){
			writer.print(i);
		}

	}
}

