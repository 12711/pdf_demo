package com.example.util;

import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDStream;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lsm on 2017/12/6.
 */
public class PdfUtils {
    public static void main (String[] args) throws Exception{
        File file = new File("C:\\Users\\lsm\\Desktop\\demo.pdf");

        File file1 = new File("C:\\Users\\lsm\\Desktop\\demo1.pdf");
        doIt(file,file1,"0","0");
    }


    public static void doIt( File inputFile, File outputFile, String strToFind, String message)
            throws IOException, Exception
    {
        // the document
        PDDocument doc = null;
        doc = PDDocument.load(inputFile);
//            PDFTextStripper stripper=new PDFTextStripper("ISO-8859-1");
         PDPageTree pages = doc.getDocumentCatalog().getPages();
         Iterator iterator = pages.iterator();
         while (iterator.hasNext()){
             PDPage ob = (PDPage)iterator.next();
             InputStream inputStream = ob.getContents();
             byte [] bytes = new byte[1024];
             inputStream.read();
             try {
            String line = null;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"ISO-8859-1"));
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            System.out.println(new String(sb.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
             System.out.println(ob.getContents());
         }
//            for( int i=0; i<pages; i++ )
//            {
//                PDPage page = (PDPage)pages.get( i );
//                PDStream contents = page.getContents();
//                PDFStreamParser parser = new PDFStreamParser(contents.getStream() );
//                parser.parse();
//                List tokens = parser.getTokens();
//                for( int j=0; j<tokens.size(); j++ )
//                {
//                    Object next = tokens.get( j );
//                    if( next instanceof PDFOperator )
//                    {
//                        PDFOperator op = (PDFOperator)next;
//                        //Tj and TJ are the two operators that display
//                        //strings in a PDF
//                        if( op.getOperation().equals( "Tj" ) )
//                        {
//                            //Tj takes one operator and that is the string
//                            //to display so lets update that operator
//                            COSString previous = (COSString)tokens.get( j-1 );
//                            String string = previous.getString();
//                            string = string.replaceFirst( strToFind, message );
//                            System.out.println(string);
//                            System.out.println(string.getBytes("GBK"));
//                            previous.reset();
//                            previous.append( string.getBytes("GBK") );
//                        }
//                        else if( op.getOperation().equals( "TJ" ) )
//                        {
//                            COSArray previous = (COSArray)tokens.get( j-1 );
//                            for( int k=0; k<previous.size(); k++ )
//                            {
//                                Object arrElement = previous.getObject( k );
//                                if( arrElement instanceof COSString )
//                                {
//                                    COSString cosString = (COSString)arrElement;
//                                    String string = cosString.getString();
//                                    string = string.replaceFirst( strToFind, message );
//                                    cosString.reset();
//                                    cosString.append( string.getBytes("GBK") );
//                                }
//                            }
//                        }
//                    }
//                }
//                //now that the tokens are updated we will replace the
//                //page content stream.
//                PDStream updatedStream = new PDStream(doc);
//                OutputStream out = updatedStream.createOutputStream();
//                ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
//                tokenWriter.writeTokens( tokens );
//                page.setContents( updatedStream );
//            }
//            doc.save( outputFile );
//        }
//        finally
//        {
//            if( doc != null )
//            {
//                doc.close();
//            }
        }
}
