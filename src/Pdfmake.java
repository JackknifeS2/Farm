import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
class Pdfmake {

    protected List getPdfString() {
        List<String[]> pdfStringList = new ArrayList<String[]>();
        boolean sort = false;
        boolean separateBeads = true;
        String password = "";
        int startPage = 1;
        int endPage = 2147483647;

        String pdfFile = "C:\\Users\\SasakiYuki\\Desktop\\farm\\hokkaidou.pdf";
        PDDocument document = null;
        StringWriter output = null;
        try {
            document = PDDocument.load(new File(pdfFile), password);
            AccessPermission ap = document.getCurrentAccessPermission();
            if (!(ap.canExtractContent())) {
                throw new IOException("You do not have permission to extract text");
            }

            output = new StringWriter();

            PDFTextStripper stripper = new PDFTextStripper(){
                int count=0;
                String saveText="";
                @Override
                protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                    super.writeString(text);
                    String[] stickString = new String[2];
                    /*
                    *品目スタート　　:145.82
                    *品目フィニッシュ:221.09
                    */

                    /*　座標調べる用のテンプレート
                    if(text.equals("Ｍ")){
                        System.out.println(text);
                        System.out.println(textPositions.get(0).getX());
                    }
                    */

                    if(text.equals("量目")){
                        System.out.println(text);
                        System.out.println(textPositions.get(0).getX());
                    }

                    if(text.equals("安値")){
                        System.out.println(text);
                        System.out.println(textPositions.get(0).getX());
                    }

                    if(text.equals("中値")){
                        System.out.println(text);
                        System.out.println(textPositions.get(0).getX());
                    }

                    if(text.equals("高値")){
                        System.out.println(text);
                        System.out.println(textPositions.get(0).getX());
                    }

                    if(text.equals("数量")){
                        System.out.println(text);
                        System.out.println(textPositions.get(0).getX());
                    }
                    
                    if(textPositions.get(0).getX()>=140 &&textPositions.get(0).getEndX()<=268.73 && textPositions.get(0).getY()>101.17999) {
                        if(!(text.equals("品目")) && !(text.equals("産地"))&& !(text.equals("品名"))) {
                            count++;
                            if(count==2){
                                stickString[0]=saveText;
                                stickString[1]=text;
                                pdfStringList.add(stickString);
                                count=0;
                            }else {
                                saveText=text;
                            }
                        }
                    }
                }
            };
            stripper.setSortByPosition(sort);
            stripper.setShouldSeparateByBeads(separateBeads);
            stripper.setStartPage(startPage);
            stripper.setEndPage(endPage);
            stripper.writeText(document, output);
            stripper.getText(document);

            for(String[] b : pdfStringList){
                System.out.println(b[0]+":"+b[1]);
            }
            System.out.println(pdfStringList.size());
            //System.out.println(content);
        } catch (Exception e) {
            System.err.println("Error processing " + pdfFile + e.getMessage());
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(document);
        }
        return pdfStringList;
    }
    public int[] vegetablesSearch(String vege){
        int[] vegePrice = new int[3];

        return vegePrice;
    }
}