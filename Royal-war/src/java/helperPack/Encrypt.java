/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MesutOezil
 */
public class Encrypt implements Serializable {

    public static String hashPassword(String password) {
        String hashword = "a";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hashword = hash.toString(16);

        } catch (NoSuchAlgorithmException nsae) {
        }
        return hashword;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        Encrypt a = new Encrypt();
//       String path="C:\\Users\\MesutOezil\\Documents\\NetBeansProjects\\Royal\\dist\\gfdeploy\\Royal\\Royal-war_war\\admin\\assignment/kinhnghiemsaudoan.docx";
//       
//       String path1="C:\\Users\\MesutOezil\\Documents\\NetBeansProjects\\Royal\\Royal-war\\web\\admin\\assignment";
//       String patha="\\dist\\gfdeploy\\Royal\\Royal-war_war";
//       String path3=path.substring(0,path.lastIndexOf("\\dist\\gfdeploy\\Royal\\Royal-war_war"))+
//               "\\Royal-war\\web"+path.substring(path.lastIndexOf(patha)+patha.length(),path.length());
//       System.out.println(path3);
//        Date date=new Date();
//       long a= date.getTime();
//        System.out.println(a);
        
//        Date now=new Date();
//        DateFormat f=new SimpleDateFormat("dd-MM-yyyy");
//        String date=f.format(now);
//       String a= date.substring(0, 2);
//       System.out.println(a);
    }
}
