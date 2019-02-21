package za.co.vzap.print;

import java.io.FileInputStream;
/**
*
* @author All Open source developers
* @version 1.0.0.0
* @since 2014/12/22
*/
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
//Reference Open Source Code to print an Image
/**
*
* @author All Open source developers
* @version 1.0.0.0
* @since 2014/12/22
*/
public class PrintGraph {

	public PrintGraph(String fileName) throws PrintException, IOException{
		
		fileName="resources/PieChart.jpeg";
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		pras.add(new Copies(1));
		PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.JPEG, pras);
		if (pss.length == 0)
			throw new RuntimeException("No printer services available.");
		PrintService ps = pss[0];
		System.out.println("Printing to " + ps);
		DocPrintJob job = ps.createPrintJob();
		FileInputStream fin = new FileInputStream(fileName);
		Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.JPEG, null);
		job.print(doc, pras);
		fin.close();
		}
	
	public static void main(String [] args){
		try {
			new PrintGraph("PieChart.jpeg");
		} catch (PrintException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

