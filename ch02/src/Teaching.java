
public class Teaching {

	public static void main(String[] args) {
		
//		System.out.println("Hello \nWorld in\t\"Java\"");
//		
//		char c = '中';
//		int i = 8;
//		int i1 = 9;
//		System.out.printf("%d\n",i);
//		
//		System.out.println("中 \u4e2d");
		
		
		int  b[][]={{1}, {2,2}, {2,2,2}};
        int sum=0;
        for(int i=0;i<b.length;i++) {            
            for(int j=0;j<b[i].length;j++) {
                sum*=b[i][j];
            }            
        }
        System.out.println("sum="+sum);


	}

}
