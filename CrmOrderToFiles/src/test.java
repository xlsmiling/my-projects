import utils.initPool;
<<<<<<< HEAD

import java.text.SimpleDateFormat;
import java.util.Date;
=======
>>>>>>> d5134277ce79e3fb9eb5918af69237cf92494cc0

public class test{

	public static void main(String[] args){
//		String a = "101314039809|109010922166|100636018986|379||S1|2017-08-27 22:32:07|99100001075|2017-08-27 22:32:07|12180034||";
//		String[] b = a.split("\\|");
//		System.out.println(b.length);
//		System.out.println(b[9]);
//		String a = "0523";
//		System.out.println(changeCode(a));
		//new initPool();
<<<<<<< HEAD
//		for (int i = 0; i < 10; i++) {
//			new Thread(new testThread()).start();
//		}
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sf.format(date));

	}


=======
		for (int i = 0; i < 10; i++) {
			new Thread(new testThread()).start();
		}

	}


>>>>>>> d5134277ce79e3fb9eb5918af69237cf92494cc0
//	public static String changeCode(String code){
//		switch (code){
//			case "0555": return "8";
//			case "025": return "250";
//			case "0510": return "510";
//			case "0511": return "511";
//			case "0512": return "512";
//			case "0513": return "513";
//			case "0514": return "514";
//			case "0515": return "515";
//			case "0516": return "516";
//			case "0517": return "517";
//			case "0518": return "518";
//			case "0519": return "519";
//			case "0523": return "523";
//			case "0527": return "527";
//			default: return "8";
//		}
//	}
}